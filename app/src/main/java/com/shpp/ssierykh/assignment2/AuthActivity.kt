package com.shpp.ssierykh.assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.content.Intent





class AuthActivity : AppCompatActivity() {

    //Announce the use of the following objects
    private var username: EditText? = null
    private var password: EditText? = null
    private var sign_in: Button? = null
    private var register: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        //Connect with the elements of our interface:
        username = (findViewById(R.id.editTextEnterEmail) as EditText?)!!
        password = findViewById(R.id.editTextTextPassword) as EditText?
        sign_in = findViewById(R.id.sign_in) as Button?
        register = findViewById(R.id.buttonRegister) as Button?

        //Handle pressing the "SignIn" button:
        sign_in?.setOnClickListener {
            if (username?.text.toString() == "admin" && password?.text.toString() == "admin") {
                Toast.makeText(getApplicationContext(), "Enter!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(getApplicationContext(), "Wrong password!", Toast.LENGTH_SHORT).show()
            }
        }

        //Switching to another screen
        register?.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }

}