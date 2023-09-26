package com.pr7.jc_biztaxi_v4.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.pr7.jc_biztaxi_v4.R
import com.pr7.jc_biztaxi_v4.data.pref.SharefPrefManager
import com.pr7.jc_biztaxi_v4.data.pref.USERTYPE
import com.pr7.jc_biztaxi_v4.ui.home.bottomscreens.BottomBar
import com.pr7.jc_biztaxi_v4.ui.home.bottomscreens.bottomNavGraphSetup
import com.pr7.jc_biztaxi_v4.ui.splash.ui.theme.StatusBarColor
import com.pr7.jc_biztaxi_v4.utils.showlogd
import com.pr7.jc_biztaxi_v4.utils.statusbarcolorchange

class HomeActivity : ComponentActivity() {
    val homeViewModel:HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homeViewModel.districtfrom.value= getString(R.string.selectanaddress)
        homeViewModel.districtto.value= getString(R.string.selectanaddress)
        homeViewModel.username.value= getString(R.string.loading)

        showlogd(funname = "check pref", SharefPrefManager.loadString(USERTYPE).toString())

        setContent {
            statusbarcolorchange(window =window , color = StatusBarColor)
            bottombarScreen(homeViewModel)
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun bottombarScreen(homeViewModel: HomeViewModel) {

    val navController = rememberNavController()
    Scaffold(
        bottomBar ={ BottomBar(navHostController = navController) }
    ) {
        bottomNavGraphSetup(navHostController = navController,homeViewModel)
        //navController.navigate(route = Screens.SeatChoose.route)
    }
}

