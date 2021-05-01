package com.example.wumbell.screens.connectOptions


import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
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
import com.example.wumbell.ui.qrCode.QrCodeActivity
import com.example.wumbell.ui.splash.WorkoutActivity


class ConnectOptions : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding= DataBindingUtil.inflate<ConnectOptionsBinding>(inflater,R.layout.connect_options, container, false)

        binding.connectButton.setOnClickListener {
            val workoutIntent=Intent(context,WorkoutActivity::class.java)
            startActivity(workoutIntent)
            activity?.finish()
        }
        binding.scanQr.setOnClickListener {
            if (ContextCompat.checkSelfPermission(requireContext(),
                    Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED) {
                startQrActivity()

            }else{
                requestPermissions(arrayOf(Manifest.permission.CAMERA),12)
            }


        }

        return binding.root
    }
    private fun startQrActivity(){
        val qrIntent= Intent(context, QrCodeActivity::class.java)
        startActivityForResult(qrIntent,3)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode== RESULT_OK && requestCode==3 ){
            val result=data?.getStringExtra("result")
            Toast.makeText(context, result, Toast.LENGTH_SHORT).show()
        }else
            Toast.makeText(context, "Try Again!", Toast.LENGTH_SHORT).show()
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
                        startQrActivity()
                    }
                } else {
                    Toast.makeText(requireContext(), "Permission Denied!", Toast.LENGTH_SHORT).show()
                }
                return
            }

        }
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val startQr=activity?.intent?.getBooleanExtra("qrconnect",false)
//        startQr?.let {
//            if(it)
//                startQrActivity()
//        }
//    }


}