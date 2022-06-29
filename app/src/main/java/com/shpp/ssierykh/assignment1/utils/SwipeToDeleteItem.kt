package com.shpp.ssierykh.assignment1.utils

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.shpp.ssierykh.assignment1.databinding.FragmentMyContactsBinding
import com.shpp.ssierykh.assignment1.model.Contact

class SwipeToDeleteItem(binding: FragmentMyContactsBinding) {
   private lateinit var contact : Contact
    val itemTouchHelperCallback = object :
        ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            viewHolder2: RecyclerView.ViewHolder,
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDirection: Int) {

            contact = viewHolder.itemView.tag as Contact
   //               onContactDelete(contact)
        }


    }

    fun setContact(): Contact{
    return contact}

    val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
   // itemTouchHelper.attachToRecyclerView(binding.rvBottomContainer)

}