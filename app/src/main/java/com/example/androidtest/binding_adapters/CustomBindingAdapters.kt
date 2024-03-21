package com.tpl.insuranceapp.revamp.binding_adapters

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import com.squareup.picasso.Picasso


object CustomBindingAdapters {

    @BindingAdapter("textChangedListener")
    @JvmStatic
    fun EditText.addTextWatcher(errorShowValue: MutableLiveData<String>) {
        this.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                /*afterTextChanged*/
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                /* beforeTextChanged*/
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                errorShowValue.value = ""
            }
        })
    }

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun ImageView.loadImage(url: String?) {
        Picasso.get().load(url).into(this)
    }

}



