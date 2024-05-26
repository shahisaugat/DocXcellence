package com.example.docxcellence

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.docxcellence.databinding.ActivityOtpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class OTPActivity : AppCompatActivity() {
    private lateinit var otpBinding: ActivityOtpBinding
    private var storedVerificationId : String? = ""
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        otpBinding = ActivityOtpBinding.inflate(layoutInflater)
        setContentView(otpBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        storedVerificationId = intent.getStringExtra("storedVerificationId")

        otpBinding.backBtn.setOnClickListener {
            goBack()
        }
        otpBinding.verifyOtpBtn.setOnClickListener {
//            verifyOTPCode()
            verifyPhoneNumberWithCode(storedVerificationId,otpBinding.editTextLayout.toString())
        }
    }
    private fun goBack() {
        val intent = Intent(this@OTPActivity, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

//    private fun verifyOTPCode() {
//        // Firebase Implementation here
//    }

    private fun verifyPhoneNumberWithCode(verificationId: String?, code: String) {

        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)

        signInWithPhoneAuthCredential(credential)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Success", "signInWithCredential:success")

                    val user = task.result?.user

                    val intent = Intent(this@OTPActivity,DashboardActivity::class.java)
                    startActivity(intent)

                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w("Failed", "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                    // Update UI
                }
            }
    }
}