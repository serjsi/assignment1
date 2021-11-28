package com.shpp.ssierykh.assignment1

import android.os.Bundle

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
import com.shpp.ssierykh.assignment1.recyclerView.ContactRecyclerView


class ContactsActivity : AppCompatActivity() {



    // view binding for the activity
    private var _binding : ActivityContactsBinding? = null
    private val binding get() = _binding!!

    // create reference to the adapter and the list
    // in the list pass the model of ContactsRecyclerView
    private lateinit var contactList : MutableList<ContactRecyclerView>
    private lateinit var rvAdapter: AdapterRecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // load data to contactsList list
        forTest()

        // initialize the adapter, and pass the required argument
        rvAdapter = AdapterRecyclerView(contactList)

        // attach adapter to the recycler view
        binding.ivBottomContainer.adapter = rvAdapter

    //   binding.tvAddContacts.setOnClickListener { addContact() }



    }




    // on destroy of view make the binding reference to null
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }



    // add items to the list manually in our case
    private fun forTest() {
        contactList = mutableListOf(

            ContactRecyclerView(PHOTO_FAKE_1,"Frank Wells" , "Baker"),
            ContactRecyclerView(PHOTO_FAKE_2,"Jasmin Bailey" , "Business owner"),
            ContactRecyclerView(PHOTO_FAKE_3,"Alaina Walters" , "Cameraman"),
            ContactRecyclerView(PHOTO_FAKE_4,"Daisy Gordon" , "Cashier"),
            ContactRecyclerView(PHOTO_FAKE_5,"Frederick Pope" , "Chef"),
            ContactRecyclerView(PHOTO_FAKE_6,"Thomas Paul" , "Civil servant"),
            ContactRecyclerView(PHOTO_FAKE_7,"Richard Todd" , "Cleaner"),
            ContactRecyclerView(PHOTO_FAKE_8,"Sharon Anderson" , "Distributor"),
            ContactRecyclerView(PHOTO_FAKE_9,"Robert Harmon" , "Engineer"),
            ContactRecyclerView(PHOTO_FAKE_10,"Ruth Johnson" , "Financier"),
            ContactRecyclerView(PHOTO_FAKE_11,"Juliet McDonald" , "Fitter"),
            ContactRecyclerView(PHOTO_FAKE_12,"Thomas Hampton" , "Guard"),
            ContactRecyclerView(PHOTO_FAKE_13,"Valentine Craig" , "Hunter"),
            ContactRecyclerView(PHOTO_FAKE_14,"Edwin Little" , "Jeweller"),
        )

    }

  /*  override fun onItemClick(position: Int) {
        Toast.makeText(this, "Item $position clicked", Toast.LENGTH_SHORT).show()
        val clickedItem = contactList[position]
        contactList.removeAt(position)//////////////
        rvAdapter.notifyItemRemoved(position)//////////////////
        rvAdapter.notifyItemChanged(position)
    }*/


}