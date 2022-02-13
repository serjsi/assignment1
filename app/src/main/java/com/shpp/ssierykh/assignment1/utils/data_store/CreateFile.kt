package com.shpp.ssierykh.assignment1.utils.data_store

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.shpp.ssierykh.assignment1.utils.Constants

val Context.dataStore by preferencesDataStore(
    name = Constants.USER_PREFERENCES_NAME
)