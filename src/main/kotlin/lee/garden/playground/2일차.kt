//package lee.garden.playground
//
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.flow.emitAll
//import kotlinx.coroutines.flow.flow
//import java.util.concurrent.Flow
//
//
//// yield 는 await를 걸어놓은 느낌~
//
//val seq = sequence {
//    yield(1)
//    yield(2)
//    yield(3)
//}
//
//fun main() {
//
//    for (num in seq) {
//        print(num)
//    }
//
//
//    Thread.sleep(5000L)
//}
//
//fun simple(): Flow<Int> = flow {
//    for (i in 1..3) {
//        delay(100)
//        emit(i)
//    }
//}
////
////fun allUsersFlow(): Flow<Long> = flow {
////    emit(getData(5000L))
////    emit(getData(1000L))
////    emit(getData(3000L))
////}
////
////fun getData(time: Long): Long {
////    Thread.sleep(time)
////    return time
////}
//
//// spring