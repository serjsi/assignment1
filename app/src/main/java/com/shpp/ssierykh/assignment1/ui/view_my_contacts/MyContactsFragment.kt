package com.shpp.ssierykh.assignment1.ui.view_my_contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.shpp.ssierykh.assignment1.R

import com.shpp.ssierykh.assignment1.databinding.FragmentMyContactsBinding
import com.shpp.ssierykh.assignment1.utils.fragment_util.factory

import com.shpp.ssierykh.assignment1.utils.fragment_util.routing
import com.shpp.ssierykh.assignment1.utils.SwitchNavigationGraph

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


        viewModel.contacts.observe(viewLifecycleOwner) {
            adapter.contacts = it
        }

        viewModel.actionShowSnackbar.observe(viewLifecycleOwner) {
            showSnackBarDeleteContact(it)
        }

        viewModel.actionShowDetails.observe(viewLifecycleOwner) {
            it?.let {
                if (SwitchNavigationGraph.featureNavigationEnabled) {
                      val emailID = it.email
                        findNavController().navigate(
                            MyContactsFragmentDirections.
                            actionMyContactsFragmentGraphToContactProfileFragment(emailID))
                } else  routing().showContactProfile(it)
            }
        }

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

    private fun showSnackBarDeleteContact(nameDeleteContact: String?) {
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
        if (SwitchNavigationGraph.featureNavigationEnabled) {
            findNavController().navigate(
                MyContactsFragmentDirections.
                actionMyContactsFragmentGraphToAddOrEditContactsDialogFragmentGraph3(null))
        } else routing().showEditProfileContact()
    }

    private fun onArrowBack() {
        routing().goBack()
    }


}



