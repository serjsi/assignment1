package com.shpp.ssierykh.assignment1.screens.first_screen

import android.app.Application
import com.shpp.ssierykh.assignment1.model.BaseContacts

import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
val baseContacts = BaseContacts()

}

