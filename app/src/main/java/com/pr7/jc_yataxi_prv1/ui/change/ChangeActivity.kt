package com.pr7.jc_yataxi_prv1.ui.change

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Window
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.pr7.jc_yataxi_prv1.R
import com.pr7.jc_yataxi_prv1.data.pref.DRIVER
import com.pr7.jc_yataxi_prv1.data.pref.PASSANGER
import com.pr7.jc_yataxi_prv1.data.pref.SharefPrefManager
import com.pr7.jc_yataxi_prv1.data.pref.USERTYPE
import com.pr7.jc_yataxi_prv1.ui.auth.registername.RegisterDriverNameActivity
import com.pr7.jc_yataxi_prv1.ui.auth.registername.RegisterPassamgerNameActivity
import com.pr7.jc_yataxi_prv1.ui.change.ui.theme.JC_YaTaxi_PRv1Theme
import com.pr7.jc_yataxi_prv1.ui.splash.ui.theme.BorderBarColor
import com.pr7.jc_yataxi_prv1.ui.splash.ui.theme.CardbackgroundLanguage
import com.pr7.jc_yataxi_prv1.ui.splash.ui.theme.StatusBarColor
import com.pr7.jc_yataxi_prv1.utils.statusbarcolorchange
import kotlinx.coroutines.launch

class ChangeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            statusbarcolorchange(window = window, color = StatusBarColor )
            changeScreen()
        }
    }
}
@Preview(showBackground = true)
@Composable
fun changeScreen() {
    val context= LocalContext.current as Activity
    val scope= rememberCoroutineScope()
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
                        SharefPrefManager.saveBoolean(USERTYPE, true)

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
                        SharefPrefManager.saveString(USERTYPE, DRIVER)
                        SharefPrefManager.saveBoolean(USERTYPE, true)
                        //change user type request need
                        val intent = Intent(context, RegisterDriverNameActivity::class.java)
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

