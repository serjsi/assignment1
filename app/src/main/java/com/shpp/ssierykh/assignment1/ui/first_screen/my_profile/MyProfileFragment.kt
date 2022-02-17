package com.shpp.ssierykh.assignment1.ui.first_screen.my_profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.shpp.ssierykh.assignment1.R
import com.shpp.ssierykh.assignment1.data.Contact
import com.shpp.ssierykh.assignment1.databinding.FragmentMyProfileBinding
import com.shpp.ssierykh.assignment1.ui.SwitchNavigationGraph
import com.shpp.ssierykh.assignment1.ui.SwitchNavigationGraph.isNavigationGraph
import com.shpp.ssierykh.assignment1.ui.contract.routing

import com.shpp.ssierykh.assignment1.utils.Constants.EMAIL_BANDLE_KEY

import com.shpp.ssierykh.assignment1.utils.Constants.PHOTO_BANDLE_KEY
import com.shpp.ssierykh.assignment1.utils.Constants.REQEUST_KEY_USER
import com.shpp.ssierykh.assignment1.utils.extensions.loadImageGlade
import com.shpp.ssierykh.assignment1.utils.extensions.toast


class MyProfileFragment : Fragment() {

    private lateinit var binding: FragmentMyProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: MyProfileViewModel by activityViewModels()
        super.onCreate(savedInstanceState)
        binding = FragmentMyProfileBinding.inflate(inflater, container, false)

        setDataContact(viewModel)

        binding.apply {
            tvSettings.setOnClickListener { onOpenSignScreen() }
            btEditProfile.setOnClickListener { onOpenEditProfile() }
            btViewMyContacts.setOnClickListener { onOpenMyContacts() }
        }
        return binding.root

    }

    private fun setDataContact(viewModel: MyProfileViewModel) {
        // Create the observer which updates the UI.
        val profileObserver = Observer<Contact> { profilContactNew ->
            // Update the UI, in this case, a TextView.
            binding.apply {
                ivPhotoProfile.loadImageGlade(profilContactNew.photoAddress)
                tvName.text = profilContactNew.name
                tvCareer.text = profilContactNew.career
                tvHomeAddress.text = profilContactNew.home
            }
        }
        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        viewModel.profilContact.observe(viewLifecycleOwner, profileObserver)
    }

    private fun onOpenMyContacts() {
        if (isNavigationGraph) {
            findNavController().navigate(
                R.id.action_myProfileFragmentGraph_to_myContactsFragmentGraph)
        }else routing().showMyContacts()
    }

    private fun onOpenEditProfile() {
        if (isNavigationGraph) {
            findNavController().navigate(
                R.id.action_myProfileFragmentGraph_to_addOrEditContactsDialogFragmentGraph
            )
        }else routing().showAddOrEditContacts()
    }

    private fun onOpenSignScreen() {
        if (isNavigationGraph) {
            toast("Go Sign Navigation")//TODO Delete////////////////////////////
            findNavController().navigate(
                R.id.action_myProfileFragmentGraph_to_signFragmentGraph,
            )
        }else  routing().showSignScreen()
    }

}


