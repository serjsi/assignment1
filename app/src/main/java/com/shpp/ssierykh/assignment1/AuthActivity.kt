package com.shpp.ssierykh.assignment1


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.content.Intent
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.asLiveData
import com.shpp.ssierykh.assignment1.Constants.NAME_EXTRA
import com.shpp.ssierykh.assignment1.Constants.PHOTO_EXTRA
import com.shpp.ssierykh.assignment1.Constants.TEST_EMAIL
import com.shpp.ssierykh.assignment1.Constants.TEST_PASSWORD
import com.shpp.ssierykh.assignment1.databinding.ActivityAuthBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding
    private lateinit var userManager: UserManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Get reference to our userManager class
        userManager = UserManager(dataStore)

        setupListeners()
        observeData()

        forTestMethod()
    }

    /**
     * Applying text watcher on each text field
     */
    @DelicateCoroutinesApi
    private fun setupListeners() {
        binding.apply {
            editTextEnterEmail.doOnTextChanged { _, _, _, _ -> isValidateEmail() }
            editTextTextPassword.doOnTextChanged { _, _, _, _ -> isValidatePassword() }
            //Switching to another screen
            buttonRegister.setOnClickListener {
                if (isValidateEmail() && isValidatePassword()) {
                    writeDataAutoLogon()
                    //Stores the values
                    val intent = Intent(this@AuthActivity, MainActivity::class.java)
                    intent.putExtra(NAME_EXTRA, editTextEnterEmail.text.toString())
                    intent.putExtra(PHOTO_EXTRA, R.drawable.lucile)
                    startActivity(intent)
                    finish()
                    //Animation
                    overridePendingTransition(0, R.anim.slide_out_right)
                    buttonRegister.isClickable = false
                } else {
                    Toast.makeText(
                        applicationContext,
                        getString(R.string.wrong_email_or_password),
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }

        }
    }

    /**
     * Checking validate E-mail
     */
    private fun isValidateEmail(): Boolean {
        binding.apply {
            if (editTextEnterEmail.text.toString().trim().isEmpty()) {
                textInputLayoutEmail.error = getString(R.string.message_cannot_be_empty)
                editTextEnterEmail.requestFocus()
                return false
            } else if (!Validators.isValidEmail(editTextEnterEmail.text.toString())
            ) {
                textInputLayoutEmail.error = getString(R.string.message_wromg_e_mail)
                editTextEnterEmail.requestFocus()
                return false
            } else {
                textInputLayoutEmail.error = ""
                binding.textInputLayoutPassword.error = null
            }
        }
        return true
    }

    /**
     * Checking validate password
     */
    private fun isValidatePassword(): Boolean {
        binding.apply {
            val passwordChek = binding.editTextTextPassword.text.toString()
            Validators.validatePassword(passwordChek)
            if (Validators.validatePassword(passwordChek) != 0) {
                textInputLayoutPassword.error =
                    getString(Validators.validatePassword(passwordChek))
                editTextTextPassword.requestFocus()
                return false
            } else {
                textInputLayoutPassword.error = null
            }
        }
        return true
    }


    //Stores the values
    @DelicateCoroutinesApi
    private fun writeDataAutoLogon() {
        GlobalScope.launch {
            binding.apply {
                if (checkBoxRemember.isChecked) {
                    userManager.storeUser(
                        editTextEnterEmail.text.toString(),
                        editTextTextPassword.text.toString(), true
                    )
                } else {
                    userManager.storeUser(false)
                }
                finish()
            }
        }
    }

    private fun observeData() {
        //Check ChekBox
        userManager.userRememberFlow.asLiveData().observe(this, {
            if (it == true) {
                //Updates remember
                binding.checkBoxRemember.isChecked = it

                //Updates email
                userManager.userEmailFlow.asLiveData().observe(this, { email ->
                    if (email != null) {
                        binding.editTextEnterEmail.setText(email, TextView.BufferType.EDITABLE)
                    }
                })

                //Updates password
                userManager.userPasswordFlow.asLiveData().observe(this, { password ->
                    if (password != null) {
                        binding.editTextTextPassword.setText(
                            password,
                            TextView.BufferType.EDITABLE
                        )
                    }
                })

            }
        })
    }

    ////////////////////////////////test method////////////////////////////////////////
    private fun forTestMethod() {
        binding.apply {
            binding.signIn.setOnClickListener {
              editTextEnterEmail.setText(TEST_EMAIL, TextView.BufferType.EDITABLE)
               editTextTextPassword.setText(TEST_PASSWORD, TextView.BufferType.EDITABLE)
            }

            //Handle pressing the "SignIn" google:

            buttonGoogle.setOnClickListener {
                val intent = Intent(this@AuthActivity, MainActivity::class.java)
                intent.putExtra(NAME_EXTRA, "serhii.sierykh@gmail.com")
                intent.putExtra(PHOTO_EXTRA, R.drawable.kot_ochki)
                startActivity(intent)
                //Animation
                overridePendingTransition(0, R.anim.slide_out_right)
            }
        }
    }
    //////////////////////////////////////////////////////////////////////////////////
}