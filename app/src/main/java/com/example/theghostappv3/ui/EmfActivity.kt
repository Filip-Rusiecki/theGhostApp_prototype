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
import com.example.theghostappv3.utilities.ProximitySensor
import kotlin.math.sqrt


class EmfActivity : AppCompatActivity() {

    lateinit var sensorManager: SensorManager
    lateinit var sensor: Sensor
    lateinit var sensorEventListener: SensorEventListener
    lateinit var progressBar: ProgressBar

    private lateinit var proximitySensor: ProximitySensor




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emf)

        progressBar = findViewById(R.id.EmfMeter_progBar)
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)

        proximitySensor = ProximitySensor(this)

        if (sensor == null) {

            Toast.makeText(this, "No sensor detected", Toast.LENGTH_SHORT).show()
        }


        sensorEventListener = object : SensorEventListener {
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
                progressBar.progress = accuracy
            }


            override fun onSensorChanged(event: SensorEvent?) {
                event?.let {
                    val x = it.values[0]
                    val y = it.values[1]
                    val z = it.values[2]

                    val mag = sqrt((x * x + y * y + z * z).toDouble()).toFloat()
                    progressBar.progress = mag.toInt()

                    val color = if (mag > 100) Color.RED
                                else if(mag > 50) Color.YELLOW else Color.GREEN

                    if(mag >= 100){
                        Toast.makeText(this@EmfActivity, "Ghost Detected", Toast.LENGTH_SHORT).show()
                    }


                    progressBar.progressDrawable.setColorFilter(color, android.graphics.PorterDuff.Mode.SRC_IN)


                }
            }
        }
    }

            override fun onPause() {
                super.onPause()
                sensorManager.unregisterListener(sensorEventListener)
                proximitySensor.stopListening()
            }

            override fun onResume() {
                super.onResume()
                sensorManager.registerListener(
                    sensorEventListener,
                    sensor,
                    SensorManager.SENSOR_DELAY_NORMAL

                )
                proximitySensor.startListening()
            }



    }