package com.example.vidushan.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.telephony.SmsManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_edit_contacts.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val requestSMS: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //ActivityCompat.requestPermissions(this, arrayOf("Manifest.permission.SEND_SMS"),1);

        if ((ActivityCompat.checkSelfPermission(this,"android.permission.SEND_SMS")
                != PackageManager.PERMISSION_GRANTED) ||
                (ActivityCompat.checkSelfPermission(this,"android.permission.READ_PHONE_STATE")
                        != PackageManager.PERMISSION_GRANTED)){

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    "android.permission.SEND_SMS")) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        arrayOf("android.permission.SEND_SMS","android.permission.READ_PHONE_STATE"),
                        1)

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }

        editContactsButton.setOnClickListener{
            performEditContacts()
        }

        sendMessageButton.setOnClickListener {
            performSMS()
        }
    }
    private fun performEditContacts(){
        val intent = Intent(this, EditContacts::class.java)
        startActivity(intent)
    }

    private fun performSMS(){

        if ((ActivityCompat.checkSelfPermission(this,"android.permission.SEND_SMS") != PackageManager.PERMISSION_GRANTED) || (ActivityCompat.checkSelfPermission(this,"android.permission.READ_PHONE_STATE") != PackageManager.PERMISSION_GRANTED)){
            ActivityCompat.requestPermissions(this, arrayOf("android.permission.SEND_SMS","android.permission.READ_PHONE_STATE"),requestSMS)
        }else{
            sendSms()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == requestSMS)sendSms()
    }
    private fun sendSms(){
       // val number = this.getSharedPreferences("Phone1", Context.MODE_PRIVATE)

        //val number = this.getPreferences(Context.MODE_PRIVATE) ?: return
        //val phone1: String = sharedPref.getString("Phone1","")

        val text = "Emergency Bro"

        SmsManager.getDefault().sendTextMessage("0774483828",null,text,null,null)

        Toast.makeText(this,"Sms Sent",Toast.LENGTH_SHORT).show()

    }
}

