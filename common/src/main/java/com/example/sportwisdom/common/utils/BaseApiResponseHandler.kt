package com.example.sportwisdom.common.utils

abstract class BaseApiResponseHandler<Action, Data>(private val apiResult: com.example.sportwisdom.common.utils.ApiResult<Data?>) {
  suspend fun getResult(): com.example.sportwisdom.common.utils.BaseAction<Action> {
    return when (apiResult) {
      is com.example.sportwisdom.common.utils.ApiResult.Success      -> {
        apiResult.value?.let { handleSuccess(it) } ?: com.example.sportwisdom.common.utils.BaseAction.EmptyResult
      }
      is com.example.sportwisdom.common.utils.ApiResult.GenericError -> com.example.sportwisdom.common.utils.BaseAction.Failed(apiResult.code, apiResult.errorMessage)

      com.example.sportwisdom.common.utils.ApiResult.NetworkError    -> com.example.sportwisdom.common.utils.BaseAction.Failed(null, "Network generic error.")
    }
  }

  abstract suspend fun handleSuccess(resultObj: Data): com.example.sportwisdom.common.utils.BaseAction<Action>
}