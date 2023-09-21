package com.pr7.jc_yataxi_prv1.ui.splash

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.pr7.jc_yataxi_prv1.R
import com.pr7.jc_yataxi_prv1.data.pref.DRIVER
import com.pr7.jc_yataxi_prv1.data.pref.LANGUAGE_EN
import com.pr7.jc_yataxi_prv1.data.pref.LANGUAGE_RU
import com.pr7.jc_yataxi_prv1.data.pref.LANGUAGE_UZ
import com.pr7.jc_yataxi_prv1.data.pref.LOGINED
import com.pr7.jc_yataxi_prv1.data.pref.ONBOARDING
import com.pr7.jc_yataxi_prv1.data.pref.PASSANGER
import com.pr7.jc_yataxi_prv1.data.pref.SharefPrefManager
import com.pr7.jc_yataxi_prv1.data.pref.USERNAMED
import com.pr7.jc_yataxi_prv1.data.pref.USERTYPE
import com.pr7.jc_yataxi_prv1.data.pref.USERTYPESELECTED
import com.pr7.jc_yataxi_prv1.ui.change.ChangeActivity
import com.pr7.jc_yataxi_prv1.ui.auth.login.LoginActivity
import com.pr7.jc_yataxi_prv1.ui.auth.registername.RegisterDriverNameActivity
import com.pr7.jc_yataxi_prv1.ui.auth.registername.RegisterPassamgerNameActivity
import com.pr7.jc_yataxi_prv1.ui.home.HomeActivity
import com.pr7.jc_yataxi_prv1.ui.onboarding.OnBoardingActivity
import com.pr7.jc_yataxi_prv1.ui.splash.ui.theme.JC_YaTaxi_PRv1Theme
import com.pr7.jc_yataxi_prv1.ui.splash.ui.theme.StatusBarColor
import com.pr7.jc_yataxi_prv1.utils.nextActivity
import com.pr7.jc_yataxi_prv1.utils.showlogd
import com.pr7.jc_yataxi_prv1.utils.statusbarcolorchange
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : ComponentActivity() {
    @SuppressLint("UnrememberedMutableState", "CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //SharefPrefManager.setLocale(LANGUAGE_RU)

        showlogd(funname = "USERNAMED",SharefPrefManager.loadBoolean(USERNAMED).toString())
        setContent {
            statusbarcolorchange(window =window , color = StatusBarColor)
            splashScreen()
            lifecycleScope.launch {
                delay(3000)

                if (!SharefPrefManager.loadBoolean(ONBOARDING)){
                    nextActivity(this@SplashActivity,OnBoardingActivity())
                    finish()
                }
                else if (!SharefPrefManager.loadBoolean(LOGINED)){
                    nextActivity(this@SplashActivity, LoginActivity())
                    finish()
                }
                else if (!SharefPrefManager.loadBoolean(USERTYPESELECTED)){
                    nextActivity(this@SplashActivity,ChangeActivity())
                    finish()
                }else if (!SharefPrefManager.loadBoolean(USERNAMED)){

                    if (SharefPrefManager.loadString(USERTYPE).toString()==DRIVER){
                        val intent = Intent(this@SplashActivity, RegisterDriverNameActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    if (SharefPrefManager.loadString(USERTYPE).toString()==PASSANGER){
                        val intent = Intent(this@SplashActivity, RegisterPassamgerNameActivity::class.java)
                        startActivity(intent)
                        finish()
                    }

                }
                else{
                    nextActivity(this@SplashActivity,HomeActivity())
                    finish()
                }



            }

        }
    }


}



//@Composable
//fun Greeting(name: String ) {
//   val context= LocalContext.current
//
//    val intent = (Intent(Settings.ACTION_LOCALE_SETTINGS))
//    Text(
//        text = "Hello $name",
//        modifier = Modifier.clickable {
//
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//                SharefPrefManager.setLocale(LANGUAGE_UZ)
//            }else{
//                val intent = (Intent(Settings.ACTION_LOCALE_SETTINGS))
//                context.startActivity(intent)
//            }
//
//        }
//    )
//}
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun splashScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(StatusBarColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {


        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription ="logo" ,
            modifier = Modifier.size(width = 144.dp, height = 175.dp)
        )
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun GreetingPreview() {
//
//
//    val context= LocalContext.current
//    JC_YaTaxi_PRv1Theme {
//        Greeting(stringResource(id = R.string.client))
//    }
//}