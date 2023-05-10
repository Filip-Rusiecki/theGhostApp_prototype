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

        val directory = File(externalMediaDirs[0].absolutePath)
        val files = directory.listFiles() as Array<File>

        val adapter = GalleryAdapter(files.reversedArray())

        var binding: ActivityGalleryBinding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(/* view = */ binding.root)


        adapter.also { binding.viewPager.adapter = it }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}