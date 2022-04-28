package com.shpp.ssierykh.assignment1.screens.first_screen.my_profile.view_my_contacts


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import androidx.recyclerview.widget.DiffUtil
import com.shpp.ssierykh.assignment1.R
import com.shpp.ssierykh.assignment1.model.Contact
import com.shpp.ssierykh.assignment1.databinding.SingleItemContactBinding
import com.shpp.ssierykh.assignment1.utils.ContactsDiffCallback
import com.shpp.ssierykh.assignment1.utils.extensions.loadImageGlade


interface ContactClickListener {

    fun onContactDetails(contact: Contact)
    fun onContactDelete(contact: Contact)
}

class AdapterContactsList(
    private val actionListener: ContactClickListener,
) : RecyclerView.Adapter<AdapterContactsList.ContactViewHolder>(), View.OnClickListener {

    var contacts: List<Contact> = emptyList()
        set(newValue) {
            val diffCallback = ContactsDiffCallback(field, newValue)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            field = newValue
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onClick(v: View) {
        val contact = v.tag as Contact
        when (v.id) {
            R.id.ivDelete -> {
                actionListener.onContactDelete(contact)
            }
            else -> {
                actionListener.onContactDetails(contact)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = SingleItemContactBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        binding.root.setOnClickListener(this)
        binding.ivDelete.setOnClickListener(this)

        return ContactViewHolder(binding)
    }


    override fun onBindViewHolder(holderContact: ContactViewHolder, position: Int) {
        val contact = contacts[position]
        with(holderContact.binding) {
            holderContact.itemView.tag = contact
            ivDelete.tag = contact

            ivPhoto.loadImageGlade(contact.photoAddress)
            //  binding.ivPhoto.loadImagePicasso(contact.photoAddress)
            tvName.text = contact.name
            tvCareer.text = contact.career

        }
    }

    // return the size of contactList
    override fun getItemCount(): Int = contacts.size

    class ContactViewHolder(val binding: SingleItemContactBinding) :
        RecyclerView.ViewHolder(binding.root)
}