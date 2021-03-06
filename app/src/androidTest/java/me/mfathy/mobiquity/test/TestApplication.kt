package me.mfathy.mobiquity.test

import android.app.Activity
import android.app.Application
import androidx.test.platform.app.InstrumentationRegistry
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import me.mfathy.mobiquity.injection.DaggerTestApplicationComponent
import me.mfathy.mobiquity.injection.TestApplicationComponent
import javax.inject.Inject

/**
 * Created by Mohammed Fathy.
 * dev.mfathy@gmail.com
 *
 * Testing application class used to provide mContext and dagger modules for tests.
 */
class TestApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Activity>
    private lateinit var appComponent: TestApplicationComponent

    companion object {
        fun appComponent(): TestApplicationComponent {
            return (InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
                    as TestApplication).appComponent
        }
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerTestApplicationComponent.builder().application(this).build()
        appComponent.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return injector
    }
}