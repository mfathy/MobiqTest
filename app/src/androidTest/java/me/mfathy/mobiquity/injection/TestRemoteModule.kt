package me.mfathy.mobiquity.injection

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import me.mfathy.mobiquity.BuildConfig
import me.mfathy.mobiquity.data.mapper.RemoteDataMapper
import me.mfathy.mobiquity.data.store.ProductsDataStore
import me.mfathy.mobiquity.data.store.remote.ProductsRemoteDataStore
import me.mfathy.mobiquity.data.store.remote.service.RemoteServiceApi
import me.mfathy.mobiquity.data.store.remote.service.ServiceFactory
import me.mfathy.mobiquity.data.store.remote.utils.NetworkConnection

@Module
abstract class TestRemoteModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providesRemoteDataMapper(): RemoteDataMapper = RemoteDataMapper()

        @Provides
        @JvmStatic
        fun providesNetworkConnection(context: Context): NetworkConnection = NetworkConnection(context)

        @Provides
        @JvmStatic
        fun providesRemoteService(
            context: Context,
            networkConnection: NetworkConnection
        ): RemoteServiceApi {
            return ServiceFactory.makeRemoteService(
                context = context,
                networkConnection = networkConnection,
                isDebug = BuildConfig.DEBUG,
                useCache = false,
                baseUrl = "http://127.0.0.1:8080"
            )
        }
    }

    @Binds
    abstract fun bindRemoteStore(remote: ProductsRemoteDataStore): ProductsDataStore

}