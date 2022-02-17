package com.shpp.ssierykh.assignment1.ui.first_screen.my_profile


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shpp.ssierykh.assignment1.data.Contact
import com.shpp.ssierykh.assignment1.data.preferences.DataStoreRepository
import com.shpp.ssierykh.assignment1.utils.Constants
import com.shpp.ssierykh.assignment1.utils.ParsingEmailToName.parsingEmailToName
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


class MyProfileViewModel : ViewModel() {


    private val _profileResource = MutableLiveData<Contact>()
    val profilContact: LiveData<Contact> get() = _profileResource
    fun setContact(profilContact: Contact) {
        if(profilContact.name == ""){
           val email = parsingEmailToName(profilContact.email)
            profilContact.name = email
        }
        _profileResource.value = profilContact
    }


}

