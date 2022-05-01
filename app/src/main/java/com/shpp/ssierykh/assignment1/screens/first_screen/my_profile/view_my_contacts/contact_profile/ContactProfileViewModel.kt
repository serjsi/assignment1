package com.shpp.ssierykh.assignment1.screens.first_screen.my_profile.view_my_contacts.contact_profile


import androidx.lifecycle.ViewModel
import com.shpp.ssierykh.assignment1.model.BaseContacts
import com.shpp.ssierykh.assignment1.model.Contact
import com.shpp.ssierykh.assignment1.utils.ParsingEmailToName.parsingEmailToName
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class ContactProfileViewModel(
    private val baseContacts: BaseContacts
) : ViewModel() {

    //StateFlow
    private val _profileContact = MutableStateFlow(Contact())
    val profilContact: StateFlow<Contact> = _profileContact.asStateFlow()




    fun loadUser(emailID: String?) {
        _profileContact.value = baseContacts.getContacts().firstOrNull { it.email == emailID }!!

    }


}

