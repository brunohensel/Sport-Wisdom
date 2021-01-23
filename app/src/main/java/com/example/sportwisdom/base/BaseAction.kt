package com.example.sportwisdom.base

sealed class BaseAction {
  object Executing : BaseAction()
  object EmptyResult : BaseAction()
  data class RemoteSuccess<out T>(val value: T) : BaseAction()
  data class CacheSuccess<out T>(val value: T) : BaseAction()
  data class Failed(val code: Int? = null, val reason: String?) : BaseAction()
}