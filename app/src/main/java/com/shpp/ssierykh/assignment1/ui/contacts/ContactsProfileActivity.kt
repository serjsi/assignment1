package com.shpp.ssierykh.assignment1.ui.contacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shpp.ssierykh.assignment1.R
import com.shpp.ssierykh.assignment1.utils.Constants.CAREER_EXTRA
import com.shpp.ssierykh.assignment1.utils.Constants.HOME_ADDRESS_EXTRA
import com.shpp.ssierykh.assignment1.utils.Constants.NAME_EXTRA
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

        setView()
        clickListener()
    }

    private fun clickListener() {
        binding.ivArrowBack.setOnClickListener {
            finish()
            overridePendingTransition(0, R.anim.slide_out_left)
        }
    }

    private fun setView() {
        val career = intent.extras?.get(CAREER_EXTRA)
        val homeAddress = intent.extras?.get(HOME_ADDRESS_EXTRA)
        binding.apply {
            val loadImage = intent.extras?.get(PHOTO_EXTRA).toString()
            ivPhotoProfile.loadImageGlade(loadImage)
            tvName.text = parsingEmailToName(intent.extras?.get(NAME_EXTRA).toString())
            if (career != null) tvCareer.text = career.toString()
            if (homeAddress != null) tvHomeAddress.text = homeAddress.toString()

        }
    }

}