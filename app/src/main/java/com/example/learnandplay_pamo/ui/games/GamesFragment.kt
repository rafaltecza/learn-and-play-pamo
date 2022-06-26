package com.example.learnandplay_pamo.ui.games

import android.os.Bundle
import android.os.Handler
import android.util.Log
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
import org.w3c.dom.Text
import kotlin.random.Random

var gameType = "addition" // variable with game type parameter default game type is addition

// Class contains logic associated with games logic
class GamesFragment : Fragment() {

    private var _binding: FragmentGamesBinding? = null
    private val binding get() = _binding!!
    private var scoreNumber = 0 // current score variable
    private var defaultTextScore = "" // default text for score is empty

    private var buttonAnswer0: ImageButton? = null // buttons with answers
    private var buttonAnswer1: ImageButton? = null
    private var buttonAnswer2: ImageButton? = null
    private var buttonAnswer3: ImageButton? = null

    private var buttonAnswerText0: TextView? = null // answers for
    private var buttonAnswerText1: TextView? = null
    private var buttonAnswerText2: TextView? = null
    private var buttonAnswerText3: TextView? = null

    private var number1Text: TextView? = null // first text of mathematics operation
    private var number2Text: TextView? = null // second text of mathematics operation
    private var calculationSignText: TextView? = null // sign with define type of mathematics operation
    private var scoreText: TextView? = null

    private var correctAnswerImage: ImageView? = null // variable contains correct answer image
    private var incorrectAnswerImage: ImageView? = null // variable contains incorrect answer image

    private var random1 = 0 // first value of operation
    private var random2 = 0 // second value of operation
    private var result = 0

    private var answersbuttons: Array<ImageButton?>? = null// array contains all buttons
    private var answersbuttonstext: Array<TextView?>? = null // array contains all buttons texts

    fun animateImage(imageView: ImageView) {
        imageView?.animate()?.alpha(0.0f)?.duration = 300; // time of changing alpha channel
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

        number1Text = binding.number1 // set first text
        number2Text = binding.number2 // set second text
        calculationSignText = binding.calculationSign // set calculation sign

        scoreText = binding.scoreText
        defaultTextScore = scoreText?.text.toString()

        buttonAnswer0 = binding.answer1 // set all buttons
        buttonAnswer1 = binding.answer2
        buttonAnswer2 = binding.answer3
        buttonAnswer3 = binding.answer4

        buttonAnswerText0 = binding.answerText1 // set all texts
        buttonAnswerText1 = binding.answerText2
        buttonAnswerText2 = binding.answerText3
        buttonAnswerText3 = binding.answerText4

        correctAnswerImage = binding.correctAnswer
        incorrectAnswerImage = binding.incorrectAnswer



        answersbuttons = arrayOf( // set array of buttons
            buttonAnswer0,
            buttonAnswer1,
            buttonAnswer2,
            buttonAnswer3
        )

        answersbuttonstext = arrayOf( // set array of texts
            buttonAnswerText0,
            buttonAnswerText1,
            buttonAnswerText2,
            buttonAnswerText3
        )

        setNewParametersForGame()
        return root
    }

    private fun setNewParametersForGame() // function with set new parameters for new game
    {
        scoreText?.text = defaultTextScore + scoreNumber.toString() // set score text
        random1 = Random.nextInt(0, 10) // set random value for 1
        random2 = Random.nextInt(0, 10) // set random value for 2
        result = getResult(random1, random2) // get result of choosen game type

        number1Text?.text = random1.toString() // set generated random value for text
        number2Text?.text = random2.toString()

        val buttonAnswerWithResult = answersbuttons?.get(Random.nextInt(0, answersbuttons!!.size)) // set button with correct answer

        var index = 0
        for (button in answersbuttons!!) {

            if(button == buttonAnswerWithResult)
            {
                answersbuttonstext?.get(index)?.text = result.toString() // set result as text on button
            }
            else
            {
                val similarToResult = result + Random.nextInt(0, 5)
                answersbuttonstext?.get(index)?.text = similarToResult.toString() // set similar results for other buttons

            }

            button?.setOnClickListener { view ->
                if(answersbuttonstext?.get(view.tag.toString().toInt())?.text?.equals(result.toString()) == true)
                {
                    //correct answer case
                    scoreNumber+=1
                    correctAnswerImage?.isVisible = true // set visible for image
                    correctAnswerImage?.alpha = 1.0f // set alpha channel
                    correctAnswerImage?.scaleX = 1.0f // set scale X
                    correctAnswerImage?.scaleY = 1.0f // set scale Y

                    val handler = Handler()
                    handler.postDelayed({
                        correctAnswerImage?.animate()?.alpha(0.0f)?.scaleX(0.0f)?.scaleY(0.0f)?.duration = 300;
                    }, 1000)

                    incorrectAnswerImage?.isVisible = false // set invisible for image
                    scoreText?.text = defaultTextScore + scoreNumber.toString()

                }
                else
                {
                    //incorrect answer case
                    correctAnswerImage?.isVisible = false;  // set invisible for image
                    incorrectAnswerImage?.isVisible = true; // set visible for image
                    incorrectAnswerImage?.alpha = 1.0f // set alpha
                    incorrectAnswerImage?.scaleX = 1.0f // set scale X
                    incorrectAnswerImage?.scaleY = 1.0f // set scale Y

                    val handler = Handler()
                    handler.postDelayed({
                        incorrectAnswerImage?.animate()?.alpha(0.0f)?.scaleX(0.0f)?.scaleY(0.0f)?.duration = 300;
                    }, 1000)
                }
                setNewParametersForGame()
            }

            index++
        }
    }

    fun getResult(x: Int, y: Int): Int { // set calculation sign

        when (gameType) {
            "addition" ->{
                calculationSignText?.text = "+"
                return x + y // return addition result
            }
            "subtraction" ->{
                calculationSignText?.text = "-"
                return x - y // return subtraction result
            }
            "multiplication" ->{
                calculationSignText?.text = "*"
                return x * y // result multiplication result
            }
            "division" ->{
                calculationSignText?.text = "/"
                return x / y // return division result
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