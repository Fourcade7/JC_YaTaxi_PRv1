package com.pr7.jc_yataxi_prv1.ui.home.bottomscreens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pr7.jc_yataxi_prv1.R
import com.pr7.jc_yataxi_prv1.data.model.newdirection.response.DirectionNewResponse
import com.pr7.jc_yataxi_prv1.data.pref.SharefPrefManager
import com.pr7.jc_yataxi_prv1.data.pref.TOKEN
import com.pr7.jc_yataxi_prv1.ui.home.HomeViewModel
import com.pr7.jc_yataxi_prv1.ui.splash.ui.theme.ButtonbackgroundLanguage
import com.pr7.jc_yataxi_prv1.ui.splash.ui.theme.CardBackgroundTransparent
import com.pr7.jc_yataxi_prv1.ui.splash.ui.theme.LayoutbackgroundColors
import com.pr7.jc_yataxi_prv1.utils.showlogd


@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun driverDirectionsScreen(homeViewModel: HomeViewModel) {



    Column(modifier = Modifier
        .fillMaxSize()
        .background(LayoutbackgroundColors)) {
        Spacer(modifier = Modifier.height(35.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
        ) {
            Card(
                modifier = Modifier.size(38.dp),
                shape = RoundedCornerShape(8.dp),
                onClick = {
                    // navHostController.navigate(Screens.Discover.route)
                }
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

                text = stringResource(id = R.string.mydirections),
                textAlign = TextAlign.Start,
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.mont_semibold)),
                modifier = Modifier.align(Alignment.Center)
            )
        }


        Spacer(modifier = Modifier.height(5.dp))



    }
}


//@Preview(showSystemUi = true, showBackground = true)
@ExperimentalMaterial3Api
@Composable
fun lazyitemtaxis(directionNewResponse: DirectionNewResponse) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(LayoutbackgroundColors)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            colors = CardDefaults.cardColors(Color.White)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Image(
                        painter = painterResource(id = R.drawable.right2x),
                        contentDescription = "driver",
                        modifier = Modifier
                            .size(60.dp)
                            .rotate(90f),
                    )
                    Spacer(modifier = Modifier.width(10.dp))

                    Column(
                        modifier = Modifier.align(Alignment.CenterVertically)
                    ) {
                        Text(
                            text = "Beruniy tumani",
                            style = TextStyle(
                                fontSize = 13.sp,
                                lineHeight = 23.sp,
                                fontFamily = FontFamily(Font(R.font.mont_bold)),
                                fontWeight = FontWeight(600),
                                color = Color(0xFF17334C),

                                ),
                            maxLines = 1

                        )
                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "Chilonzor tumani",
                            style = TextStyle(
                                fontSize = 13.sp,
                                lineHeight = 23.sp,
                                fontFamily = FontFamily(Font(R.font.mont_bold)),
                                fontWeight = FontWeight(600),
                                color = Color(0xFF17334C),
                            )
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Card(

                        colors = CardDefaults.cardColors(CardBackgroundTransparent),
                        shape = RoundedCornerShape(19.dp),
                        onClick = {

                        }
                    ) {
                        Row(
                            modifier = Modifier.padding(
                                start = 9.dp,
                                end = 9.dp,
                                top = 5.dp,
                                bottom = 5.dp
                            ),
                            verticalAlignment = Alignment.CenterVertically,

                            ) {
                            Image(
                                painterResource(id =R.drawable.baseline_more_vert_24 ),
                                contentDescription = "edit",
                                modifier = Modifier
                                    .size(25.dp)
                                    .rotate(90f)
                            )


                        }
                    }

                }
                Spacer(modifier = Modifier.height(10.dp))
                Divider()
                Spacer(modifier = Modifier.height(14.dp))
                Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
                    Text(
                        text = stringResource(id = R.string.timealeave),
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.mont_light)),
                            fontWeight = FontWeight(600),
                            color = Color.Black,
                            textAlign = TextAlign.Center,

                            ),

                        )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "end time",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.mont_bold)),
                            fontWeight = FontWeight(600),
                            color = Color.Black,
                            textAlign = TextAlign.Center,

                            ),

                        )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
                    Text(
                        text = stringResource(id = R.string.numberofpassengers),
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.mont_light)),
                            fontWeight = FontWeight(600),
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                        )
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "2",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.mont_bold)),
                            fontWeight = FontWeight(600),
                            color = Color.Black,
                            textAlign = TextAlign.Center,

                            ),

                        )
                }

                Spacer(modifier = Modifier.height(10.dp))


            }
        }
    }
}