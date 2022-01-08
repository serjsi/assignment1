package com.shpp.ssierykh.assignment1.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shpp.ssierykh.assignment1.R
import com.shpp.ssierykh.assignment1.utils.Constants.CAREER_EXTRA
import com.shpp.ssierykh.assignment1.utils.Constants.HOME_ADDRESS_EXTRA
import com.shpp.ssierykh.assignment1.utils.Constants.NAME_EXTRA
import com.shpp.ssierykh.assignment1.utils.Constants.PHOTO_EXTRA
import com.shpp.ssierykh.assignment1.databinding.ActivityMyProfileBinding
import com.shpp.ssierykh.assignment1.utils.extensions.loadImageGlade


import java.util.*


class MyProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMyProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nameChange = intent.extras?.get(NAME_EXTRA).toString()
        val career = intent.extras?.get(CAREER_EXTRA).toString()
        val homeAddress = intent.extras?.get(HOME_ADDRESS_EXTRA).toString()
        setView(nameChange, career, homeAddress)
        goMyContacts()
        forTestMethod()
    }

    private fun setView(nameChange: String, career: String, homeAddress: String) {
        binding.apply {
            val loadImage = intent.extras?.get(PHOTO_EXTRA)
            ivPhotoProfile.loadImageGlade(loadImage)
            tvName.text = nameParsing(nameChange)
            if (career != "null") tvCareer.text = career
            if (homeAddress != "null") tvHomeAddress.text = homeAddress

        }
    }


    //Parsing E-mail to Name and Surname
    private fun nameParsing(string: String): String {
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

    private fun goMyContacts() {
        binding.btViewMyContacts.setOnClickListener {
            val intent = Intent(this, MyContactsActivity::class.java)
            startActivity(intent)
            //Animation
            //   finish()
            overridePendingTransition(0, R.anim.slide_out_left)

        }
    }

    //Switching to another screen////////////////////////////delete---------------------------
    private fun forTestMethod() {
        binding.btEditProfile.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            //Animation
            finish()
            overridePendingTransition(0, R.anim.slide_out_left)

        }
    }
}