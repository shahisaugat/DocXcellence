package com.example.docxcellence

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.docxcellence.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var loginBinding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        loginBinding.backBtn.setOnClickListener {
            goBack()
        }
        loginBinding.sendOtpBtn.setOnClickListener {
            sendOTP()
        }
    }
    private fun sendOTP() {
        // Firebase Implementation Here

        // Move to another screen after OTP is sent!
        val intent = Intent(this@LoginActivity, OTPActivity::class.java)
        startActivity(intent)
    }

    private fun goBack() {
        val intent = Intent(this@LoginActivity, SplashActivity::class.java)
        startActivity(intent)
        finish()
    }
}