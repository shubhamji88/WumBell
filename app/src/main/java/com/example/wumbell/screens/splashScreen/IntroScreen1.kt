package com.example.wumbell.ui.splash


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.wumbell.R
import com.example.wumbell.databinding.StartupPage1Binding


class IntroScreen1 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding= DataBindingUtil.inflate<StartupPage1Binding>(inflater,R.layout.startup_page1, container, false)
        binding.button.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_introScreen1_to_introScreen2)
        }
        return binding.root
    }

}