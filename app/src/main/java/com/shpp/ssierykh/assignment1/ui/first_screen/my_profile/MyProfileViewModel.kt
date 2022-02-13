package com.shpp.ssierykh.assignment1.ui.first_screen.my_profile


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.ViewModel
import com.shpp.ssierykh.assignment1.data.Contact


class MyProfileViewModel : ViewModel() {
    //TODO For test, later delete
    init {
        Log.i("MyProfileViewModel", "MyProfileViewModel created!")

    }

    override fun onCleared() {
        super.onCleared()
        Log.i("MyProfileViewModel", "MyProfileViewModel destroyed!")
    }



    private val _profileResource = MutableLiveData<Contact>()
    val profilContact: LiveData<Contact> get() = _profileResource
    fun setContact(profilContact: Contact) {
        _profileResource.value = profilContact
    }
}

