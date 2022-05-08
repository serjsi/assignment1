package com.shpp.ssierykh.assignment1.screens.first_screen.my_profile.view_my_contacts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.shpp.ssierykh.assignment1.R

import com.shpp.ssierykh.assignment1.databinding.FragmentMyContactsBinding
import com.shpp.ssierykh.assignment1.model.Contact

import com.shpp.ssierykh.assignment1.screens.contract.routing
import com.shpp.ssierykh.assignment1.screens.factory
import com.shpp.ssierykh.assignment1.utils.extensions.toast


class MyContactsFragment() : Fragment() {
    private lateinit var binding: FragmentMyContactsBinding
    private lateinit var adapter: AdapterContactsList


    private val viewModel: MyContactsViewModel by viewModels { factory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyContactsBinding.inflate(inflater, container, false)
        adapter = AdapterContactsList(viewModel)


        viewModel.contacts.observe(viewLifecycleOwner, Observer {
            Log.d("test","!---------------------LOAD ConTACT")
            adapter.contacts = it
        })

        viewModel.actionShowSnackbar.observe(viewLifecycleOwner, Observer {
            showSnackbarDeleteContact( it)
        })

        viewModel.actionShowDetails.observe(viewLifecycleOwner, Observer {
            it?.let { it1 -> routing().showContactProfile(it1) }

        })

        binding.apply {
            ivArrowBack.setOnClickListener { onArrowBack() }
            tvAddContacts.setOnClickListener { onAddContact() }

            // attach adapter to the recycler view
            val layoutManager = LinearLayoutManager(requireContext())
            rvBottomContainer.layoutManager = layoutManager
            rvBottomContainer.adapter = adapter
        }
        viewModel.swipeDeleteItem(binding)

        return binding.root
    }

    private fun showSnackbarDeleteContact( nameDeleteContact: String?) {
        Snackbar.make(
            binding.rvBottomContainer,
            "${getString(R.string.Contact)}  $nameDeleteContact ${getString(R.string.is_deleted)} ",
            Snackbar.LENGTH_LONG
        ).setAction(getString(R.string.UNDO)) {
                viewModel. restoreLastDeletingContact()
                this.toast(
                " ${getString(R.string.Contact)}  $nameDeleteContact" +
                        " ${getString(R.string.is_restored)}"
            )
        }.apply { show()}
    }


    private fun onAddContact() {
        routing().showAddOrEditContacts()
    }

    private fun onArrowBack() {
        routing().goBack()
    }


}



