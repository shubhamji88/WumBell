package com.example.wumbell.screens.workout.menu


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.wumbell.R
import com.example.wumbell.data.ExerciseData
import com.example.wumbell.databinding.WorkoutMenuBinding
import com.example.wumbell.screens.ar.ARActivity
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
        binding.exer1.setOnClickListener {
            view?.findNavController()?.navigate(MenuPageFragmentDirections.actionMenuPageFragmentToTimer(ExerciseData.data[index].list[0].fieldCount))
        }
        binding.nextbutton1.setOnClickListener {
            startAr()
        }
        binding.nextbutton2.setOnClickListener {
            startAr()
        }
        binding.nextbutton.setOnClickListener {
            startAr()
        }
        binding.nextbutton4.setOnClickListener {
            startAr()
        }
        binding.exer2.setOnClickListener {
            view?.findNavController()?.navigate(MenuPageFragmentDirections.actionMenuPageFragmentToTimer(ExerciseData.data[index].list[1].fieldCount))
        }
        binding.exer3.setOnClickListener {
            view?.findNavController()?.navigate(MenuPageFragmentDirections.actionMenuPageFragmentToTimer(ExerciseData.data[index].list[2].fieldCount))
        }
        binding.exer4.setOnClickListener {
            view?.findNavController()?.navigate(MenuPageFragmentDirections.actionMenuPageFragmentToTimer(ExerciseData.data[index].list[3].fieldCount))
        }

    }
    fun startAr(){
        val arIntent= Intent(context,ARActivity::class.java)
        startActivity(arIntent)
    }

}