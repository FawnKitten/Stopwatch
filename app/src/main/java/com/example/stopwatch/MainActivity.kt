package com.example.stopwatch

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Button
import android.widget.Chronometer

class MainActivity : AppCompatActivity() {

    // make a class-wide static constant in kotlin
    companion object {
        // all your "static" constants go here
        const val TAG = "MainActivity"
        const val STATE_DISPLAY = "how many seconds since start of timer"
        const val STATE_RUNNING = "whether the chronometer is running"
    }

    lateinit var startStop: Button
    lateinit var chronometer: Chronometer
    lateinit var reset: Button
    var timeStopped: Long = 0
    var running = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.v(TAG, "onCreate: instance created")
        wireWidgets()
        setListeners()
        if(savedInstanceState != null) {
            running = savedInstanceState.getBoolean(STATE_RUNNING)
            if (running) {
                timeStopped = savedInstanceState.getLong(STATE_DISPLAY)
                // chronometer.base = SystemClock.elapsedRealtime() -
                    // savedInstanceState.getLong(STATE_DISPLAY )
                runClock()
            } else {
                timeStopped = savedInstanceState.getLong(STATE_DISPLAY)
                chronometer.base = SystemClock.elapsedRealtime() - timeStopped
            }
            Log.d(TAG, "onCreate: STATE_TIME=${savedInstanceState.getLong(STATE_DISPLAY)}")
        }
    }

    /**
     * use this to preserve state through orientation changes
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // calculate the display time
        val wasRunning = running
        if (running)
            stopClock()
        outState.putLong(STATE_DISPLAY, timeStopped)
        outState.putBoolean(STATE_RUNNING, wasRunning)
    }

    private fun setListeners() {
        startStop.setOnClickListener {
            if (running) {
                stopClock()
            } else {
                runClock()
            }
        }

        reset.setOnClickListener {
            chronometer.base = SystemClock.elapsedRealtime()
            timeStopped = 0
        }
    }

    private fun runClock() {
        running = true
        startStop.setBackgroundColor(Color.RED)
        chronometer.base = SystemClock.elapsedRealtime() - timeStopped
        chronometer.start()
        startStop.text = "Stop"
    }

    private fun stopClock() {
        running = false
        startStop.setBackgroundColor(resources.getColor(R.color.purple_500))
        timeStopped = SystemClock.elapsedRealtime() - chronometer.base
        chronometer.stop()
        startStop.text = "Start"
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