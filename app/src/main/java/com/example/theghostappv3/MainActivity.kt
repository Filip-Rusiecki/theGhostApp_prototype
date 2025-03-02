package com.example.theghostappv3

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.theghostappv3.ui.*


class MainActivity : AppCompatActivity() {

    lateinit var imageButton_Microphone: ImageView
    lateinit var imageButton_Camera: ImageView
    lateinit var imageButton_Emf: ImageView
    lateinit var imageButton_Map: ImageView
    lateinit var imageButton_Settings: ImageView


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


       imageButton_Map = findViewById(R.id.imageButton_Map)

        imageButton_Map.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }


        imageButton_Settings = findViewById(R.id.imageButton_Settings)

        imageButton_Settings.setOnClickListener {
            val intent = Intent(this, Settings::class.java)
            startActivity(intent)
        }















    }
}
