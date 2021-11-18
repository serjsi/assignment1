package com.shpp.ssierykh.assignment1.recyclerView


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shpp.ssierykh.assignment1.databinding.SingleItemBinding


class AdapterRecyclerView (var contactsList: List<ContactsRecyclerView>,
) : RecyclerView.Adapter<AdapterRecyclerView.ViewHolder>(){



    // create an inner class with name ViewHolder
    // It takes a view argument, in which pass the generated class of single_item.xml
    // ie SingleItemBinding and in the RecyclerView.ViewHolder(binding.root) pass it like this
    inner class ViewHolder(val binding: SingleItemBinding) : RecyclerView.ViewHolder(binding.root)

    // inside the onCreateViewHolder inflate the view of SingleItemBinding
    // and return new ViewHolder object containing this layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SingleItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    // bind the items with each item of the list contactsList which than will be
    // shown in recycler view
    // to keep it simple we are not setting any image data to view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(contactsList[position]){
                binding.tvName.text = this.name
                binding.tvCareer.text = this.career
            }
        }
    }

    // return the size of contactList
    override fun getItemCount(): Int {
        return contactsList.size
    }

}

