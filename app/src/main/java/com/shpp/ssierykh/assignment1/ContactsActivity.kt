package com.shpp.ssierykh.assignment1

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.shpp.ssierykh.assignment1.Constants.PHOTO_FAKE_1
import com.shpp.ssierykh.assignment1.Constants.PHOTO_FAKE_2
import com.shpp.ssierykh.assignment1.Constants.PHOTO_FAKE_3
import com.shpp.ssierykh.assignment1.Constants.PHOTO_FAKE_4
import com.shpp.ssierykh.assignment1.Constants.PHOTO_FAKE_5
import com.shpp.ssierykh.assignment1.Constants.PHOTO_FAKE_6
import com.shpp.ssierykh.assignment1.Constants.PHOTO_FAKE_7
import com.shpp.ssierykh.assignment1.Constants.PHOTO_FAKE_8
import com.shpp.ssierykh.assignment1.Constants.PHOTO_FAKE_9
import com.shpp.ssierykh.assignment1.Constants.PHOTO_FAKE_10
import com.shpp.ssierykh.assignment1.Constants.PHOTO_FAKE_11
import com.shpp.ssierykh.assignment1.Constants.PHOTO_FAKE_12
import com.shpp.ssierykh.assignment1.Constants.PHOTO_FAKE_13
import com.shpp.ssierykh.assignment1.Constants.PHOTO_FAKE_14
import com.shpp.ssierykh.assignment1.databinding.ActivityContactsBinding
import com.shpp.ssierykh.assignment1.recyclerView.AdapterRecyclerView
import com.shpp.ssierykh.assignment1.recyclerView.ContactsRecyclerView
import android.widget.Toast




class ContactsActivity : AppCompatActivity() {



    // view binding for the activity
    private var _binding : ActivityContactsBinding? = null
    private val binding get() = _binding!!

    // create reference to the adapter and the list
    // in the list pass the model of ContactsRecyclerView
    private lateinit var rvAdapter: AdapterRecyclerView
    private lateinit var contactsList : MutableList<ContactsRecyclerView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // load data to contactsList list
        forTest()

        // initialize the adapter, and pass the required argument
        rvAdapter = AdapterRecyclerView(contactsList)

        // attach adapter to the recycler view
        binding.ivBottomContainer.adapter = rvAdapter
    }



    // on destroy of view make the binding reference to null
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    // add items to the list manually in our case
    private fun forTest() {
        contactsList = mutableListOf(

            ContactsRecyclerView(PHOTO_FAKE_1,"Frank Wells" , "Baker"),
            ContactsRecyclerView(PHOTO_FAKE_2,"Jasmin Bailey" , "Business owner"),
            ContactsRecyclerView(PHOTO_FAKE_3,"Alaina Walters" , "Cameraman"),
            ContactsRecyclerView(PHOTO_FAKE_4,"Daisy Gordon" , "Cashier"),
            ContactsRecyclerView(PHOTO_FAKE_5,"Frederick Pope" , "Chef"),
            ContactsRecyclerView(PHOTO_FAKE_6,"Thomas Paul" , "Civil servant"),
            ContactsRecyclerView(PHOTO_FAKE_7,"Richard Todd" , "Cleaner"),
            ContactsRecyclerView(PHOTO_FAKE_8,"Sharon Anderson" , "Distributor"),
            ContactsRecyclerView(PHOTO_FAKE_9,"Robert Harmon" , "Engineer"),
            ContactsRecyclerView(PHOTO_FAKE_10,"Ruth Johnson" , "Financier"),
            ContactsRecyclerView(PHOTO_FAKE_11,"Juliet McDonald" , "Fitter"),
            ContactsRecyclerView(PHOTO_FAKE_12,"Thomas Hampton" , "Guard"),
            ContactsRecyclerView(PHOTO_FAKE_13,"Valentine Craig" , "Hunter"),
            ContactsRecyclerView(PHOTO_FAKE_14,"Edwin Little" , "Jeweller"),
        )
    }




}