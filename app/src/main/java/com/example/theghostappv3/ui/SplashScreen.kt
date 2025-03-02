package com.example.theghostappv3.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.theghostappv3.MainActivity
import com.example.theghostappv3.R
import com.example.theghostappv3.R.id.ghost_splash

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        val imageView: ImageView = findViewById(ghost_splash)
        Glide.with(this).load(R.drawable.ghost_splash_main).into(imageView)

// todo add sound effect and fix the anim timing and add a loading bar


        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 7000 )
    }
}