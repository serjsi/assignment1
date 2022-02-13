package com.shpp.ssierykh.assignment1.ui.first_screen.my_profile.edit_profile


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
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import com.shpp.ssierykh.assignment1.data.Contact
import com.shpp.ssierykh.assignment1.databinding.DialogAddOrEditContactProfileBinding
import com.shpp.ssierykh.assignment1.ui.contract.routing
import com.shpp.ssierykh.assignment1.ui.first_screen.my_profile.MyProfileViewModel
import com.shpp.ssierykh.assignment1.utils.Constants.CAREER_BANDLE_KEY
import com.shpp.ssierykh.assignment1.utils.Constants.EMAIL_BANDLE_KEY
import com.shpp.ssierykh.assignment1.utils.Constants.HOME_BANDLE_KEY
import com.shpp.ssierykh.assignment1.utils.Constants.PHOTO_BANDLE_KEY
import com.shpp.ssierykh.assignment1.utils.Constants.REQEUST_KEY_USER
import com.shpp.ssierykh.assignment1.utils.Validators.isValidateEmail


class AddOrEditContactsDialogFragment() :
    DialogFragment() {


    interface OnAddContactListener {
        fun onAddContact(addItem: Contact)
    }


    private lateinit var binding: DialogAddOrEditContactProfileBinding

    private val imageAvatar = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = DialogAddOrEditContactProfileBinding.inflate(inflater, container, false)
        binding.ivArrowBack.setOnClickListener { goBackMyProfile() }

        saveContact()

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


    private fun saveContact() {
        binding.apply {
            etEmailA.doOnTextChanged { _, _, _, _ -> isValidateEmail() }

            btSave.setOnClickListener {

                /*   val selectedDate =
                       ContactForRecyclerView(Constants.PHOTO_FAKE_1, userName, career)*/

                val viewModel: MyProfileViewModel by activityViewModels()
                viewModel.setContact(Contact( etEmailA.text.toString(),"",
                    etUserName.text.toString(),etCareer.text.toString(),etAddress.text.toString()))

             /*   setFragmentResult(
                    REQEUST_KEY_USER,
                    bundleOf(
                        EMAIL_BANDLE_KEY to etUserName.text.toString(),
                        PHOTO_BANDLE_KEY to R.drawable.lucile,
                        CAREER_BANDLE_KEY to etCareer.text.toString(),
                        HOME_BANDLE_KEY to etAddress.text.toString()
                    ),
                )*/
                    routing().goBack()///////////////////////////////////////////////////////////////


            }
        }
    }


//TODO Corected
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

}