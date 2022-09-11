package com.shpp.ssierykh.assignment1.activity_and_splash

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle

import com.shpp.ssierykh.assignment1.R
import com.shpp.ssierykh.assignment1.databinding.ActivityMainBinding

import com.shpp.ssierykh.assignment1.model.Contact
import com.shpp.ssierykh.assignment1.navigate.Routing
import com.shpp.ssierykh.assignment1.utils.SwitchNavigationGraph.featureNavigationEnabled

import com.shpp.ssierykh.assignment1.ui.my_profile.MyProfileFragment
import com.shpp.ssierykh.assignment1.ui.edit_profile.EditProfileContactDialogFragment
import com.shpp.ssierykh.assignment1.ui.view_my_contacts.MyContactsFragment
import com.shpp.ssierykh.assignment1.ui.contact_profile.ContactProfileFragment
import com.shpp.ssierykh.assignment1.ui.sign.SignFragment
import dagger.hilt.android.AndroidEntryPoint


private lateinit var binding: ActivityMainBinding



@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Routing {

    // actions to be launched when activity is active
    private val actions = mutableListOf<() -> Unit>()
    private lateinit var pLauncher: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!featureNavigationEnabled) {
            binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
        }
        setContentView(R.layout.activity_main)


        // Initially display the first fragment in main activity
        if (savedInstanceState == null && !featureNavigationEnabled) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.nav_host_fragment, SignFragment())
                .commit()
        }

    }

    override fun showMyProfileScreen() {
      replaceFragment(MyProfileFragment())
    }

    override fun showEditProfileContact() {
        replaceFragment(EditProfileContactDialogFragment())
    }

    override fun showEditProfileContact(contact: Contact?) {
            if (contact != null) {
                supportFragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.nav_host_fragment,
                        EditProfileContactDialogFragment.newInstance(contact.email))
                    .commit()
            }
    }

    override fun showMyContacts() {
      replaceFragment(MyContactsFragment())
    }

    override fun showContactProfile(contact: Contact) {
        runWhenActive {
            supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.nav_host_fragment, ContactProfileFragment.newInstance(contact.email))
                .commit()
        }
    }


    override fun goBack() {
        runWhenActive { onBackPressed()}


    }

    override fun onResume() {
        super.onResume()
        actions.forEach { it() }
        actions.clear()
    }

    private fun checkAddressReadPermission() {
        when {
            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) ==
                    PackageManager.PERMISSION_GRANTED -> {
            }
            shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS) -> {
                pLauncher.launch(Manifest.permission.READ_CONTACTS)
            }
            else -> {
                pLauncher.launch(Manifest.permission.READ_CONTACTS)
            }
        }
    }

    private fun registerPermissionListener() {
        pLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) {
            if (it) {
                Toast.makeText(this, "Read Contacts Allowed", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Read ContactsDenied", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun downloadContactsPhone() {
        Log.v("_______Contacts","start")
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) ==
            PackageManager.PERMISSION_GRANTED
        ) {

            val cursor = this.contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, null
            )
            cursor?.let {
                while (it.moveToNext()) {
                    val fullName =
                        it.getString(it.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                    val phone =
                        it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                    val newModel = Contact()
                    newModel.name = fullName
                    newModel.phone = phone
                    //contacts.add(newModel)
                    Log.v("_______Contacts","-$fullName--")
                }
            }
            cursor?.close()
        }
    }


    /**
     * Avoiding [IllegalStateException] if navigation action has been called after restoring app from background.
     * For example: press "Delete" button in details screen, minimize app and then restore it.
     */
    private fun runWhenActive(action: () -> Unit) {
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)) {
            // activity is active -> just execute the action
            action()
        } else {
            // activity is not active -> add action to queue
            actions += action
        }
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