package com.example.vidushan.myapplication

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.storage.StorageManager
import kotlinx.android.synthetic.main.activity_edit_contacts.*
import kotlinx.android.synthetic.main.activity_main.*


class EditContacts : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_contacts)

        setContactsButton.setOnClickListener{
            performEditContactsButton()
        }

    }

    override fun onResume() {
        super.onResume()
        //val sharedPref = this.getPreferences(Context.MODE_PRIVATE) ?: return
        val sharedPref = this.applicationContext.getSharedPreferences("PreferenceFile", Context.MODE_PRIVATE)
        val phone1: String = sharedPref.getString("Phone1","")
        val phone2: String = sharedPref.getString("Phone2","")
        val phone3: String = sharedPref.getString("Phone3","")

        phoneNumber1EditText.setText(phone1)
        phoneNumber2EditText.setText(phone2)
        phoneNumber3EditText.setText(phone3)
    }


    private fun performEditContactsButton(){
        var number1: String = phoneNumber1EditText.text.toString()
        var number2: String = phoneNumber2EditText.text.toString()
        var number3: String = phoneNumber3EditText.text.toString()


        val sharedPrefer = this.applicationContext.getSharedPreferences("PreferenceFile", Context.MODE_PRIVATE)
        //SharedPreferences sharedPrefer = getShare
        with(sharedPrefer.edit()){
            putString("Phone1", number1)
            putString("Phone2",number2)
            putString("Phone3",number3)
            commit()
        }
        finish()





    }
}
