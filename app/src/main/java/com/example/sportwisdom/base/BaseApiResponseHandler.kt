package com.example.sportwisdom.base

abstract class BaseApiResponseHandler<Data>(private val apiResult: ApiResult<Data?>) {
  suspend fun getResult(): BaseAction {
    return when (apiResult) {
      is ApiResult.Success -> {
        apiResult.value?.let { handleSuccess(it) } ?: BaseAction.Failed(null, "Data must not be null")
      }
      is ApiResult.GenericError -> BaseAction.Failed(apiResult.code, apiResult.errorMessage)

      ApiResult.NetworkError -> BaseAction.Failed(null, "Network generic error.")
    }
  }



  abstract suspend fun handleSuccess(resultObj: Data): BaseAction.Success<Data>
}