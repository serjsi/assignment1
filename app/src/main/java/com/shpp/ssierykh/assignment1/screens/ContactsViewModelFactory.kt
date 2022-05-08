package com.shpp.ssierykh.assignment1.screens

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shpp.ssierykh.assignment1.screens.first_screen.App
import com.shpp.ssierykh.assignment1.screens.first_screen.my_profile.view_my_contacts.MyContactsViewModel
import com.shpp.ssierykh.assignment1.screens.first_screen.my_profile.view_my_contacts.contact_profile.ContactProfileViewModel




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
