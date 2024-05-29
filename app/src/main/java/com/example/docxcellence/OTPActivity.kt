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
    var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var typedCode: String
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
            typedCode = otpBinding.code1.text.toString() + otpBinding.code2.text.toString() + otpBinding.code3.text.toString() + otpBinding.code4.text.toString() + otpBinding.code5.text.toString() + otpBinding.code6.text.toString()
            verifyPhoneNumberWithCode(storedVerificationId,typedCode.trim())
        }
    }
    private fun goBack() {
        val intent = Intent(this@OTPActivity, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun verifyPhoneNumberWithCode(verificationId: String?, code: String) {
        // [START verify_with_code]
        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
        // [END verify_with_code]
        signInWithPhoneAuthCredential(credential)
        val intent = Intent(this@OTPActivity,DashboardActivity::class.java)
        startActivity(intent)
        finish()
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
                    finish()

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