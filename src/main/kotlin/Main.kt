import kotlinx.coroutines.*

fun main() {
    exampleLaunchGlobalWaiting()
}

suspend fun printLnDelayed(message: String) {
    delay(1000)
    println(message)
}

fun exampleBlocking() = runBlocking {
    print("one")
    printLnDelayed("two")
    print("three")
}

fun exampleBlockingDispatcher() {
    runBlocking(Dispatchers.Default) {
        println("one - desde tread ${Thread.currentThread().name}")
        printLnDelayed("two - desde tread ${Thread.currentThread().name}")
    }
    println("three - desde tread ${Thread.currentThread().name}")
}

fun exampleLaunchGlobal() = runBlocking {
    println("one - desde tread ${Thread.currentThread().name}")
    GlobalScope.launch {
        printLnDelayed("two - desde tread ${Thread.currentThread().name}")
    }
    println("three - desde tread ${Thread.currentThread().name}")
    delay(3000)
}

fun exampleLaunchGlobalWaiting() = runBlocking {
    println("one - desde tread ${Thread.currentThread().name}")
    val job = GlobalScope.launch {
        printLnDelayed("two - desde tread ${Thread.currentThread().name}")
    }
    println("three - desde tread ${Thread.currentThread().name}")
    job.join()
}