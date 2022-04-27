package com.shpp.ssierykh.assignment1.ui.first_screen.my_profile.view_my_contacts
///https://medium.com/@vgoyal_1/datastore-android-how-to-use-it-like-a-pro-using-kotlin-2c2440683d78
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shpp.ssierykh.assignment1.model.BaseContacts
import com.shpp.ssierykh.assignment1.model.Contact
import com.shpp.ssierykh.assignment1.model.ContactListener

data class UserListItem(
    val contact: Contact
)

class MyContactsViewModel(
    private val baseContacts: BaseContacts
) : ViewModel(), ContactClickListener {

    /*    private var _contacts = MutableLiveData<List<UserListItem>>()
        val contacts: LiveData<List<UserListItem>> = _contacts  */
    private var _contacts = MutableLiveData<List<Contact>>()
    val contacts: LiveData<List<Contact>> = _contacts


    override fun onContactDetails(contact: Contact) {
        Log.i("MyContactsViewModel", "onUserDetails ${contact.email}")
    }


    override fun onContactDelete(contact: Contact) {
        Log.i("MyContactsViewModel", "onUserDelete ${contact.email}")
        baseContacts.deleteContact(contact)
    }

    private val listener: ContactListener = {
        baseContacts.getContacts()
    }

    init {
        baseContacts.addListener(listener)
        loadUser()
        _contacts.value = baseContacts.getContacts()
    }

    override fun onCleared() {
        super.onCleared()
        baseContacts.removeListener(listener)
        Log.i("MyContactsViewModel", "MyContactsViewModel destroyed!")
    }


    private fun loadUser(): List<Contact> {
        return baseContacts.getContacts()
    }


}
