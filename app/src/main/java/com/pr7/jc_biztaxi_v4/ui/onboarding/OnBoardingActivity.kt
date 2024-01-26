package com.pr7.jc_biztaxi_v4.ui.onboarding

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pr7.jc_biztaxi_v4.R
import com.pr7.jc_biztaxi_v4.data.pref.LANGUAGE_COUNT
import com.pr7.jc_biztaxi_v4.data.pref.LANGUAGE_EN
import com.pr7.jc_biztaxi_v4.data.pref.LANGUAGE_RU
import com.pr7.jc_biztaxi_v4.data.pref.LANGUAGE_UZ
import com.pr7.jc_biztaxi_v4.data.pref.ONBOARDING
import com.pr7.jc_biztaxi_v4.data.pref.SharefPrefManager
import com.pr7.jc_biztaxi_v4.ui.auth.login.LoginActivity
import com.pr7.jc_biztaxi_v4.ui.splash.ui.theme.ButtonbackgroundLanguage
import com.pr7.jc_biztaxi_v4.ui.splash.ui.theme.CardbackgroundLanguage
import com.pr7.jc_biztaxi_v4.ui.splash.ui.theme.StatusBarColor
import com.pr7.jc_biztaxi_v4.utils.statusbarcolorchange
import kotlinx.coroutines.launch

class OnBoardingActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            statusbarcolorchange(window = window, color = StatusBarColor)
            onBoardMain()
        }
    }
}


@ExperimentalFoundationApi
@Composable
fun onBoardMain() {
    val pagerState = rememberPagerState { 3 }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current as Activity

    val array= arrayOf("Lang",LANGUAGE_EN, LANGUAGE_RU, LANGUAGE_UZ)
    var counter by remember{
        mutableStateOf(SharefPrefManager.loadInt(LANGUAGE_COUNT))
    }



    Column() {
        Card(
            modifier = Modifier
                .padding(top = 16.dp, end = 16.dp, start = 16.dp)
                .clickable {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        counter++
                        if (counter==4){
                            counter=0
                        }
                        SharefPrefManager.saveInt(LANGUAGE_COUNT,counter)
                        SharefPrefManager.setLocale(array[ SharefPrefManager.loadInt(LANGUAGE_COUNT)])


                    }
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
                        val intent = (Intent(Settings.ACTION_LOCALE_SETTINGS))
                        context.startActivity(intent)
                    }
                },
            colors = CardDefaults.cardColors(CardbackgroundLanguage),
            shape = RoundedCornerShape(25.dp),
            elevation = CardDefaults.cardElevation(5.dp)
        ) {
            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.globe),
                    contentDescription = "globe",
                    modifier = Modifier.size(25.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = when(counter){
                                        1->{"En"}
                                        2->{"Ru"}
                                        3->{"Uz"}
                         else -> {"Lang"}
                    },
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.mont_semibold))
                )
                Icon(
                    painter = painterResource(id = R.drawable.arrowdown),
                    contentDescription = "globe",
                    modifier = Modifier.size(25.dp)
                )
            }
        }
        Column(
            modifier = Modifier.weight(2f),
            verticalArrangement = Arrangement.Bottom
        ) {

            onBoardingScreen(pagerState = pagerState)
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(
                    id = when (pagerState.currentPage) {
                        0 -> {
                            R.drawable.dot1
                        }

                        1 -> {
                            R.drawable.dot2
                        }

                        2 -> {
                            R.drawable.dot3
                        }

                        else -> {
                            R.drawable.dot1
                        }
                    }
                ),
                contentDescription = "dot",
                modifier = Modifier
                    .size(70.dp)
                    .align(alignment = Alignment.CenterHorizontally)
            )
        }
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(54.dp)
                .clickable {


                    scope.launch {
                        pagerState.animateScrollToPage(
                            pagerState.currentPage + 1
                        )
                    }
                    if (pagerState.currentPage == 2) {
                        context.startActivity(Intent(context, LoginActivity::class.java))
                        SharefPrefManager.saveBoolean(ONBOARDING, true)
                        context.finish()


                    }

                },
            shape = RoundedCornerShape(15.dp),
            color = ButtonbackgroundLanguage
        ) {
            Column(verticalArrangement = Arrangement.Center) {

                Text(
                    text = if (pagerState.currentPage == 2) stringResource(id = R.string.start) else stringResource(
                        id = R.string.next
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily(Font(R.font.mont_semibold)),
                    fontSize = 17.sp
                )
            }

        }
        Spacer(modifier = Modifier.height(15.dp))

    }

}


