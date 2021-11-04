package com.shpp.ssierykh.assignment1

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class UserManager(val dataStore: DataStore<Preferences>) {

    //Create some keys
    companion object {
        val USER_EMAIL_KEY = stringPreferencesKey("USER_EMAIL")
        val USER_PASSWORD_KEY = stringPreferencesKey("USER_PASSWORD")
        val USER_REMEMBER_KEY = booleanPreferencesKey("USER_REMEMBER")
    }

    //Store user data
    suspend fun storeUser(email: String, password: String, remember: Boolean) {
        dataStore.edit {
            it[USER_EMAIL_KEY] = email
            it[USER_PASSWORD_KEY] = password
            it[USER_REMEMBER_KEY] = remember

        }
    }


    //Create a email flow
    val userEmailFlow: Flow<String?> = dataStore.data.map {
        it[USER_EMAIL_KEY]
    }

    //Create a password flow
    val userPasswordFlow: Flow<String?> = dataStore.data.map {
        it[USER_PASSWORD_KEY]
    }

    //Create a remember flow
    val userRememberFlow: Flow<Boolean?> = dataStore.data.map {
        it[USER_REMEMBER_KEY]
    }

}
