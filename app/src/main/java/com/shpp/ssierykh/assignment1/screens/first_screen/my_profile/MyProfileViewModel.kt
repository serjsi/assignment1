package com.shpp.ssierykh.assignment1.screens.first_screen.my_profile


import androidx.lifecycle.ViewModel
import com.shpp.ssierykh.assignment1.model.Contact
import com.shpp.ssierykh.assignment1.utils.ParsingEmailToName.parsingEmailToName
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class MyProfileViewModel : ViewModel() {

    //LiveData
/*    private val _profileResource = MutableLiveData<Contact>()
    val profilContact: LiveData<Contact> get() = _profileResource*/

    //StateFlow
    private val _profileResource = MutableStateFlow(Contact())
    val profilContact: StateFlow<Contact> = _profileResource.asStateFlow()


    fun setContact(profilContact: Contact) {
        if (profilContact.name == "") {
            val email = parsingEmailToName(profilContact.email)
            profilContact.name = email
        }
        _profileResource.value = profilContact
    }


}

