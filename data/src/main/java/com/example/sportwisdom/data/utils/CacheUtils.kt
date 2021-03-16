package com.example.sportwisdom.data.utils

import com.example.sportwisdom.common.utils.CacheResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout

suspend fun <T> safeCacheCall(dispatcher: CoroutineDispatcher, cacheCall: suspend () -> T?): CacheResult<T?> {
  return withContext(dispatcher) {
    try {
      withTimeout(3_000L) {
        CacheResult.Success(cacheCall.invoke())
      }
    } catch (error: Throwable) {
      when (error) {
        is TimeoutCancellationException -> CacheResult.GenericError("Cache error timeout")
        else                            -> CacheResult.GenericError("Unknown cache error")
      }
    }
  }
}