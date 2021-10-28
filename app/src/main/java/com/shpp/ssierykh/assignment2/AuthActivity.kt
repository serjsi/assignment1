package com.shpp.ssierykh.assignment2


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.content.Intent
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import com.shpp.ssierykh.assignment2.Constants.TEST_EMAIL
import com.shpp.ssierykh.assignment2.Constants.TEST_PASSWORD
import androidx.databinding.DataBindingUtil
import com.shpp.ssierykh.assignment2.Constants.MIN_LENGTH_PASSWORD
import com.shpp.ssierykh.assignment2.Validators.isStringContainNumber
import com.shpp.ssierykh.assignment2.Validators.isStringContainSpecialCharacter
import com.shpp.ssierykh.assignment2.Validators.isStringLowerAndUpperCase
import com.shpp.ssierykh.assignment2.Validators.isValidEmail
import com.shpp.ssierykh.assignment2.databinding.ActivityAuthBinding


class AuthActivity : AppCompatActivity() {

    //Announce the use of the following objects

    lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_auth)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_auth)

        setupListeners()

        binding.checkBoxRemember.setOnClickListener {
            binding.editTextEnterEmail.setText(TEST_EMAIL, TextView.BufferType.EDITABLE)
            binding.editTextTextPassword.setText(TEST_PASSWORD, TextView.BufferType.EDITABLE)
        }


        //Handle pressing the "SignIn" google:
        binding.buttonGoogle.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("name", "serhii.sierykh@gmail.com")
            intent.putExtra("myPhoto", R.drawable.my_photo)
            startActivity(intent)
            //Animation
            overridePendingTransition(0, R.anim.slide_out_right)

        }


        //Switching to another screen
        binding.buttonRegister.setOnClickListener {

            if (isValidate() || !TextUtils.isEmpty(binding.editTextEnterEmail.text) ||
                !TextUtils.isEmpty(binding.editTextTextPassword.text)
            ) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("name", binding.editTextEnterEmail.text.toString())
                intent.putExtra("myPhoto", R.drawable.lucile)
                startActivity(intent)
                //Animation
                overridePendingTransition(0, R.anim.slide_out_right)
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


    private fun isValidate(): Boolean = validateEmail() && validatePassword()

    private fun setupListeners() {
        binding.editTextEnterEmail.addTextChangedListener(TextFieldValidation(binding.editTextEnterEmail))
        binding.editTextTextPassword.addTextChangedListener(TextFieldValidation(binding.editTextTextPassword))
    }

    private fun validatePassword(): Boolean {

        if (binding.editTextTextPassword.text.toString().trim().isEmpty()) {
            binding.textInputLayoutPassword.error = "Required Field!"
            binding.editTextTextPassword.requestFocus()
            return false
        } else if (binding.editTextTextPassword.text.toString().length < MIN_LENGTH_PASSWORD) {
            binding.textInputLayoutPassword.error =
                "Password can't be less than $MIN_LENGTH_PASSWORD"
            binding.editTextTextPassword.requestFocus()
            return false
        } else if (!isStringContainNumber(binding.editTextTextPassword.text.toString())) {
            binding.textInputLayoutPassword.error = "Required at least 1 digit"
            binding.editTextTextPassword.requestFocus()
            return false
        } else if (!isStringLowerAndUpperCase(binding.editTextTextPassword.text.toString())) {
            binding.textInputLayoutPassword.error =
                "Password must contain upper and lower case letters"
            binding.editTextTextPassword.requestFocus()
            return false
        } else if (!isStringContainSpecialCharacter(binding.editTextTextPassword.text.toString())) {
            binding.textInputLayoutPassword.error = "1 special character required"
            binding.editTextTextPassword.requestFocus()
            return false
        } else {
            binding.textInputLayoutPassword.isErrorEnabled = false
        }

        return true
    }

    private fun validateEmail(): Boolean {
        if (binding.editTextEnterEmail.text.toString().trim().isEmpty()) {
            binding.textInputLayoutEmail.error = "Required Field!"
            binding.editTextEnterEmail.requestFocus()
            return false
        } else if (!isValidEmail(binding.editTextEnterEmail.text.toString())) {
            binding.textInputLayoutEmail.error = "Invalid Email!"
            binding.editTextEnterEmail.requestFocus()
            return false
        } else {
            binding.textInputLayoutEmail.isErrorEnabled = false
        }
        return true
    }


    /**
     * applying text watcher on each text field
     */
    inner class TextFieldValidation(private val view: View) : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            // checking ids of each text field and applying functions accordingly.
            when (view.id) {
                R.id.editTextEnterEmail -> {
                    validateEmail()
                }
                R.id.editTextTextPassword -> {
                    validatePassword()
                }

            }
        }
    }


}




