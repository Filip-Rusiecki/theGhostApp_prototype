package com.example.theghostappv3.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorManager
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity

class ProximityNotification {

    lateinit var sensor: Sensor
    lateinit var sensorManager: SensorManager




    @SuppressLint("SuspiciousIndentation")
    fun proximityNotification() {
       sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)

            if (sensor == null) {
                // No proximity sensor
            } else {
                // Proximity sensor exists


            }



    }


}



