package lee.garden.playground

import kotlinx.coroutines.delay

suspend fun myFunc() {
    println("BEFORE")
    delay(1000L)
    println("AFTER")
}
