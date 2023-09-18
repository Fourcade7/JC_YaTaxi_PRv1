package com.pr7.jc_yataxi_prv1.ui.home.bottomscreens

import android.app.TimePickerDialog
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import androidx.navigation.NavHostController
import com.pr7.jc_yataxi_prv1.R
import com.pr7.jc_yataxi_prv1.ui.splash.ui.theme.ButtonbackgroundLanguage
import com.pr7.jc_yataxi_prv1.ui.splash.ui.theme.CardStrokeColors
import com.pr7.jc_yataxi_prv1.ui.splash.ui.theme.LayoutbackgroundColors

@ExperimentalMaterial3Api
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun driverDiscoverScreen() {
//    val disfrom : State<String?> = driverHomeVIewModel.districtfrom.observeAsState()
//    val disto : State<String?> = driverHomeVIewModel.districtto.observeAsState()




    val context = LocalContext.current
    val calendar = java.util.Calendar.getInstance()
    var passangercount by remember {
        mutableIntStateOf(1)
    }

    var selectedTimeText by remember {
        mutableStateOf("00:00")
    }


    // Fetching current hour, and minute
    var hour = calendar[java.util.Calendar.HOUR_OF_DAY]
    var minute = calendar[java.util.Calendar.MINUTE]

    val timePicker = TimePickerDialog(
        context,
        { _, selectedHour: Int, selectedMinute: Int ->
            selectedTimeText = "$selectedHour:$selectedMinute"
        }, hour, minute, false
    )

    Column(
        modifier = Modifier
            .background(LayoutbackgroundColors)
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(38.dp))

        Column( modifier = Modifier
            .padding(15.dp)) {
            Text(

                text = stringResource(id = R.string.newdirection),
                textAlign = TextAlign.Start,
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.mont_semibold)),
                //modifier = Modifier.align(Alignment.Center)
            )
            Spacer(modifier = Modifier.height(12.dp))

            Text(

                text = stringResource(id = R.string.where),
                textAlign = TextAlign.Start,
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.mont_semibold)),
                //modifier = Modifier.align(Alignment.Center)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Card(
                colors = CardDefaults.cardColors(Color.White),
                border = BorderStroke(width = 1.dp,color = CardStrokeColors),
                onClick = {
//                    driverHomeVIewModel.succesregdr.value=true
//                    driverHomeVIewModel.succesdisdr.value=false
//                    driverHomeVIewModel.districtchoose.value="from"
//                    navHostController.navigate(DriverBottomScreens.DriverRegions.route)

                }

            ) {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .fillMaxWidth()

                ) {
                    Text(
                        text = stringResource(id = R.string.selectanaddress),
                        modifier = Modifier.align(Alignment.CenterStart)
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.arrowdown),
                        contentDescription = "Done",
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .width(27.dp)
                            .height(45.dp)
                    )






                }
            }
            Spacer(modifier = Modifier.height(12.dp))


            Text(

                text = stringResource(id = R.string.whereto),
                textAlign = TextAlign.Start,
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.mont_semibold)),
                //modifier = Modifier.align(Alignment.Center)
            )
            Spacer(modifier = Modifier.height(12.dp))

            Card(
                colors = CardDefaults.cardColors(Color.White),
                border = BorderStroke(width = 1.dp,color = CardStrokeColors),
                onClick = {
//                    driverHomeVIewModel.succesregdr.value=true
//                    driverHomeVIewModel.succesdisdr.value=false
//                    driverHomeVIewModel.districtchoose.value="to"
//                    navHostController.navigate(DriverBottomScreens.DriverRegions.route)
                }
            ) {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .fillMaxWidth()

                ) {
                    Text(
                        text = stringResource(id = R.string.selectanaddress),
                        modifier = Modifier.align(Alignment.CenterStart)
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.arrowdown),
                        contentDescription = "Done",
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .width(27.dp)
                            .height(45.dp)
                    )






                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            Text(

                text = stringResource(id = R.string.choosetime),
                textAlign = TextAlign.Start,
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.mont_semibold)),
                //modifier = Modifier.align(Alignment.Center)
            )
            Spacer(modifier = Modifier.height(12.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                Card(
                    colors = CardDefaults.cardColors(Color.White),
                    modifier = Modifier.weight(1f),
                    border = BorderStroke(width = 1.dp,color = CardStrokeColors),
                    onClick = {
                        timePicker.show()
                    }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Card(
                            elevation = CardDefaults.cardElevation(1.dp),
                            shape = CircleShape,
                            modifier = Modifier.align(Alignment.CenterVertically),
                            colors = CardDefaults.cardColors(LayoutbackgroundColors),

                            ) {
                            Icon(
                                painter = painterResource(id = R.drawable.clockcircle),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(40.dp)
                                    .padding(5.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(10.dp))


                        Column {
                            Text(
                                text = stringResource(id = R.string.time),
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    lineHeight = 23.sp,
                                    fontFamily = FontFamily(Font(R.font.mont_light)),
                                    fontWeight = FontWeight(600),
                                    color = Color(0xFF17334C),
                                )
                            )
                            Spacer(modifier = Modifier.height(7.dp))

                            Text(
                                text = "08:00",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    lineHeight = 23.sp,
                                    fontFamily = FontFamily(Font(R.font.mont_bold)),
                                    fontWeight = FontWeight(700),
                                    color = Color(0xFF17334C),

                                    )
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.width(15.dp))
                Card(
                    colors = CardDefaults.cardColors(Color.White),
                    modifier = Modifier
                        .weight(1f)
                        .clickable {
                            timePicker.show()

                        },
                    border = BorderStroke(width = 1.dp,color = CardStrokeColors),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Card(
                            elevation = CardDefaults.cardElevation(1.dp),
                            shape = CircleShape,
                            modifier = Modifier.align(Alignment.CenterVertically),
                            colors = CardDefaults.cardColors(LayoutbackgroundColors)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.clockcircle),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(40.dp)
                                    .padding(5.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(10.dp))


                        Column {
                            Text(
                                text = stringResource(id = R.string.time),
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    lineHeight = 23.sp,
                                    fontFamily = FontFamily(Font(R.font.mont_light)),
                                    fontWeight = FontWeight(600),
                                    color = Color(0xFF17334C),
                                )
                            )
                            Spacer(modifier = Modifier.height(7.dp))

                            Text(
                                text = "12:00",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    lineHeight = 23.sp,
                                    fontFamily = FontFamily(Font(R.font.mont_bold)),
                                    fontWeight = FontWeight(700),
                                    color = Color(0xFF17334C),
                                )
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))







            Text(

                text = stringResource(id = R.string.numberofpassengers),
                textAlign = TextAlign.Start,
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.mont_semibold)),
                //modifier = Modifier.align(Alignment.Center)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Card(
                colors = CardDefaults.cardColors(Color.White),
                modifier = Modifier.fillMaxWidth(),
                border = BorderStroke(width = 1.dp,color = CardStrokeColors),
                onClick = {}
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        elevation = CardDefaults.cardElevation(1.dp),
                        shape = CircleShape,
                        modifier = Modifier.align(Alignment.CenterVertically),
                        colors = CardDefaults.cardColors(LayoutbackgroundColors)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.passangeway),
                            contentDescription = "",
                            modifier = Modifier
                                .size(40.dp)
                                .padding(5.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))


                    Column {
                        Text(
                            text = stringResource(id = R.string.passangers),
                            style = TextStyle(
                                fontSize = 12.sp,
                                lineHeight = 23.sp,
                                fontFamily = FontFamily(Font(R.font.mont_light)),
                                fontWeight = FontWeight(600),
                                color = Color(0xFF17334C),
                            )
                        )
                        Spacer(modifier = Modifier.height(7.dp))

                        Text(
                            text = passangercount.toString(),
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 23.sp,
                                fontFamily = FontFamily(Font(R.font.mont_bold)),
                                fontWeight = FontWeight(700),
                                color = Color(0xFF17334C),
                            )
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Row {
                        Card(
                            elevation = CardDefaults.cardElevation(1.dp),
                            shape = CircleShape,
                            modifier = Modifier.align(Alignment.CenterVertically),
                            colors = CardDefaults.cardColors(LayoutbackgroundColors),
                            onClick = {
                                if (passangercount>1){
                                    passangercount--
                                }

                            }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.minus),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(40.dp)
                                    .padding(5.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Card(
                            elevation = CardDefaults.cardElevation(1.dp),
                            shape = CircleShape,
                            modifier = Modifier.align(Alignment.CenterVertically),
                            colors = CardDefaults.cardColors(ButtonbackgroundLanguage),
                            onClick = {
                                passangercount++

                            }
                        ) {

                            Icon(
                                painter = painterResource(id = R.drawable.plus),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(40.dp)
                                    .padding(5.dp)
                            )
                        }
                    }


                }
            }
            Spacer(modifier = Modifier.height(12.dp))

            Text(

                text = stringResource(id = R.string.seatassigment),
                textAlign = TextAlign.Start,
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.mont_semibold)),
                //modifier = Modifier.align(Alignment.Center)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Card(
                colors = CardDefaults.cardColors(Color.White),
                modifier = Modifier

                    .clickable {
                        //navHostController.navigate(DriverBottomScreens.DriverSeatChoose.route)

                    },
                border = BorderStroke(width = 1.dp,color = CardStrokeColors),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        elevation = CardDefaults.cardElevation(1.dp),
                        shape = CircleShape,
                        modifier = Modifier.align(Alignment.CenterVertically),
                        colors = CardDefaults.cardColors(LayoutbackgroundColors)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.seat),
                            contentDescription = "",
                            modifier = Modifier
                                .size(40.dp)
                                .padding(5.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))


                    Column {
                        Text(
                            text = stringResource(id = R.string.seatposition),
                            style = TextStyle(
                                fontSize = 12.sp,
                                lineHeight = 23.sp,
                                fontFamily = FontFamily(Font(R.font.mont_light)),
                                fontWeight = FontWeight(600),
                                color = Color(0xFF17334C),
                            )
                        )
                        Spacer(modifier = Modifier.height(7.dp))

                        Text(
                            text = stringResource(id = R.string.all),
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 23.sp,
                                fontFamily = FontFamily(Font(R.font.mont_bold)),
                                fontWeight = FontWeight(700),
                                color = Color(0xFF17334C),
                            )
                        )
                    }
                }


            }

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(54.dp)
                    .clickable {
                        //navController.navigate(Screens.Orders.route)
                    },
                shape = RoundedCornerShape(15.dp),
                color = ButtonbackgroundLanguage
            ) {
                Column(verticalArrangement = Arrangement.Center) {

                    Text(
                        text = stringResource(id = R.string.adddirection),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(Font(R.font.mont_semibold)),
                        fontSize = 17.sp,

                        )
                }

            }

        }

    }
}