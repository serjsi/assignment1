package com.shpp.ssierykh.assignment1


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.shpp.ssierykh.assignment1.Constants.NAME_EXTRA
import com.shpp.ssierykh.assignment1.Constants.NAME_SP
import com.shpp.ssierykh.assignment1.Constants.PASSWORD_SP
import com.shpp.ssierykh.assignment1.Constants.PHOTO_EXTRA
import com.shpp.ssierykh.assignment1.Constants.REMEMBER_SP
import com.shpp.ssierykh.assignment1.Constants.TEST_EMAIL
import com.shpp.ssierykh.assignment1.Constants.TEST_PASSWORD
import com.shpp.ssierykh.assignment1.data.PreferenceStorage
import com.shpp.ssierykh.assignment1.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private  var shPref: PreferenceStorage = PreferenceStorage(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
        loadAutologin()
        forTestMethod()
    }

    /**
     * Applying text watcher on each text field
     */
    private fun setupListeners() {
        binding.apply {
            etEnterEmail.doOnTextChanged { _, _, _, _ -> isValidateEmail() }
            etTextPassword.doOnTextChanged { _, _, _, _ -> isValidatePassword() }
            //Switching to another screen
            btRegister.setOnClickListener {
                if (isValidateEmail() && isValidatePassword()) {
                    //Stores the values
                    val intent = Intent(this@MainActivity, ProfileActivity::class.java)
                    intent.putExtra(NAME_EXTRA, etEnterEmail.text.toString())
                    intent.putExtra(PHOTO_EXTRA, R.drawable.lucile)
                    startActivity(intent)
                    finish()
                    //Animation
                    overridePendingTransition(0, R.anim.slide_out_right)
                    btRegister.isClickable = false
                    saveAutologin()
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

            val emailCheck = etEnterEmail.text.toString()
            when {
                emailCheck.isEmpty() -> {
                    tilEmail.error = getString(R.string.message_cannot_be_empty)
                    etEnterEmail.requestFocus()
                    return false
                }
                !Validators.isValidEmail(emailCheck) -> {
                    tilEmail.error = getString(R.string.message_wromg_e_mail)
                    etEnterEmail.requestFocus()
                    return false
                }
                else -> {
                    tilEmail.isErrorEnabled = false
                    return true
                }
            }

        }
    }

    /**
     * Checking validate password
     */
    private fun isValidatePassword(): Boolean {
        binding.apply {
            val passwordChek = binding.etTextPassword.text.toString()
            Validators.validatePassword(passwordChek)
            return if (Validators.validatePassword(passwordChek) != 0) {
                tilPassword.error =
                    getString(Validators.validatePassword(passwordChek))
                etTextPassword.requestFocus()
                false
            } else {
                tilPassword.isErrorEnabled = false
                true
            }
        }
    }

    /**
     * Saving in SharedPreference autologin
     */
    private fun saveAutologin() {
        binding.apply {
            shPref.save(REMEMBER_SP, cbRemember.isChecked)
            shPref.save(NAME_SP, etEnterEmail.text.toString())
            shPref.save(PASSWORD_SP, etTextPassword.text.toString())
        }
    }

    /**
     * Loading  SharedPreference autologin
     */
    private fun loadAutologin() {
        val remember: Boolean = shPref.getBoolean(REMEMBER_SP, false)
        if (remember) {
            binding.apply {
                cbRemember.isChecked = true
                etEnterEmail.setText(shPref.getString(NAME_SP, ""))
                etTextPassword.setText(shPref.getString(PASSWORD_SP, ""))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
       if(!binding.cbRemember.isChecked) {
           saveAutologin()
       }
    }


    ////////////////////////////////test method////////////////////////////////////////
    private fun forTestMethod() {
        binding.apply {
            binding.tvSignIn.setOnClickListener {
                etEnterEmail.setText(TEST_EMAIL, TextView.BufferType.EDITABLE)
                etTextPassword.setText(TEST_PASSWORD, TextView.BufferType.EDITABLE)
            }

            //Handle pressing the "SignIn" google:

            btGoogle.setOnClickListener {
                val intent = Intent(this@MainActivity, ProfileActivity::class.java)
                intent.putExtra(NAME_EXTRA, "serhii.sierykh@gmail.com")
                intent.putExtra(PHOTO_EXTRA, R.mipmap.ic_kot)
                startActivity(intent)
                //Animation
                overridePendingTransition(0, R.anim.slide_out_right)
            }
        }
    }
    //////////////////////////////////////////////////////////////////////////////////
}