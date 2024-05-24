package com.example.docxcellence

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.docxcellence.databinding.ActivityOtpBinding

class OTPActivity : AppCompatActivity() {
    private lateinit var otpBinding: ActivityOtpBinding
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
        otpBinding.backBtn.setOnClickListener {
            goBack()
        }
        otpBinding.verifyOtpBtn.setOnClickListener {
            verifyOTPCode()
            // App might crash as no functionality has been assigned!
        }
    }
    private fun goBack() {
        val intent = Intent(this@OTPActivity, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun verifyOTPCode() {
        // Firebase Implementation here
    }
}