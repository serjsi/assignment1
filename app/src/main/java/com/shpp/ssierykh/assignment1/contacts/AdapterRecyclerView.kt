package com.shpp.ssierykh.assignment1.contacts


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shpp.ssierykh.assignment1.databinding.SingleItemContactBinding
import android.view.View
import com.shpp.ssierykh.assignment1.extensions.OutImages.loadImageGlade



class AdapterRecyclerView(
    private var contactList: MutableList<ContactRecyclerView>,
    private val listener: OnItemClickListener,
) :  RecyclerView.Adapter<AdapterRecyclerView.ViewHolder>()  {


    // create an inner class with name ViewHolder
    // It takes a view argument, in which pass the generated class of single_item.xml
    // ie SingleItemContactsBinding and in the RecyclerView.ViewHolder(binding.root) pass it like this
    //inner class ViewHolder(val binding: SingleItemContactsBinding) : RecyclerView.ViewHolder(binding.root)
    inner class ViewHolder(val binding: SingleItemContactBinding) :
        RecyclerView.ViewHolder(binding.root),
    View.OnClickListener {



        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            if (absoluteAdapterPosition != RecyclerView.NO_POSITION) {
                listener.onItemClick(absoluteAdapterPosition)
            }
        }



    }

    // inside the onCreateViewHolder inflate the view of SingleItemContactsBinding
    // and return new ViewHolder object containing this layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView = SingleItemContactBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemView)

    }

    // bind the items with each item of the list contactsList which than will be
    // shown in recycler view
    // to keep it simple we are not setting any image data to view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder) {
            with(contactList[position]) {
               binding.ivPhoto.loadImageGlade(photoAddress)
              //  binding.ivPhoto.loadImagePicasso(photoAddress)

                binding.tvName.text = this.name
                binding.tvCareer.text = this.career

                binding.ivDelete.setOnClickListener {
                    listener.onItemDelete(position)
                }
            }
        }
    }


    // return the size of contactList
    override fun getItemCount(): Int {
        return contactList.size
    }



    override fun getItemViewType(position: Int): Int {
        return position
    }



    interface OnItemClickListener {
        fun onItemClick(position: Int)
        fun onItemDelete(position: Int)
    }



}



