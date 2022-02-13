package com.shpp.ssierykh.assignment1.utils.data_store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.shpp.ssierykh.assignment1.utils.Constants.USER_PREFERENCES_NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map




/*private object PreferencesKeys {
    val SORT_ORDER = stringPreferencesKey("sort_order")
    val SHOW_COMPLETED = booleanPreferencesKey("show_completed")
}*/
class SaveLoginDataStore(private val dataStore: DataStore<Preferences>) {

    //Create some keys
    companion object {
        val USER_EMAIL_KEY = stringPreferencesKey("USER_EMAIL")
        val USER_PASSWORD_KEY = stringPreferencesKey("USER_PASSWORD")
        val USER_REMEMBER_KEY = booleanPreferencesKey("USER_REMEMBER_KEY")
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