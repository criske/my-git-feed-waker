package cpf.crskdev.gitfeed.waker

import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.TimeUnit

/**
 * Created by Cristian Pela on 13.07.2021.
 */
interface GitFeedPing {

    fun execute(): Int {
        var url = URL("https://my-git-feed.herokuapp.com/check/ping")
        val connection = (url.openConnection() as HttpURLConnection).apply {
            connectTimeout = TimeUnit.MINUTES.toMillis(2).toInt()
        }
        connection.connect()
        val responseCode = connection.responseCode
        connection.disconnect()
        return responseCode
    }

}