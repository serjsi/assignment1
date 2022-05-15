package com.shpp.ssierykh.assignment1.utils.fragment_util

import androidx.fragment.app.Fragment
import com.shpp.ssierykh.assignment1.model.Contact


fun Fragment.routing() = requireActivity() as Routing

interface Routing {

    fun showMyProfileScreen()
    fun showEditProfileContact()
    fun showEditProfileContact(contact: Contact?)
    fun showMyContacts()
    fun showContactProfile(contact: Contact)
    fun goBack()
}
