package com.shpp.ssierykh.assignment1.screens.first_screen.my_profile.view_my_contacts

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shpp.ssierykh.assignment1.screens.first_screen.App

open class MyContactsViewModelFactory(private val app: App) :
    ViewModelProvider.Factory {



    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MyContactsViewModel::class.java)) {
            MyContactsViewModel(app.baseContacts) as T
        } else {
            throw IllegalArgumentException("View Model Not Found")
        }
    }
}

fun Fragment.factory() = MyContactsViewModelFactory(requireContext().applicationContext as App)