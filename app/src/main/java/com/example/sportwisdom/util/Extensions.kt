package com.example.sportwisdom.util

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

fun EditText.getTextAfterChangeAsFlow(): StateFlow<String> {
  val query = MutableStateFlow("")
  doAfterTextChanged { editable ->
    query.value = editable.toString()
  }
  return query
}

fun Activity.hideSoftKeyboard() {
  val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
  inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
}