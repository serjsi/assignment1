package com.shpp.ssierykh.assignment1

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
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
import com.shpp.ssierykh.assignment1.Constants.SNACKbAR_LENGTH_MANUAL
import com.shpp.ssierykh.assignment1.contacts.*
import com.shpp.ssierykh.assignment1.databinding.ActivityMyContactsBinding
import kotlinx.android.synthetic.main.activity_my_contacts.*
import android.graphics.drawable.ColorDrawable









class MyContactsActivity : AppCompatActivity(), AdapterRecyclerView.OnItemClickListener,
    AddContactsDialog.OnAddContactListener {


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

        binding.ivArrowBack.setOnClickListener { finish() }

        swipeDeleteItem()
    }

    private fun swipeDeleteItem() {
        val itemTouchHelperCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

      /*      override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                val background = ColorDrawable(Color.RED)
            //   background.setBounds(0, getTop(), itemView.getLeft() + dX, itemView.getBottom())
                background.setBounds(0, getTop(), itemView.getLeft() + dX, itemView.getBottom())
                background.draw(c)
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }*/

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
        itemTouchHelper.attachToRecyclerView(binding.ivBottomContainer)


    }



    private fun dialogAddContact() {
        AddContactsDialog(this).show(supportFragmentManager, "customDialog")
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

    override fun onAddContact(addItem: ContactRecyclerView) {
        contactList.add(addItem)
        contactList.sortBy { contactRecyclerView -> contactRecyclerView.name }
        rvAdapter.notifyItemInserted(contactList.indexOf(addItem))
        Toast.makeText(applicationContext,
            "${getString(R.string.Contact)} ${addItem.name} ${getString(R.string.is_add)} ",
            Toast.LENGTH_SHORT).show()
    }

    override fun onItemDelete(position: Int) {
        val deleteItem = contactList[position]
        contactList.removeAt(position)
        rvAdapter.notifyItemRemoved(position)
        isSnack(position, deleteItem)
    }



    @SuppressLint("WrongConstant")
    private fun isSnack(position: Int, deleteItem: ContactRecyclerView) {
        Snackbar.make(iv_BottomContainer,
            "${getString(R.string.Contact)}  ${deleteItem.name} ${getString(R.string.is_deleted)} ",
            SNACKbAR_LENGTH_MANUAL).setAction(getString(R.string.UNDO)) {
            // Show another SnackBar.
            val snackBarRestore =
                Snackbar.make(iv_BottomContainer,
                    "${getString(R.string.Contact)} ${deleteItem.name} ${getString(R.string.is_restored)}",
                    Snackbar.LENGTH_SHORT)
            contactList.add(position, deleteItem)
            rvAdapter.notifyItemInserted(position)
            snackBarRestore.show()
        }.apply {
            show()
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
