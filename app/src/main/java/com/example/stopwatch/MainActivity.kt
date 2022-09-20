package com.example.stopwatch

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.os.SystemClock
import android.util.Log
import android.widget.Button
import android.widget.Chronometer

class MainActivity : AppCompatActivity() {

    // make a class-wide static constant in kotlin
    companion object {
        // all your "static" constants go here
        val TAG = "MainActivity"
        val STATE_STARTED = "how many seconds since start of timer"
        val STATE_RUNNING = "whether the chronometer is running"
    }

    lateinit var startStop: Button
    lateinit var chronometer: Chronometer
    lateinit var reset: Button
    var timeStopped: Long = 0
    var running = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.v(TAG, "onCreate: instance created")
        wireWidgets()
        setListeners()
        if(savedInstanceState != null) {
            running = savedInstanceState.getBoolean(STATE_RUNNING)
            timeStopped = savedInstanceState.getLong(STATE_STARTED)
            chronometer.base = SystemClock.elapsedRealtime() - timeStopped
            // if (running) chronometer.start()
            Log.d(TAG, "onCreate: STATE_TIME=${savedInstanceState.getLong(STATE_STARTED)}")
        }
    }

    /**
     * use this to preserve state through orientation changes
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // calculate the display time
        val displayTime =
            /* if (running)
                chronometer.base
            else */
                timeStopped
        outState.putLong(STATE_STARTED, displayTime)
        outState.putBoolean(STATE_RUNNING, running)
    }

    private fun setListeners() {
        startStop.setOnClickListener {
            if (running) {
                running = false
                startStop.setBackgroundColor(Color.RED)
                chronometer.base = SystemClock.elapsedRealtime() - timeStopped
                chronometer.start()
                startStop.text = "Stop"
            } else {
                running = true
                startStop.setBackgroundColor(resources.getColor(R.color.purple_500))
                timeStopped = SystemClock.elapsedRealtime() - chronometer.base
                chronometer.stop()
                startStop.text = "Start"
            }
        }

        reset.setOnClickListener {
            chronometer.base = SystemClock.elapsedRealtime()
            timeStopped = 0
        }
    }

    private fun wireWidgets() {
        startStop = findViewById(R.id.button_main_start)
        chronometer = findViewById(R.id.chronometer_main_stopwatch)
        reset = findViewById(R.id.button_main_reset)
    }

    override fun onStart() {
        super.onStart()
        Log.v(TAG, "onStart: let's get this program Started!")
    }

    override fun onRestart() {
        super.onRestart()
        Log.v(TAG, "onRestart: restarted")
    }

    override fun onResume() {
        super.onResume()
        Log.v(TAG, "onResume: resumed")
    }

    override fun onPause() {
        super.onPause()
        Log.v(TAG, "onPause: paused")
    }

    override fun onStop() {
        super.onStop()
        Log.v(TAG, "onStop: stopped")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v(TAG, "onDestroy: destroyed")
    }
}