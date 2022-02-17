package com.shpp.ssierykh.assignment1.data

import com.shpp.ssierykh.assignment1.utils.Constants

typealias UsersListener = (users: List<Contact>) -> Unit


class BaseContacts {
    private var contacts = mutableListOf<Contact>()
    private val listeners = mutableSetOf<UsersListener>()

    fun loadContacts() {
        contacts = mutableListOf(

            Contact("FrankWells@mail.ua", Constants.PHOTO_FAKE_1, "Frank Wells", "Baker"),
            Contact(
                "JasminBailey@mail.ua",
                Constants.PHOTO_FAKE_2,
                "Jasmin Bailey",
                "Business owner"
            ),
            Contact("AlainaWalters@mail.ua", Constants.PHOTO_FAKE_3, "Alaina Walters", "Cameraman"),
            Contact("DaisyGordon@mail.ua", Constants.PHOTO_FAKE_4, "Daisy Gordon", "Cashier"),
            Contact("FrederickPope@mail.ua", Constants.PHOTO_FAKE_5, "Frederick Pope", "Chef"),
            Contact("ThomasPaul@mail.ua", Constants.PHOTO_FAKE_6, "Thomas Paul", "Civil servant"),
            Contact("RichardTodd@mail.ua", Constants.PHOTO_FAKE_7, "Richard Todd", "Cleaner"),
            Contact(
                "SharonAnderson@mail.ua",
                Constants.PHOTO_FAKE_8,
                "Sharon Anderson",
                "Distributor"
            ),
            Contact("RobertHarmon@mail.ua", Constants.PHOTO_FAKE_9, "Robert Harmon", "Engineer"),
            Contact("RuthJohnson@mail.ua", Constants.PHOTO_FAKE_10, "Ruth Johnson", "Financier"),
            Contact("JulietMcDonald@mail.ua", Constants.PHOTO_FAKE_11, "Juliet McDonald", "Fitter"),
            Contact("ThomasHampton@mail.ua", Constants.PHOTO_FAKE_12, "Thomas Hampton", "Guard"),
            Contact("ValentineCraig@mail.ua", Constants.PHOTO_FAKE_13, "Valentine Craig", "Hunter"),
            Contact("EdwinLittle@mail.ua", Constants.PHOTO_FAKE_14, "Edwin Little", "Jeweller"),
        )
        contacts.sortBy { contactRecyclerView -> contactRecyclerView.name }
    }


    fun deleteUser(user: Contact) {
        val indexToDelete = contacts.indexOfFirst { it.email == user.email }
        if (indexToDelete != -1) {
            contacts.removeAt(indexToDelete)
            notifyChanges()
        }
    }


    fun addListener(listener: UsersListener) {
        listeners.add(listener)
        listener.invoke(contacts)
    }

    fun removeListener(listener: UsersListener) {
        listeners.remove(listener)
    }

    private fun notifyChanges() {
        listeners.forEach { it.invoke(contacts) }
    }

}