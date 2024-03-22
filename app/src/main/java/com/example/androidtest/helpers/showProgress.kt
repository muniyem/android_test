package com.example.androidtest.helpers

import android.content.Context
import android.widget.ProgressBar
import android.widget.Toast

fun ProgressBar.showProgress(context: Context, message: String = "Loading...") {
    this.visibility = android.view.View.VISIBLE
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun ProgressBar.hideProgress() {
    this.visibility = android.view.View.GONE
}
