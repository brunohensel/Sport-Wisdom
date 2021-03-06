package com.example.sportwisdom.base

abstract class BaseCacheResponseHandler<Action, Data>(private val cacheResult: CacheResult<Data?>) {

  suspend fun getResult(): BaseAction<Action> {
    return when (cacheResult) {
      is CacheResult.Success -> cacheResult.value?.let { handleSuccess(it) } ?: BaseAction.EmptyResult
      is CacheResult.GenericError -> BaseAction.Failed(null, cacheResult.errorMessage)
    }
  }

  abstract suspend fun handleSuccess(resultObj: Data): BaseAction<Action>
}