package com.shpp.ssierykh.assignment1.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shpp.ssierykh.assignment1.ui.first_screen.StartActivity

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this,StartActivity::class.java)
        startActivity(intent)
        finish()
    }
}