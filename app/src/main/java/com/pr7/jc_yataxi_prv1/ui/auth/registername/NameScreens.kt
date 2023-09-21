package com.pr7.jc_yataxi_prv1.ui.auth.registername

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pr7.jc_yataxi_prv1.R

sealed class CarListScreens constructor(
    val route: String,
    val title: String,
    val icon: Int
) {
    object CarLIst : CarListScreens(
        route = "carlist_screen",
        title = "Manzil",
        icon = R.drawable.discover
    )
    object DriverName : CarListScreens(
        route = "driversetname_screen",
        title = "Manzil",
        icon = R.drawable.discover
    )
    object CarColor : CarListScreens(
        route = "carcolor_screen",
        title = "Manzil",
        icon = R.drawable.discover
    )
}

@ExperimentalMaterial3Api
@Composable
fun NavGraphSetup(
    navHostController: NavHostController,
    registerNameViewModel: RegisterNameViewModel
) {


    NavHost(
        navController = navHostController,
        startDestination = CarListScreens.DriverName.route
    ){

        composable(route = CarListScreens.CarLIst.route){ drivercarList(navHostController, registerNameViewModel = registerNameViewModel) }
        composable(route = CarListScreens.DriverName.route){ registerdrivernameScreen(navHostController,registerNameViewModel) }
        composable(route = CarListScreens.CarColor.route){ carColorScreen(registerNameViewModel = registerNameViewModel, navHostController = navHostController) }



    }
}