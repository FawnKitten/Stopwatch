package com.example.stopwatch

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Button
import android.widget.Chronometer

class MainActivity : AppCompatActivity() {

    // make a classwide static constant in kotlin
    companion object {
        // all your "static" constants go here
        val TAG = "MainActivity"
    }

    lateinit var startStop: Button
    lateinit var chronometer: Chronometer
    lateinit var reset: Button
    var timeStopped: Long = 0

    var running = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate: instance created")
        wireWidgets()
        setListeners()
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
        Log.d(TAG, "onStart: let's get this program Started!")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: restarted")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: resumed")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: paused")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: stopped")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: destroyed")
    }
}