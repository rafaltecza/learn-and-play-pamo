package com.example.learnandplay_pamo.ui.games

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.learnandplay_pamo.R
import com.example.learnandplay_pamo.databinding.FragmentGamesBinding
import kotlin.random.Random

var gameType = "addition"

class GamesFragment : Fragment() {


    private var _binding: FragmentGamesBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var scoreNumber = 0
    private var defaultTextScore = ""

    private var buttonAnswer0: Button? = null
    private var buttonAnswer1: Button? = null
    private var buttonAnswer2: Button? = null
    private var buttonAnswer3: Button? = null
    private var number1Text: TextView? = null
    private var number2Text: TextView? = null
    private var calculationSignText: TextView? = null
    private var scoreText: TextView? = null

    private var random1 = 0
    private var random2 = 0
    private var result = 0

    private var answersbuttons: Array<Button?>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(GamesViewModel::class.java)

        _binding = FragmentGamesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textGame
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        val buttonBack: Button = binding.back
        buttonBack.setOnClickListener {
            findNavController().navigate(R.id.navigation_dashboard)
        }

        number1Text = binding.number1
        number2Text = binding.number2
        calculationSignText = binding.calculationSign

        scoreText = binding.scoreText
        defaultTextScore = scoreText?.text.toString()

        buttonAnswer0 = binding.answer1
        buttonAnswer1 = binding.answer2
        buttonAnswer2 = binding.answer3
        buttonAnswer3 = binding.answer4

        answersbuttons = arrayOf(
            buttonAnswer0,
            buttonAnswer1,
            buttonAnswer2,
            buttonAnswer3
        )

        setNewParametersForGame()

        return root
    }

    private fun setNewParametersForGame()
    {
         random1 = Random.nextInt(0, 10)
         random2 = Random.nextInt(0, 10)
         result = getResult(random1, random2)

        number1Text?.text = random1.toString()
        number2Text?.text = random2.toString()

        val buttonAnswerWithResult = answersbuttons?.get(Random.nextInt(0, answersbuttons!!.size))

        for (button in answersbuttons!!) {

            if(button ==  buttonAnswerWithResult)
            {
                button?.text = result.toString()
            }
            else
            {
                val similarToResult = result + Random.nextInt(0, 5)
                button?.text = similarToResult.toString()
            }

            button?.setOnClickListener {
                if(button.text.equals(result.toString()))
                {
                    //correct answer case
                    scoreNumber+=1
                    scoreText?.text = defaultTextScore + scoreNumber.toString()
                }
                else
                {
                    //incorrect answer case
                }
                setNewParametersForGame()
            }
        }
    }

    fun getResult(x: Int, y: Int): Int {

        when (gameType) {
            "addition" ->{
                calculationSignText?.text = "+"
                return x + y
            }
            "subtraction" ->{
                calculationSignText?.text = "-"
                return x - y
            }
            "multiplication" ->{
                calculationSignText?.text = "*"
                return x * y
            }
            "division" ->{
                calculationSignText?.text = "/"
                return x / y
            }
            else -> {
                print("Error")
                return 0
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}