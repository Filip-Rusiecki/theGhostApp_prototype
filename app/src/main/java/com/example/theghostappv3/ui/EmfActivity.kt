package com.example.theghostappv3.ui

import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import com.example.theghostappv3.R


class EmfActivity : AppCompatActivity() {

    lateinit var sensorManager: SensorManager
    lateinit var sensor: Sensor
    lateinit var sensorEventListener: SensorEventListener
    lateinit var progressBar: ProgressBar




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emf)

        progressBar = findViewById(R.id.EmfMeter_progBar)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)



        sensorEventListener = object : SensorEventListener {
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
                progressBar.progress = accuracy
            }

            override fun onSensorChanged(event: SensorEvent?) {
                if (event != null) {


                    progressBar.progress = event.values[0].toInt()

                    progressBar.currentDrawable?.setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN)

                    // Colour change on progress bar based on value

                    if(event.values[0] > 50) {
                        progressBar.currentDrawable?.setColorFilter(Color.YELLOW, android.graphics.PorterDuff.Mode.SRC_IN)
                    }


                    if (event.values[0] > 100) {
                        progressBar.currentDrawable?.setColorFilter(Color.RED, android.graphics.PorterDuff.Mode.SRC_IN)
                    }

                }



            }// end of sensor changed



        } // if sensor is null show toast message

        if (sensorManager == null) {

            Toast.makeText(this, "No sensor detected", Toast.LENGTH_SHORT).show()

        }

        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL)

    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(sensorEventListener)
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL)
    }




}