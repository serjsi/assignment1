package com.shpp.ssierykh.assignment1.ui.first_screen.my_profile.view_my_contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shpp.ssierykh.assignment1.model.BaseContacts

open class MyContactsViewModelFactory(private val baseContacts: BaseContacts) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MyContactsViewModel::class.java)) {
            MyContactsViewModel(this.baseContacts) as T
        } else {
            throw IllegalArgumentException("View Model Not Found")
        }
    }
}