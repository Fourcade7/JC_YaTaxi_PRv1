package com.pr7.jc_yataxi_prv1.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.pr7.jc_yataxi_prv1.R
import com.pr7.jc_yataxi_prv1.data.pref.SharefPrefManager
import com.pr7.jc_yataxi_prv1.data.pref.USERTYPE
import com.pr7.jc_yataxi_prv1.ui.home.bottomscreens.BottomBar
import com.pr7.jc_yataxi_prv1.ui.home.bottomscreens.bottomNavGraphSetup
import com.pr7.jc_yataxi_prv1.ui.home.ui.theme.JC_YaTaxi_PRv1Theme
import com.pr7.jc_yataxi_prv1.ui.splash.ui.theme.StatusBarColor
import com.pr7.jc_yataxi_prv1.utils.showlogd
import com.pr7.jc_yataxi_prv1.utils.statusbarcolorchange

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

