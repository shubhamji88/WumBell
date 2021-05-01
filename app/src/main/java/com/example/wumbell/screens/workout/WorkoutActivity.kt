package com.example.wumbell.ui.splash

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.wumbell.R
import com.example.wumbell.screens.connectOptions.ConnectActivity
import com.example.wumbell.ui.qrCode.QrCodeActivity

class WorkoutActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.workout_activity)
        var qrcode=findViewById<LinearLayout>(R.id.qr)
        qrcode.setOnClickListener{
            val connect=Intent(this, ConnectActivity::class.java)
            connect.putExtra("qrconnect",true)
            startActivity(connect)
            finish()
        }
    }
}