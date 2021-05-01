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
import androidx.lifecycle.ViewModelProvider
import com.example.wumbell.R
import com.example.wumbell.databinding.ConnectOptionsBinding
import com.example.wumbell.ui.qrCode.QrCodeActivity
import com.example.wumbell.ui.splash.WorkoutActivity


class ConnectOptions : Fragment() {
    private lateinit var viewModel:ConnectOptionsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding= DataBindingUtil.inflate<ConnectOptionsBinding>(inflater,R.layout.connect_options, container, false)
        val application = requireNotNull(this.activity).application
        val viewModelFactory = ConnectOptionsViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ConnectOptionsViewModel::class.java)
        binding.scanQr.setOnClickListener {
            if (ContextCompat.checkSelfPermission(requireContext(),
                    Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED) {
                startQrActivity()

            }else{
                requestPermissions(arrayOf(Manifest.permission.CAMERA),12)
            }
        }
        bindUI(binding)
        return binding.root
    }

    private fun bindUI(binding: ConnectOptionsBinding) {
        binding.connectButton.setOnClickListener {
            val code=binding.codeInput.text.toString().trim()
            if(!code.isNullOrEmpty())
                validateCode(code)
        }
        viewModel.navigateToMainPage.observe(viewLifecycleOwner, {
            if (!it.isNullOrEmpty()) {
                val workoutIntent = Intent(context, WorkoutActivity::class.java)
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                workoutIntent.putExtra("name", it)
                startActivity(workoutIntent)
                viewModel.doneNavigating()
                activity?.finish()
            }
        })
    }

    private fun validateCode(code: String?) {
        if(code=="ruhack") {
            viewModel.checkOnline()
        }
    }

    private fun startQrActivity(){
        val qrIntent= Intent(context, QrCodeActivity::class.java)
        startActivityForResult(qrIntent,3)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode== RESULT_OK && requestCode==3 ){
            val result=data?.getStringExtra("result")
                validateCode(result)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val startQr=activity?.intent?.getBooleanExtra("qrconnect",false)
        startQr?.let {
            if(it)
                startQrActivity()
        }
    }


}