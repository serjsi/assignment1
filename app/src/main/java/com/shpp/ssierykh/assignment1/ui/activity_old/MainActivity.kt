package com.shpp.ssierykh.assignment1.ui.activity_old


import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.shpp.ssierykh.assignment1.R
import com.shpp.ssierykh.assignment1.utils.Constants.EMAIL_EXTRA
import com.shpp.ssierykh.assignment1.utils.Constants.NAME_SP
import com.shpp.ssierykh.assignment1.utils.Constants.PASSWORD_SP
import com.shpp.ssierykh.assignment1.utils.Constants.PHOTO_EXTRA
import com.shpp.ssierykh.assignment1.utils.Constants.REMEMBER_SP
import com.shpp.ssierykh.assignment1.utils.Constants.TEST_EMAIL
import com.shpp.ssierykh.assignment1.utils.Constants.TEST_PASSWORD
import com.shpp.ssierykh.assignment1.data.PreferenceStorage
import com.shpp.ssierykh.assignment1.databinding.ActivityMainBinding
import com.shpp.ssierykh.assignment1.utils.Validators.isValidateEmail
import com.shpp.ssierykh.assignment1.utils.Validators.messageValidationPassword
import com.shpp.ssierykh.assignment1.utils.extensions.clickWithDebounce


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var shPref: PreferenceStorage = PreferenceStorage(this)

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
            etEmail.doOnTextChanged { _, _, _, _ -> isValidateEmail() }
            etPassword.doOnTextChanged { _, _, _, _ -> isValidatePassword() }
            //Switching to another screen
            btRegister.setOnClickListener {
                if (isValidateEmail() && isValidatePassword()) {
                    //Stores the values
                    val intent = Intent(this@MainActivity, MyProfileActivity::class.java)
                    intent.putExtra(EMAIL_EXTRA, etEmail.text.toString())
                    intent.putExtra(PHOTO_EXTRA, R.drawable.lucile)
                    startActivity(intent)
                    finish()
                    //Animation
                    overridePendingTransition(0, R.anim.slide_in)
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

            val emailCheck = etEmail.text.toString()
            when {
                emailCheck.isEmpty() -> {
                    tilEmail.error = getString(R.string.message_cannot_be_empty)
                    etEmail.requestFocus()
                    return false
                }
                !isValidateEmail(etEmail) -> {
                    tilEmail.error = getString(R.string.message_wromg_e_mail)
                    etEmail.requestFocus()
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

            //validatePassword(passwordChek)
            return if (messageValidationPassword(etPassword) !=  R.string.stop_validation) {
                tilPassword.error =
                    getString(messageValidationPassword(etPassword))
                etPassword.requestFocus()
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
            shPref.save(NAME_SP, etEmail.text.toString())
            shPref.save(PASSWORD_SP, etPassword.text.toString())
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
                etEmail.setText(shPref.getString(NAME_SP, ""))
                etPassword.setText(shPref.getString(PASSWORD_SP, ""))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!binding.cbRemember.isChecked) {
            saveAutologin()
        }
    }


    ////////////////////////////////test method////////////////////////////////////////
    private fun forTestMethod() {
        binding.apply {
            tvSignIn.clickWithDebounce {
                etEmail.setText(TEST_EMAIL, TextView.BufferType.EDITABLE)
                etPassword.setText(TEST_PASSWORD, TextView.BufferType.EDITABLE)
            }

            //Handle pressing the "SignIn" google:

            binding.cvGoogle.clickWithDebounce {
                val intent = Intent(this@MainActivity, MyProfileActivity::class.java)
                intent.putExtra(EMAIL_EXTRA, getString(R.string.fake_mail))
                intent.putExtra(PHOTO_EXTRA, R.mipmap.ic_kot_round)
                startActivity(intent)
                //Animation
                finish()
                overridePendingTransition(0, R.anim.slide_in)
            }
        }
    }
    //////////////////////////////////////////////////////////////////////////////////
}