package com.shpp.ssierykh.assignment1.screens.first_screen.my_profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.shpp.ssierykh.assignment1.R
import com.shpp.ssierykh.assignment1.databinding.FragmentMyProfileBinding
import com.shpp.ssierykh.assignment1.screens.SwitchNavigationGraph.featureNavigationEnabled
import com.shpp.ssierykh.assignment1.screens.contract.routing

import com.shpp.ssierykh.assignment1.utils.extensions.loadImageGlade
import com.shpp.ssierykh.assignment1.utils.extensions.toast
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach


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
            ivArrowBack.visibility =View.GONE
            tvProfile.visibility=View.GONE
        }
        return binding.root

    }

    private fun setDataContact(viewModel: MyProfileViewModel) {
        // Create the observer which updates the UI.
        //LiveData
     /*   val profileObserver = Observer<Contact> { profilContactNew ->
            // Update the UI, in this case, a TextView.
            binding.apply {
                ivPhotoProfile.loadImageGlade(profilContactNew.photoAddress)
                tvName.text = profilContactNew.name
                tvCareer.text = profilContactNew.career
                tvHomeAddress.text = profilContactNew.home
            }
        }
        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        viewModel.profilContact.observe(viewLifecycleOwner, profileObserver)*/

        //Flow
        lifecycleScope.launchWhenStarted {
            viewModel.profilContact.onEach { profilContactNew ->
                // Update the UI, in this case, a TextView.
                binding.apply {
                    ivPhotoProfile.loadImageGlade(profilContactNew.photoAddress)
                    tvName.text = profilContactNew.name
                    tvCareer.text = profilContactNew.career
                    tvHomeAddress.text = profilContactNew.home
                }

            }
                .collect ()
        }
    }

    private fun onOpenMyContacts() {
        if (featureNavigationEnabled) {
            findNavController().navigate(
                R.id.action_myProfileFragmentGraph_to_myContactsFragmentGraph)
        }else routing().showMyContacts()
    }

    private fun onOpenEditProfile() {
        if (featureNavigationEnabled) {
            findNavController().navigate(
                R.id.action_myProfileFragmentGraph_to_addOrEditContactsDialogFragmentGraph
            )
        }else routing().showAddOrEditContacts()
    }

    private fun onOpenSignScreen() {
        if (featureNavigationEnabled) {
            toast("Go Sign Navigation")//TODO Delete////////////////////////////
            findNavController().navigate(
                R.id.action_myProfileFragmentGraph_to_signFragmentGraph,
            )
        }else  routing().showSignScreen()
    }

}


