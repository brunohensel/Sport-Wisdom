package com.example.sportwisdom.util

import com.example.sportwisdom.base.ApiResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> safeApiCall(dispatcher: CoroutineDispatcher, apiCall: suspend () -> T?): ApiResult<T?> {
  return withContext(dispatcher) {
    try {
      // throws TimeoutCancellationException
      withTimeout(12_000L) {
        ApiResult.Success(apiCall.invoke())
      }
    } catch (throwable: Throwable) {
      throwable.printStackTrace()
      when (throwable) {
        is TimeoutCancellationException -> ApiResult.GenericError(408, "Network timeout")
        is IOException -> ApiResult.NetworkError
        is HttpException -> {
          val code = throwable.code()
          val errorResponse = convertErrorBody(throwable)
          ApiResult.GenericError(code, errorResponse)
        }
        else                            -> ApiResult.GenericError(null, "Unknown network error")
      }
    }
  }
}

private fun convertErrorBody(throwable: HttpException): String? {
  return try {
    throwable.response()?.message()
  } catch (exception: Exception) {
    exception.printStackTrace()
    "Unknown error"
  }
}