package me.mfathy.mobiquity.domain.interactor.base

import io.reactivex.Single
import io.reactivex.observers.DisposableSingleObserver
import me.mfathy.mobiquity.domain.executor.ExecutionThread

/**
 * Created by Mohammed Fathy
 * dev.mfathy@gmail.com
 *
 * SingleUseCase is an abstract class which provide a Single observable to emit required data or error.
 */
abstract class SingleUseCase<T> constructor(private val executionThread: ExecutionThread) {

    protected abstract fun buildUseCaseSingle(): Single<T>

    fun execute(observer: DisposableSingleObserver<T>): DisposableSingleObserver<T> {
        return this.buildUseCaseSingle()
            .subscribeOn(executionThread.subscriber)
            .observeOn(executionThread.observer)
            .subscribeWith(observer)
    }
}