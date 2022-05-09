package com.shpp.ssierykh.assignment1.ui.sign

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shpp.ssierykh.assignment1.data.preferences.DataStoreRepository
import com.shpp.ssierykh.assignment1.utils.Constants.NAME_SP
import com.shpp.ssierykh.assignment1.utils.Constants.PASSWORD_SP
import com.shpp.ssierykh.assignment1.utils.Constants.REMEMBER_SP
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class SignViewModel @Inject constructor(
    private val repository: DataStoreRepository) : ViewModel() {

    fun saveEmail(value: String) {
        viewModelScope.launch {
            repository.putString(NAME_SP, value)
        }
    }

    fun getEmail(): String? = runBlocking {
        repository.getString(NAME_SP)
    }

    fun savePassword(value: String) {
        viewModelScope.launch {
            repository.putString(PASSWORD_SP, value)
        }
    }

    fun getPassword(): String? = runBlocking {
        repository.getString(PASSWORD_SP)
    }

    fun saveRemember(value: Boolean) {
        viewModelScope.launch {
            repository.putBoolean(REMEMBER_SP, value)
        }
    }

    fun getRemember(): Boolean? = runBlocking {
        repository.getBoolean(REMEMBER_SP)
    }

}
