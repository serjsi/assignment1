package com.shpp.ssierykh.assignment1.ui.first_screen.my_profile.view_my_contacts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shpp.ssierykh.assignment1.data.BaseContacts
import com.shpp.ssierykh.assignment1.data.Contact
import com.shpp.ssierykh.assignment1.ui.activity_old.contacts.AdapterContacts

data class UserListItem(
    val user: Contact
)

class MyContactsViewModel : ViewModel(), ContactClickListener {
    //TODO For test, later delete
    init {
        Log.i("MyContactsViewModel", "MyContactsViewModel created!")
    }
    override fun onCleared() {
        super.onCleared()
        Log.i("MyContactsViewModel", "MyContactsViewModel destroyed!")
    }
    private val _users = MutableLiveData<Result<List<UserListItem>>>()
    val users: LiveData<Result<List<UserListItem>>> = _users


    override fun onUserDetails(contact: Contact) {
        TODO("Not yet implemented")
    }

    override fun onUserDelete(contact: Contact) {
        TODO("Not yet implemented")
    }


}
