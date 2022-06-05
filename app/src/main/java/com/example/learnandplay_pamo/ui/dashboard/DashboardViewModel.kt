package com.example.learnandplay_pamo.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Czego dzisiaj chcesz sie nauczyc"
    }
    val text: LiveData<String> = _text
}