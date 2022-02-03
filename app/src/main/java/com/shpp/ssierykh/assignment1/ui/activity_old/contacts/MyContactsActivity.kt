package com.shpp.ssierykh.assignment1.ui.activity_old.contacts

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.shpp.ssierykh.assignment1.R
import com.shpp.ssierykh.assignment1.data.FakeBaseContacts.fakeBase
import com.shpp.ssierykh.assignment1.databinding.ActivityMyContactsBinding
import com.shpp.ssierykh.assignment1.utils.Constants
import kotlinx.android.synthetic.main.activity_my_contacts.*
import java.util.ArrayList


class MyContactsActivity : AppCompatActivity(), AdapterContacts.OnItemClickListener,
    AddContactsDialogFragment.OnAddContactListener {


    // view binding for the activity
    private var _binding: ActivityMyContactsBinding? = null
    private val binding get() = _binding!!

    // create reference to the adapter and the list
    // in the list pass the model of ContactsRecyclerView
    private lateinit var contactList: MutableList<ContactForRecyclerView>
    private lateinit var rvAdapter: AdapterContacts

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMyContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // load data to contactsList list
        contactList = fakeBase()

        // initialize the adapter, and pass the required argument
        rvAdapter = AdapterContacts(contactList, this)

        // attach adapter to the recycler view
        binding.rvBottomContainer.adapter = rvAdapter

        setOnClickListener()
        swipeDeleteItem()
    }


    // on destroy of view make the binding reference to null
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onItemClick(position: Int) {
        val clickedItem = contactList[position]
        val intent = Intent(this, ContactsProfileActivity::class.java)
        intent.putExtra(Constants.EMAIL_EXTRA, clickedItem.name)
        intent.putExtra(Constants.PHOTO_EXTRA, clickedItem.photoAddress)
        intent.putExtra(Constants.CAREER_EXTRA, clickedItem.career)
        startActivity(intent)
        //Animation
        overridePendingTransition(0, R.anim.slide_out)

    }

    override fun onAddContact(addItem: ContactForRecyclerView) {
        val oldList = ArrayList(contactList)
        contactList.add(addItem)
        contactList.sortBy { contactRecyclerView -> contactRecyclerView.name }
        diffContactsOutAdapter(oldList)
        Toast.makeText(
            applicationContext,
            "${getString(R.string.Contact)} ${addItem.name} ${getString(R.string.is_add)} ",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onItemDelete(position: Int) {
        val deleteItem = contactList[position]
        val oldList = ArrayList(contactList)
        contactList.removeAt(position)
        diffContactsOutAdapter(oldList)
        showSnackBar(position, deleteItem)
    }

    private fun diffContactsOutAdapter(oldList: ArrayList<ContactForRecyclerView>) {
        val diffCallBack = ContactsDiffCallback(oldList, contactList)
        val diffResult = DiffUtil.calculateDiff(diffCallBack, true)
        diffResult.dispatchUpdatesTo(rvAdapter)
    }

    private fun swipeDeleteItem() {
        val itemTouchHelperCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                viewHolder2: RecyclerView.ViewHolder,
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDirection: Int) {
                onItemDelete(viewHolder.absoluteAdapterPosition)
            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.rvBottomContainer)
    }

    private fun setOnClickListener() {
        binding.tvAddContacts.setOnClickListener {
            AddContactsDialogFragment(this).show(supportFragmentManager, "customDialog")
        }

        binding.ivArrowBack.setOnClickListener { finish() }
    }


    private fun showSnackBar(position: Int, deleteItem: ContactForRecyclerView) {
        Snackbar.make(
            rvBottomContainer,
            "${getString(R.string.Contact)}  ${deleteItem.name} ${getString(R.string.is_deleted)} ",
            Snackbar.LENGTH_LONG
        ).setAction(getString(R.string.UNDO)) {
            val oldList = ArrayList(contactList)
            contactList.add(position, deleteItem)
            diffContactsOutAdapter(oldList)
            Snackbar.make(
                rvBottomContainer,
                "${getString(R.string.Contact)} ${deleteItem.name} ${getString(R.string.is_restored)}",
                Snackbar.LENGTH_SHORT
            ).show()
        }.apply {
            show()
        }
    }
}