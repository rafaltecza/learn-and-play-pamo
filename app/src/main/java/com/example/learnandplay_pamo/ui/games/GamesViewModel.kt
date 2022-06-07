package com.example.learnandplay_pamo.ui.games

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class GamesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {

        value = "Dodawanie "
    }
    val text: LiveData<String> = _text
}