package com.shpp.ssierykh.assignment1.ui.contact_profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.shpp.ssierykh.assignment1.databinding.FragmentContactProfileBinding
import com.shpp.ssierykh.assignment1.utils.fragment_util.factory
import com.shpp.ssierykh.assignment1.utils.SwitchNavigationGraph.featureNavigationEnabled

import com.shpp.ssierykh.assignment1.utils.fragment_util.routing


import com.shpp.ssierykh.assignment1.utils.extensions.loadImageGlade
import com.shpp.ssierykh.assignment1.utils.extensions.toast

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach


class ContactProfileFragment : Fragment() {

    private lateinit var binding: FragmentContactProfileBinding
    private val viewModel: ContactProfileViewModel by viewModels { factory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (featureNavigationEnabled) {
            val args: ContactProfileFragmentArgs by navArgs()
            viewModel.loadContact(args.contactArg)
        } else viewModel.loadContact(requireArguments().getString(ARG_EMAIL_ID))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        binding = FragmentContactProfileBinding.inflate(layoutInflater, container, false)

        setDataProfile()

        binding.apply {
            ivArrowBack.setOnClickListener { onArrowBack() }
            btMessage.setOnClickListener { toast("test message") } //TODO deleting
        }
        return binding.root

    }

    private fun setDataProfile() {
        lifecycleScope.launchWhenStarted {
            viewModel.profilContact.onEach { profilContact ->
                // Update the UI, in this case, a TextView.
                binding.apply {
                    ivPhotoProfile.loadImageGlade(profilContact.photoAddress)
                    tvName.text = profilContact.name
                    tvCareer.text = profilContact.career
                    tvHomeAddress.text = profilContact.home
                }
            }
                .collect()
        }
    }

    companion object {
        private const val ARG_EMAIL_ID = "ARG_EMAIL_ID"
        fun newInstance(emailId: String): ContactProfileFragment {
            val fragment = ContactProfileFragment()
            fragment.arguments = bundleOf(ARG_EMAIL_ID to emailId)
            return fragment
        }

    }

    private fun onArrowBack() {
        routing().goBack()
    }
}


