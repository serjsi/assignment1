package com.shpp.ssierykh.assignment1.ui.edit_profile


import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.shpp.ssierykh.assignment1.R


import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import com.shpp.ssierykh.assignment1.model.Contact
import com.shpp.ssierykh.assignment1.databinding.DialogAddOrEditContactProfileBinding
import com.shpp.ssierykh.assignment1.ui.fragment_util.factory
import com.shpp.ssierykh.assignment1.ui.fragment_util.routing
import com.shpp.ssierykh.assignment1.utils.Validators.isValidateEmail


class AddOrEditContactsDialogFragment() :
    DialogFragment() {



    private lateinit var binding: DialogAddOrEditContactProfileBinding

    private val imageAvatar = 0

    private val viewModel: AddOrEditContactsViewModel by viewModels { factory() }

/*    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadUser(requireArguments().getString(AddOrEditContactsDialogFragment.ARG_EMAIL_ID))
    }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DialogAddOrEditContactProfileBinding.inflate(inflater, container, false)
        binding.ivArrowBack.setOnClickListener { goBackMyProfile() }
    //    val viewModel: MyProfileViewModel by activityViewModels()
        setDataContact(viewModel)
        saveContact(viewModel)

        setPhotoProfil()

        return binding.root

    }

    override fun onResume() {
        super.onResume()
        val params: WindowManager.LayoutParams? = dialog?.window?.attributes
        params?.width = WindowManager.LayoutParams.MATCH_PARENT
        params?.height = WindowManager.LayoutParams.MATCH_PARENT
        dialog?.onWindowAttributesChanged(params)
    }


    private fun goBackMyProfile() {
        routing().goBack()
    }


    private fun AddOrEditContactsDialogFragment.setPhotoProfil() {
        binding.ivAddPhoto.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_PICK

            ActivityResultContracts.TakePicture()
            startActivityForResult(
                Intent.createChooser(intent, "Select Picture"),
                imageAvatar
            )

        }
    }


    private fun saveContact(viewModel: AddOrEditContactsViewModel) {
        binding.apply {
            etEmailA.doOnTextChanged { _, _, _, _ -> isValidateEmail() }

            btSave.setOnClickListener {

                /*   val selectedDate =
                       ContactForRecyclerView(Constants.PHOTO_FAKE_1, userName, career)*/

                viewModel.setContact(Contact( etEmailA.text.toString(),"dsf",
                    etUserName.text.toString(),etCareer.text.toString(),etAddress.text.toString()))

                    routing().goBack()


            }
        }
    }

    private fun setDataContact(viewModel: AddOrEditContactsViewModel) {

       /* val profileObserver2 = Observer<Contact> { profilContact ->
            // Update the UI, in this case, a TextView.
            binding.apply {
              ivPhotoProfile.loadImageGlade(profilContact.photoAddress)
              etUserName.text= profilContact.name
               etCareer.text = profilContact.career
               etAddress.text = profilContact.home
            }
        }
        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        viewModel.profilContact.observe(this.viewLifecycleOwner, profileObserver2)*/
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