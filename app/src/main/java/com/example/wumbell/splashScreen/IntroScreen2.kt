package com.example.wumbell.ui.splash


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.wumbell.MainActivity
import com.example.wumbell.R
import com.example.wumbell.databinding.Startuppage2Binding


class IntroScreen2 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        (requireActivity() as SplashScreenActivity).supportActionBar!!.hide()
        val binding= DataBindingUtil.inflate<Startuppage2Binding>(inflater,R.layout.startuppage2, container, false)
        binding.button.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_introScreen1_to_introScreen2)
        }
        binding.button.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
            this.activity?.finish()
        }
        return binding.root
    }

}