package com.example.sportwisdom.base

sealed class BaseAction<out T> {
  object Executing : BaseAction<Nothing>()
  object EmptyResult : BaseAction<Nothing>()
  data class RemoteSuccess<out T : Any>(val value: T) : BaseAction<T>()
  data class CacheSuccess<out T : Any>(val value: T) : BaseAction<T>()
  data class SideEffect<out T : Any>(val value: T) : BaseAction<T>()
  data class Failed(val code: Int? = null, val reason: String?) : BaseAction<Nothing>()
}