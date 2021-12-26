package com.shpp.ssierykh.assignment1.utils

import com.shpp.ssierykh.assignment1.utils.Constants
import com.shpp.ssierykh.assignment1.R

object Validators {

    /**
     * Сhecking validate password
     * @param passwordChek input password
     * @return 0 if matches with else push
     */
    fun validatePassword(passwordChek: String): Int {
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

        return 0
    }



    /**
     * checking pattern of email
     * @param email input email
     * @return true if matches with email address else false
     */
    fun isValidEmail(email: String): Boolean {
        return email.contains(
            Regex(
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                        "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
            )
        )
    }
}