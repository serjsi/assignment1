package com.shpp.ssierykh.assignment1.ui.first_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.shpp.ssierykh.assignment1.databinding.FragmentMyProfileBinding
import com.shpp.ssierykh.assignment1.ui.contract.routing


class MyProfileFragment : Fragment() {


/*override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    options = savedInstanceState?.getParcelable(KEY_OPTIONS) ?: Options.DEFAULT
}*/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMyProfileBinding.inflate(inflater, container, false)

     /*   navigator().listenResult(Options::class.java, viewLifecycleOwner) {
            this.options = it
        }*/

        binding.btEditProfile.setOnClickListener { onOpenSignScreen() }


        return binding.root
    }

    private fun onOpenSignScreen() {
        routing().showSignScreen()
    }


}