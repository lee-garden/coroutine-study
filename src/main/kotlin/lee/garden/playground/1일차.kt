package lee.garden.playground

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Main

fun main() {

//    thread {
//        Functions.getNewsFromApi()
//        Functions.getUserFromApi()
//    }

    GlobalScope.launch {
        Functions.getNewsFromApi()
    }
    GlobalScope.launch {
        Functions.getUserFromApi()
    }

    val seq = sequence {
        yield(1)
        yield(2)
        yield(3)
    }

    Thread.sleep(5000L)
}