package com.shpp.ssierykh.assignment1.ui.first_screen

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

import com.shpp.ssierykh.assignment1.R
import com.shpp.ssierykh.assignment1.databinding.ActivityStartBinding
import com.shpp.ssierykh.assignment1.model.BaseContacts
import com.shpp.ssierykh.assignment1.ui.SwitchNavigationGraph.isNavigationGraph
import com.shpp.ssierykh.assignment1.ui.contract.Routing
import com.shpp.ssierykh.assignment1.ui.first_screen.my_profile.MyProfileFragment
import com.shpp.ssierykh.assignment1.ui.first_screen.my_profile.MyProfileViewModel
import com.shpp.ssierykh.assignment1.ui.first_screen.my_profile.edit_profile.AddOrEditContactsDialogFragment
import com.shpp.ssierykh.assignment1.ui.first_screen.my_profile.view_my_contacts.MyContactsFragment
import com.shpp.ssierykh.assignment1.ui.first_screen.sign.SignFragment
import dagger.hilt.android.AndroidEntryPoint


private lateinit var binding: ActivityStartBinding
private lateinit var navController: NavController

@AndroidEntryPoint
class StartActivity : AppCompatActivity(), Routing {
    private val viewModel: MyProfileViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!isNavigationGraph) {
            binding = ActivityStartBinding.inflate(layoutInflater).also { setContentView(it.root) }
        }
        setContentView(R.layout.activity_start)

        // Initially display the first fragment in main activity
        if (savedInstanceState == null && !isNavigationGraph) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.nav_host_fragment, SignFragment())
                .commit()
        }

    }

    override fun showMyProfileScreen() {
        replaceFragment(MyProfileFragment())
    }

    override fun showSignScreen() {
        replaceFragment(SignFragment())
    }

    override fun showAddOrEditContacts() {
        replaceFragment(AddOrEditContactsDialogFragment())
    }

    override fun showMyContacts() {
        replaceFragment(MyContactsFragment( ))
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
        .replace(R.id.nav_host_fragment, fragment)
        .addToBackStack(null)
        .commit()

}