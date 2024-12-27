package com.example.test
import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class SMSActivity : AppCompatActivity() {

    private lateinit var txtPhoneNumber: EditText
    private lateinit var txtMessage: EditText
    private lateinit var btnSendSMS: Button

    private val SMS_PERMISSION_CODE = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_smsactivity)

        txtPhoneNumber = findViewById(R.id.txtPhoneNumber)
        txtMessage = findViewById(R.id.txtMessage)
        btnSendSMS = findViewById(R.id.btnSendSMS)

        // Set click listener for the Send button
        btnSendSMS.setOnClickListener {
            sendSMS()
        }

        // Check and request SMS permission
        checkSmsPermission()
    }

    private fun sendSMS() {
        val phoneNumber = txtPhoneNumber.text.toString()
        val message = txtMessage.text.toString()

        if (phoneNumber.isEmpty() || message.isEmpty()) {
            Toast.makeText(this, "Please enter phone number and message", Toast.LENGTH_SHORT).show()
            return
        }

        if(!checkSmsPermission())
            return;


        try {
            val smsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(phoneNumber, null, message, null, null)
            Toast.makeText(this, "SMS sent successfully", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(this, "Failed to send SMS: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkSmsPermission() : Boolean{
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS), SMS_PERMISSION_CODE)
            return  false;
        }

        return  true;
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == SMS_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, send SMS
                sendSMS()
            } else {
                Toast.makeText(this, "SMS permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}