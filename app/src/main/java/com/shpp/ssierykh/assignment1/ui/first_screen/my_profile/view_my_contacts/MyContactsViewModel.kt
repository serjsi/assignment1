package com.shpp.ssierykh.assignment1.ui.first_screen.my_profile.view_my_contacts
///https://medium.com/@vgoyal_1/datastore-android-how-to-use-it-like-a-pro-using-kotlin-2c2440683d78
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shpp.ssierykh.assignment1.data.BaseContacts
import com.shpp.ssierykh.assignment1.data.Contact

data class UserListItem(
    val contact: Contact
)

class MyContactsViewModel(
    private val baseContacts : BaseContacts
) : ViewModel(), ContactClickListener {
    //TODO For test, later delete
/*    init {
        Log.i("MyContactsViewModel", "MyContactsViewModel created!")
    }
    override fun onCleared() {
        super.onCleared()
        Log.i("MyContactsViewModel", "MyContactsViewModel destroyed!")
    }*/
    private val _contacts = MutableLiveData<Result<List<UserListItem>>>()
    val contacts: LiveData<Result<List<UserListItem>>> = _contacts


    override fun onUserDetails(contact: Contact) {
        TODO("Not yet implemented")
    }

    override fun onUserDelete(contact: Contact) {
        TODO("Not yet implemented")
    }

//    private val listener: BaseContacts {
//    }

    init {
//       baseContacts.addListener(listener)
        loadContacts()
    }

    override fun onCleared() {
        super.onCleared()
//        baseContacts.removeListener(listener)
    }

    fun loadContacts() {
        baseContacts.loadContacts()

    }

}
