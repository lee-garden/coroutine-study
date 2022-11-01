package lee.garden.playground

import kotlinx.coroutines.*

//fun main() {
//
//    runBlocking {
//        delay(1000L)
//        println("World!")
//    }
//
//    println("Hello,")
//}

fun main() = runBlocking {
    println("1: " + Thread.currentThread().name)

    val result = async {
        println("2: " + Thread.currentThread().name)
        getNews()
    }

    val summary = getSummary()

    printNews(result.await(), summary)
}

suspend fun getNews(): String {
    println("3: " + Thread.currentThread().name)
    delay(5000L)
    return "Hello news"
}

suspend fun getSummary(): String {
    println("4: " + Thread.currentThread().name)
    delay(10000L)
    return "Hello summary"
}

fun printNews(news: String, summary: String) {
    println("$news $summary")
}

//
//fun main() = runBlocking {
//    launch {
//        println("world")
//    }
//    launch {
//        delay(2000L)
//        println("world")
//    }
//    delay(5000L)
//    println("런블럭 안에 런블럭")
//    println("hello,")
//}
//
//suspend fun getArticle() = coroutineScope {
//    launch {
//
//    }
//}
