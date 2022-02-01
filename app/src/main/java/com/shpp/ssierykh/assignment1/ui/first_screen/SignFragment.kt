package com.shpp.ssierykh.assignment1.ui.first_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shpp.ssierykh.assignment1.databinding.FragmentSignBinding
import com.shpp.ssierykh.assignment1.ui.contract.routing


class SignFragment : Fragment() {

/*      override fun onCreateView(
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
  *//*

*//*override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    options = savedInstanceState?.getParcelable(KEY_OPTIONS) ?: Options.DEFAULT
}*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSignBinding.inflate(inflater, container, false)

        /*     navigator().listenResult(Options::class.java, viewLifecycleOwner) {
            this.options = it
        }*/

        binding.btRegister.setOnClickListener { onOpenMayProfile() }


        return binding.root
    }


    private fun onOpenMayProfile() {
        routing().showMyProfileScreen()
    }
}



