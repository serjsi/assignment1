package com.shpp.ssierykh.assignment1.recyclerView


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shpp.ssierykh.assignment1.databinding.SingleItemBinding




class AdapterRecyclerView(
   private var contactsList: MutableList<ContactsRecyclerView>,
) : RecyclerView.Adapter<AdapterRecyclerView.ViewHolder>(){




    // create an inner class with name ViewHolder
    // It takes a view argument, in which pass the generated class of single_item.xml
    // ie SingleItemBinding and in the RecyclerView.ViewHolder(binding.root) pass it like this
    inner class ViewHolder(val binding: SingleItemBinding) : RecyclerView.ViewHolder(binding.root)

    // inside the onCreateViewHolder inflate the view of SingleItemBinding
    // and return new ViewHolder object containing this layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = SingleItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)

    }

    // bind the items with each item of the list contactsList which than will be
    // shown in recycler view
    // to keep it simple we are not setting any image data to view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder){
            with(contactsList[position]){
              //  val resourceId ="http://developer.alexanderklimov.ru/android/images/android_cat.jpg"
                val resourceId = this.photoAddress
                Glide
                    .with(binding.ivPhoto)
                    .load(resourceId)
                    .circleCrop()
                    .into(binding.ivPhoto)
                binding.tvName.text = this.name
                binding.tvCareer.text = this.career
            }
        }

 /*       // remove the item from recycler view
        holder.remove.setOnClickListener {
            contactsList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position,contactsList.size)
        }*/

    }



    // return the size of contactList
    override fun getItemCount(): Int {
        return contactsList.size

    }
/*
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.tvName
        val basket: ImageView = itemView.ivBasket
    }
*/


    // this two methods useful for avoiding duplicate item
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun getItemViewType(position: Int): Int {
        return position
    }

}

