package me.mfathy.mobiquity.injection.module

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

/**
 * Created by Mohammed Fathy.
 * dev.mfathy@gmail.com
 *
 * Dagger module to provide remote dependencies.
 */
@Module
abstract class RemoteModule {

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
                isDebug = BuildConfig.DEBUG,
                networkConnection = networkConnection
            )
        }
    }

    @Binds
    abstract fun bindRemoteStore(remote: ProductsRemoteDataStore): ProductsDataStore

}