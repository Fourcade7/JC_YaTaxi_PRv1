package com.pr7.jc_yataxi_prv1.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController

const val TAG = "PR77777"
 lateinit var CONTEXT:MyApp



fun showlogd(funname:String,text:String){
    Log.d("PR77777", "$funname: $text")
}

@Composable
fun statusbarcolorchange(window: Window,color: Color) {
    WindowCompat.setDecorFitsSystemWindows(window, false)
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = color,
            darkIcons = false
        )
    }
}

fun nextActivity(context: Context,activity: Activity){
    val intent=Intent(context,activity::class.java)
    context.startActivity(intent)

}