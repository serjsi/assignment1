package com.shpp.ssierykh.assignment1.utils.fragment_util

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shpp.ssierykh.assignment1.utils.App
import com.shpp.ssierykh.assignment1.ui.view_my_contacts.MyContactsViewModel
import com.shpp.ssierykh.assignment1.ui.contact_profile.ContactProfileViewModel
import com.shpp.ssierykh.assignment1.ui.edit_profile.EditProfileContactViewModel
import com.shpp.ssierykh.assignment1.ui.edit_profile.my_profile.MyProfileViewModel


@Suppress("UNCHECKED_CAST")
open class ContactsViewModelFactory(private val app: App) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            MyContactsViewModel::class.java -> MyContactsViewModel(app.baseContacts) as T
            MyProfileViewModel::class.java -> MyProfileViewModel(app.baseContacts) as T
            ContactProfileViewModel::class.java -> ContactProfileViewModel(app.baseContacts) as T
            EditProfileContactViewModel::class.java -> EditProfileContactViewModel(app.baseContacts) as T
            else -> {
                throw IllegalStateException("Unknown view model class")
            }
        }
    }
}

fun Fragment.factory() = ContactsViewModelFactory(requireContext().applicationContext as App)
