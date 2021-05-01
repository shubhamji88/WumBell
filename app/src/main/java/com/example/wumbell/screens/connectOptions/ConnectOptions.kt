package com.example.wumbell.screens.connectOptions


import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.wumbell.R
import com.example.wumbell.databinding.ConnectOptionsBinding


class ConnectOptions : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding= DataBindingUtil.inflate<ConnectOptionsBinding>(inflater,R.layout.connect_options, container, false)

        binding.scanQr.setOnClickListener {
            if (ContextCompat.checkSelfPermission(requireContext(),
                    Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED) {
//                startQrActivity()

            }else{
                requestPermissions(arrayOf(Manifest.permission.CAMERA),12)
            }


        }
        return binding.root
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        when (requestCode) {
            12 -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED) {
                    if ((ContextCompat.checkSelfPermission(requireContext(),
                            Manifest.permission.CAMERA) ==
                                PackageManager.PERMISSION_GRANTED)) {
//                        startQrActivity()
                    }
                } else {
                    Toast.makeText(requireContext(), "Permission Denied!", Toast.LENGTH_SHORT).show()
                }
                return
            }

        }
    }
}