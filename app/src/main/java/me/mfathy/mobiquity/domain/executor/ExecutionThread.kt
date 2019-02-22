package me.mfathy.mobiquity.domain.executor

import io.reactivex.Scheduler

/**
 * Scheduler thread contract.
 */
interface ExecutionThread {
    val subscriber: Scheduler
    val observer: Scheduler
}
