package com.shpp.ssierykh.assignment1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.shpp.ssierykh.assignment1.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        val nameChange = intent.extras?.get("name").toString()
        binding.textViewName.text = nameParsing(nameChange)


//        val photo: ImageView = findViewById(R.id.imageViewPhotoProfile)
//        photo.setImageResource(intent.extras?.get("myPhoto") as Int)
        binding.imageViewPhotoProfile.setImageResource(intent.extras?.get("myPhoto") as Int)


        //Switching to another screen////////////////////////////delete---------------------------
       // editProfile = findViewById<Button>(R.id.buttonEditProfile)
        binding.buttonEditProfile.setOnClickListener {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
            //Animation
            overridePendingTransition(0,R.anim.slide_out_left)

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

}




