package com.pr7.jc_biztaxi_v4.ui.home.bottomscreens

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.pr7.jc_biztaxi_v4.R
import com.pr7.jc_biztaxi_v4.data.pref.LANGUAGE_EN
import com.pr7.jc_biztaxi_v4.data.pref.LANGUAGE_RU
import com.pr7.jc_biztaxi_v4.data.pref.LANGUAGE_TYPE
import com.pr7.jc_biztaxi_v4.data.pref.LANGUAGE_UZ
import com.pr7.jc_biztaxi_v4.data.pref.SharefPrefManager
import com.pr7.jc_biztaxi_v4.data.pref.THEME_CHANGE
import com.pr7.jc_biztaxi_v4.ui.home.ui.theme.JC_YaTaxi_PRv1Theme
import com.pr7.jc_biztaxi_v4.ui.splash.ui.theme.CardBackgroundTransparent
import com.pr7.jc_biztaxi_v4.ui.splash.ui.theme.LayoutbackgroundColors


@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun changeLanguageScreen(navHostController: NavHostController) {
    val context= LocalContext.current as Activity

    val langtype=SharefPrefManager.loadString(LANGUAGE_TYPE)

    var themechange by remember {
        mutableStateOf(SharefPrefManager.loadBoolean(THEME_CHANGE))
    }

    JC_YaTaxi_PRv1Theme(darkTheme = themechange) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)) {
            //Spacer(modifier = Modifier.height(35.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
            ) {
                Card(
                    modifier = Modifier.size(38.dp),
                    shape = RoundedCornerShape(8.dp),
                    onClick = {
                        navHostController.popBackStack()
                    },
                    colors = CardDefaults.cardColors(CardBackgroundTransparent),
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.arrowleft),
                            contentDescription = "logo2",
                            modifier = Modifier
                                .size(13.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.width(15.dp))
                Text(

                    text = stringResource(id = R.string.changelanguage),
                    textAlign = TextAlign.Start,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.mont_semibold)),
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.tertiary
                )
            }


            Spacer(modifier = Modifier.height(15.dp))
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().clickable {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

                            SharefPrefManager.setLocale(LANGUAGE_EN)
                            SharefPrefManager.saveString(LANGUAGE_TYPE, LANGUAGE_EN)

                        }
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
                            val intent = (Intent(Settings.ACTION_LOCALE_SETTINGS))
                            context.startActivity(intent)

                        }
                    }
                ) {
                    Text(

                        text = "English",
                        textAlign = TextAlign.Start,
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.mont_semibold)),
                        color = MaterialTheme.colorScheme.tertiary

                        )
                    Spacer(modifier = Modifier.weight(1f))
                    if (langtype== LANGUAGE_EN){
                        Icon(
                            painter = painterResource(id = R.drawable.tick),
                            contentDescription = "done",
                            modifier = Modifier.size(25.dp),
                            tint = Color.Green
                        )
                    }

                }

                Spacer(modifier = Modifier.height(10.dp))
                Divider(color = MaterialTheme.colorScheme.onTertiary)
                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().clickable {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

                            SharefPrefManager.setLocale(LANGUAGE_RU)
                            SharefPrefManager.saveString(LANGUAGE_TYPE, LANGUAGE_RU)


                        }
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
                            val intent = (Intent(Settings.ACTION_LOCALE_SETTINGS))
                            context.startActivity(intent)

                        }
                    }
                ) {
                    Text(

                        text = "Русский",
                        textAlign = TextAlign.Start,
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.mont_semibold)),
                        color = MaterialTheme.colorScheme.tertiary
                        )
                    Spacer(modifier = Modifier.weight(1f))
                    if (langtype== LANGUAGE_RU){
                        Icon(
                            painter = painterResource(id = R.drawable.tick),
                            contentDescription = "done",
                            modifier = Modifier.size(25.dp),
                            tint = Color.Green
                        )
                    }

                }
                Spacer(modifier = Modifier.height(10.dp))
                Divider(color = MaterialTheme.colorScheme.onTertiary)
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().clickable {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

                            SharefPrefManager.setLocale(LANGUAGE_UZ)
                            SharefPrefManager.saveString(LANGUAGE_TYPE, LANGUAGE_UZ)


                        }
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
                            val intent = (Intent(Settings.ACTION_LOCALE_SETTINGS))
                            context.startActivity(intent)

                        }
                    }
                ) {
                    Text(

                        text = "O'zbekcha",
                        textAlign = TextAlign.Start,
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.mont_semibold)),
                        color = MaterialTheme.colorScheme.tertiary
                        )
                    Spacer(modifier = Modifier.weight(1f))
                    if (langtype== LANGUAGE_UZ){
                        Icon(
                            painter = painterResource(id = R.drawable.tick),
                            contentDescription = "done",
                            modifier = Modifier.size(25.dp),
                            tint = Color.Green
                        )
                    }

                }

                Spacer(modifier = Modifier.height(10.dp))
            }



        }
    }

}