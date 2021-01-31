package com.example.sportwisdom.util

import android.app.Activity
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import com.example.sportwisdom.R
import com.google.android.material.snackbar.Snackbar
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

fun Snackbar.setIcon(drawable: Drawable?): Snackbar {
  return this.apply {
    setAction(" ") {}
    val textView = view.findViewById<TextView>(R.id.snackbar_action)
    textView.text = ""

    drawable?.setTintMode(PorterDuff.Mode.SRC_ATOP)
    textView.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
  }
}