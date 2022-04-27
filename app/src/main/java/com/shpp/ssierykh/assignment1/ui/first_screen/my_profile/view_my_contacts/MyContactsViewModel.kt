package com.shpp.ssierykh.assignment1.ui.first_screen.my_profile.view_my_contacts
///https://medium.com/@vgoyal_1/datastore-android-how-to-use-it-like-a-pro-using-kotlin-2c2440683d78
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.shpp.ssierykh.assignment1.databinding.FragmentMyContactsBinding
import com.shpp.ssierykh.assignment1.databinding.SingleItemContactBinding
import com.shpp.ssierykh.assignment1.model.BaseContacts
import com.shpp.ssierykh.assignment1.model.Contact
import com.shpp.ssierykh.assignment1.model.ContactListener
import kotlinx.android.synthetic.main.activity_main.view.*

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
        baseContacts.deleteContact(contact)
    }

    private val listener: ContactListener = {
        _contacts.value = baseContacts.getContacts()
    }

    init {
        baseContacts.addListener(listener)
        loadUser()
        _contacts.value = baseContacts.getContacts()
    }

    override fun onCleared() {
        super.onCleared()
        baseContacts.removeListener(listener)
    }


    private fun loadUser(): List<Contact> {
        return baseContacts.getContacts()
    }

    fun swipeDeleteItem(binding: FragmentMyContactsBinding) {
        val itemTouchHelperCallback = object :
            ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                viewHolder2: RecyclerView.ViewHolder,
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDirection: Int) {
                val contact = viewHolder.itemView.tag as Contact
                onContactDelete(contact)
            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.rvBottomContainer)
    }

}
