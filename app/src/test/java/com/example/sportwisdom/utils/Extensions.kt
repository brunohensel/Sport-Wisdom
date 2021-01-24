package com.example.sportwisdom.utils

import com.nhaarman.mockitokotlin2.given

infix fun <T> T.willReturn(value: T) {
  given(this)
    .willReturn(value)
}