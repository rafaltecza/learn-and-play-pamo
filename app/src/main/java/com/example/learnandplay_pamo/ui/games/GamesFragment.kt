package com.example.learnandplay_pamo.ui.games

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
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

    private var correctAnswerImage: ImageView? = null;
    private var incorrectAnswerImage: ImageView? = null;

    private var random1 = 0
    private var random2 = 0
    private var result = 0

    private var answersbuttons: Array<Button?>? = null

    fun animateImage(imageView: ImageView) {
        imageView?.animate()?.alpha(0.0f)?.duration = 300;
    }

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

        val buttonBack: ImageButton = binding.back
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

        correctAnswerImage = binding.correctAnswer
        incorrectAnswerImage = binding.incorrectAnswer



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
                    correctAnswerImage?.isVisible = true
                    correctAnswerImage?.alpha = 1.0f
                    correctAnswerImage?.scaleX = 1.0f
                    correctAnswerImage?.scaleY = 1.0f

                    val handler = Handler()
                    handler.postDelayed({
                        correctAnswerImage?.animate()?.alpha(0.0f)?.scaleX(0.0f)?.scaleY(0.0f)?.duration = 300;
                    }, 1000)

                    incorrectAnswerImage?.isVisible = false
                    scoreText?.text = defaultTextScore + scoreNumber.toString()

                }
                else
                {
                    //incorrect answer case
                    correctAnswerImage?.isVisible = false;
                    incorrectAnswerImage?.isVisible = true;
                    incorrectAnswerImage?.alpha = 1.0f
                    incorrectAnswerImage?.scaleX = 1.0f
                    incorrectAnswerImage?.scaleY = 1.0f

                    val handler = Handler()
                    handler.postDelayed({
                        incorrectAnswerImage?.animate()?.alpha(0.0f)?.scaleX(0.0f)?.scaleY(0.0f)?.duration = 300;
                    }, 1000)
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