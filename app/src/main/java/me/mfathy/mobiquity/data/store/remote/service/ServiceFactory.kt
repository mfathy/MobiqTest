package me.mfathy.mobiquity.data.store.remote.service

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import me.mfathy.mobiquity.BuildConfig.BASE_URL
import me.mfathy.mobiquity.data.store.remote.utils.NetworkConnection
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Mohammed Fathy
 * dev.mfathy@gmail.com
 *
 * ServiceFactory is a me.mfathy.mobiquity.factory class which is responsible for creating Okhttp client and creates
 * an implementation of retrofit remote service.
 */
object ServiceFactory {

    private const val cacheSize = (5 * 1024 * 1024).toLong()

    fun makeRemoteService(
        context: Context,
        networkConnection: NetworkConnection,
        isDebug: Boolean
    ): RemoteServiceApi {
        val okHttpClient = makeOkHttpClient(
            context,
            makeLoggingInterceptor((isDebug)),
            makeCachingInterceptor(networkConnection)
        )
        return makeRemoteService(okHttpClient, GsonBuilder().create())
    }

    private fun makeRemoteService(okHttpClient: OkHttpClient, gson: Gson): RemoteServiceApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit.create(RemoteServiceApi::class.java)
    }

    private fun makeOkHttpClient(
        context: Context,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        httpCachingInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(httpCachingInterceptor)
            .cache(makeHttpCache(context))
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .build()
    }

    /**
     *  If there is Internet, get the cache that was stored 5 seconds ago.
     *  If the cache is older than 5 seconds, then discard it,
     *  and indicate an error in fetching the response.
     *  The 'max-age' attribute is responsible for this behavior.
     *
     *  If there is no Internet, get the cache that was stored 7 days ago.
     *  If the cache is older than 7 days, then discard it,
     *  and indicate an error in fetching the response.
     *  The 'max-stale' attribute is responsible for this behavior.
     *  The 'only-if-cached' attribute indicates to not retrieve new data; fetch the cache only instead.
     */
    private fun makeCachingInterceptor(networkConnection: NetworkConnection): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()

            request = if (networkConnection.hasInternetConnection())
                request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
            else
                request.newBuilder().header(
                    "Cache-Control",
                    "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                ).build()
            chain.proceed(request)
        }
    }

    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }

    private fun makeHttpCache(context: Context): Cache = Cache(context.cacheDir, cacheSize)
}