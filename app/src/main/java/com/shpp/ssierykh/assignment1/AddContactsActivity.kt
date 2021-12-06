package com.shpp.ssierykh.assignment1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.shpp.ssierykh.assignment1.databinding.ActivityAddContactProfileBinding

import java.util.*


class AddContactsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddContactProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddContactProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivArrowBack.setOnClickListener { finish() }

    }
}