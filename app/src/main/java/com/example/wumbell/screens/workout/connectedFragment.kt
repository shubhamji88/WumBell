package com.example.wumbell.screens.workout


import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.wumbell.R
import com.example.wumbell.databinding.ConnectOptionsBinding
import com.example.wumbell.databinding.WorkoutConnectedBinding
import com.example.wumbell.ui.qrCode.QrCodeActivity
import com.example.wumbell.ui.splash.WorkoutActivity


class connectedFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding= DataBindingUtil.inflate<WorkoutConnectedBinding>(inflater,R.layout.workout_connected, container, false)
        setUpSpinner(binding)
        return binding.root
    }

    private fun setUpSpinner(binding: WorkoutConnectedBinding) {
        val equipmentList= arrayOf("Dumbell","Kettlebell","Bench press","Declined bench press","Cable crossover","Indoor rower")
        val bodypartList= arrayOf("chest", "shoulder","bicep","tricep","upper back","lower back", "hamstring","glutes","calves")
        val equipmentSpinner =binding.equipmentSpinner
        val bodySpinner=binding.bodyPartSpinner
        equipmentSpinner.adapter=
            ArrayAdapter(requireContext(), R.layout.spinner_layout,equipmentList)
        bodySpinner.adapter=
            ArrayAdapter(requireContext(), R.layout.spinner_layout,bodypartList)
        equipmentSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                Toast.makeText(context, equipmentList[position], Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
        bodySpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                Toast.makeText(context, bodypartList[position], Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }

}