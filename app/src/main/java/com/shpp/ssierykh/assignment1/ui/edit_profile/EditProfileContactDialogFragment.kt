package com.shpp.ssierykh.assignment1.ui.edit_profile


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import androidx.core.os.bundleOf
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.shpp.ssierykh.assignment1.R
import com.shpp.ssierykh.assignment1.databinding.FragmentSignBinding
import com.shpp.ssierykh.assignment1.model.Contact
import com.shpp.ssierykh.assignment1.navigate.routing
import com.shpp.ssierykh.assignment1.utils.SwitchNavigationGraph
import com.shpp.ssierykh.assignment1.utils.Validators
import com.shpp.ssierykh.assignment1.utils.Validators.isValidateEmail
import com.shpp.ssierykh.assignment1.utils.extensions.loadImageGlade
import com.shpp.ssierykh.assignment1.utils.fragment_util.factory
import kotlinx.android.synthetic.main.fragment_sign.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import com.shpp.ssierykh.assignment1.databinding.EditContactProfileBinding as EditContactProfileBinding1


class EditProfileContactDialogFragment : DialogFragment() {


    private lateinit var binding: EditContactProfileBinding1
    private var imageUri: Uri? = null
    private var pressSaveContact: Boolean = false

    private val viewModel: EditProfileContactViewModel by viewModels { factory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (SwitchNavigationGraph.featureNavigationEnabled) {
            val args: EditProfileContactDialogFragmentArgs by navArgs()
            args.contactArg?.let { viewModel.loadContact(args.contactArg) }
        } else savedInstanceState?.let {
            viewModel.loadContact(requireArguments().getString(ARG_EMAIL_ID))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = EditContactProfileBinding1.inflate(inflater, container, false)
        binding.ivArrowBack.setOnClickListener { routing().goBack() }
        imageUri = viewModel.takePhoto().toUri()

        setDataContact(viewModel)

        saveContact(viewModel)

        getImageFromGallery()

        isVisibleNameFragment()

        return binding.root

    }

    override fun onResume() {
        super.onResume()
        val params: WindowManager.LayoutParams? = dialog?.window?.attributes
        params?.width = WindowManager.LayoutParams.MATCH_PARENT
        params?.height = WindowManager.LayoutParams.MATCH_PARENT
        dialog?.onWindowAttributesChanged(params)
    }

    private fun isVisibleNameFragment() {
        binding.tvAddContacts.visibility = if (!viewModel.getVisible()) View.VISIBLE else View.GONE
        binding.tvEditProfile.visibility = if (viewModel.getVisible()) View.VISIBLE else View.GONE
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


    private fun saveContact(viewModel: EditProfileContactViewModel) {
        binding.apply {
            showMessageErrorAfterClicking()
            btSave.setOnClickListener {
                    checkingTextAfterClicking()
                if (isValidateEmail(etEmail)) {
                    viewModel.setContact(Contact(
                            etEmail.text.toString(),
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
    }

    private fun setDataContact(viewModel: EditProfileContactViewModel) {
        lifecycleScope.launchWhenStarted {
            viewModel.profilContact.onEach { data ->
                binding.apply {
                    ivPhotoProfile.loadImageGlade(data.photoAddress)
                    etUserName.setText(data.name)
                    etCareer.setText(data.career)
                    etEmail.setText(data.email)
                    etAddress.setText(data.home)
                }
            }
                .collect()
        }
    }




//TODO Corrected//////////////////////////////////////////////////////////////

    private fun EditContactProfileBinding1.checkingTextAfterClicking() {
        pressSaveContact = true
        if (!isValidateEmail(etEmail)) tilEmail.error = getString(R.string.message_wromg_e_mail)

    }

    private fun EditContactProfileBinding1.showMessageErrorAfterClicking() {

        etEmail.doOnTextChanged { _, _, _, _ ->
            if (!isValidateEmail(etEmail) && pressSaveContact) {
                tilEmail.error = getString(R.string.message_wromg_e_mail)
            } else tilEmail.isErrorEnabled = false
        }

        /*       etPassword.doOnTextChanged { _, _, _, _ ->
                   if (!Validators.isValidatePassword(etPassword) && pressSaveContact) {
                       tilPassword.error = getString(Validators.messageValidationPassword(etPassword))
                   } else tilPassword.isErrorEnabled = false
               }*/
    }

    companion object {

        private const val ARG_EMAIL_ID = "ARG_EMAIL_ID"
        fun newInstance(emailId: String): EditProfileContactDialogFragment {
            val fragment = EditProfileContactDialogFragment()
            fragment.arguments = bundleOf(ARG_EMAIL_ID to emailId)
            return fragment
        }

    }

}


