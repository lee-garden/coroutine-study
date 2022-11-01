package lee.garden.playground

import kotlinx.coroutines.delay

object Functions {

    suspend fun getNewsFromApi() {
//        delay(1000L)
        Thread.sleep(1000L)
        println("News!")
    }
    suspend fun getUserFromApi() {
//        delay(1000L)
        Thread.sleep(1000L)
        println("User!")
    }
}