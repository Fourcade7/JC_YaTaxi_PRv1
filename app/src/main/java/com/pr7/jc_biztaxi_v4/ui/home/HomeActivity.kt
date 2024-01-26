package com.pr7.jc_biztaxi_v4.ui.home

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.GsonBuilder
import com.pr7.jc_biztaxi_v4.R
import com.pr7.jc_biztaxi_v4.data.model.userinfo.UserInfoResponse
import com.pr7.jc_biztaxi_v4.data.pref.DISFROM_ID
import com.pr7.jc_biztaxi_v4.data.pref.DISTO_ID
import com.pr7.jc_biztaxi_v4.data.pref.DataStoreManager
import com.pr7.jc_biztaxi_v4.data.pref.NOTIFICATION_ORDER
import com.pr7.jc_biztaxi_v4.data.pref.PHONE
import com.pr7.jc_biztaxi_v4.data.pref.REFRESH_TOKEN
import com.pr7.jc_biztaxi_v4.data.pref.REGFROM_ID
import com.pr7.jc_biztaxi_v4.data.pref.REGTO_ID
import com.pr7.jc_biztaxi_v4.data.pref.SharefPrefManager
import com.pr7.jc_biztaxi_v4.data.pref.TOKEN
import com.pr7.jc_biztaxi_v4.data.pref.USERNAME
import com.pr7.jc_biztaxi_v4.data.pref.USERTYPE
import com.pr7.jc_biztaxi_v4.ui.home.bottomscreens.BottomBar
import com.pr7.jc_biztaxi_v4.ui.home.bottomscreens.Screens
import com.pr7.jc_biztaxi_v4.ui.home.bottomscreens.bottomNavGraphSetup
import com.pr7.jc_biztaxi_v4.ui.home.ui.theme.JC_YaTaxi_PRv1Theme
import com.pr7.jc_biztaxi_v4.ui.splash.ui.theme.StatusBarColor
import com.pr7.jc_biztaxi_v4.utils.showlogd
import com.pr7.jc_biztaxi_v4.utils.statusbarcolorchange
import java.lang.Exception



@SuppressLint("StaticFieldLeak")
lateinit var dataStoreManagerhome: DataStoreManager

class HomeActivity : ComponentActivity() {
    val homeViewModel:HomeViewModel by viewModels()
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createNotificationChannel()
        dataStoreManagerhome= DataStoreManager(this@HomeActivity)

        homeViewModel.districtfrom.value= getString(R.string.selectanaddress)
        homeViewModel.districtto.value= getString(R.string.selectanaddress)
        homeViewModel.username.value= getString(R.string.loading)



        val token=SharefPrefManager.loadString(TOKEN).toString()
        showlogd("TOKEN ACCES",token)
       // SharefPrefManager.saveString(TOKEN, "${token}777")
        homeViewModel.getuserinfo(token = SharefPrefManager.loadString(TOKEN).toString())
        homeViewModel.mlivedataUserInfo.observe(this@HomeActivity){result->

            result?.onSuccess {
                showlogd(funname = "userinfo", it.user_type.toString())
                showlogd(funname = "userinfo firstname", it.first_name.toString())

                homeViewModel.username.value = "${it.first_name.toString()} ${it.last_name.toString()}"
                SharefPrefManager.saveString(USERNAME, "${it.first_name} ${it.last_name}")
                SharefPrefManager.saveString(PHONE, "${it.phone}")


            }
            result?.onFailure {
                showlogd(funname = "userinfo", it.message.toString())
                try {
                    val gson = GsonBuilder().create()
                    val gsonparse: UserInfoResponse =
                        gson.fromJson(it.message, UserInfoResponse::class.java)
                    if (gsonparse.code != null) {
                        showlogd(funname = "TNV Ошибка", text = gsonparse.code.toString())
                        //messageresponse=gsonparse.message.toString()
                        homeViewModel.username.value = "NetWork Error"

                        if (gsonparse.code == "token_not_valid") {
                            //REFRESH TOKEN REQUEST
                            //AND
                            homeViewModel.refreshtoken(refreshtoken = SharefPrefManager.loadString(REFRESH_TOKEN).toString())
                        }


                    }


                } catch (e: Exception) {
                    result.onFailure {
                        homeViewModel.username.value = it.message.toString()
                    }

                }

            }

        }
        homeViewModel.mlivedataRefreshtoken.observe(this@HomeActivity){result->

            result?.onSuccess {

                SharefPrefManager.saveString(TOKEN, it.access.toString())
                SharefPrefManager.saveString(REFRESH_TOKEN, it.refresh.toString())
                homeViewModel.getuserinfo(token = SharefPrefManager.loadString(TOKEN).toString())
                showlogd("Refresh request", text = "${it.access}")
                //recreate()
            }
            result?.onFailure {

            }
        }


