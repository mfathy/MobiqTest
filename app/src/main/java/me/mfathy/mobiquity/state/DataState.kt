package me.mfathy.mobiquity.state

import me.mfathy.mobiquity.domain.model.Category

/**
 * Created by Mohammed Fathy
 * dev.mfathy@gmail.com
 *
 * DataState sealed class to represent each type of data returned from domain layer.
 */
sealed class DataState {
    data class OnSuccess(val data: List<Category>) : DataState()
    data class OnError(val error: Throwable) : DataState()
    object OnLoading : DataState()
}
