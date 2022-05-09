package com.shpp.ssierykh.assignment1.ui.fragment_util

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shpp.ssierykh.assignment1.utils.App
import com.shpp.ssierykh.assignment1.ui.view_my_contacts.MyContactsViewModel
import com.shpp.ssierykh.assignment1.ui.contact_profile.ContactProfileViewModel




@Suppress("UNCHECKED_CAST")
open class ContactsViewModelFactory(private val app: App) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

    val viewModel = when (modelClass) {
        MyContactsViewModel::class.java -> {
            MyContactsViewModel(app.baseContacts)
        }
        ContactProfileViewModel::class.java -> {
            ContactProfileViewModel(app.baseContacts)
        }
        else -> {
            throw IllegalStateException("Unknown view model class")
        }
    }
    return viewModel as T
}
}

fun Fragment.factory() = ContactsViewModelFactory(requireContext().applicationContext as App)
