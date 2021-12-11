package com.shpp.ssierykh.assignment1.contacts


import android.content.Context
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.DialogFragment
import com.shpp.ssierykh.assignment1.Constants
import com.shpp.ssierykh.assignment1.R
import com.shpp.ssierykh.assignment1.databinding.DialogAddContactProfileBinding
import com.shpp.ssierykh.assignment1.extensions.Validators
import androidx.core.app.ActivityCompat.startActivityForResult

import android.content.Intent
import android.database.Cursor
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.app.ActivityCompat.startActivityForResult
import java.io.File
import androidx.recyclerview.widget.RecyclerView

import androidx.recyclerview.widget.ItemTouchHelper





class AddContactsDialog(private var onDateSelectedListener: OnAddContactListener) :
    DialogFragment() {


    interface OnAddContactListener {
        fun onAddContact(addItem: ContactRecyclerView)
    }


    private lateinit var binding: DialogAddContactProfileBinding

    private  var IMAGE_AVATAR = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = DialogAddContactProfileBinding.inflate(inflater, container, false)
        binding.ivArrowBack.setOnClickListener { dismiss() }

        saveContact()

        binding.ivAddPhoto.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_PICK
            startActivityForResult(Intent.createChooser(intent, "Select Picture"),
                IMAGE_AVATAR)

         /*   if(requestCode == IMAGE_AVATAR && data!=null){
                Uri selectedImage = data.getData();
                // create file
                Cursor c = getContentResolver().query(selectedImage,null,null,null,null);
                c.moveToFirst();
                String path = c.getString(c.getColumnIndex(MediaStore.MediaColumns.DATA));
                File file = new File(ImagePickUpUtil.getRealPathFromURI(this,selectedImage));

            }*/

        }

        return binding.root

    }

    private fun saveContact() {
        binding.apply {
            etEmail.doOnTextChanged { _, _, _, _ -> isValidateEmail() }

            btSave.setOnClickListener {
                val userName = etUserName.text.toString()
                val career = etCareer.text.toString()
                val selectedDate =
                    ContactRecyclerView(Constants.PHOTO_FAKE_1, userName, career)
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

            val emailCheck = etEmail.text.toString()
            when {
                emailCheck.isEmpty() -> {
                    tilEmail.error = getString(R.string.message_cannot_be_empty)
                    etEmail.requestFocus()
                    return false
                }
                !Validators.isValidEmail(emailCheck) -> {
                    tilEmail.error = getString(R.string.message_wromg_e_mail)
                    etEmail.requestFocus()
                    return false
                }
                else -> {
                    tilEmail.isErrorEnabled = false
                    return true
                }
            }

        }
    }



    override fun onResume() {
        super.onResume()
        val params: WindowManager.LayoutParams? = dialog?.window?.attributes
        params?.width = WindowManager.LayoutParams.MATCH_PARENT
        params?.height = WindowManager.LayoutParams.MATCH_PARENT
        dialog?.onWindowAttributesChanged(params)
    }


}