package cpf.crskdev.gitfeed.waker

import android.app.Application
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

/**
 * Created by Cristian Pela on 13.07.2021.
 */
class GitFeedWakerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val wakeRequest = PeriodicWorkRequestBuilder<GitFeedWaker>(
            30, TimeUnit.MINUTES,
            10, TimeUnit.MINUTES
        ).build()
        WorkManager
            .getInstance(this)
            .enqueue(wakeRequest)
    }


}