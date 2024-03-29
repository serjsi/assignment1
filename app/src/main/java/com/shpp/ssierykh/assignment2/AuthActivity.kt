package com.shpp.ssierykh.assignment2


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import com.shpp.ssierykh.assignment2.Constants.TEST_EMAIL
import com.shpp.ssierykh.assignment2.Constants.TEST_PASSWORD
import androidx.databinding.DataBindingUtil
import com.shpp.ssierykh.assignment2.Constants.MIN_LENGTH_PASSWORD
import com.shpp.ssierykh.assignment2.databinding.ActivityAuthBinding


class AuthActivity : AppCompatActivity() {

    //Announce the use of the following objects

    lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
            if ( validateEmail() && validatePassword()  ) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("name", binding.editTextEnterEmail.text.toString())
                intent.putExtra("myPhoto", R.drawable.lucile)
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
        when {
            //checking is empty
            passwordChek.isEmpty() -> {
                binding.textInputLayoutPassword.error = getString(R.string.message_cannot_be_empty)
                binding.editTextTextPassword.requestFocus()
                return false
            }//checking is string min length
            passwordChek.length < MIN_LENGTH_PASSWORD -> {
                binding.textInputLayoutPassword.error =
                    getString(R.string.password_cant_be_less_than)+ MIN_LENGTH_PASSWORD
                binding.editTextTextPassword.requestFocus()
                return false
            }//checking is string contain any number
            !passwordChek.contains(Regex("""\d""")) -> {
                binding.textInputLayoutPassword.error = getString(R.string.required_at_least_1_digit)
                binding.editTextTextPassword.requestFocus()
                return false
            }//checking is string contain lower case letters"
            !passwordChek.contains(Regex("""[a-z]""")) -> {
                binding.textInputLayoutPassword.error =
                    getString(R.string.password_must_contain_lower_case_letters)
                binding.editTextTextPassword.requestFocus()
                return false
            }//checking is string contain lower upper case letters"
            !passwordChek.contains(Regex("""[A-Z]""")) -> {
                binding.textInputLayoutPassword.error =
                    getString(R.string.password_must_contain_upper_case_letters)
                binding.editTextTextPassword.requestFocus()
                return false
            }//checking is string contain any special character
            !passwordChek.contains(Regex("""[^a-zA-Z0-9]""")) -> {
                binding.textInputLayoutPassword.error = getString(R.string._1_special_character_required)
                binding.editTextTextPassword.requestFocus()
                return false
            }
            else -> {
                binding.textInputLayoutPassword.isErrorEnabled = false
            }
        }

        return true
    }


    /**
     * Сhecking validate E-mail
     */
    private fun validateEmail(): Boolean {
        if (binding.editTextEnterEmail.text.toString().trim().isEmpty()) {
            binding.textInputLayoutEmail.error = getString(R.string.message_cannot_be_empty)
            binding.editTextEnterEmail.requestFocus()
            return false
            //} else if (!isValidEmail(binding.editTextEnterEmail.text.toString())) {
        } else if (!binding.editTextEnterEmail.text.toString().contains(
                Regex(
                    "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
                )
            )
        ) {
            binding.textInputLayoutEmail.error = getString(R.string.message_wromg_e_mail)
            binding.editTextEnterEmail.requestFocus()
            return false
        } else {
            binding.textInputLayoutEmail.isErrorEnabled = false
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


}




