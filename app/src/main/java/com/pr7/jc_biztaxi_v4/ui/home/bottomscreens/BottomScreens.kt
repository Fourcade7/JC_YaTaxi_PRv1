package com.pr7.jc_biztaxi_v4.ui.home.bottomscreens

import android.annotation.SuppressLint
import android.provider.Settings.Global
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getString
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.pr7.jc_biztaxi_v4.R
import com.pr7.jc_biztaxi_v4.data.pref.DRIVER
import com.pr7.jc_biztaxi_v4.data.pref.PASSANGER
import com.pr7.jc_biztaxi_v4.data.pref.SharefPrefManager
import com.pr7.jc_biztaxi_v4.data.pref.THEME_CHANGE
import com.pr7.jc_biztaxi_v4.data.pref.USERTYPE
import com.pr7.jc_biztaxi_v4.discoverScreen
import com.pr7.jc_biztaxi_v4.orderScreen
import com.pr7.jc_biztaxi_v4.profileScreen
import com.pr7.jc_biztaxi_v4.ui.home.HomeViewModel
import com.pr7.jc_biztaxi_v4.ui.home.dataStoreManagerhome
import com.pr7.jc_biztaxi_v4.ui.home.ui.theme.JC_YaTaxi_PRv1Theme
import com.pr7.jc_biztaxi_v4.ui.splash.ui.theme.BottomColors
import com.pr7.jc_biztaxi_v4.ui.splash.ui.theme.LayoutbackgroundColors
import com.pr7.jc_biztaxi_v4.ui.splash.ui.theme.StatusBarColor2
import com.pr7.jc_biztaxi_v4.utils.CONTEXT
import com.pr7.jc_biztaxi_v4.utils.showlogd
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


sealed class Screens constructor(
    val route: String,
    val title: String,
    val icon: Int
) {
    object Discover : Screens(
        route = "discover_screen",
        title = getString(CONTEXT, R.string.adsress),
        icon = R.drawable.discover
    )


    object Orders : Screens(
        route = "order_screen",
        title = if (SharefPrefManager.loadString(USERTYPE).toString()== PASSANGER) getString(CONTEXT, R.string.drivers) else getString(CONTEXT, R.string.mydirections) ,
        icon = R.drawable.order
    )

    object Profile : Screens(
        route = "profile_screen",
        title = getString(CONTEXT, R.string.profile),
        icon = R.drawable.usercirle
    )

    object SeatChoose : Screens(
        route = "seat_screen",
        title = "SearChoose",
        icon = R.drawable.usercirle
    )

    object Regions : Screens(
        route = "regions_screen",
        title = "Regions",
        icon = R.drawable.usercirle
    )
    object ChangeLanguage : Screens(
        route = "changelanguage_screen",
        title = "changelanguage",
        icon = R.drawable.usercirle
    )


}


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun RowScope.addItem(
    screens: Screens,
    currentDestination: NavDestination?,
    navHostController: NavHostController
) {
    var changetheme by remember {
        mutableStateOf(false)
    }
    GlobalScope.launch(Dispatchers.Main) {
        dataStoreManagerhome.load(THEME_CHANGE).collect{ data->
           // showlogd("datastoremanager LOAD","$data")
            if (data=="true"){
                changetheme=true
            }else{
                changetheme=false
            }
        }
    }

    NavigationBarItem(

        label = {
            Text(
                text = screens.title,
                fontFamily = FontFamily(Font(R.font.mont_bold))
            )
        },
        icon = {
            Icon(
                painter = painterResource(id = screens.icon),
                contentDescription = "",
                modifier = Modifier
                    .size(35.dp)
                    .padding(bottom = 5.dp)

            )
        },
        selected = currentDestination?.hierarchy?.any { it.route == screens.route } == true,
        onClick = {
            navHostController.navigate(screens.route)
        },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = BottomColors,
            selectedTextColor = BottomColors,
            indicatorColor = if (changetheme) StatusBarColor2 else Color.White,
            unselectedIconColor = Color.Gray,
            unselectedTextColor = Color.Gray,
        ),


        )
}


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun BottomBar(navHostController: NavHostController) {


    val screens = listOf(
        Screens.Discover,
        Screens.Orders,
        Screens.Profile,

        )
    var changetheme by remember {
        mutableStateOf(false)
    }
   GlobalScope.launch(Dispatchers.Main) {
       dataStoreManagerhome.load(THEME_CHANGE).collect{ data->
          //showlogd("datastoremanager LOAD","$data")
           if (data=="true"){
               changetheme=true
           }else{
               changetheme=false
           }
       }
   }

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    JC_YaTaxi_PRv1Theme {
        NavigationBar(
            containerColor = if (changetheme) StatusBarColor2 else Color.White
        ) {
            screens.forEach {
                addItem(
                    screens = it,
                    currentDestination = currentDestination,
                    navHostController = navHostController
                )
            }
        }
    }


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun bottomNavGraphSetup(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel
    ) {


    NavHost(navController = navHostController, startDestination = Screens.Discover.route) {
        composable(route = Screens.Discover.route) {

        if (SharefPrefManager.loadString(USERTYPE).toString()== PASSANGER){
            discoverScreen(navHostController,homeViewModel)
        }else if (SharefPrefManager.loadString(USERTYPE).toString()== DRIVER){
            driverDiscoverScreen(navHostController,homeViewModel)
        }else{
            discoverScreen(navHostController,homeViewModel)

        }

        }
        composable(route = Screens.Orders.route) {
            if (SharefPrefManager.loadString(USERTYPE).toString()== PASSANGER){
                orderScreen(homeViewModel,navHostController)
            }else if (SharefPrefManager.loadString(USERTYPE).toString()== DRIVER){
                driverDirectionsScreen(homeViewModel,navHostController)
            }

        }
        composable(route = Screens.Profile.route) { profileScreen(navHostController = navHostController) }
        composable(route = Screens.SeatChoose.route) { seeatChooseScreen2(homeViewModel,navHostController) }
        composable(route = Screens.Regions.route) { regionsListScreen(navHostController=navHostController,homeViewModel) }
        composable(route = Screens.ChangeLanguage.route) { changeLanguageScreen(navHostController) }


    }
}