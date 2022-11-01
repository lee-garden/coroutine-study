package lee.garden.playground

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

//suspend fun main() {
//    println("Before")
//    println("${Thread.currentThread().name}")
//
//    suspendCoroutine<Unit> { c ->
//        thread {
//            println("thread 0 ${Thread.currentThread().name}")
//            thread {
//                println("thread 1 ${Thread.currentThread().name}")
//                Thread.sleep(1000L)
//                c.resume(Unit)
//            }
//            println("thread 0 finish ? ${Thread.currentThread().name}")
//        }
//    }
//
//    println("${Thread.currentThread().name}")
//    println("After")
//}

private val executor =
    Executors.newSingleThreadScheduledExecutor {
        Thread(it, "scheduler").apply { isDaemon = true }
    }




var continuation: Continuation<Unit>? = null

suspend fun cont() {
    suspendCoroutine<Unit> { c ->
        continuation = c
        println("continuation 밑에~") // 얘는 찍힘
    }
    println("suspend block 밖에~") // 얘는 안찍힘
}
suspend fun main() {
    println("Before ${Thread.currentThread().name}")
    cont()
    println("실행이 여기까진 내려옴") // 얘는 안찍힘
    continuation?.resume(Unit)
    println("After ${Thread.currentThread().name}")
}








//fun getUser(): Boolean {
//    Thread.sleep(3000L)
//    println("GetUSer!")
//    if (true) {
//        throw NumberFormatException()
//    }
//    return false
//}

fun 함수(): Result<Boolean> {

    if (true) {
        throw Exception()
    }

    return Result.success(true)
}

//suspendCoroutine<Unit> { continuation ->
//
//    // 얘가 다 수행이 되고 일시중단 하지만 resume을 만나는 순간 다시 돌아감.
//    // 여기서 3초가 걸리는 api를 호출
//    executor.schedule({
//        continuation.resume(Unit)
//    }, 1000, TimeUnit.MILLISECONDS)
//}
