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
import com.shpp.ssierykh.assignment1.data.Contact
import com.shpp.ssierykh.assignment1.databinding.FragmentMyProfileBinding
import com.shpp.ssierykh.assignment1.ui.contract.routing
import com.shpp.ssierykh.assignment1.utils.Constants.CAREER_BANDLE_KEY
import com.shpp.ssierykh.assignment1.utils.Constants.EMAIL_BANDLE_KEY
import com.shpp.ssierykh.assignment1.utils.Constants.HOME_BANDLE_KEY
import com.shpp.ssierykh.assignment1.utils.Constants.NAME_BANDLE_KEY
import com.shpp.ssierykh.assignment1.utils.Constants.PHOTO_BANDLE_KEY
import com.shpp.ssierykh.assignment1.utils.Constants.REQEUST_KEY_USER


class MyProfileFragment : Fragment() {
    private var email = ""
    private var photo = ""
    private var name = ""
    private var career = ""
    private var home = ""

    private lateinit var binding: FragmentMyProfileBinding

 //var profile =Contact("","","","","")
    private var start = false //TODO/////////////////////////////////


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel: MyProfileViewModel by activityViewModels()
        super.onCreate(savedInstanceState)
        binding = FragmentMyProfileBinding.inflate(inflater, container, false)


          // getFragmentResult(viewModel)


        // Create the observer which updates the UI.
        val profileObserver = Observer<Contact> { profilContactNew ->
            // Update the UI, in this case, a TextView.
            //  binding.ivPhotoProfile.loadImageGlade(profilContactNew.photoAddress)
            binding.tvName.text = profilContactNew.email
            binding.tvCareer.text = profilContactNew.career
            binding.tvHomeAddress.text = profilContactNew.career
            Log.d("MyProfileFragment", "Observer")///TODO//////////////////////////////////////////
        }
        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        viewModel.profilContact.observe(viewLifecycleOwner, profileObserver)

        binding.apply {
            tvSettings.setOnClickListener { onOpenSignScreen() }
            btEditProfile.setOnClickListener { onOpenEditProfile() }
            btViewMyContacts.setOnClickListener { onOpenMyContacts() }
        }


        return binding.root

    }



/*    override fun onResume() {
        super.onResume()
        start = true
        profile = Contact(email, photo, name, career, home)
        Log.d("Fragment1", "onResume${profile.email}")
    }


    private fun getFragmentResult(viewModel: MyProfileViewModel) {

        setFragmentResultListener(REQEUST_KEY_USER) { key, bundle ->
            photo = bundle.getInt(PHOTO_BANDLE_KEY).toString()
            email = bundle.getString(EMAIL_BANDLE_KEY) ?: ""
            name = bundle.getString(NAME_BANDLE_KEY) ?: ""
            career = bundle.getString(CAREER_BANDLE_KEY) ?: ""
            home = bundle.getString(HOME_BANDLE_KEY) ?: ""
            Log.d(
                "MyProfileFragment",
                "onsetFragmentResultListener$email"
            )///TODO//////////////////////////////////////////

        }
        Log.d(
            "MyProfileFragment",
            "receiverBandl$email"
        )///TODO//////////////////////////////////////////


    }*/


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


