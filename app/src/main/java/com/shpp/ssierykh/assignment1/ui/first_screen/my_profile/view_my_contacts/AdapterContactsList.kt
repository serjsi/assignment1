package com.shpp.ssierykh.assignment1.ui.first_screen.my_profile.view_my_contacts


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.shpp.ssierykh.assignment1.R
import com.shpp.ssierykh.assignment1.model.Contact
import com.shpp.ssierykh.assignment1.databinding.SingleItemContactBinding
import com.shpp.ssierykh.assignment1.utils.extensions.loadImageGlade


interface ContactClickListener {
    fun onUserDetails(contact: Contact)
    fun onUserDelete(contact: Contact)
}

class AdapterContactsList(
    private val  actionListener: ContactClickListener,
) : RecyclerView.Adapter<AdapterContactsList.ContactViewHolder>(),View.OnClickListener {

    var contacts: List<Contact> = emptyList()
        set(newValue) {
            field = newValue
            this.notifyDataSetChanged()
        }

    override fun onClick(v: View) {
        val contact = v.tag as Contact
        when (v.id) {
            R.id.ivDelete -> {
                actionListener.onUserDelete(contact)
            }
            else -> {
               actionListener.onUserDetails(contact)
            }
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val itemView = SingleItemContactBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(itemView)

    }


    override fun onBindViewHolder(holderContact: ContactViewHolder, position: Int) {
        val contact = contacts[position]
        with(holderContact.binding) {
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





