package com.shpp.ssierykh.assignment1.contacts


import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.shpp.ssierykh.assignment1.databinding.DialogAddContactProfileBinding


class AddContactsDialog : DialogFragment() {


    private lateinit var binding: DialogAddContactProfileBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = DialogAddContactProfileBinding.inflate(inflater, container, false)

        binding.btSave.setOnClickListener() { dismiss() }
        binding.ivArrowBack.setOnClickListener() { dismiss() }
        return binding.root


    }

    override fun onResume() {
        super.onResume()
        val params: WindowManager.LayoutParams? = dialog?.window?.attributes
        params?.width = WindowManager.LayoutParams.MATCH_PARENT
        params?.height = WindowManager.LayoutParams.MATCH_PARENT
        dialog?.onWindowAttributesChanged(params)
    }
}