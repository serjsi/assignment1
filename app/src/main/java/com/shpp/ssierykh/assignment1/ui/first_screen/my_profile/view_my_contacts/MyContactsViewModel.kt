package com.shpp.ssierykh.assignment1.ui.first_screen.my_profile.view_my_contacts
///https://medium.com/@vgoyal_1/datastore-android-how-to-use-it-like-a-pro-using-kotlin-2c2440683d78
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shpp.ssierykh.assignment1.model.BaseContacts
import com.shpp.ssierykh.assignment1.model.Contact

data class UserListItem(
    val contact: Contact
)

class MyContactsViewModel(
    private val baseContacts: BaseContacts
) : ViewModel(), ContactClickListener {
    //TODO For test, later delete
    init {
        Log.i("MyContactsViewModel", "MyContactsViewModel created!")
       //aseContacts.addListener(listener)
        loadContacts()    }

    private val _contacts = MutableLiveData<Result<List<UserListItem>>>()
    val contacts: LiveData<Result<List<UserListItem>>> = _contacts


    override fun onUserDetails(contact: Contact) {
        TODO("Not yet implemented")
    }

    override fun onUserDelete(contact: Contact) {
        TODO("Not yet implemented")
    }

/*    private val listener: BaseContacts {
        usersResult = if (it.isEmpty()) {
            EmptyResult()
        } else {
            SuccessResult(it)
        }    }*/



    override fun onCleared() {
        super.onCleared()
//        baseContacts.removeListener(listener)
        Log.i("MyContactsViewModel", "MyContactsViewModel destroyed!") }

    fun loadContacts() {
        baseContacts.loadContacts()

    }

}
