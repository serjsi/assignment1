package com.shpp.ssierykh.assignment1

import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
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
import com.shpp.ssierykh.assignment1.databinding.ActivityMyContactsBinding
import com.shpp.ssierykh.assignment1.contacts.AdapterRecyclerView
import com.shpp.ssierykh.assignment1.contacts.AddContactsDialog
import com.shpp.ssierykh.assignment1.contacts.ContactRecyclerView
import com.shpp.ssierykh.assignment1.contacts.ContactsProfileActivity
import kotlinx.android.synthetic.main.activity_my_contacts.*


class MyContactsActivity : AppCompatActivity(), AdapterRecyclerView.OnItemClickListener {


    // view binding for the activity
    private var _binding: ActivityMyContactsBinding? = null
    private val binding get() = _binding!!

    // create reference to the adapter and the list
    // in the list pass the model of ContactsRecyclerView
    private lateinit var contactList: MutableList<ContactRecyclerView>
    private lateinit var rvAdapter: AdapterRecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMyContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // load data to contactsList list
        forTest()

        // initialize the adapter, and pass the required argument
        rvAdapter = AdapterRecyclerView(contactList, this)

        // attach adapter to the recycler view
        binding.ivBottomContainer.adapter = rvAdapter



        binding.tvAddContacts.setOnClickListener { dialogAddContact() }
        // binding.tvAddContacts.setOnClickListener { goAddContacts() }

        binding.ivArrowBack.setOnClickListener { finish() }

    }

    fun dialogAddContact() {
        AddContactsDialog().show(supportFragmentManager, "customDialog")
    }


    // on destroy of view make the binding reference to null
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    override fun onItemClick(position: Int) {
        val clickedItem = contactList[position]
        val intent = Intent(this, ContactsProfileActivity::class.java)
        intent.putExtra(Constants.NAME_EXTRA, clickedItem.name)
        intent.putExtra(Constants.PHOTO_EXTRA, clickedItem.photoAddress)
        intent.putExtra(Constants.CAREER_EXTRA, clickedItem.career)
        startActivity(intent)
        //Animation
        overridePendingTransition(0, R.anim.slide_out_right)

    }


    override fun onItemDelete(position: Int) {
        val deleteItem = contactList[position]
        contactList.removeAt(position)
        rvAdapter.notifyItemRemoved(position)
        isSnack(position,deleteItem)
    }

    private fun isSnack(position: Int, deleteItem: ContactRecyclerView) {
        val snackbar = Snackbar
            .make(iv_BottomContainer, "Contact ${deleteItem.name} is deleted ", 5000)
            .setAction("UNDO") { // Show another Snackbar.
                val snackbar1 =
                    Snackbar.make(iv_BottomContainer,
                        "Contact ${deleteItem.name} is restored!",
                        Snackbar.LENGTH_SHORT)
                contactList.add(deleteItem)
                contactList.sortBy { contactRecyclerView -> contactRecyclerView.name }
                rvAdapter.notifyItemChanged(position)
                snackbar1.show()
            }
        snackbar.show()
    }

    //Switching to another screen
    private fun goAddContacts() {
        binding.tvAddContacts.setOnClickListener {
            val intent = Intent(this, AddContactsDialog::class.java)
            startActivity(intent)
            //Animation
            //   finish()
            overridePendingTransition(0, R.anim.slide_out_left)

        }
    }


    // add items to the list manually in our case
    private fun forTest() {
        contactList = mutableListOf(

            ContactRecyclerView(PHOTO_FAKE_1, "Frank Wells", "Baker"),
            ContactRecyclerView(PHOTO_FAKE_2, "Jasmin Bailey", "Business owner"),
            ContactRecyclerView(PHOTO_FAKE_3, "Alaina Walters", "Cameraman"),
            ContactRecyclerView(PHOTO_FAKE_4, "Daisy Gordon", "Cashier"),
            ContactRecyclerView(PHOTO_FAKE_5, "Frederick Pope", "Chef"),
            ContactRecyclerView(PHOTO_FAKE_6, "Thomas Paul", "Civil servant"),
            ContactRecyclerView(PHOTO_FAKE_7, "Richard Todd", "Cleaner"),
            ContactRecyclerView(PHOTO_FAKE_8, "Sharon Anderson", "Distributor"),
            ContactRecyclerView(PHOTO_FAKE_9, "Robert Harmon", "Engineer"),
            ContactRecyclerView(PHOTO_FAKE_10, "Ruth Johnson", "Financier"),
            ContactRecyclerView(PHOTO_FAKE_11, "Juliet McDonald", "Fitter"),
            ContactRecyclerView(PHOTO_FAKE_12, "Thomas Hampton", "Guard"),
            ContactRecyclerView(PHOTO_FAKE_13, "Valentine Craig", "Hunter"),
            ContactRecyclerView(PHOTO_FAKE_14, "Edwin Little", "Jeweller"),
        )
        contactList.sortBy { contactRecyclerView -> contactRecyclerView.name }

    }

}