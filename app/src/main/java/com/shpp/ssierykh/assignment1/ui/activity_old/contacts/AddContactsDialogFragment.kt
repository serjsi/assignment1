package com.shpp.ssierykh.assignment1.ui.activity_old.contacts


import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.shpp.ssierykh.assignment1.utils.Constants
import com.shpp.ssierykh.assignment1.R


import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.doOnTextChanged
import com.shpp.ssierykh.assignment1.data.ContactForRecyclerView
import com.shpp.ssierykh.assignment1.databinding.DialogAddOrEditContactProfileBinding
import com.shpp.ssierykh.assignment1.utils.Validators.isValidateEmail


class AddContactsDialogFragment(private var onDateSelectedListener: OnAddContactListener) :
    DialogFragment() {


    interface OnAddContactListener {
        fun onAddContact(addItem: ContactForRecyclerView)
    }


    private lateinit var binding: DialogAddOrEditContactProfileBinding

    private val imageAvatar = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = DialogAddOrEditContactProfileBinding.inflate(inflater, container, false)
        binding.ivArrowBack.setOnClickListener { dismiss() }

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


    private fun saveContact() {
        binding.apply {
            etEmailA.doOnTextChanged { _, _, _, _ -> isValidateEmail() }

            btSave.setOnClickListener {
                val userName = etUserName.text.toString()
                val career = etCareer.text.toString()
                val selectedDate =
                    ContactForRecyclerView(Constants.PHOTO_FAKE_1, userName, career)
                dismiss()
                onDateSelectedListener.onAddContact(selectedDate)
            }
        }
    }

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