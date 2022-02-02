package com.shpp.ssierykh.assignment1.ui.first_screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

import com.shpp.ssierykh.assignment1.R
import com.shpp.ssierykh.assignment1.databinding.ActivityStartBinding
import com.shpp.ssierykh.assignment1.ui.contract.Routing


private lateinit var binding: ActivityStartBinding

class StartActivity : AppCompatActivity(), Routing {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityStartBinding.inflate(layoutInflater).also { setContentView(it.root) }
        setContentView(R.layout.activity_start)

        // Initially display the first fragment in main activity
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.host,SignFragment())
                .commit()
        }
    }

    override fun showMyProfileScreen() {
       replaceFragment(MyProfileFragment())
    }

    override fun showSignScreen() {
      replaceFragment(SignFragment())
    }

    override fun goBack() {
        onBackPressed()
    }
}

// Extension function to replace fragment
 fun AppCompatActivity.replaceFragment(fragment: Fragment) {
    supportFragmentManager
        .beginTransaction()
        .setCustomAnimations(
            R.anim.slide_in,
            R.anim.fade_out,
            R.anim.fade_in,
            R.anim.slide_out
        )
        .replace(R.id.host, fragment)
        .addToBackStack(null)
        .commit()

}