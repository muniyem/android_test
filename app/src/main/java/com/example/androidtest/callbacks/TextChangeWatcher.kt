package com.example.androidtest.callbacks

interface TextChangeWatcher {
    fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int)
}