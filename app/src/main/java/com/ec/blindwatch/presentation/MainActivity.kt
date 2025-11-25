package com.ec.blindwatch.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity


import java.time.LocalTime

import android.content.Context
import android.os.VibrationEffect

import kotlinx.coroutines.delay
import android.util.Log
import android.os.VibratorManager
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val currentTime = LocalTime.now()
        val parsedHour = clockParser(currentTime)

        triggerVibrationAndClose(parsedHour)
    }

    private fun triggerVibrationAndClose(f: Int) {
        val vibratorManager = getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
        val vibrator = vibratorManager.defaultVibrator

        if (vibrator.hasVibrator()) {
            val dot = 120L
            val dash = 360L
            val wait = 200L

            /*
                val vibrationPattern = when (f % 12) {
                0 -> longArrayOf(0, dash, wait, dash, wait, dash, wait, dash, wait, dash)
                1 -> longArrayOf(0, dot, wait, dash, wait, dash, wait, dash, wait, dash)
                2 -> longArrayOf(0, dot, wait, dot, wait, dash, wait, dash, wait, dash)
                3 -> longArrayOf(0, dot, wait, dot, wait, dot, wait, dash, wait, dash)
                4 -> longArrayOf(0, dot, wait, dot, wait, dot, wait, dot, wait, dash)
                5 -> longArrayOf(0, dot, wait, dot, wait, dot, wait, dot, wait, dot)
                6 -> longArrayOf(0, dash, wait, dot, wait, dot, wait, dot, wait, dot)
                7 -> longArrayOf(0, dash, wait, dash, wait, dot, wait, dot, wait, dot)
                8 -> longArrayOf(0, dash, wait, dash, wait, dash, wait, dot, wait, dot)
                9 -> longArrayOf(0, dash, wait, dash, wait, dash, wait, dash, wait, dot)
                10 -> longArrayOf(0, dot, wait, dash, wait, dash, wait, dash, wait, dash, wait, dash)
                11 -> longArrayOf(0, dot, wait, dash, wait, dash, wait, dash, wait, dash, wait, dot)
                else -> longArrayOf()
            }
                */

            val vibrationPattern = when (f % 12) {
                0 -> longArrayOf(0, dash, wait, dash, wait, dash, wait, dash, wait, dash)
                1 -> longArrayOf(0, dot)
                2 -> longArrayOf(0, dot, wait, dot)
                3 -> longArrayOf(0, dot, wait, dot, wait, dot)
                4 -> longArrayOf(0, dot, wait, dot, wait, dot, wait, dot)
                5 -> longArrayOf(0, dot, wait, dot, wait, dot, wait, dot, wait, dot)
                6 -> longArrayOf(0, dash)
                7 -> longArrayOf(0, dash, wait, dash)
                8 -> longArrayOf(0, dash, wait, dash, wait, dash)
                9 -> longArrayOf(0, dash, wait, dash, wait, dash, wait, dash)
                10 -> longArrayOf(0, dot, wait, dash, wait, dot)
                11 -> longArrayOf(0, dash, wait, dot, wait, dash)
                else -> longArrayOf()
            }

            if (vibrationPattern.isNotEmpty()) {
                Log.d("VIB_PATTERN", vibrationPattern.contentToString())
                val vibrationEffect = VibrationEffect.createWaveform(vibrationPattern, -1)
                vibrator.vibrate(vibrationEffect)

                lifecycleScope.launch {
                    val totalVibrationTime = vibrationPattern.sum()
                    delay(totalVibrationTime + 200L)

                    finishAndRemoveTask()
                }
            } else {
                finishAndRemoveTask()
            }
        } else {
            finishAndRemoveTask()
        }
    }

    private fun clockParser(time: LocalTime): Int {
        var hour = time.hour
        val minutes = time.minute
        if (minutes > 25) hour += 1
        return hour
    }
}