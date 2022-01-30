package com.shpp.ssierykh.assignment1.ui.first_screen

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.shpp.ssierykh.assignment1.R

class MyProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.my_profile_fragment, container, false)

        // Get the activity and widget
        val context = activity as AppCompatActivity
        val btnNavigate: Button = v.findViewById(R.id.btEditProfile)

        // Replace fragment
        btnNavigate.setOnClickListener {
            context.replaceFragment(SignFragment())
        }

        return v
    }
}