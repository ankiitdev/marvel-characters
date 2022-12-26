package com.ankiitdev.marvelcharacters.core.utils

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun Context?.showToast(message: String, duration: Int = Toast.LENGTH_LONG) {
    this?.let {
        Toast.makeText(this, message, duration).show()
    }
}

fun View?.showSnackbar(
    text: String?, action: String? = null, runnable: (() -> Unit)? = null,
    duration: Int = Snackbar.LENGTH_LONG,
    color: Int = Color.parseColor("#000000")
) {
    this?.let { view ->
        text?.let {
            val snackbar = Snackbar.make(view, it, duration)
            snackbar.view.setBackgroundColor(color)
            if (action != null) {
                snackbar.setActionTextColor(Color.WHITE)
                snackbar.setAction(action) {
                    this.post(runnable)
                    snackbar.dismiss()
                }
            }
            snackbar.show()
        }
    }
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}