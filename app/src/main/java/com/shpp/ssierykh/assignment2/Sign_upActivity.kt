package com.shpp.ssierykh.assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast





class Sign_upActivity : AppCompatActivity() {

    //
    private var username: EditText? = null
    private var password: EditText? = null
    private var login: Button? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up)

        //
        username = (findViewById(R.id.editTextEnterEmail) as EditText?)!!
        password = findViewById(R.id.editTextTextPassword) as EditText?
        login = findViewById(R.id.sign_in) as Button?



    }
    //
    fun Login(view: View?) {

        //
        //
        if (username?.getText().toString() == "admin" && password?.getText().toString() == "admin") {
            Toast.makeText(getApplicationContext(), "Enter!", Toast.LENGTH_SHORT).show()
        }
    }

}