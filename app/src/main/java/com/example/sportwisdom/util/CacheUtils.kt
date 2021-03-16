package com.example.sportwisdom.util

import com.example.sportwisdom.common.utils.CacheResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout

suspend fun <T> safeCacheCall(dispatcher: CoroutineDispatcher, cacheCall: suspend () -> T?): com.example.sportwisdom.common.utils.CacheResult<T?> {
  return withContext(dispatcher) {
    try {
      withTimeout(3_000L) {
        com.example.sportwisdom.common.utils.CacheResult.Success(cacheCall.invoke())
      }
    } catch (error: Throwable) {
      when (error) {
        is TimeoutCancellationException -> com.example.sportwisdom.common.utils.CacheResult.GenericError("Cache error timeout")
        else                            -> com.example.sportwisdom.common.utils.CacheResult.GenericError("Unknown cache error")
      }
    }
  }
}