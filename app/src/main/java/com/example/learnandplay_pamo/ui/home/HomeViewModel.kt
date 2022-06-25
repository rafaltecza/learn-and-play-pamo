package com.example.learnandplay_pamo.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Aby rozpocząć kliknij przycisk"
    }
    val text: LiveData<String> = _text
}