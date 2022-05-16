package com.shpp.ssierykh.assignment1.ui.my_profile

import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.shpp.ssierykh.assignment1.R
import com.shpp.ssierykh.assignment1.databinding.FragmentMyProfileBinding
import com.shpp.ssierykh.assignment1.model.Contact
import com.shpp.ssierykh.assignment1.ui.edit_profile.EditProfileContactViewModel
import com.shpp.ssierykh.assignment1.utils.Constants.EMAIL_BANDLE_KEY
import com.shpp.ssierykh.assignment1.utils.Constants.REQEUST_KEY_USER
import com.shpp.ssierykh.assignment1.utils.SwitchNavigationGraph.featureNavigationEnabled
import com.shpp.ssierykh.assignment1.utils.fragment_util.routing

import com.shpp.ssierykh.assignment1.utils.extensions.loadImageGlade
import com.shpp.ssierykh.assignment1.utils.extensions.toast
import com.shpp.ssierykh.assignment1.utils.fragment_util.factory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach


class MyProfileFragment : Fragment() {

    private lateinit var binding: FragmentMyProfileBinding
    private lateinit var myContact: Contact
    private val viewModel: MyProfileViewModel by viewModels { factory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(REQEUST_KEY_USER) { _, bundle ->
            bundle.getString(EMAIL_BANDLE_KEY)?.let { viewModel.setContact(it) }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        binding = FragmentMyProfileBinding.inflate(inflater, container, false)

        setDataContact()

        binding.apply {
            btEditProfile.setOnClickListener { onOpenEditProfile() }
            btViewMyContacts.setOnClickListener { onOpenMyContacts() }

        }
        return binding.root

    }

    private fun setDataContact() {
        lifecycleScope.launchWhenStarted {

            viewModel.profilContact.onEach { profilContactNew ->
                // Update the UI, in this case, a TextView.
                myContact = profilContactNew
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
                MyProfileFragmentDirections.actionMyProfileFragmentGraphToAddOrEditContactsDialogFragmentGraph(
                    myContact.email
                )
            )
        } else routing().showEditProfileContact(myContact)
    }

}