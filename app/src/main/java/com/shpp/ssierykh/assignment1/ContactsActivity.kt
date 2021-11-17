package com.shpp.ssierykh.assignment1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shpp.ssierykh.assignment1.databinding.ActivityContactsBinding
import com.shpp.ssierykh.assignment1.recyclerView.AdapterRecyclerView
import com.shpp.ssierykh.assignment1.recyclerView.ContactsRecyclerView

class ContactsActivity : AppCompatActivity() {
    // view binding for the activity
    private var _binding : ActivityContactsBinding? = null
    private val binding get() = _binding!!

    // create reference to the adapter and the list
    // in the list pass the model of ContactsRecyclerView
    private lateinit var rvAdapter: AdapterRecyclerView
    private lateinit var contactsList : List<ContactsRecyclerView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // load data to language list
        forTest()

        // initialize the adapter, and pass the required argument
        rvAdapter = AdapterRecyclerView(contactsList)

        // attach adapter to the recycler view
        binding.imageViewBottomContainer.adapter = rvAdapter
    }



    // on destroy of view make the binding reference to null
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    // add items to the list manually in our case
    private fun forTest() {
        contactsList = listOf(

            ContactsRecyclerView("Frank Wells" , "Baker"),
            ContactsRecyclerView("Jasmin Bailey" , "Business owner"),
            ContactsRecyclerView("Alaina Walters" , "Cameraman"),
            ContactsRecyclerView("Daisy Gordon" , "Cashier"),
            ContactsRecyclerView("Frederick Pope" , "Chef"),
            ContactsRecyclerView("Thomas Paul" , "Civil servant"),
            ContactsRecyclerView("Richard Todd" , "Cleaner"),
            ContactsRecyclerView("Sharon Anderson" , "Distributor"),
            ContactsRecyclerView("Robert Harmon" , "Engineer"),
            ContactsRecyclerView("Ruth Johnson" , "Financier"),
            ContactsRecyclerView("Juliet McDonald" , "Fitter"),
            ContactsRecyclerView("Thomas Hampton" , "Guard"),
            ContactsRecyclerView("Valentine Craig" , "Hunter"),
            ContactsRecyclerView("Edwin Little" , "Jeweller"),

           /* ContactsRecyclerView("https://thispersondoesnotexist.com/","Frank Wells" , "Baker"),
            ContactsRecyclerView("https://thispersondoesnotexist.com/","Jasmin Bailey" , "Business owner"),
            ContactsRecyclerView("https://thispersondoesnotexist.com/","Alaina Walters" , "Cameraman"),
            ContactsRecyclerView("https://thispersondoesnotexist.com/","Daisy Gordon" , "Cashier"),
            ContactsRecyclerView("https://thispersondoesnotexist.com/","Frederick Pope" , "Chef"),
            ContactsRecyclerView("https://thispersondoesnotexist.com/","Thomas Paul" , "Civil servant"),
            ContactsRecyclerView("https://thispersondoesnotexist.com/","Richard Todd" , "Cleaner"),
            ContactsRecyclerView("https://thispersondoesnotexist.com/","Sharon Anderson" , "Distributor"),
            ContactsRecyclerView("https://thispersondoesnotexist.com/","Robert Harmon" , "Engineer"),
            ContactsRecyclerView("https://thispersondoesnotexist.com/","Ruth Johnson" , "Financier"),
            ContactsRecyclerView("https://thispersondoesnotexist.com/","Juliet McDonald" , "Fitter"),
            ContactsRecyclerView("https://thispersondoesnotexist.com/","Thomas Hampton" , "Guard"),
            ContactsRecyclerView("https://thispersondoesnotexist.com/","Valentine Craig" , "Hunter"),
            ContactsRecyclerView("https://thispersondoesnotexist.com/","Edwin Little" , "Jeweller"),*/
        )
    }
}