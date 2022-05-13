package com.shpp.ssierykh.assignment1.ui.fragment_util

import androidx.fragment.app.Fragment
import com.shpp.ssierykh.assignment1.model.Contact
import kotlinx.coroutines.flow.StateFlow


fun Fragment.routing() = requireActivity() as Routing

interface Routing {

    fun showMyProfileScreen()
    fun showAddOrEditContacts(contact: Contact?)
    fun showMyContacts()
    fun showContactProfile(contact: Contact)
    fun goBack()
}
