package com.example.theghostappv3.utilities

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.VibrationEffect
import android.os.Vibrator
// based on https://developer.android.com/reference/kotlin/android/os/VibratorManager && https://www.geeksforgeeks.org/android-proximity-sensor-using-kotlin/

class ProximitySensor(context: Context): SensorEventListener {

    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val proximitySensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
    private val vibrator: Vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

    private val sharedPreferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
    private var isClose = false


    fun startListening() {
        if (!isClose && proximitySensor != null) {
            sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL)
            isClose = true
        }

    }

    fun stopListening() {
        if (isClose) {
            sensorManager.unregisterListener(this)
            isClose = false
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null && event.sensor.type == Sensor.TYPE_PROXIMITY) {
            if (event.values[0] < proximitySensor!!.maximumRange) {
                if(vibrationAllowed()) {
                    vibrate()
                }
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // not needed
    }


    private fun vibrate() {
        vibrator.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE))

    }

// settings
    private fun vibrationAllowed(): Boolean {
        return sharedPreferences.getBoolean("proximity_allowed", true)
    }
}


