package com.pr7.jc_biztaxi_v4.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import com.google.firebase.messaging.FirebaseMessaging
import com.pr7.jc_biztaxi_v4.R
import com.pr7.jc_biztaxi_v4.data.pref.DRIVER
import com.pr7.jc_biztaxi_v4.data.pref.LOGINED
import com.pr7.jc_biztaxi_v4.data.pref.ONBOARDING
import com.pr7.jc_biztaxi_v4.data.pref.PASSANGER
import com.pr7.jc_biztaxi_v4.data.pref.SharefPrefManager
import com.pr7.jc_biztaxi_v4.data.pref.TOKEN
import com.pr7.jc_biztaxi_v4.data.pref.USERNAMED
import com.pr7.jc_biztaxi_v4.data.pref.USERTYPE
import com.pr7.jc_biztaxi_v4.data.pref.USERTYPESELECTED
import com.pr7.jc_biztaxi_v4.ui.change.ChangeActivity
import com.pr7.jc_biztaxi_v4.ui.auth.login.LoginActivity
import com.pr7.jc_biztaxi_v4.ui.auth.registername.RegisterDriverNameActivity
import com.pr7.jc_biztaxi_v4.ui.auth.registername.RegisterPassamgerNameActivity
import com.pr7.jc_biztaxi_v4.ui.home.HomeActivity
import com.pr7.jc_biztaxi_v4.ui.onboarding.OnBoardingActivity
import com.pr7.jc_biztaxi_v4.ui.splash.ui.theme.ButtonbackgroundLanguage
import com.pr7.jc_biztaxi_v4.ui.splash.ui.theme.StatusBarColor
import com.pr7.jc_biztaxi_v4.utils.nextActivity
import com.pr7.jc_biztaxi_v4.utils.showlogd
import com.pr7.jc_biztaxi_v4.utils.statusbarcolorchange
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : ComponentActivity() {
    @SuppressLint("UnrememberedMutableState", "CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //SharefPrefManager.saveString(TOKEN,"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjk3NjQzMDI4LCJpYXQiOjE2OTc1NTY2MjgsImp0aSI6ImNiYzc4ZTBkZjE5MDRkZTFiNWMyMjEzYzdhNDVhNDdmIiwidXNlcl9pZCI6IjNkNTg1MWUyLWUyYTEtNDQ4ZS04OGZjLWRiOTkzODY4YzM0ZSJ9.86XBolgFvo_muRXl7LmmHpGb0_aKwMyeqDZ9-rX80II")
        FirebaseMessaging.getInstance().token.addOnSuccessListener {
            showlogd(funname = "FirebaseMessaging", text = it)
        }.addOnFailureListener {
            showlogd(funname = "FirebaseMessaging ERROR", text = it.toString())
        }
        val token=SharefPrefManager.loadString(TOKEN).toString()
        showlogd("TOKEN ACCES",token)
        //SharefPrefManager.saveString(TOKEN, "${token}777")


        //showlogd(funname = "USERNAMED",SharefPrefManager.loadBoolean(USERNAMED).toString())
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

        Text(

            text = stringResource(id = R.string.wetaxi),
            textAlign = TextAlign.Start,
            fontSize = 40.sp,
            fontFamily = FontFamily(Font(R.font.mont_bold)),
            color = ButtonbackgroundLanguage
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