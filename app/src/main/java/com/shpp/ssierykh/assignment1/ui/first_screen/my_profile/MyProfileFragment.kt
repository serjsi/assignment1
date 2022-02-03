package com.shpp.ssierykh.assignment1.ui.first_screen.my_profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.shpp.ssierykh.assignment1.databinding.FragmentMyProfileBinding
import com.shpp.ssierykh.assignment1.ui.contract.routing


class MyProfileFragment : Fragment() {


    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        options = savedInstanceState?.getParcelable(KEY_OPTIONS) ?: Options.DEFAULT
    }*/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMyProfileBinding.inflate(inflater, container, false)

        /*   navigator().listenResult(Options::class.java, viewLifecycleOwner) {
               this.options = it
           }*/

        binding.tvSettings.setOnClickListener { onOpenSignScreen() }
        binding.btEditProfile.setOnClickListener { onOpenEditProfile() }
        binding.btViewMyContacts.setOnClickListener{onOpenMyContacts()}

        return binding.root
    }

    private fun onOpenMyContacts() {
        routing().showMyContacts()
    }

    private fun onOpenEditProfile() {
        routing().showAddOrEditContacts()
    }

    private fun onOpenSignScreen() {
        routing().showSignScreen()
    }


}