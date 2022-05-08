package com.shpp.ssierykh.assignment1.screens

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shpp.ssierykh.assignment1.screens.activity_old.MainActivity
import com.shpp.ssierykh.assignment1.screens.first_screen.StartActivity

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, StartActivity::class.java)
        // Switch to old variant witch  activity
//               val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}