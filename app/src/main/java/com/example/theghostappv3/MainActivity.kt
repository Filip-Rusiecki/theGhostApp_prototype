package com.example.theghostappv3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.theghostappv3.ui.CameraActivity
import com.example.theghostappv3.ui.EmfActivity
import com.example.theghostappv3.ui.Gallery
import com.example.theghostappv3.ui.MicrophoneActivity

class MainActivity : AppCompatActivity() {

    lateinit var imageButton_Microphone: ImageView
    lateinit var imageButton_Camera: ImageView
    lateinit var imageButton_Emf: ImageView
    lateinit var imageButton_Gallery: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        imageButton_Microphone = findViewById(R.id.imageButton_Microphone)

        imageButton_Microphone.setOnClickListener {
            val intent = Intent(this, MicrophoneActivity::class.java)
            startActivity(intent)
        }

        imageButton_Camera = findViewById(R.id.imageButton_Camera)

        imageButton_Camera.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
        }

        imageButton_Emf = findViewById(R.id.imageButton_EMF)

        imageButton_Emf.setOnClickListener {
            val intent = Intent(this, EmfActivity::class.java)
            startActivity(intent)
        }

        imageButton_Gallery = findViewById(R.id.imageButton_Gallery)

        imageButton_Gallery.setOnClickListener {
            val intent = Intent(this, Gallery::class.java)
            startActivity(intent)
        }











    }
}