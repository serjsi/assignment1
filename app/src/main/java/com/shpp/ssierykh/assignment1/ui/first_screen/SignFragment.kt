package com.shpp.ssierykh.assignment1.ui.first_screen

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.shpp.ssierykh.assignment1.R
import com.shpp.ssierykh.assignment1.databinding.FragmentSignBinding


/**
 * An example full-screen fragment that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class SignFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v =  inflater.inflate(R.layout.fragment_sign, container, false)

        // Get the activity and widget
        val context = activity as AppCompatActivity
        val btnNavigate: Button = v.findViewById(R.id.btRegister)

        // Replace fragment
        btnNavigate.setOnClickListener {
            context.replaceFragment(MyProfileFragment())
        }

        return v
    }
}
