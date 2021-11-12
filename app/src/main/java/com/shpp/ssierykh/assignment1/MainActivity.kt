package com.shpp.ssierykh.assignment1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shpp.ssierykh.assignment1.Constants.NAME_EXTRA
import com.shpp.ssierykh.assignment1.Constants.PHOTO_EXTRA
import com.shpp.ssierykh.assignment1.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nameChange = intent.extras?.get(NAME_EXTRA).toString()
        setView(nameChange)
        forTestMethod()
    }

    private fun setView(nameChange: String) {
        binding.apply {
            textViewName.text = nameParsing(nameChange)
            imageViewPhotoProfile.setImageResource(intent.extras?.get(PHOTO_EXTRA) as Int)
        }
    }


    //Parsing E-mail to Name and Surname
    private fun nameParsing(string: String): String {
        val name: String?
        val surname: String?
        //Possibility check parsing
        if (string.indexOf(".") > -1 && string.indexOf(".") < string.indexOf("@")) {
            val parts = string.split(".", limit = 2)
            name = parts[0].substring(0, 1).uppercase(Locale.getDefault()) +
                    parts[0].substring(1)
            surname = parts[1].substring(0, 1).uppercase(Locale.getDefault()) +
                    parts[1].substring(1, parts[1].indexOf("@"))
        } else {
            return string.substring(0, 1).uppercase(Locale.getDefault()) +
                    string.substring(1, string.indexOf("@"))
        }
        return "$name $surname"
    }

    //Switching to another screen////////////////////////////delete---------------------------
    private fun forTestMethod() {
        binding.buttonEditProfile.setOnClickListener {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
            //Animation
            finish()
            overridePendingTransition(0, R.anim.slide_out_left)

        }
    }
}