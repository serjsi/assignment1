package com.shpp.ssierykh.assignment1


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
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
    lateinit var binding: ActivityAuthBinding

    private lateinit var userManager: UserManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Get reference to our userManager class
        userManager = UserManager(dataStore)

        setupListeners()
        observeData()


        //Switching to another screen
        binding.buttonRegister.setOnClickListener {
            if (validateEmail() && validatePassword()) {
                //Stores the values
                writeDataAutoLogon()
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra(NAME_EXTRA, binding.editTextEnterEmail.text.toString())
                intent.putExtra(PHOTO_EXTRA, R.drawable.lucile)
                startActivity(intent)
                //Animation
                overridePendingTransition(0, R.anim.slide_out_right)

            } else {
                Toast.makeText(
                    applicationContext,
                    getString(R.string.wrong_email_or_password),
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }

        ////////////////////////////////test method////////////////////////////////////////
        binding.signIn.setOnClickListener {
            binding.editTextEnterEmail.setText(TEST_EMAIL, TextView.BufferType.EDITABLE)
            binding.editTextTextPassword.setText(TEST_PASSWORD, TextView.BufferType.EDITABLE)
        }

        //Handle pressing the "SignIn" google:
        binding.buttonGoogle.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(NAME_EXTRA, "serhii.sierykh@gmail.com")
            intent.putExtra(PHOTO_EXTRA, R.drawable.kot_ochki)
            startActivity(intent)
            //Animation
            overridePendingTransition(0, R.anim.slide_out_right)
        }
        //////////////////////////////////////////////////////////////////////////////////
    }


    private fun setupListeners() {
        binding.editTextEnterEmail.addTextChangedListener(TextFieldValidation(binding.editTextEnterEmail))
        binding.editTextTextPassword.addTextChangedListener(TextFieldValidation(binding.editTextTextPassword))
    }


    /**
     * Сhecking validate password
     */
    private fun validatePassword(): Boolean {
        val passwordChek = binding.editTextTextPassword.text.toString()

        Validators.validatePassword(passwordChek)
        if (Validators.validatePassword(passwordChek) != 0){
                binding.textInputLayoutPassword.error =
                    getString(Validators.validatePassword(passwordChek))
                binding.editTextTextPassword.requestFocus()
                return false
            }
            else   binding.textInputLayoutPassword.isErrorEnabled = false

        return true
    }



    /**
     * Сhecking validate E-mail
     */
    private fun validateEmail(): Boolean {
        binding.apply {
            if (editTextEnterEmail.text.toString().trim().isEmpty()) {
                textInputLayoutEmail.error = getString(R.string.message_cannot_be_empty)
                editTextEnterEmail.requestFocus()
                return false
                //} else if (!isValidEmail(binding.editTextEnterEmail.text.toString())) {
            } else if (!Validators.isValidEmail(editTextEnterEmail.text.toString())
            ) {
                textInputLayoutEmail.error = getString(R.string.message_wromg_e_mail)
                editTextEnterEmail.requestFocus()
                return false
            } else {
                textInputLayoutEmail.isErrorEnabled = false
            }
        }
        return true
    }


    /**
     * Applying text watcher on each text field
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


    //Stores the values
    @DelicateCoroutinesApi
    private fun writeDataAutoLogon() {
        GlobalScope.launch {
            if (binding.checkBoxRemember.isChecked) {
                userManager.storeUser(binding.editTextEnterEmail.text.toString(),
                    binding.editTextTextPassword.text.toString(), true)
            }else{
                userManager.storeUser("", "", false)
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
                userManager.userEmailFlow.asLiveData().observe(this, {
                    if (it != null) {
                        binding.editTextEnterEmail.setText(it, TextView.BufferType.EDITABLE)
                    }
                })

                //Updates password
                userManager.userPasswordFlow.asLiveData().observe(this, {
                    if (it != null) {
                        binding.editTextTextPassword.setText(it, TextView.BufferType.EDITABLE)
                    }
                })

            }
        })
    }
}





