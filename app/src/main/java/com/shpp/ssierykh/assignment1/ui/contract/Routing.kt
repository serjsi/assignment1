package com.shpp.ssierykh.assignment1.ui.contract

import androidx.fragment.app.Fragment
import androidx.navigation.NavController


fun Fragment.routing(): Routing {
    return requireActivity() as Routing
}
interface Routing {

    fun showMyProfileScreen()
    fun showSignScreen()
    fun showAddOrEditContacts()
    fun showMyContacts()
    fun goBack()
}
