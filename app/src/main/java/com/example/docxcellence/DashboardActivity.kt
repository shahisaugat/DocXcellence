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
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
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
        val currentUser = mAuth.currentUser
        Glide.with(this).load(currentUser?.photoUrl).into(dashboardBinding.profilePicture)

        val imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel(R.drawable.banner, ScaleTypes.CENTER_INSIDE))
        imageList.add(SlideModel(R.drawable.banner1, ScaleTypes.CENTER_INSIDE))

        val imageSlider = dashboardBinding.imageSlider
        imageSlider.setImageList(imageList)
        imageSlider.startSliding(100)
        imageSlider.startSliding()
        imageSlider.stopSliding()
    }
}


