package com.example.sportwisdom.common.utils

abstract class BaseCacheResponseHandler<Action, Data>(private val cacheResult: CacheResult<Data?>) {

  suspend fun getResult(): com.example.sportwisdom.common.utils.BaseAction<Action> {
    return when (cacheResult) {
      is CacheResult.Success -> cacheResult.value?.let { handleSuccess(it) } ?: com.example.sportwisdom.common.utils.BaseAction.EmptyResult
      is CacheResult.GenericError -> com.example.sportwisdom.common.utils.BaseAction.Failed(null, cacheResult.errorMessage)
    }
  }

  abstract suspend fun handleSuccess(resultObj: Data): com.example.sportwisdom.common.utils.BaseAction<Action>
}