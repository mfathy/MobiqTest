package me.mfathy.mobiquity.data.store.remote.service

import io.reactivex.Single
import me.mfathy.mobiquity.data.store.remote.model.NetCategory
import retrofit2.http.GET

/**
 * Created by Mohammed Fathy
 * dev.mfathy@gmail.com
 *
 * RemoteServiceApi retrofit end point to access remote server api.
 */
interface RemoteServiceApi {

    @GET(".")
    fun getCategories(): Single<List<NetCategory>>
}