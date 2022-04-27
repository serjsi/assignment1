package com.shpp.ssierykh.assignment1.ui.first_screen.my_profile.view_my_contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.shpp.ssierykh.assignment1.databinding.FragmentMyContactsBinding
import com.shpp.ssierykh.assignment1.ui.contract.routing


class MyContactsFragment() : Fragment() {
    private lateinit var binding: FragmentMyContactsBinding
    private lateinit var adapter: AdapterContactsList


    private val viewModel : MyContactsViewModel by viewModels { factory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyContactsBinding.inflate(inflater, container, false)

        adapter = AdapterContactsList(viewModel)


        viewModel.contacts.observe(viewLifecycleOwner, Observer {
          adapter.contacts = it

        })


        binding.ivArrowBack.setOnClickListener { onArrowBack() }
        binding.tvAddContacts.setOnClickListener { onAddContact() }

        // attach adapter to the recycler view
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvBottomContainer.layoutManager = layoutManager
        binding.rvBottomContainer.adapter = adapter

        viewModel.swipeDeleteItem(binding)

        return binding.root

    }


    private fun onAddContact() {
        routing().showAddOrEditContacts()
    }

    private fun onArrowBack() {
        routing().goBack()
    }


}

