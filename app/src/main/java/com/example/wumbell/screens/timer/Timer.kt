package com.example.wumbell.screens.timer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.wumbell.R
import com.example.wumbell.databinding.TimernrepsBinding

class Timer : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding=DataBindingUtil.inflate<TimernrepsBinding>(inflater,R.layout.timernreps, container, false)

        val application = requireNotNull(this.activity).application
        val viewModelFactory = TimerViewModelFactory(application)
        val viewModel= ViewModelProvider(this, viewModelFactory).get(TimerViewModel::class.java)
        val count=TimerArgs.fromBundle(requireArguments()).count
        binding.target.text= "Target: $count reps"
        viewModel.countDownTimer.start()
        viewModel.timer.observe(viewLifecycleOwner,{
            binding.timerText.text=it
        })
        binding.Pausebutton.setOnClickListener {
            viewModel.countDownTimer.cancel()
            binding.Pausebutton.visibility=View.INVISIBLE
            binding.startbutton.visibility=View.VISIBLE
        }
        binding.startbutton.setOnClickListener {
            viewModel.countDownTimer.start()
            binding.Pausebutton.visibility=View.VISIBLE
            binding.startbutton.visibility=View.INVISIBLE
        }
        viewModel.reps.observe(viewLifecycleOwner,{binding.repsText.text=it})
        binding.lifecycleOwner = this
        return binding.root
    }

}