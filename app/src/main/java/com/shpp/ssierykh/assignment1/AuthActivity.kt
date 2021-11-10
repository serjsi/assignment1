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
                binding.buttonRegister.isClickable = false
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

    /**
     * Applying text watcher on each text field
     */
    private fun setupListeners() {

        binding.editTextEnterEmail.doOnTextChanged{text,_,_,_ -> validateEmail()}
        binding.editTextTextPassword.doOnTextChanged{text,_,_,_ -> validatePassword()}

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
            } else if (!Validators.isValidEmail(editTextEnterEmail.text.toString())
            ) {
                textInputLayoutEmail.error = getString(R.string.message_wromg_e_mail)
                editTextEnterEmail.requestFocus()
                return false
            } else {
                binding.textInputLayoutPassword.error = null
            }
        }
        return true
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
            else  {
            binding.textInputLayoutPassword.error = null
        }

        return true
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







