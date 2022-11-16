package lee.garden.playground

import kotlinx.coroutines.*
import kotlinx.coroutines.GlobalScope.coroutineContext
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

// 저번주
//fun main() = runBlocking {
//    launch {
//    }
//    val news = async {
//    }
//    val user = getUser()
//    func(news.await(), user)
//}

// 코루틴 콘텍스트 요소 찾기
//fun main() {
//    val ctx: CoroutineContext =
//        CoroutineName("A Name") + Job()
//
//    val coroutineName = ctx[CoroutineName]
//    val coroutineJob = ctx[Job]
//
//    println(coroutineName)
//    println(coroutineJob)
//}

// 컨텍스트 추가
//fun main() {
//    val coroutineName = CoroutineName("Nam1")
//
//    val job = Job()
//
//    val ctx = coroutineName + job
//}

//fun main() {
//    val emptyCoroutineContext = EmptyCoroutineContext
//
//    val coroutineContext = emptyCoroutineContext + CoroutineName("Name") + emptyCoroutineContext
//
//    println(coroutineContext[CoroutineName])
//}

//fun main() {
//    val coroutineContext = CoroutineName("name1") + Job()
//
//    coroutineContext.fold("") { acc, e -> "$acc $e"}.also(::println)
//}

fun CoroutineScope.log(msg: String) {
    println(coroutineContext[CoroutineName]?.name + " " + msg)
}

//fun main() = runBlocking(CoroutineName("main")+ Job()) {
//
//    log("Started")
//
//    val v1 = async {
//        delay(500L)
//        log("Running async")
//        getUser()
//        42
//    }
//
//    log("하이 헬로")
//
//    v1.await()
//    log("딜레이 전 ")
//    delay(1000L)
//}
//
//suspending function 에서 컨텍스트 액세스
//suspend fun getUser() {
//    // 얘도 자기를 부른 부모의 컨텍스트를 알 수 있다. 왜? 사실은 continuation을 전달 받고 있기 때문에.
//}

//class CounterContext(
//    private val name: String
//) : CoroutineContext.Element {
//    override val key: CoroutineContext.Key<*> = Key
//    private var nextNumber = 0
//    fun printNext() {
//        println("$name: $nextNumber")
//        nextNumber++
//    }
//
//    companion object Key:CoroutineContext.Key<CounterContext>
//}
//
//suspend fun printNext() {
//    coroutineContext[CounterContext]?.printNext()
//}

//suspend fun main(): Unit =
//    withContext(CounterContext("Outer")) {
//        printNext() // Outer: 0
//        launch {
//            printNext() // Outer: 1
//            launch {
//                printNext() // Outer: 2
//            }
//            launch(CounterContext("Inner")) {
//                printNext() // Inner: 0
//                printNext() // Inner: 1
//                launch {
//                    printNext() // Inner: 2
//                }
//            }
//        }
//        printNext() // Outer: 3
//    }

// 코루틴 컨텍스트가 무엇인가?
// 코루틴 컨텍스트의 특성 like Map<>
// 부모로부터 자식으로 넘어가는 context
// suspend function 에서 어떻게 context 를 참조 하는가