package com.shpp.ssierykh.assignment1.ui.contract

import androidx.fragment.app.Fragment



fun Fragment.routing(): Routing {
    return requireActivity() as Routing
}
interface Routing {
    fun showMyProfileScreen()
    fun showSignScreen()
    fun goBack()
}
