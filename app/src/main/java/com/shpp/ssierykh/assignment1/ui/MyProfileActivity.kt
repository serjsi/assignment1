package com.shpp.ssierykh.assignment1.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.shpp.ssierykh.assignment1.R
import com.shpp.ssierykh.assignment1.utils.Constants.CAREER_EXTRA
import com.shpp.ssierykh.assignment1.utils.Constants.HOME_ADDRESS_EXTRA
import com.shpp.ssierykh.assignment1.utils.Constants.NAME_EXTRA
import com.shpp.ssierykh.assignment1.utils.Constants.PHOTO_EXTRA
import com.shpp.ssierykh.assignment1.databinding.ActivityMyProfileBinding
import com.shpp.ssierykh.assignment1.ui.contacts.MyContactsActivity
import com.shpp.ssierykh.assignment1.utils.ParsingEmailToName.parsingEmailToName
import com.shpp.ssierykh.assignment1.utils.extensions.loadImageGlade


class MyProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMyProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setView()
        goMyContacts()
        forTestMethod()
    }

    private fun setView() {
        val career = intent.extras?.get(CAREER_EXTRA)
        val homeAddress = intent.extras?.get(HOME_ADDRESS_EXTRA)
        binding.apply {
            val loadImage  = intent.extras?.getInt(PHOTO_EXTRA)
            ivPhotoProfile.loadImageGlade(loadImage)
            tvName.text = parsingEmailToName(intent.extras?.get(NAME_EXTRA).toString())
            if (career != null) tvCareer.text = career.toString()
            if (homeAddress != null) tvHomeAddress.text = homeAddress.toString()

        }
    }


    private fun goMyContacts() {
        binding.btViewMyContacts.setOnClickListener {
            val intent = Intent(this, MyContactsActivity::class.java)
            startActivity(intent)
            //Animation

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


