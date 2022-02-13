package com.shpp.ssierykh.assignment1.ui.first_screen.sign

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData

import com.shpp.ssierykh.assignment1.utils.data_store.SaveLoginDataStore
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class SignViewModel : ViewModel() {
   /* private var autologin = SaveLoginDataStore(dataStore)

    //Stores the values
    @DelicateCoroutinesApi
    fun writeLoginDataStore(email: String, password: String, remember: Boolean) {
        if (remember) {
            GlobalScope.launch {
                autologin.storeUser(email, password, remember)
            }

        } else {
            GlobalScope.launch {
                autologin.storeUser("", "", remember)

            }

        }*/

   /*      private fun observeData() {

           //Check ChekBox
             autologin.userRememberFlow.asLiveData().observe(this, {
               if (it == true) {

                   //Updates email
                   userManager.userEmailFlow.asLiveData().observe(this, {
                       if (it != null) {
                           email = it
                           binding.editTextEnterEmail.setText(email, TextView.BufferType.EDITABLE)
                       }
                   })

                   //Updates password
                   userManager.userPasswordFlow.asLiveData().observe(this, {
                       if (it != null) {
                           password = it
                           binding.editTextTextPassword.setText(password, TextView.BufferType.EDITABLE)
                       }
                   })

                   //Updates remember
                   userManager.userRememberFlow.asLiveData().observe(this, {
                       if (it != null) {
                           remember = it
                           binding.checkBoxRemember.isChecked = it
                       }
                   })
               }
           })
       } */// TODO: Implement the ViewModel
    }
