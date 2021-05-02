package com.example.wumbell.screens.workout.menu


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.wumbell.R
import com.example.wumbell.data.ExerciseData
import com.example.wumbell.databinding.WorkoutMenuBinding
import com.example.wumbell.screens.workout.mainPage.ExerciseAdapter


class MenuPageFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding= DataBindingUtil.inflate<WorkoutMenuBinding>(inflater,R.layout.workout_menu, container, false)
        updateUI(binding)
        return binding.root
    }

    private fun updateUI(binding: WorkoutMenuBinding) {
    val index=MenuPageFragmentArgs.fromBundle(requireArguments()).index
        binding.data=ExerciseData.data[index]
    val selectedEquipment=MenuPageFragmentArgs.fromBundle(requireArguments())
        binding.equip.text=selectedEquipment.equip
    }


}