        FirebaseMessaging.getInstance().token.addOnSuccessListener {
            showlogd(funname = "FirebaseMessaging", text = it)
            homeViewModel.firebaseFCM(token = SharefPrefManager.loadString(TOKEN).toString(), fcmToken = it)
        }.addOnFailureListener {
            showlogd(funname = "FirebaseMessaging ERROR", text = it.toString())
        }

        homeViewModel.mlivedataFirebaseFCM.observe(this@HomeActivity){result->
            result?.onSuccess {
                showlogd(funname = "firebase send", text = it.message.toString())
            }
            result?.onFailure {
                showlogd(funname = "firebase send error", text = it.message.toString())
            }
        }


        if (SharefPrefManager.loadInt(REGFROM_ID)!=0 && SharefPrefManager.loadInt(REGTO_ID)!=0 && SharefPrefManager.loadInt(
                DISFROM_ID)!=0 && SharefPrefManager.loadInt(DISTO_ID)!=0) {

            //navController.navigate(Screens.Orders.route)
            homeViewModel.getalldirections(
                token = SharefPrefManager.loadString(TOKEN).toString(),
                fromdisid = SharefPrefManager.loadInt(DISFROM_ID),
                todisid = SharefPrefManager.loadInt(DISTO_ID),
                fromregid = SharefPrefManager.loadInt(REGFROM_ID),
                toregid = SharefPrefManager.loadInt(REGTO_ID)
            )

        }




        showlogd(funname = "check pref", SharefPrefManager.loadString(USERTYPE).toString())

        setContent {

           JC_YaTaxi_PRv1Theme(darkTheme = false) {
               val navController = rememberNavController()


                  // statusbarcolorchange(window =window , color = StatusBarColor)

               bottombarScreen(homeViewModel,navController)
               homeViewModel.mlivedatanewseat.observe(this@HomeActivity){result->

                   result?.onSuccess {
                       showlogd(funname = "new Seat", text = " Succesfuly ")
                       homeViewModel.getalldirections(
                           token = SharefPrefManager.loadString(TOKEN).toString(),
                           fromdisid = homeViewModel.disfromid.value!!,
                           todisid = homeViewModel.distoid.value!!,
                           fromregid = homeViewModel.regfromid.value!!,
                           toregid = homeViewModel.regtoid.value!!
                       )
                       navController.navigate(Screens.Orders.route)
                   }
                   result?.onFailure {

                   }

               }

               homeViewModel.mlivedataGetDirections.observe(this@HomeActivity){
                       result ->

                   result?.onSuccess {

                       navController.navigate(Screens.Orders.route)

                   }
                   result?.onFailure {

                   }

               }
           }
        }
    }


    fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Personal Notification"
            val descriptionText = "channel_description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("CHANNEL_ID", name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
        //Simple notification

    }
}
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun bottombarScreen(homeViewModel: HomeViewModel,navController:NavHostController) {


    Scaffold(
        bottomBar ={ BottomBar(navHostController = navController) }
    ) {
        bottomNavGraphSetup(navHostController = navController,homeViewModel)
        //navController.navigate(route = Screens.SeatChoose.route)
    }
}


@Composable
fun myAlertDialog(message:String,opened:Boolean) {
    var openDialog by remember { mutableStateOf(opened) }
   if (openDialog){
       AlertDialog(
           modifier = Modifier
               .clip(RoundedCornerShape(8.dp))
               .fillMaxWidth(), // corner rounded//not working
           onDismissRequest = {

           },
           title = { Text(text = stringResource(id = R.string.seatreservation)) },
           text = { Text(text = message) },
           confirmButton = {
               Button(onClick = {
                   SharefPrefManager.saveBoolean(NOTIFICATION_ORDER,false)
                   openDialog=false


               }) {
                   Text(text = stringResource(id = R.string.yes))
               }
           },
           dismissButton = {
               Button(onClick = {
               }) {
                   Text(text = stringResource(id = R.string.no))
                   SharefPrefManager.saveBoolean(NOTIFICATION_ORDER,false)
                   openDialog=false
               }
           }
       )
   }
}

