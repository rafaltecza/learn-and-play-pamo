package com.example.learnandplay_pamo.ui.dashboard


import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.learnandplay_pamo.databinding.ActivityMainBinding
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.learnandplay_pamo.R
import com.example.learnandplay_pamo.databinding.FragmentDashboardBinding
import com.example.learnandplay_pamo.ui.games.GamesFragment
import com.example.learnandplay_pamo.ui.games.gameType

class DashboardFragment : Fragment() {

private var _binding: FragmentDashboardBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

    _binding = FragmentDashboardBinding.inflate(inflater, container, false)
    val root: View = binding.root

    val textView: TextView = binding.textDashboard
    dashboardViewModel.text.observe(viewLifecycleOwner) {
      textView.text = it
    }


      val buttonAddition: Button = binding.buttonAddition
      val buttonSubtraction: Button = binding.buttonSubtraction
      val buttonMultiplication: Button = binding.buttonMultiplication
      val buttonDivision: Button = binding.buttonDivision

    buttonAddition.setOnClickListener {
        gameType = "addition"
        findNavController().navigate(R.id.navigation_games)
      }

    buttonSubtraction.setOnClickListener {
        gameType = "subtraction"
        findNavController().navigate(R.id.navigation_games)
      }

    buttonMultiplication.setOnClickListener {
        gameType = "multiplication"
       findNavController().navigate(R.id.navigation_games)
      }

    buttonDivision.setOnClickListener {
        gameType = "division"
       findNavController().navigate(R.id.navigation_games)
      }


    return root
  }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}