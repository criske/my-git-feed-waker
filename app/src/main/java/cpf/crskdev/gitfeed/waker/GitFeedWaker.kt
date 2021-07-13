package cpf.crskdev.gitfeed.waker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf

/**
 * Created by Cristian Pela on 13.07.2021.
 */
class GitFeedWaker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {

    companion object {
        const val GIT_FEED_PING_RESULT_CODE = "GIT_FEED_PING_RESULT_CODE"
    }

    override fun doWork(): Result {
        val ping = this.createGitFeedPing()
        return try {
            val resultCode = ping.execute()
            Result.success(
                workDataOf(GIT_FEED_PING_RESULT_CODE to resultCode)
            )
        } catch (ex: Exception) {
            Result.failure()
        }
    }

    private fun createGitFeedPing(): GitFeedPing {
        return object : GitFeedPing {}
    }

}