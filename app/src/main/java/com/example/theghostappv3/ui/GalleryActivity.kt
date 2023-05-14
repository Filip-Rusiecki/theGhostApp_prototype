package com.example.theghostappv3.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.theghostappv3.R
import com.example.theghostappv3.databinding.ActivityGalleryBinding
import com.example.theghostappv3.utilities.GalleryAdapter
import java.io.File


class GalleryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        val directory = File(externalMediaDirs[0].absolutePath)
        val files = directory.listFiles() as Array<File>

        val adapter = GalleryAdapter(files.reversedArray())

        var binding: ActivityGalleryBinding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)


        adapter.also { binding.viewPager.adapter = it }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}