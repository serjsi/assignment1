package com.shpp.ssierykh.assignment1.ui.contacts

import androidx.recyclerview.widget.DiffUtil
import com.shpp.ssierykh.assignment1.data.ContactForRecyclerView

class ContactsDiffCallback(
    private val oldList: List<ContactForRecyclerView>,
    private val newList: List<ContactForRecyclerView>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldUser = oldList[oldItemPosition]
        val newUser = newList[newItemPosition]
        return oldUser.name == newUser.name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
     val oldUser = oldList[oldItemPosition]
     val newUser = newList[newItemPosition]
        return oldUser == newUser
    }
}