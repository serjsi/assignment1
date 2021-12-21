package com.shpp.ssierykh.assignment1.extensions

import com.shpp.ssierykh.assignment1.Constants
import com.shpp.ssierykh.assignment1.R

object Validators {

    /**
     * Сhecking validate password
     * @return 0 if matches with else push
     */
    fun String.validatePassword(): Int {
        when {
            //checking is empty
            this.isEmpty() -> {
                return R.string.message_cannot_be_empty
            }
            //checking is string min length
            this.length < Constants.MIN_LENGTH_PASSWORD -> {
                return R.string.password_cant_be_less_than
            }
            //checking is string contain any number
            !this.contains(Regex("""\d""")) -> {
                return R.string.required_at_least_1_digit
            }
            //checking is string contain lower case letters"
            !this.contains(Regex("""[a-z]""")) -> {
                return R.string.password_must_contain_lower_case_letters

            }
            //checking is string contain lower upper case letters"
            !this.contains(Regex("""[A-Z]""")) -> {
                return R.string.password_must_contain_upper_case_letters
            }
            //checking is string contain any special character
            !this.contains(Regex("""[^a-zA-Z0-9]""")) -> {
                return R.string._1_special_character_required
            }
        }

        return 0
    }

    /**
     * checking pattern of email
     * @return true if matches with email address else false
     */
    fun String.isValidEmail(): Boolean {
        return this.contains(
            Regex(
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                        "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
            )
        )
    }
}