package com.shpp.ssierykh.assignment1.utils

import android.app.Application
import com.shpp.ssierykh.assignment1.data.BaseContacts


import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
val baseContacts = BaseContacts()

}

