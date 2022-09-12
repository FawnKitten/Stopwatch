package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    // make a classwide static constant in kotlin
    companion object {
        // all your "static" constants go here
        val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate: instance created")
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