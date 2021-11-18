package com.shpp.ssierykh.assignment1


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.content.Intent
import android.content.SharedPreferences
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.asLiveData
import com.shpp.ssierykh.assignment1.Constants.NAME_EXTRA
import com.shpp.ssierykh.assignment1.Constants.NAME_SP
import com.shpp.ssierykh.assignment1.Constants.PASSWORD_SP
import com.shpp.ssierykh.assignment1.Constants.PHOTO_EXTRA
import com.shpp.ssierykh.assignment1.Constants.REMEMBER_SP
import com.shpp.ssierykh.assignment1.Constants.TEST_EMAIL
import com.shpp.ssierykh.assignment1.Constants.TEST_PASSWORD
import com.shpp.ssierykh.assignment1.databinding.ActivityMainBinding
import kotlinx.coroutines.DelicateCoroutinesApi


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var shPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Get reference to our userManager class


        setupListeners()

        loadAutologin()
        forTestMethod()
    }

    /**
     * Applying text watcher on each text field
     */
    @DelicateCoroutinesApi
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
            if (Validators.validatePassword(passwordChek) != 0) {
                tilPassword.error =
                    getString(Validators.validatePassword(passwordChek))
                etTextPassword.requestFocus()
                return false
            } else {
                tilPassword.isErrorEnabled = false
                return true
            }
        }
    }

    /**
     * Saving in SharedPreference autologin
     */
    private fun saveAutologin() {
        shPref = getSharedPreferences("PrefAutologin", MODE_PRIVATE)
        val ed: SharedPreferences.Editor = shPref.edit()
        ed.putBoolean(REMEMBER_SP, binding.cbRemember.isChecked)
        ed.putString(NAME_SP, binding.etEnterEmail.text.toString())
        ed.putString(PASSWORD_SP, binding.etTextPassword.text.toString())
        ed.commit()
    }

    /**
     * Loading  SharedPreference autologin
     */
    private fun loadAutologin() {
        shPref = getSharedPreferences("PrefAutologin", MODE_PRIVATE)
        val remember: Boolean = shPref.getBoolean(REMEMBER_SP, false)
        if (remember) {
            binding.cbRemember.isChecked = true
            binding.etEnterEmail.setText(shPref.getString(NAME_SP, ""))
            binding.etTextPassword.setText(shPref.getString(PASSWORD_SP, ""))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        saveAutologin()
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
                intent.putExtra(PHOTO_EXTRA, R.drawable.kot_ochki)
                startActivity(intent)
                //Animation
                overridePendingTransition(0, R.anim.slide_out_right)
            }
        }
    }
    //////////////////////////////////////////////////////////////////////////////////
}