package com.shpp.ssierykh.assignment1.ui.activity_old

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shpp.ssierykh.assignment1.R
import com.shpp.ssierykh.assignment1.utils.Constants.CAREER_EXTRA
import com.shpp.ssierykh.assignment1.utils.Constants.HOME_ADDRESS_EXTRA
import com.shpp.ssierykh.assignment1.utils.Constants.EMAIL_EXTRA
import com.shpp.ssierykh.assignment1.utils.Constants.PHOTO_EXTRA
import com.shpp.ssierykh.assignment1.databinding.ActivityMyProfileBinding
import com.shpp.ssierykh.assignment1.ui.activity_old.contacts.MyContactsActivity
import com.shpp.ssierykh.assignment1.utils.ParsingEmailToName.parsingEmailToName
import com.shpp.ssierykh.assignment1.utils.extensions.loadImageGlade


class MyProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMyProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val career = intent.extras?.getString(CAREER_EXTRA) ?: ""
        val homeAddress = intent.extras?.getString(HOME_ADDRESS_EXTRA) ?: ""
        val email = intent.extras?.getString(EMAIL_EXTRA) ?: ""
        val loadImage = intent.extras?.getInt(PHOTO_EXTRA) ?: R.drawable.ic_mok
        setView(career, homeAddress, email, loadImage )
        goMyContacts()
        forTestMethod()
    }

    private fun setView(career: String, homeAddress: String, email: String, loadImage: Int) {
        binding.apply {
            ivPhotoProfile.loadImageGlade(loadImage)
            tvName.text = parsingEmailToName(email)
            tvCareer.text = career
            tvHomeAddress.text = homeAddress
        }
    }


    private fun goMyContacts() {
        binding.btViewMyContacts.setOnClickListener {
            val intent = Intent(this, MyContactsActivity::class.java)
            startActivity(intent)
            //Animation

            overridePendingTransition(0, R.anim.slide_out)

        }
    }

    //Switching to another screen////////////////////////////delete---------------------------
    private fun forTestMethod() {
        binding.btEditProfile.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            //Animation
            finish()
            overridePendingTransition(0, R.anim.slide_out)

        }
    }
}


