package com.example.theghostappv3.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.theghostappv3.R

class Settings : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences


    private lateinit var proximitySwitch: Switch
    private lateinit var proximitySensorStatusTxt: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        // shared preferences for settings activity TODO move to separate file and add more setting options



        sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE)
        proximitySensorStatusTxt = findViewById(R.id.ProximitySensor_StatusTxt)


        // switch
        proximitySwitch = findViewById(R.id.ProximitySensor_Switch)
        proximitySwitch.isChecked = sharedPreferences.getBoolean("proximity", false)

        proximitySwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                sharedPreferences.edit().putBoolean("proximity_allowed", true).apply()
                proximitySensorStatusTxt.text = "On"
            } else {
                sharedPreferences.edit().putBoolean("proximity_allowed", false).apply()
                proximitySensorStatusTxt.text = "Off"
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }


    override fun onDestroy() {
        super.onDestroy()
        finish()
    }


}