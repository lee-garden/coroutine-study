package lee.garden.playground

import kotlinx.coroutines.*

// 저번주
// 코루틴 컨텍스트가 무엇인가?
// 코루틴 컨텍스트의 특성 like Map<> collection
// 부모로부터 자식으로 넘어가는 context
// suspend function 에서 어떻게 context 를 참조 하는가


//suspend fun main() = coroutineScope {
//    // Job created with a builder is active
//    val job = Job()
//    println(job) // JobImpl{Active}@ADD
//    // until we complete it with a method
//    job.complete()
//    println(job) // JobImpl{Completed}@ADD
//
//    // launch is initially active by default
//    val activeJob = launch {
//        delay(1000)
//    }
//    println(activeJob) // StandaloneCoroutine{Active}@ADD
//    // here we wait until this job is done
//    activeJob.join() // (1 sec)
//    println(activeJob) // StandaloneCoroutine{Completed}@ADD
//
//    // launch started lazily is in New state
//    val lazyJob = launch(start = CoroutineStart.LAZY) {
//        delay(1000)
//    }
//    println(lazyJob) // LazyStandaloneCoroutine{New}@ADD
//    // we need to start it, to make it active
//    lazyJob.start()
//    println(lazyJob) // LazyStandaloneCoroutine{Active}@ADD
//    lazyJob.join() // (1 sec)
//    println(lazyJob) //LazyStandaloneCoroutine{Completed}@ADD
//}

//| 상태 | isActive | isCompleted | isCancelled |
//| --- | --- | --- | --- |
//| New | 거짓 | 거짓 | 거짓 |
//| Active | 진실 | 거짓 | 거짓 |
//| Completing  | 진실 | 거짓 | 거짓 |
//| Cancelling  | 거짓 | 거짓 | 진실 |
//| Cancelled | 거짓 | 진실 | 진실 |
//| Completed | 거짓 | 진실 | 거짓 |

//fun main(): Unit = runBlocking {
//    val job: Job = launch {
//        delay(1000)
//        println("Test")
//    }
//}

//fun main(): Unit = runBlocking {
//    val deferred: Deferred<String> = async {
//        delay(1000)
//        "Test"
//    }
//    deferred.await() // Option + Command + B
//
//    val job: Job = deferred
//}


// Job은 유일함. 자식으로 전파되지 않음.
//fun main(): Unit {
//    runBlocking {
//        val name = CoroutineName("Some name")
//        val job = Job()
//
//        val originMom = coroutineContext[Job]
//        launch(name + job) {
//            delay(1000)
//            val childName = coroutineContext[CoroutineName]
//            println(childName == name) // true
//            val childJob = coroutineContext[Job]
//            println(childJob == job) // false
//            println(childJob == job.children.first()) // true
//            println(childJob == originMom) // maybe false
//            originMom.toString()
//        }
//        println("end")
//        while (true) { }
//    }
//}

// Job 간의 부모 - 자식 관계가 형성이 되면 참조로 서로 찾아 갈 수 있어
//fun main(): Unit = runBlocking {
//    val childJob: Job = launch {
//        delay(1000)
//    }
//
//    val childJob2: Job = launch {
//        delay(1000)
//    }
//
//    val parentJob: Job = coroutineContext.job
//    // or coroutineContext[Job]!!
//    println(childJob == parentJob) // false
//    val parentChildren: List<Job> = parentJob.children.toList()
//    println(parentChildren.first() == childJob) // true
//}

//
//fun main(): Unit = runBlocking {
//    launch(Job()) {
//        delay(1000) // 얘는 남의 자식이니까 안기다려
//        println("Will not be printed")
//    }
//}

//Test2
//Test1
//Test1 뒤에 찍힘?
//All tests are done

// join vs await ? ? 뭐임?
// 이거는 좀 더 파보죠 나중에 .. 각자 알아서 .. 아니 좀 궁금하긴 함
//fun main(): Unit = runBlocking {
//    val job1 = launch {
//        delay(3000)
//        println("Test1")
//    }
//    val job2 = launch {
//        delay(2000)
//        println("Test2")
//    }
//    job1.join()
//    println("Test1 뒤에 찍힘?")
//    job2.join()
//    println("All tests are done")
//}

// 와 종현이형 베트남 갔네

//suspend fun main(): Unit = coroutineScope {
//    val job = Job()
//    launch(job) {
//        delay(1000)
//        println("Text 1")
//    }
//    launch(job) {
//        delay(2000)
//        println("Text 2")
//    }
////    job.complete() // 얘가 있어야 끝남.
//    job.join()
//    println("이거 안나오지")
//}

fun main() = runBlocking {
    val job = Job()

    launch(job) {
        repeat(5) { num ->
            delay(200)
            println("Rep$num")
        }
    }

    launch {
        delay(500)
        job.completeExceptionally(Error("Some error"))
    }

    job.join() // 166번째줄에서 job이 터지니까 job의 자식들도 터져서 2, 3, 4 는 안찍힘. 그 뒤도 안찍힘

    launch(job) {
        println("Will not be printed")
    }

    println("Done")
}
// Rep0
// Rep1
// Done