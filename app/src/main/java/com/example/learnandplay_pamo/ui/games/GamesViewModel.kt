package com.example.learnandplay_pamo.ui.games

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class GamesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {

        when (gameType) { // Texts for all game types
            "addition" -> value = "Dodawanie"
            "subtraction" -> value = "Odejmowanie "
            "multiplication" -> value = "Mnożenie "
            "division" -> value = "Dzielenie "
            else -> {
                print("Error")
            }
        }

    }
    val text: LiveData<String> = _text
}