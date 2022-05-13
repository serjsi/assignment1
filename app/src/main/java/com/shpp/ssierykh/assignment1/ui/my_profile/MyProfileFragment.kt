package com.shpp.ssierykh.assignment1.ui.my_profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.shpp.ssierykh.assignment1.R
import com.shpp.ssierykh.assignment1.databinding.FragmentMyProfileBinding
import com.shpp.ssierykh.assignment1.ui.edit_profile.AddOrEditContactsViewModel
import com.shpp.ssierykh.assignment1.ui.fragment_util.factory
import com.shpp.ssierykh.assignment1.utils.SwitchNavigationGraph.featureNavigationEnabled
import com.shpp.ssierykh.assignment1.ui.fragment_util.routing

import com.shpp.ssierykh.assignment1.utils.extensions.loadImageGlade
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
            btEditProfile.setOnClickListener { onOpenEditProfile() }
            btViewMyContacts.setOnClickListener { onOpenMyContacts() }

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
                .collect()
        }
    }

    private fun onOpenMyContacts() {
        if (featureNavigationEnabled) {
            findNavController().navigate(
                R.id.action_myProfileFragmentGraph_to_myContactsFragmentGraph
            )
        } else routing().showMyContacts()
    }

    private fun onOpenEditProfile() {
        if (featureNavigationEnabled) {
            findNavController().navigate(
               MyProfileFragmentDirections.
               actionMyProfileFragmentGraphToAddOrEditContactsDialogFragmentGraph(
                   "AlainaWalters@mail.ua") //TODO
            )
        } //else routing().showAddOrEditContacts( viewModel.myContact())
    }

}