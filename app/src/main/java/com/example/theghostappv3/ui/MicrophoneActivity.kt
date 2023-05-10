package com.example.theghostappv3.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.media.AudioRecord
import android.media.MediaRecorder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.theghostappv3.R
import com.example.theghostappv3.utilities.ProximitySensor
import com.example.theghostappv3.utilities.textOutputAdapter
import java.util.*
import kotlin.math.sqrt


class MicrophoneActivity : AppCompatActivity() {

    lateinit var proximitySensor: ProximitySensor

    lateinit var microphoneImageView: ImageView
    lateinit var outputText: TextView
    lateinit var textToSpeech: TextToSpeech
    lateinit var sound_progressBar: ProgressBar
    lateinit var sound_Switch : Switch
    lateinit var outputRecyclerView: RecyclerView

    val outputList = mutableListOf<String>()

    lateinit var outputAdapter: textOutputAdapter

    lateinit var activityResultLuncher: ActivityResultLauncher<Intent>


    private val SAMPLE_RATE = 44100
    private val CHANNEL_CONFIG = android.media.AudioFormat.CHANNEL_IN_MONO
    private val AUDIO_FORMAT = android.media.AudioFormat.ENCODING_PCM_16BIT
    private val BUFFER_SIZE = 2 * AudioRecord.getMinBufferSize(

        SAMPLE_RATE,
        CHANNEL_CONFIG,
        AUDIO_FORMAT
    )

    private var audioRecord: AudioRecord? = null
    private var isRecording = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_microphone)

        proximitySensor = ProximitySensor(this)

        // check if the device has a permission to use the microphone

        if (!packageManager.hasSystemFeature(PackageManager.FEATURE_MICROPHONE)) {
            Toast.makeText(this, "No microphone found", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Microphone found", Toast.LENGTH_SHORT).show()
        }

        microphoneImageView = findViewById(R.id.imageView_btnSpeak)
        sound_progressBar = findViewById(R.id.sound_progressBar)
        sound_Switch = findViewById(R.id.sound_Switch)

        outputAdapter = textOutputAdapter(outputList)
        outputRecyclerView = findViewById(R.id.recyclerView_textOutput)
        outputRecyclerView.adapter = outputAdapter



        microphoneImageView.setOnClickListener {

            val mIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)

            mIntent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            mIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())

            mIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to Ghost")

            try {
                activityResultLuncher.launch(mIntent)


            } catch (e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }
        }

        activityResultLuncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it?.resultCode == RESULT_OK && null != it.data) {

                val resultArray: ArrayList<String> = it.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS) as ArrayList<String>

                addTextOutput(Objects.requireNonNull(resultArray[0]))

                textToSpeech = TextToSpeech(this, TextToSpeech.OnInitListener { status ->
                    if (status != TextToSpeech.ERROR) {


                        textToSpeech.language = Locale.UK
                        textToSpeech.speak(resultArray[0], TextToSpeech.QUEUE_ADD, null)
                    }
                })

            }
        }
            // sound level detection switch button

        sound_Switch.setOnCheckedChangeListener() { _, isChecked ->
            if (isChecked) {
                stopSoundCheck()
            } else {
                startSoundCheck()
            }
        }
    }

    // text output to an adapter and recycler view as a list
    fun addTextOutput(text: String){
        outputList.add(text)
        outputAdapter.notifyItemInserted(outputList.size - 1)
        outputRecyclerView.smoothScrollToPosition(outputList.size - 1)
    }

    // progress bar for sound level detection
   private fun startSoundCheck(){
       if (ActivityCompat.checkSelfPermission(
               this,
               Manifest.permission.RECORD_AUDIO
           ) != PackageManager.PERMISSION_GRANTED
       ) {
              ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.RECORD_AUDIO),
                111
              )
           return
       }
       audioRecord = AudioRecord(
            MediaRecorder.AudioSource.MIC,
            SAMPLE_RATE,
            CHANNEL_CONFIG,
            AUDIO_FORMAT,
            BUFFER_SIZE
        )

       isRecording = true
       audioRecord?.startRecording()
       sound_progressBar.visibility = View.VISIBLE
       updateSoundLevel()
   }



    private fun stopSoundCheck(){
        isRecording = false
        audioRecord?.stop()
        audioRecord?.release()
        audioRecord = null
        sound_progressBar.visibility = View.INVISIBLE

    }


    private fun updateSoundLevel(){
        val buffer = ShortArray(BUFFER_SIZE)

        Thread{
            while (isRecording){
                audioRecord?.read(buffer, 0, BUFFER_SIZE)
                val soundLevel = calculateRMSLevel(buffer)
                runOnUiThread{
                    sound_progressBar.progress = soundLevel.toInt()
                }
            }
        }.start()

    }

    private fun calculateRMSLevel(audioData: ShortArray): Double {
        var sum = 0.0
        for (i in audioData.indices) {
            sum += audioData[i] * audioData[i]
        }
        return sqrt(sum / audioData.size)
    }

    override fun onPause() {
        super.onPause()
        stopSoundCheck()
        proximitySensor.stopListening()
    }

    override fun onResume() {
        super.onResume()
        proximitySensor.startListening()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopSoundCheck()
        proximitySensor.stopListening()
    }

}

