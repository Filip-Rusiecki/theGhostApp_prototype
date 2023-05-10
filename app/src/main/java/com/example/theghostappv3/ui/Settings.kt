package com.example.theghostappv3.ui

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import android.widget.TextView
import com.example.theghostappv3.R
import com.example.theghostappv3.utilities.ProximitySensor

class Settings : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    private var isDarkModeOn: Boolean = false

    private lateinit var proximitySwitch: Switch
    private lateinit var ProximitySensor_StatusTxt: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)


        sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE)
        ProximitySensor_StatusTxt = findViewById(R.id.ProximitySensor_StatusTxt)


        // switch
        proximitySwitch = findViewById(R.id.ProximitySensor_Switch)
        proximitySwitch.isChecked = sharedPreferences.getBoolean("proximity", false)

        proximitySwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                sharedPreferences.edit().putBoolean("proximity", true).apply()
                ProximitySensor_StatusTxt.text = "On"
            } else {
                sharedPreferences.edit().putBoolean("proximity", false).apply()
                ProximitySensor_StatusTxt.text = "Off"
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }





}