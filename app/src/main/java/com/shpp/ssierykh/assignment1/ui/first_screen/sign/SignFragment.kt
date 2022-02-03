package com.shpp.ssierykh.assignment1.ui.first_screen.sign

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.shpp.ssierykh.assignment1.R
import com.shpp.ssierykh.assignment1.databinding.FragmentSignBinding
import com.shpp.ssierykh.assignment1.ui.contract.routing
import com.shpp.ssierykh.assignment1.utils.Constants
import com.shpp.ssierykh.assignment1.utils.Validators.isValidateEmail
import com.shpp.ssierykh.assignment1.utils.Validators.isValidatePassword
import com.shpp.ssierykh.assignment1.utils.Validators.messageValidationPassword
import com.shpp.ssierykh.assignment1.utils.extensions.clickWithDebounce


class SignFragment : Fragment() {
    var pressRegistration = false

    /*

    *//*override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    options = savedInstanceState?.getParcelable(KEY_OPTIONS) ?: Options.DEFAULT
}*/
    private lateinit var binding: FragmentSignBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignBinding.inflate(inflater, container, false)

        /*     navigator().listenResult(Options::class.java, viewLifecycleOwner) {
            this.options = it
        }*/

        //  binding.btRegister.setOnClickListener { onOpenMayProfile() }
        setupListeners()
        // loadAutologin()
        forTestMethod()
        return binding.root
    }

    private fun setupListeners() {
        binding.apply {
            showMessageErrorAfterClicking()
            //Switching to another screen
            btRegister.setOnClickListener {
                checkingTextAfterClicking()
                if (isValidateEmail(etEmail) && isValidatePassword(etPassword)) {
                    routing().showMyProfileScreen()
                    pressRegistration = false
                }

                //Stores the values
                /*      val intent = Intent(this@MainActivity, MyProfileActivity::class.java)
                      intent.putExtra(Constants.EMAIL_EXTRA, etEmail.text.toString())
                      intent.putExtra(Constants.PHOTO_EXTRA, R.drawable.lucile)
                      startActivity(intent)
                      finish()
                      //Animation
                      overridePendingTransition(0, R.anim.slide_in)
                      btRegister.isClickable = false
                      saveAutologin()*/

            }
        }
    }

    private fun FragmentSignBinding.checkingTextAfterClicking() {
        pressRegistration = true
        if (!isValidateEmail(etEmail)) tilEmail.error = getString(R.string.message_wromg_e_mail)
        if (!isValidatePassword(etPassword)) tilPassword.error =
            getString(messageValidationPassword(etPassword))
    }

    private fun FragmentSignBinding.showMessageErrorAfterClicking() {
        etEmail.doOnTextChanged { _, _, _, _ ->
            if (!isValidateEmail(etEmail) && pressRegistration) {
                tilEmail.error = getString(R.string.message_wromg_e_mail)
            } else tilEmail.isErrorEnabled = false
        }

        etPassword.doOnTextChanged { _, _, _, _ ->
            if (!isValidatePassword(etPassword) && pressRegistration) {
                tilPassword.error = getString(messageValidationPassword(etPassword))
            } else tilPassword.isErrorEnabled = false
        }
    }

    ////////////////////////////////test method////////////////////////////////////////
    private fun forTestMethod() {
        binding.apply {
            tvSignIn.clickWithDebounce {
                etEmail.setText(Constants.TEST_EMAIL, TextView.BufferType.EDITABLE)
                etPassword.setText(Constants.TEST_PASSWORD, TextView.BufferType.EDITABLE)
            }

            //Handle pressing the "SignIn" google:

            /*        binding.cvGoogle.clickWithDebounce {
                        val intent = Intent(this@MainActivity, MyProfileActivity::class.java)
                        intent.putExtra(Constants.EMAIL_EXTRA, getString(R.string.fake_mail))
                        intent.putExtra(Constants.PHOTO_EXTRA, R.mipmap.ic_kot_round)
                        startActivity(intent)
                        //Animation
                        finish()
                        overridePendingTransition(0, R.anim.slide_in)
                    }*/
        }
    }
    //////////////////////////////////////////////////////////////////////////////////

}