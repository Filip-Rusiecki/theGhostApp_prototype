package com.example.theghostappv3.ui




import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.theghostappv3.R
import com.example.theghostappv3.utilities.Const

import android.graphics.Bitmap
import androidx.core.content.FileProvider

import coil.load
import com.example.theghostappv3.databinding.ActivityGalleryBinding
import com.firebase.ui.auth.ui.email.EmailActivity


open class Gallery : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var bitmap: Bitmap
    private lateinit var imageUri: String
    private lateinit var binding : ActivityGalleryBinding
    private val GALLERY_REQUEST_CODE = 100



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (allPremissionGranted()) {
            onGallery()
        } else {
            ActivityCompat.requestPermissions(
                this, Const.REQUEST_PERMISSIONS,
                Const.REQUEST_CODE_PERMISSIONS
            )
        }

    }
    // check if all permission are granted

    private fun allPremissionGranted() = Const.REQUEST_PERMISSIONS.all {
        checkSelfPermission(it) == PackageManager.PERMISSION_GRANTED

    }

    private fun onGallery() {
       val intent = Intent(Intent.ACTION_PICK)
        //Ghostapp photos directory
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)

        startActivityForResult(intent, Const.REQUEST_CODE_PERMISSIONS)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == Const.REQUEST_CODE_PERMISSIONS && resultCode == RESULT_OK) {
            val uri = data?.data
            imageView.setImageURI(uri)

            if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK) {

                imageUri = data?.data.toString()
                imageView.load(imageUri) {
                    crossfade(true)
                    crossfade(1000)


            }

                val bitmap = data?.extras?.get("data") as Bitmap
                imageView.setImageBitmap(bitmap)



            }

        }
    }
}


































