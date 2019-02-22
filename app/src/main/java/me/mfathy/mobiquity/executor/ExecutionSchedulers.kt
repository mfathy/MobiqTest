package me.mfathy.mobiquity.executor

import io.reactivex.Scheduler
import me.mfathy.mobiquity.domain.executor.ExecutionThread
import javax.inject.Inject

/**
 * Created by Mohammed Fathy.
 * dev.mfathy@gmail.com
 *
 * Rx Schedules
 */
class ExecutionSchedulers @Inject constructor(
    override val subscriber: Scheduler,
    override val observer: Scheduler
) : ExecutionThread