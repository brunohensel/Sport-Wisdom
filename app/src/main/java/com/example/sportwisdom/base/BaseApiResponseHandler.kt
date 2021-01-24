package com.example.sportwisdom.base

abstract class BaseApiResponseHandler<Action, Data>(private val apiResult: ApiResult<Data?>) {
  suspend fun getResult(): BaseAction<Action> {
    return when (apiResult) {
      is ApiResult.Success -> {
        apiResult.value?.let { handleSuccess(it) } ?: BaseAction.EmptyResult
      }
      is ApiResult.GenericError -> BaseAction.Failed(apiResult.code, apiResult.errorMessage)

      ApiResult.NetworkError -> BaseAction.Failed(null, "Network generic error.")
    }
  }

  abstract suspend fun handleSuccess(resultObj: Data): BaseAction<Action>
}