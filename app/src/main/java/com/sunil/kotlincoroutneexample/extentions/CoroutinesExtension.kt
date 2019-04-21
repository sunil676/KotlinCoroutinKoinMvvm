package com.sunil.kotlincoroutneexample.extentions

import kotlinx.coroutines.*

fun launchAsync(block: suspend CoroutineScope.() -> Unit): Job {
    return GlobalScope.launch(Dispatchers.Main){block()}
}

suspend fun <T> async(block: suspend CoroutineScope.() -> T): Deferred<T> {
    return GlobalScope.async(Dispatchers.IO) { block() }
}

suspend fun <T> asyncAwait(block: suspend CoroutineScope.() -> T): T {
    return async(block).await()
}