package com.example.theghostappv3.ui

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.theghostappv3.R
import java.util.*


class MicrophoneActivity : AppCompatActivity() {

    lateinit var microphoneImageView: ImageView
    lateinit var outputText: TextView
    lateinit var textToSpeech: TextToSpeech

    private val REQUEST_CODE_SPEECH_INPUT = 1

    lateinit var activityResultLuncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_microphone)


        // check if the device has a permission to use the microphone

        if (!packageManager.hasSystemFeature(PackageManager.FEATURE_MICROPHONE)) {
            Toast.makeText(this, "No microphone found", Toast.LENGTH_SHORT).show()
            finish()
        }

        microphoneImageView = findViewById(R.id.imageView_btnSpeak)
        outputText = findViewById(R.id.textView_output)

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

            outputText.setText(Objects.requireNonNull(resultArray[0])).toString()

            textToSpeech = TextToSpeech(this, TextToSpeech.OnInitListener { status ->
                if (status != TextToSpeech.ERROR) {


                    textToSpeech.language = Locale.UK
                    textToSpeech.speak(resultArray[0], TextToSpeech.QUEUE_ADD, null)
                }
            })



        }
    }
}

    }

