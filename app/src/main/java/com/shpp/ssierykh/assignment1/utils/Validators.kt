package com.shpp.ssierykh.assignment1.utils

import com.google.android.material.textfield.TextInputEditText
import com.shpp.ssierykh.assignment1.R

object Validators {



  fun isValidatePassword(password : TextInputEditText): Boolean {
            return messageValidationPassword(password) == R.string.stop_validation
  }


    /**
     * Сhecking validate password
     *  @param password input password
     * @return stop_validation if matches with else push
     */
    fun messageValidationPassword(password : TextInputEditText): Int {
        val passwordChek = password.text.toString()
        when {
            //checking is empty
            passwordChek.isEmpty() -> {
                return R.string.message_cannot_be_empty
            }
            //checking is string min length
            passwordChek.length < Constants.MIN_LENGTH_PASSWORD -> {
                return R.string.password_cant_be_less_than
            }
            //checking is string contain any number
            !passwordChek.contains(Regex("""\d""")) -> {
                return R.string.required_at_least_1_digit
            }
            //checking is string contain lower case letters"
            !passwordChek.contains(Regex("""[a-z]""")) -> {
                return R.string.password_must_contain_lower_case_letters

            }
            //checking is string contain lower upper case letters"
            !passwordChek.contains(Regex("""[A-Z]""")) -> {
                return R.string.password_must_contain_upper_case_letters
            }
            //checking is string contain any special character
            !passwordChek.contains(Regex("""[^a-zA-Z0-9]""")) -> {
                return  R.string._1_special_character_required
            }
        }

        return R.string.stop_validation
    }



    /**
     * checking pattern of email
     * @param emailIn input email
     * @return true if matches with email address else false
     */
    fun isValidateEmail(emailIn: TextInputEditText): Boolean {
        val email = emailIn.text.toString()
        return email.contains(
            Regex(
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                        "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
            )
        )
    }
}