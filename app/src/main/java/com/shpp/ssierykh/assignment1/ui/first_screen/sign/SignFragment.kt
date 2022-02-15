package com.shpp.ssierykh.assignment1.ui.first_screen.sign

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.widget.doOnTextChanged
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.shpp.ssierykh.assignment1.R
import com.shpp.ssierykh.assignment1.data.Contact
import com.shpp.ssierykh.assignment1.databinding.FragmentSignBinding
import com.shpp.ssierykh.assignment1.ui.SwitchNavigationGraph.isNavigationGraph
import com.shpp.ssierykh.assignment1.ui.contract.routing
import com.shpp.ssierykh.assignment1.ui.first_screen.my_profile.MyProfileViewModel
import com.shpp.ssierykh.assignment1.utils.Constants
import com.shpp.ssierykh.assignment1.utils.Constants.EMAIL_BANDLE_KEY
import com.shpp.ssierykh.assignment1.utils.Constants.PHOTO_BANDLE_KEY
import com.shpp.ssierykh.assignment1.utils.Constants.REQEUST_KEY_USER

import com.shpp.ssierykh.assignment1.utils.Validators.isValidateEmail
import com.shpp.ssierykh.assignment1.utils.Validators.isValidatePassword
import com.shpp.ssierykh.assignment1.utils.Validators.messageValidationPassword
import com.shpp.ssierykh.assignment1.utils.data_store.SaveLoginDataStore
import com.shpp.ssierykh.assignment1.utils.extensions.clickWithDebounce
import com.shpp.ssierykh.assignment1.utils.extensions.toast
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class SignFragment : Fragment() {
    private var pressRegistration = false

    private lateinit var binding: FragmentSignBinding
    private lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignBinding.inflate(inflater, container, false)
        val viewModel: MyProfileViewModel by activityViewModels()

        setupListeners(viewModel)
        // observeData()
        forTestMethod()

        return binding.root

    }


    private fun setupListeners(viewModel: MyProfileViewModel) {
        binding.apply {
            showMessageErrorAfterClicking()
            btRegister.setOnClickListener {
                checkingTextAfterClicking()
                if (isValidateEmail(etEmail) && isValidatePassword(etPassword)) {
                    viewModel.setContact(Contact(etEmail.text.toString()))
                    if (isNavigationGraph) {
                        toast("Go MyProfile Navigation")//TODO Delete////////////////////////////
                        findNavController().navigate(
                            R.id.action_signFragmentGraph_to_myProfileFragmentGraph,
                            null
                        )
                    } else
                        routing().showMyProfileScreen()
                    pressRegistration = false
                }
            }
        }
    }

    private fun FragmentSignBinding.checkingTextAfterClicking() {
        pressRegistration = true
        if (!isValidateEmail(etEmail)) tilEmail.error = getString(R.string.message_wromg_e_mail)
        if (!isValidatePassword(etPassword)) tilPassword.error =
            getString(messageValidationPassword(etPassword))
    }

    private fun FragmentSignBinding.showMessageErrorAfterClicking() {
        etEmail.doOnTextChanged { _, _, _, _ ->
            if (!isValidateEmail(etEmail) && pressRegistration) {
                tilEmail.error = getString(R.string.message_wromg_e_mail)
            } else tilEmail.isErrorEnabled = false
        }

        etPassword.doOnTextChanged { _, _, _, _ ->
            if (!isValidatePassword(etPassword) && pressRegistration) {
                tilPassword.error = getString(messageValidationPassword(etPassword))
            } else tilPassword.isErrorEnabled = false
        }
    }


/*    private var autologin = SaveLoginDataStore(da)

    //Stores the values
    @DelicateCoroutinesApi
    fun writeLoginDataStore(email: String, password: String, remember: Boolean) {
        if (remember) {
            GlobalScope.launch {
                autologin.storeUser(email, password, remember)
            }

        } else {
            GlobalScope.launch {
                autologin.storeUser("", "", remember)

            }

        }
    }
    @SuppressLint("FragmentLiveDataObserve")
    private fun observeData() {
        //Check ChekBox
        autologin.userRememberFlow.asLiveData().observe(this, {
            if (it == true) {
                //Updates remember
                binding.cbRemember.isChecked = it

                //Updates email
                autologin.userEmailFlow.asLiveData().observe(this, { email ->
                    if (email != null) {
                        binding.etEmail.setText(email, TextView.BufferType.EDITABLE)
                    }
                })

                //Updates password
                autologin.userPasswordFlow.asLiveData().observe(this, { password ->
                    if (password != null) {
                        binding.etPassword.setText(
                            password,
                            TextView.BufferType.EDITABLE
                        )
                    }
                })

            }
        })
    }*/

    ////////////////////////////////test method////////////////////////////////////////
    private fun forTestMethod() {
        binding.apply {
            tvSignIn.clickWithDebounce {
                etEmail.setText(Constants.TEST_EMAIL, TextView.BufferType.EDITABLE)
                etPassword.setText(Constants.TEST_PASSWORD, TextView.BufferType.EDITABLE)
            }

        }

        setFragmentResult(
            REQEUST_KEY_USER,
            bundleOf(
                EMAIL_BANDLE_KEY to binding.etEmail.text.toString(),
                PHOTO_BANDLE_KEY to R.drawable.lucile
            )
        )
    }
    //////////////////////////////////////////////////////////////////////////////////

}