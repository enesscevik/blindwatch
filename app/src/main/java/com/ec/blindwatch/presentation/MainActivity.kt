package com.ec.blindwatch.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.tooling.preview.devices.WearDevices
import com.ec.blindwatch.presentation.theme.BlindwatchTheme

import java.time.LocalTime

import android.content.Context
import android.os.VibrationEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.delay
import android.util.Log
import android.os.VibratorManager



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        setTheme(android.R.style.Theme_DeviceDefault)

        val currentTime = LocalTime.now()

        setContent {
            WearApp(currentTime)
        }
    }
}

@Composable
fun WearApp(time: LocalTime) {
    BlindwatchTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            contentAlignment = Alignment.Center
        ) {

            VibrateManager(clockParser(time))
        }
    }
}


@Composable
fun VibrateManager(f: Int) {
    val context = LocalContext.current
    val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
    val vibrator = vibratorManager.defaultVibrator

    LaunchedEffect(key1 = f) {
        if (vibrator.hasVibrator()) {

            val dot = 100L // 60
            val dash = 300L //180
            val wait = 150L

            val vibrationPattern = when (f % 12) {
                0 -> longArrayOf(0, dash , wait ,dash, wait, dash , wait, dash , wait , dash)
                1 -> longArrayOf(0, dot, wait, dash, wait, dash, wait, dash ,wait, dash)
                2 -> longArrayOf(0, dot, wait, dot, wait, dash, wait, dash, wait, dash)
                3 -> longArrayOf(0, dot, wait, dot, wait, dot, wait, dash, wait, dash)
                4 -> longArrayOf(0, dot , wait, dot, wait, dot, wait, dot, wait, dash)
                5 -> longArrayOf(0,  dot, wait, dot, wait, dot, wait, dot, wait, dot)
                6 -> longArrayOf(0, dash, wait, dot, wait, dot, wait , dot, wait ,dot)
                7 -> longArrayOf(0,dash, wait, dash, wait, dot, wait, dot, wait, dot)
                8 -> longArrayOf(0, dash, wait, dash, wait, dash , wait, dot, wait , dot )
                9 -> longArrayOf(0, dash , wait ,dash, wait, dash , wait, dash , wait , dot)
                10 -> longArrayOf(0, dot, wait, dash, wait, dash, wait, dash, wait, dash, wait, dash)
                11 -> longArrayOf(0, dot, wait, dash, wait, dash, wait, dash, wait, dash, wait, dot)
                else -> longArrayOf()
            }
            if (vibrationPattern.isNotEmpty()) {
                vibrationPattern.forEach {
                    Log.d("VIB_PATTERN", "[$it]")
                }
                val vibrationEffect = VibrationEffect.createWaveform(vibrationPattern, -1)
                vibrator.vibrate(vibrationEffect)
            }
            val totalVibrationTime = vibrationPattern.sum()
            delay(totalVibrationTime + 500L)
            (context as? ComponentActivity)?.finish()

        }
    }
}

fun clockParser(time: LocalTime): Int {
    var hour = time.hour
    val minutes = time.minute
    if(minutes > 25)
        hour += 1
//    if(minutes > 15 && minutes < 45) {
//        hour = hour * (-1)
//    }
    return hour
}

@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    WearApp(LocalTime.now())
}