package com.shpp.ssierykh.assignment1.utils.extensions


import android.widget.Toast
import androidx.fragment.app.Fragment



fun Fragment.toast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
}

