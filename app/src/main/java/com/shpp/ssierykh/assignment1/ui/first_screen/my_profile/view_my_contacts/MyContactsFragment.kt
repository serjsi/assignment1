package com.shpp.ssierykh.assignment1.ui.first_screen.my_profile.view_my_contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider

import com.shpp.ssierykh.assignment1.databinding.FragmentMyContactsBinding
import com.shpp.ssierykh.assignment1.model.BaseContacts
import com.shpp.ssierykh.assignment1.ui.contract.routing
import java.util.Observer


class MyContactsFragment(val baseContacts: BaseContacts) : Fragment() {
    private lateinit var binding: FragmentMyContactsBinding
    private lateinit var adapter: AdapterContactsList
    private lateinit var vm: MyContactsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyContactsBinding.inflate(inflater, container, false)
            //  val vm: MyContactsViewModel by viewModels()
        vm = ViewModelProvider(this, MyContactsViewModelFactory(baseContacts))
            .get(MyContactsViewModel::class.java)

        // initialize the adapter, and pass the required argument

        adapter = AdapterContactsList(vm)

        // attach adapter to the recycler view
        binding.rvBottomContainer.adapter = adapter

    /*    vm.contacts.observe(viewLifecycleOwner, Observer{
            adapter.contacts = baseContacts.loadContacts()
        })*/

        binding.ivArrowBack.setOnClickListener { onArrowBack() }
        binding.tvAddContacts.setOnClickListener { onAddContact() }
        return binding.root
    }




    private fun onAddContact() {
        routing().showAddOrEditContacts()
    }

    private fun onArrowBack() {
        routing().goBack()
    }
}