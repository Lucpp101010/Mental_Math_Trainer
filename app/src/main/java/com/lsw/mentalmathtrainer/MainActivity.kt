package com.lsw.mentalmathtrainer

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.speech.tts.TextToSpeech
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    // tts = TextToSpeech
    lateinit var tts: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        tts = TextToSpeech(applicationContext, TextToSpeech.OnInitListener {status->
            if(status != TextToSpeech.ERROR)
            {
                tts.setLanguage(Locale.GERMANY)
            }
        })

        var levelHandler = LevelHandler(arrayOf(antwort1, antwort2, antwort3, antwort4), aufgabe, tts, timeBar)
        levelHandler.startLevel(0)

        antwort1.setOnClickListener {
            levelHandler.handleInput(0)
        }
        antwort2.setOnClickListener {
            levelHandler.handleInput(1)
        }
        antwort3.setOnClickListener {
            levelHandler.handleInput(2)
        }
        antwort4.setOnClickListener {
            levelHandler.handleInput(3)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
