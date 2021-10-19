package com.shpp.ssierykh.assignment2

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import java.util.*


class MainActivity : AppCompatActivity() {


    //Announce the use of the following objects

    private var editProfile: Button? = null



    //private var name: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editProfile = findViewById<Button>(R.id.buttonEditProfile)


        val name: TextView = findViewById(R.id.textViewName)
        val nameChange = intent.extras?.get("name").toString()
        name.text = nameParsing(nameChange)


        //Switching to another screen////////////////////////////delet
        editProfile?.setOnClickListener {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
        }

    }

    //Parsing E-mail to Name and Surname
    private fun nameParsing(string: String): String? {
        var name: String? = null
        var surname: String? = null
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


