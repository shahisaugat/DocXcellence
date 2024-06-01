package com.example.docxcellence

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageSwitcher
import android.widget.ImageView
import android.widget.ViewSwitcher

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.bumptech.glide.Glide
import com.example.docxcellence.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {
    private val mAuth = FirebaseAuth.getInstance()
    private lateinit var dashboardBinding: ActivityDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        dashboardBinding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(dashboardBinding.root)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize ImageSwitcher using view binding
        val view = dashboardBinding.root
        val imageSwitcher = dashboardBinding.imageSwitcher
        imageSwitcher.setFactory(ViewSwitcher.ViewFactory {
            val imageView = ImageView(view.context)
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            imageView
        })

        /// Initialize image resource IDs
        val imageIds = arrayOf(R.drawable.image1, R.drawable.image2) // Replace with actual resource IDs
        var currentIndex = 0

        // Initialize handler and runnable for image switching
        val handler = Handler(Looper.getMainLooper())
        val runnable = object : Runnable {
            override fun run() {
                currentIndex = (currentIndex + 1) % imageIds.size
                imageSwitcher.setImageResource(imageIds[currentIndex])
                handler.postDelayed(this, 3000) // Switch image every 3 seconds
            }
        }

        // Start the image switcher
        handler.postDelayed(runnable, 3000)

        val currentUser = mAuth.currentUser
        Glide.with(this).load(currentUser?.photoUrl).into(dashboardBinding.profilePicture)

        dashboardBinding.bottomNavigationView.background = null
        dashboardBinding.bottomNavigationView.menu.getItem(2).isEnabled = false
        dashboardBinding.textView4.text=currentUser?.displayName.toString()
    }


}


