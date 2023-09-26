package com.pr7.jc_biztaxi_v4.ui.change

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.gson.GsonBuilder
import com.pr7.jc_biztaxi_v4.R
import com.pr7.jc_biztaxi_v4.data.model.changeusertype.ChangeUserTypeResponse
import com.pr7.jc_biztaxi_v4.data.pref.DRIVER
import com.pr7.jc_biztaxi_v4.data.pref.PASSANGER
import com.pr7.jc_biztaxi_v4.data.pref.SharefPrefManager
import com.pr7.jc_biztaxi_v4.data.pref.TOKEN

import com.pr7.jc_biztaxi_v4.data.pref.USERTYPE
import com.pr7.jc_biztaxi_v4.data.pref.USERTYPESELECTED
import com.pr7.jc_biztaxi_v4.ui.auth.registername.RegisterDriverNameActivity
import com.pr7.jc_biztaxi_v4.ui.auth.registername.RegisterPassamgerNameActivity
import com.pr7.jc_biztaxi_v4.ui.home.HomeActivity
import com.pr7.jc_biztaxi_v4.ui.splash.ui.theme.BorderBarColor
import com.pr7.jc_biztaxi_v4.ui.splash.ui.theme.StatusBarColor
import com.pr7.jc_biztaxi_v4.utils.nextActivity
import com.pr7.jc_biztaxi_v4.utils.showlogd
import com.pr7.jc_biztaxi_v4.utils.statusbarcolorchange

class ChangeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            statusbarcolorchange(window = window, color = StatusBarColor )
            changeScreen()
            if (SharefPrefManager.loadBoolean(USERTYPESELECTED)){
                nextActivity(this@ChangeActivity,HomeActivity())
                finish()
            }


        }
    }
}
@Preview(showBackground = true)
@Composable
fun changeScreen() {
    val context= LocalContext.current as Activity

    val changeViewModel:ChangeViewModel= viewModel()

    val mlivedataChangeUsertype by changeViewModel.mlivedataChangeUsertype.observeAsState()
    val iscompletedchangetype by changeViewModel.iscompletedchangetype.observeAsState()


    var countopenotp by remember {
        mutableStateOf<Int?>(0)
    }
    if (countopenotp==1){
        if (SharefPrefManager.loadString(TOKEN)!=null){
            context.startActivity(Intent(context, RegisterDriverNameActivity::class.java))
            context.finish()
        }

    }

    mlivedataChangeUsertype.let {result ->
        result?.onSuccess {
                showlogd(funname = "user type SUCCES CHANGED",it.user_type.toString())
                SharefPrefManager.saveString(USERTYPE, DRIVER)
                SharefPrefManager.saveBoolean(USERTYPESELECTED, true)
                countopenotp=countopenotp!!+1
        }
        result?.onFailure {
                try {
                    val gson= GsonBuilder().create()
                    val gsonparse: ChangeUserTypeResponse =gson.fromJson(it.message, ChangeUserTypeResponse::class.java)
                    showlogd(funname = "user type ERROR 1 CHANGED",gsonparse.code.toString())
                    countopenotp=0

                }catch (e:Exception){
                    showlogd(funname = "user type ERROR 2 CHANGED",it.message.toString())
                    countopenotp=0
                }
        }

    }


    Box() {
        Image(
            painter = painterResource(id = R.drawable.frame),
            contentDescription = "frame",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )



        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(32.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.choose),
                textAlign = TextAlign.Center,
                fontSize = 28.sp,
                fontFamily = FontFamily(Font(R.font.mont_bold))
            )
            Spacer(modifier = Modifier.height(20.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clickable {
                        //change user type request need
                        SharefPrefManager.saveString(USERTYPE, PASSANGER)
                        SharefPrefManager.saveBoolean(USERTYPESELECTED, true)
                        val intent = Intent(context, RegisterPassamgerNameActivity::class.java)
                        context.startActivity(intent)
                        context.finish()


                    },
                border = BorderStroke(width = 1.dp, color = BorderBarColor),
                colors = CardDefaults.cardColors(Color.White),
                elevation = CardDefaults.cardElevation(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.passangervector),
                        contentDescription = "passenger",
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Fit,
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(
                        text = stringResource(id = R.string.iampassanger),
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.mont_semibold))
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        painter = painterResource(id = R.drawable.round_keyboard_arrow_right_24),
                        contentDescription = "arrow"
                    )

                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clickable {
                               changeViewModel.changeUsertype(SharefPrefManager.loadString(TOKEN).toString(),DRIVER)
                        // SharefPrefManager.saveString(USERTYPE, DRIVER)
                        //SharefPrefManager.saveBoolean(USERTYPESELECTED, true)
                        //change user type request need
                        //val intent = Intent(context, RegisterDriverNameActivity::class.java)
                        //context.startActivity(intent)
                        //context.finish()

                    },
                border = BorderStroke(width = 1.dp, color = BorderBarColor),
                colors = CardDefaults.cardColors(Color.White),
                elevation = CardDefaults.cardElevation(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.drivervector),
                        contentDescription = "driver",
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Fit,
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(
                        text = stringResource(id = R.string.iamdriver),
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.mont_semibold))
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        painter = painterResource(id = R.drawable.round_keyboard_arrow_right_24),
                        contentDescription = "arrow"
                    )

                }
            }

        }
    }
}

