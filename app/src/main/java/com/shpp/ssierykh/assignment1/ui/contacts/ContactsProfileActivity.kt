package com.shpp.ssierykh.assignment1.ui.contacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shpp.ssierykh.assignment1.R
import com.shpp.ssierykh.assignment1.utils.Constants.CAREER_EXTRA
import com.shpp.ssierykh.assignment1.utils.Constants.HOME_ADDRESS_EXTRA
import com.shpp.ssierykh.assignment1.utils.Constants.EMAIL_EXTRA
import com.shpp.ssierykh.assignment1.utils.Constants.PHOTO_EXTRA
import com.shpp.ssierykh.assignment1.databinding.ActivityContactsProfileBinding
import com.shpp.ssierykh.assignment1.utils.ParsingEmailToName.parsingEmailToName
import com.shpp.ssierykh.assignment1.utils.extensions.loadImageGlade


class ContactsProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactsProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityContactsProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val career = intent.extras?.getString(CAREER_EXTRA) ?: ""
        val homeAddress = intent.extras?.getString(HOME_ADDRESS_EXTRA) ?: ""
        val email = intent.extras?.getString(EMAIL_EXTRA) ?: ""
        val loadImage = intent.extras?.getString(PHOTO_EXTRA) ?: R.drawable.ic_mok

        setView(career, homeAddress, email, loadImage as String)
        clickListener()
    }

    private fun clickListener() {
        binding.ivArrowBack.setOnClickListener {
            finish()
            overridePendingTransition(0, R.anim.slide_out)
        }
    }


    private fun setView(career: String, homeAddress: String, email: String, loadImage: String) {
        binding.apply {
            ivPhotoProfile.loadImageGlade(loadImage)
            tvName.text = parsingEmailToName(email)
            tvCareer.text = career
            tvHomeAddress.text = homeAddress
        }
    }

}