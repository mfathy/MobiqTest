package me.mfathy.mobiquity.exception

import android.content.Context
import me.mfathy.mobiquity.R
import retrofit2.HttpException
import java.io.IOException

/**
 * Use this class to create suitable message for each error.
 */
object ErrorMessageFactory {
    fun create(resourceManager: Context?, error: Throwable): String? {
        return when (error) {
            is HttpException -> when (error.code()) {
                400 -> resourceManager?.getString(R.string.bad_request_error)
                401 -> resourceManager?.getString(R.string.unauthorized_error)
                403 -> resourceManager?.getString(R.string.forbidden_error)
                404 -> resourceManager?.getString(R.string.not_found_error)
                500 -> resourceManager?.getString(R.string.server_error_error)
                else -> resourceManager?.getString(R.string.unknown_error)
            }
            is IOException -> resourceManager?.getString(R.string.network_error)
            else -> resourceManager?.getString(R.string.unknown_error)
        }
    }
}
