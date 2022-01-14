package com.shpp.ssierykh.assignment1.utils

import java.util.*

object ParsingEmailToName {
    //Parsing E-mail to Name and Surname
     fun parsingEmailToName(string: String): String {
        val name: String?
        val surname: String?
        //Possibility check parsing
        when {
            string.indexOf(".") > -1 && string.indexOf(".") < string.indexOf("@") -> {
                val parts = string.split(".", limit = 2)
                name = parts[0].substring(0, 1).uppercase(Locale.getDefault()) +
                        parts[0].substring(1)
                surname = parts[1].substring(0, 1).uppercase(Locale.getDefault()) +
                        parts[1].substring(1, parts[1].indexOf("@"))
                return "$name $surname"
            }
            string.indexOf("@") > -1 -> {
                return string.substring(0, 1).uppercase(Locale.getDefault()) +
                        string.substring(1, string.indexOf("@"))
            }
        }
        return string
    }
}