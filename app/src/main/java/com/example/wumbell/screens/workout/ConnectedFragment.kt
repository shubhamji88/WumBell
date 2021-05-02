package com.example.wumbell.screens.workout


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.wumbell.R
import com.example.wumbell.data.ExerciseData.Companion.bodypartList
import com.example.wumbell.data.ExerciseData.Companion.equipmentList
import com.example.wumbell.databinding.WorkoutConnectedBinding


class ConnectedFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val binding= DataBindingUtil.inflate<WorkoutConnectedBinding>(inflater,R.layout.workout_connected, container, false)
        updateUI(binding)
        return binding.root
    }

    private fun updateUI(binding: WorkoutConnectedBinding) {
        val equipmentSpinner =binding.equipmentSpinner
        var equipmentPos=0
        var bodyPos=0
        val bodySpinner=binding.bodyPartSpinner
        equipmentSpinner.adapter=
            ArrayAdapter(requireContext(), R.layout.spinner_layout,equipmentList)
        bodySpinner.adapter=
            ArrayAdapter(requireContext(), R.layout.spinner_layout,bodypartList)
        equipmentSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View?, position: Int, id: Long) {
                equipmentPos=position
                if(position!=0)
                Toast.makeText(context, "Selected equipment: "+equipmentList[position], Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
        bodySpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View?, position: Int, id: Long) {
                bodyPos=position
                if(position!=0)
                Toast.makeText(context,"Selected body part: "+ bodypartList[position], Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
        val deviceName=activity?.intent?.getStringExtra("name")
        binding.deviceName.text=deviceName
        binding.nextbutton.setOnClickListener {
            view?.findNavController()?.navigate(ConnectedFragmentDirections.actionConnectedFragmentToMainPageFragment(equipmentPos,bodyPos))
        }
    }

}