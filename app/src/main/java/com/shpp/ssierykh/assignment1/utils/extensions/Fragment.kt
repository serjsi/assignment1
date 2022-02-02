package com.shpp.ssierykh.assignment1.utils.extensions

import android.content.pm.PackageManager
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.shpp.ssierykh.assignment1.BuildConfig
import com.shpp.ssierykh.assignment1.R
import com.shpp.ssierykh.assignment1.utils.Validators
import kotlinx.android.synthetic.main.fragment_sign.*


fun Fragment.toast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
}

