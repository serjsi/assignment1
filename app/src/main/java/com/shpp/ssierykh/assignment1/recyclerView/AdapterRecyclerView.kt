package com.shpp.ssierykh.assignment1.recyclerView


import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shpp.ssierykh.assignment1.databinding.SingleItemContactBinding
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.widget.AppCompatImageView


class AdapterRecyclerView(
    private var contactList: MutableList<ContactRecyclerView>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<AdapterRecyclerView.ViewHolder>() {


    // create an inner class with name ViewHolder
    // It takes a view argument, in which pass the generated class of single_item.xml
    // ie SingleItemContactsBinding and in the RecyclerView.ViewHolder(binding.root) pass it like this
    //inner class ViewHolder(val binding: SingleItemContactsBinding) : RecyclerView.ViewHolder(binding.root)
    inner class ViewHolder(val binding: SingleItemContactBinding) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    // inside the onCreateViewHolder inflate the view of SingleItemContactsBinding
    // and return new ViewHolder object containing this layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = SingleItemContactBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)


    }

    // bind the items with each item of the list contactsList which than will be
    // shown in recycler view
    // to keep it simple we are not setting any image data to view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder) {
            with(contactList[position]) {
                Glide
                        .with(binding.ivPhoto)
                        .load(this.photoAddress)
                        .circleCrop()
                        .into(binding.ivPhoto)

                binding.tvName.text = this.name
                binding.tvCareer.text = this.career

                binding.ivDelete.setOnClickListener {
                     deleteItem(position)
//                    onItemDelete(position)

                }
            }
        }
    }




    @SuppressLint("NotifyDataSetChanged")
    private fun deleteItem(index: Int) {

        val temp = contactList.get(index)
        contactList.removeAt(index)
        notifyDataSetChanged()

        object : CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                //   mTextField.setText("seconds remaining: " + millisUntilFinished / 1000)

            }

            override fun onFinish() {
                //  mTextField.setText("Time's finished!")
                contactList.add(index, temp)
                this@AdapterRecyclerView.notifyDataSetChanged()
            }
        }.start()
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

