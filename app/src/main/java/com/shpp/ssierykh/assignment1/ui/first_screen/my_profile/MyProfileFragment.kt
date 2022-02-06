package com.shpp.ssierykh.assignment1.ui.first_screen.my_profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.shpp.ssierykh.assignment1.databinding.FragmentMyProfileBinding
import com.shpp.ssierykh.assignment1.ui.contract.routing
import com.shpp.ssierykh.assignment1.utils.Constants.CAREER_BANDLE_KEY
import com.shpp.ssierykh.assignment1.utils.Constants.EMAIL_BANDLE_KEY
import com.shpp.ssierykh.assignment1.utils.Constants.HOME_BANDLE_KEY
import com.shpp.ssierykh.assignment1.utils.Constants.PHOTO_BANDLE_KEY
import com.shpp.ssierykh.assignment1.utils.Constants.REQEUST_KEY_USER
import com.shpp.ssierykh.assignment1.utils.ParsingEmailToName.parsingEmailToName
import com.shpp.ssierykh.assignment1.utils.extensions.loadImageGlade


class MyProfileFragment : Fragment() {

/*
    val viewModel = ViewModelProvider(this, SavedStateVMFactory(this))
        .get(MyProfileViewModel::class.java)*/

    private lateinit var binding: FragmentMyProfileBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(REQEUST_KEY_USER) { key, bundle ->

            binding.tvName.text = parsingEmailToName(bundle.getString(EMAIL_BANDLE_KEY) ?: "")
            binding.ivPhotoProfile.loadImageGlade( bundle.getInt(PHOTO_BANDLE_KEY ))
            binding.tvCareer.text = bundle.getString(CAREER_BANDLE_KEY ) ?: ""
            binding.tvHomeAddress.text = bundle.getString(HOME_BANDLE_KEY ) ?: ""
            //       setListener()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyProfileBinding.inflate(inflater, container, false)



        binding.apply {
            tvSettings.setOnClickListener { onOpenSignScreen() }
            btEditProfile.setOnClickListener { onOpenEditProfile() }
            btViewMyContacts.setOnClickListener { onOpenMyContacts() }
        }
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