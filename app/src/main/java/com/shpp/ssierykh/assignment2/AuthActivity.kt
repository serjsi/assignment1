package com.shpp.ssierykh.assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.content.Intent
import android.text.TextUtils
import com.shpp.ssierykh.assignment2.Constants.TEST_EMAIL
import com.shpp.ssierykh.assignment2.Constants.TEST_PASSWORD
import androidx.databinding.DataBindingUtil

import com.shpp.ssierykh.assignment2.databinding.ActivityAuthBinding



class AuthActivity : AppCompatActivity() {

    //Announce the use of the following objects
  //  private var username: EditText? = null
//    private var usernameTextInputLayout: TextInputLayout? = null
//    private var password: EditText? = null
//    private var passwordTextInputLayout: TextInputLayout? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_auth)
        val binding: ActivityAuthBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_auth)


        //Connect with the elements of our interface:
       // username = (findViewById(R.id.editTextEnterEmail) as EditText?)!!
   //     usernameTextInputLayout = (findViewById(R.id.textInputLayoutEmail) as TextInputLayout?)!!


       // password = findViewById(R.id.editTextTextPassword) as EditText?
     //   passwordTextInputLayout = (findViewById(R.id.textInputLayoutPassword) as TextInputLayout?)!!


        //Handle pressing the "SignIn" button:
        binding.signIn.setOnClickListener {
            if (binding.editTextEnterEmail.text.toString() == TEST_EMAIL &&
                binding.editTextTextPassword.text.toString() == TEST_PASSWORD) {
                Toast.makeText(applicationContext, "Enter!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Wrong password!", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        //Switching to another screen
        binding.buttonRegister.setOnClickListener {
            if (!TextUtils.isEmpty(binding.editTextEnterEmail.text) ||
                !TextUtils.isEmpty(binding.editTextTextPassword.text)) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("name", binding.editTextEnterEmail.text.toString())
                intent.putExtra("myPhoto", R.drawable.my_photo)
                startActivity(intent)
            } else {
                Toast.makeText(
                    applicationContext,
                    getString(R.string.empty_email_or_password),
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }

    }
}


