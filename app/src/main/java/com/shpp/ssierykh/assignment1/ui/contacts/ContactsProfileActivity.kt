package com.shpp.ssierykh.assignment1.ui.contacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.shpp.ssierykh.assignment1.R
import com.shpp.ssierykh.assignment1.utils.Constants.CAREER_EXTRA
import com.shpp.ssierykh.assignment1.utils.Constants.HOME_ADDRESS_EXTRA
import com.shpp.ssierykh.assignment1.utils.Constants.NAME_EXTRA
import com.shpp.ssierykh.assignment1.utils.Constants.PHOTO_EXTRA
import com.shpp.ssierykh.assignment1.databinding.ActivityContactsProfileBinding

import java.util.*


class ContactsProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactsProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityContactsProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nameChange = intent.extras?.get(NAME_EXTRA).toString()
        val career = intent.extras?.get(CAREER_EXTRA).toString()
        val homeAddress = intent.extras?.get(HOME_ADDRESS_EXTRA).toString()
        setView(nameChange, career, homeAddress)

        binding.ivArrowBack.setOnClickListener {
            finish()
            overridePendingTransition(0, R.anim.slide_out_left)}
    }

    private fun setView(nameChange: String, career: String, homeAddress: String) {
        binding.apply {
            tvName.text = nameParsing(nameChange)
            Glide
                .with(ivPhotoProfile)
                .load(intent.extras?.get(PHOTO_EXTRA))
                .circleCrop()
                .into(ivPhotoProfile)
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
}