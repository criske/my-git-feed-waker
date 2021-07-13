package cpf.crskdev.gitfeed.waker

import android.content.Context
import android.util.Log
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
        const val ID = "GIT_FEED_WAKER"
        private val LOG_TAG = GitFeedWaker::class.simpleName
    }

    override fun doWork(): Result {
        val ping = this.createGitFeedPing()
        return try {
            val resultCode = ping.execute()
            Log.i(LOG_TAG, "Pinged my-git-feed-server and returned result [$resultCode]")
            Result.success(
                workDataOf(GIT_FEED_PING_RESULT_CODE to resultCode)
            )
        } catch (ex: Exception) {
            Log.i(LOG_TAG, "Failed to ping my-git-feed-server - reason: ${ex.localizedMessage}")
            Result.failure()
        }
    }

    private fun createGitFeedPing(): GitFeedPing {
        return object : GitFeedPing {}
    }

}