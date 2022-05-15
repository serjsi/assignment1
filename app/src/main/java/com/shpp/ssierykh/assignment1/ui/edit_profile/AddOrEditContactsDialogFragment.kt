package com.shpp.ssierykh.assignment1.ui.edit_profile


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.shpp.ssierykh.assignment1.R
import com.shpp.ssierykh.assignment1.databinding.DialogAddOrEditContactProfileBinding
import com.shpp.ssierykh.assignment1.model.Contact
import com.shpp.ssierykh.assignment1.utils.SwitchNavigationGraph
import com.shpp.ssierykh.assignment1.utils.Validators.isValidateEmail
import com.shpp.ssierykh.assignment1.utils.extensions.loadImageGlade
import com.shpp.ssierykh.assignment1.utils.fragment_util.factory
import com.shpp.ssierykh.assignment1.utils.fragment_util.routing
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach


class AddOrEditContactsDialogFragment() :
    DialogFragment() {


    private lateinit var binding: DialogAddOrEditContactProfileBinding
    private var imageUri: Uri? = null


    private val viewModel: AddOrEditContactsViewModel by viewModels { factory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (SwitchNavigationGraph.featureNavigationEnabled) {
            val args: AddOrEditContactsDialogFragmentArgs by navArgs()
            args.contactArg?.let { viewModel.loadContact(args.contactArg) }
        } else savedInstanceState?.let {
            viewModel.loadContact(requireArguments().getString(ARG_EMAIL_ID))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DialogAddOrEditContactProfileBinding.inflate(inflater, container, false)
        binding.ivArrowBack.setOnClickListener { routing().goBack() }

        setDataContact(viewModel)
        saveContact(viewModel)

        getImageFromGallery()

        return binding.root

    }

    override fun onResume() {
        super.onResume()
        val params: WindowManager.LayoutParams? = dialog?.window?.attributes
        params?.width = WindowManager.LayoutParams.MATCH_PARENT
        params?.height = WindowManager.LayoutParams.MATCH_PARENT
        dialog?.onWindowAttributesChanged(params)
    }



    private fun getImageFromGallery() {
        val imagePickerActivityResult: ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            result?.let {
                imageUri = result.data?.data
                viewModel.setPhotoProfile(imageUri.toString())
            }
        }

        binding.ivAddPhoto.setOnClickListener {
            val galleryIntent = Intent(Intent.ACTION_PICK)
            galleryIntent.type = "image/*"
            imagePickerActivityResult.launch(galleryIntent)
        }
    }


    private fun saveContact(viewModel: AddOrEditContactsViewModel) {
        binding.apply {
            etEmailA.doOnTextChanged { _, _, _, _ -> isValidateEmail() }

            btSave.setOnClickListener {
                viewModel.setContact(
                    Contact(
                        etEmailA.text.toString(),
                        imageUri.toString(),
                        etUserName.text.toString(),
                        etCareer.text.toString(),
                        etAddress.text.toString()
                    )
                )
                routing().goBack()
            }
        }
    }

    private fun setDataContact(viewModel: AddOrEditContactsViewModel) {
        lifecycleScope.launchWhenStarted {
            viewModel.profilContact.onEach { data ->
                // Update the UI, in this case, a TextView.
                binding.apply {
//                    ivPhotoProfile.loadImageGlade(data.photoAddress)
                    ivPhotoProfile.loadImageGlade(data.photoAddress)
                    etUserName.setText(data.name)
                    etCareer.setText(data.career)
                    etEmailA.setText(data.email)
                    etAddress.setText(data.home)
                }
            }
                .collect()
        }
    }


//TODO Corrected//////////////////////////////////////////////////////////////
    /**
     * Checking validate E-mail
     */
    private fun isValidateEmail(): Boolean {
        binding.apply {

            val emailCheck = etEmailA.text.toString()
            when {
                emailCheck.isEmpty() -> {
                    tilEmail.error = getString(R.string.message_cannot_be_empty)
                    etEmailA.requestFocus()
                    return false
                }
                !isValidateEmail(etEmailA) -> {
                    tilEmail.error = getString(R.string.message_wromg_e_mail)
                    etEmailA.requestFocus()
                    return false
                }
                else -> {
                    tilEmail.isErrorEnabled = false
                    return true
                }
            }

        }
    }

    companion object {

        private const val ARG_EMAIL_ID = "ARG_EMAIL_ID"
        fun newInstance(emailId: String): AddOrEditContactsDialogFragment {
            val fragment = AddOrEditContactsDialogFragment()
            fragment.arguments = bundleOf(ARG_EMAIL_ID to emailId)
            return fragment
        }

    }

}


