package com.pr7.jc_biztaxi_v4.ui.auth.registername

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.compose.rememberNavController
import com.pr7.jc_biztaxi_v4.ui.splash.ui.theme.StatusBarColor
import com.pr7.jc_biztaxi_v4.utils.statusbarcolorchange

@OptIn(ExperimentalMaterial3Api::class)
class RegisterDriverNameActivity : ComponentActivity() {
    val registerNameViewModel :RegisterNameViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            statusbarcolorchange(window = window, color = StatusBarColor )

            val navHostController= rememberNavController()
            NavGraphSetup(navHostController = navHostController, registerNameViewModel = registerNameViewModel)
            //navHostController.popBackStack()
            //registerdrivernameScreen()
        }
    }
}

