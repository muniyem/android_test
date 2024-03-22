package com.example.androidtest.helpers

import android.text.Editable
import android.text.TextWatcher
import com.example.androidtest.callbacks.TextChangeWatcher


class TextWatcherHelper(private val textChangeWatcher: TextChangeWatcher) : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        // No implementation needed here
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        // Forward the call to the provided TextChangeWatcher
        textChangeWatcher.onTextChanged(s, start, before, count)
    }

    override fun afterTextChanged(s: Editable?) {
        // No implementation needed here
    }
}