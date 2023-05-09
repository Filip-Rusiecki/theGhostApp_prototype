package com.example.theghostappv3.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.theghostappv3.R
import com.example.theghostappv3.databinding.ActivityGalleryBinding
import utilities.GalleryAdapter
import java.io.File


class GalleryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        var binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val directory = File(externalMediaDirs[0].absolutePath)
        val files = directory.listFiles() as Array<File>

        val adapter = GalleryAdapter(files.reversedArray())
        binding.viewPager.adapter = adapter

    }




    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}