package com.shpp.ssierykh.assignment1.data

import com.shpp.ssierykh.assignment1.ui.activity_old.contacts.ContactForRecyclerView
import com.shpp.ssierykh.assignment1.utils.Constants

object FakeBaseContacts {
    // add items to the list manually in our case
     fun fakeBase() : MutableList<ContactForRecyclerView>{
       val contactList = mutableListOf(

            ContactForRecyclerView(Constants.PHOTO_FAKE_1, "Frank Wells", "Baker"),
            ContactForRecyclerView(Constants.PHOTO_FAKE_2, "Jasmin Bailey", "Business owner"),
            ContactForRecyclerView(Constants.PHOTO_FAKE_3, "Alaina Walters", "Cameraman"),
            ContactForRecyclerView(Constants.PHOTO_FAKE_4, "Daisy Gordon", "Cashier"),
            ContactForRecyclerView(Constants.PHOTO_FAKE_5, "Frederick Pope", "Chef"),
            ContactForRecyclerView(Constants.PHOTO_FAKE_6, "Thomas Paul", "Civil servant"),
            ContactForRecyclerView(Constants.PHOTO_FAKE_7, "Richard Todd", "Cleaner"),
            ContactForRecyclerView(Constants.PHOTO_FAKE_8, "Sharon Anderson", "Distributor"),
            ContactForRecyclerView(Constants.PHOTO_FAKE_9, "Robert Harmon", "Engineer"),
            ContactForRecyclerView(Constants.PHOTO_FAKE_10, "Ruth Johnson", "Financier"),
            ContactForRecyclerView(Constants.PHOTO_FAKE_11, "Juliet McDonald", "Fitter"),
            ContactForRecyclerView(Constants.PHOTO_FAKE_12, "Thomas Hampton", "Guard"),
            ContactForRecyclerView(Constants.PHOTO_FAKE_13, "Valentine Craig", "Hunter"),
            ContactForRecyclerView(Constants.PHOTO_FAKE_14, "Edwin Little", "Jeweller"),
        )
        contactList.sortBy { contactRecyclerView -> contactRecyclerView.name }
    return contactList
    }
}