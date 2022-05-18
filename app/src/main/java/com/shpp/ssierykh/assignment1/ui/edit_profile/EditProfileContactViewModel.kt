package com.shpp.ssierykh.assignment1.ui.edit_profile

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.shpp.ssierykh.assignment1.data.BaseContacts
import com.shpp.ssierykh.assignment1.model.Contact
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class EditProfileContactViewModel(
    private val baseContacts: BaseContacts
) : ViewModel() {

    private var isEditProfile: Boolean = false

    //StateFlow
    private val _profileContact = MutableStateFlow(Contact())
    val profilContact: StateFlow<Contact> = _profileContact.asStateFlow()


    fun loadContact(emailID: String?) {
        isEditProfile = true
        emailID?.let {
            _profileContact.value = baseContacts.getContactForEmail(emailID)!!
        }
    }

    fun setContact(contact: Contact) {
        if (isEditProfile) {
            baseContacts.setContact(contact)
        } else baseContacts.addContact(contact)
    }


    fun setPhotoProfile(photoUri: String) {
        val data = _profileContact.value
        _profileContact.value = Contact(data.email, photoUri, data.name, data.career, data.home)

    }

    fun getVisible(): Boolean {
        return isEditProfile
    }

    fun takePhoto(): String {
            return _profileContact.value.photoAddress

    }
}
