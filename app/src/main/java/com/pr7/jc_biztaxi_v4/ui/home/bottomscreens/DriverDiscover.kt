package com.pr7.jc_biztaxi_v4.ui.home.bottomscreens

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.gson.GsonBuilder
import com.pr7.jc_biztaxi_v4.R
import com.pr7.jc_biztaxi_v4.data.model.orderseat.OrderSeat
import com.pr7.jc_biztaxi_v4.data.model.userinfo.UserInfoResponse
import com.pr7.jc_biztaxi_v4.data.pref.NOTIFICATION_MESSAGE
import com.pr7.jc_biztaxi_v4.data.pref.NOTIFICATION_ORDER
import com.pr7.jc_biztaxi_v4.data.pref.NOTIFICATION_SEAT_ID
import com.pr7.jc_biztaxi_v4.data.pref.PHONE
import com.pr7.jc_biztaxi_v4.data.pref.REFRESH_TOKEN
import com.pr7.jc_biztaxi_v4.data.pref.SharefPrefManager
import com.pr7.jc_biztaxi_v4.data.pref.THEME_CHANGE
import com.pr7.jc_biztaxi_v4.data.pref.TOKEN
import com.pr7.jc_biztaxi_v4.data.pref.USERNAME
import com.pr7.jc_biztaxi_v4.ui.home.HomeViewModel
import com.pr7.jc_biztaxi_v4.ui.home.ui.theme.JC_YaTaxi_PRv1Theme
import com.pr7.jc_biztaxi_v4.ui.splash.ui.theme.ButtonbackgroundLanguage
import com.pr7.jc_biztaxi_v4.ui.splash.ui.theme.CardStrokeColors
import com.pr7.jc_biztaxi_v4.ui.splash.ui.theme.LayoutbackgroundColors
import com.pr7.jc_biztaxi_v4.utils.showlogd
import java.lang.Exception
import java.util.Calendar

@ExperimentalMaterial3Api
//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun driverDiscoverScreen(navController:NavHostController,homeViewModel: HomeViewModel) {

    val mlivedatauserinfo by homeViewModel.mlivedataUserInfo.observeAsState()
    val mlivedatarefreshtoken by homeViewModel.mlivedataRefreshtoken.observeAsState()
    val iscomplatedotp by homeViewModel.iscompleteduserinfo.observeAsState()

    //need here change refresh token
   // homeViewModel.getuserinfo(token = SharefPrefManager.loadString(TOKEN).toString())

    val mlivedatadirectionNew by homeViewModel.mlivedatadirectionNew.observeAsState()
    val iscomplated by homeViewModel.iscompleteddirectionNew.observeAsState()

    var openDialog by remember { mutableStateOf(false) }

    var requestcountuserinfo by remember {
        mutableIntStateOf(0)
    }
    if (requestcountuserinfo==0){
        homeViewModel.getuserinfo(token = SharefPrefManager.loadString(TOKEN).toString())

    }

    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    var passangercount by remember {
        mutableIntStateOf(0)
    }


    var requestcount by remember {
        mutableIntStateOf(0)
    }

    var selectedTimeText by remember {
        mutableStateOf("00:00")
    }
    var selectedTimeText2 by remember {
        mutableStateOf("00:00")
    }

    var clockchange by remember {
        mutableStateOf(false)
    }
    var username by remember {
        mutableStateOf("Загрузка...")
    }
    var price by remember {
        mutableStateOf("")
    }

//    if (SharefPrefManager.loadBoolean(NOTIFICATION_ORDER)){
//        myAlertDialog(message = SharefPrefManager.loadString(NOTIFICATION_MESSAGE).toString())
//    }
    showlogd(funname = "ORDER: ","${SharefPrefManager.loadBoolean(NOTIFICATION_ORDER)}")
    if ( SharefPrefManager.loadBoolean(NOTIFICATION_ORDER)){
        AlertDialog(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .fillMaxWidth(), // corner rounded//not working
            onDismissRequest = {

            },
            title = { Text(text = stringResource(id = R.string.seatreservation)) },
            text = { Text(text = SharefPrefManager.loadString(NOTIFICATION_MESSAGE).toString()) },
            confirmButton = {
                Button(onClick = {
                    SharefPrefManager.saveBoolean(NOTIFICATION_ORDER,false)

                    homeViewModel.orderseatConfirm(
                        token = SharefPrefManager.loadString(TOKEN).toString(),
                        id = SharefPrefManager.loadString(NOTIFICATION_SEAT_ID).toString().toInt(),
                        orderSeat = OrderSeat(status = "confirmed")
                    )
                    navController.navigate(Screens.Discover.route)
                }) {
                    Text(text = stringResource(id = R.string.yes))
                }
            },
            dismissButton = {
                Button(onClick = {
                    SharefPrefManager.saveBoolean(NOTIFICATION_ORDER,false)

                    homeViewModel.orderseatConfirm(
                        token = SharefPrefManager.loadString(TOKEN).toString(),
                        id = SharefPrefManager.loadString(NOTIFICATION_SEAT_ID).toString().toInt(),
                        orderSeat = OrderSeat(status = "cancelled")
                    )
                    navController.navigate(Screens.Discover.route)
                }) {
                    Text(text = stringResource(id = R.string.no))

                }
            }
        )
    }

    mlivedatauserinfo.let { result ->

        result?.onSuccess {
            showlogd(funname = "userinfo", it.user_type.toString())
            showlogd(funname = "userinfo firstname", it.first_name.toString())
            username = "${it.first_name.toString()} ${it.last_name.toString()}"
            SharefPrefManager.saveString(USERNAME,"${it.first_name} ${it.last_name}")
            SharefPrefManager.saveString(PHONE,"${it.phone}")
            requestcountuserinfo=1

        }
        result?.onFailure {
            showlogd(funname = "userinfo", it.message.toString())
            try {
                val gson = GsonBuilder().create()
                val gsonparse: UserInfoResponse =
                    gson.fromJson(it.message, UserInfoResponse::class.java)
                if (gsonparse.code != null) {
                    showlogd(funname = "Ошибка", text = gsonparse.code.toString())
                    //messageresponse=gsonparse.message.toString()
                    username = "Ошибка"

                    if (gsonparse.code == "token_not_valid") {
                        //REFRESH TOKEN REQUEST
                        //AND
                        homeViewModel.refreshtoken(
                            refreshtoken = SharefPrefManager.loadString(
                                REFRESH_TOKEN
                            ).toString()
                        )
                    }


                }


            } catch (e: Exception) {
                result.onFailure {
                    username = it.message.toString()
                }

            }

        }

    }

    mlivedatarefreshtoken.let { result ->

        result?.onSuccess {
            homeViewModel.getuserinfo(token = SharefPrefManager.loadString(it.access.toString()).toString())
            SharefPrefManager.saveString(TOKEN,it.access.toString())
            SharefPrefManager.saveString(REFRESH_TOKEN,it.refresh.toString())
        }
        result?.onFailure {

        }


    }



    mlivedatadirectionNew.let {result ->

        result?.onSuccess {
            if (requestcount==1){
                navController.navigate(Screens.Orders.route)
                requestcount=0

            }
            showlogd(funname = "new direction","ready")
        }
        result?.onFailure {

        }

    }


    // Fetching current hour, and minute

    var hour = calendar[Calendar.HOUR_OF_DAY]
    var minute = calendar[Calendar.MINUTE]
    var second = calendar[Calendar.SECOND]

    val timePicker = TimePickerDialog(
        context,
        { _, selectedHour: Int, selectedMinute: Int ->
            if (clockchange){
                selectedTimeText = "$selectedHour:$selectedMinute"

            }else{
                selectedTimeText2 = "$selectedHour:$selectedMinute"

            }
        }, hour, minute, false
    )
    var themechange by remember {
        mutableStateOf(SharefPrefManager.loadBoolean(THEME_CHANGE))
    }

    JC_YaTaxi_PRv1Theme(darkTheme = themechange) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
                .padding(15.dp)
        ) {
            //Spacer(modifier = Modifier.height(38.dp))


            Text(

                text = stringResource(id = R.string.newdirection),
                textAlign = TextAlign.Start,
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.mont_semibold)),
                color = MaterialTheme.colorScheme.tertiary
                //modifier = Modifier.align(Alignment.Center)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Column( modifier = Modifier

                .weight(1f)
                .fillMaxWidth()
                .height(700.dp)
                .verticalScroll(rememberScrollState())
            ) {
                Text(

                    text = stringResource(id = R.string.where),
                    textAlign = TextAlign.Start,
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.mont_semibold)),
                    //modifier = Modifier.align(Alignment.Center)
                    color = MaterialTheme.colorScheme.tertiary

                )

                Spacer(modifier = Modifier.height(12.dp))

                Card(
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onBackground),
                    border = BorderStroke(width = 1.dp,color = CardStrokeColors),
                    onClick = {
//                    driverHomeVIewModel.succesregdr.value=true
//                    driverHomeVIewModel.succesdisdr.value=false
//                    driverHomeVIewModel.districtchoose.value="from"
//                    navHostController.navigate(DriverBottomScreens.DriverRegions.route)
                        navController.navigate(Screens.Regions.route)
                        homeViewModel.districtchoose.value="from"

                    }

                ) {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 15.dp)
                            .fillMaxWidth()

                    ) {
                        Text(
                            text = homeViewModel.districtfrom.value!!,
                            modifier = Modifier.align(Alignment.CenterStart),
                            color = MaterialTheme.colorScheme.tertiary

                        )

                        Icon(
                            painter = painterResource(id = R.drawable.arrowdown),
                            contentDescription = "Done",
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .width(27.dp)
                                .height(45.dp),
                            tint =  MaterialTheme.colorScheme.tertiary
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
                    color = MaterialTheme.colorScheme.tertiary

                )
                Spacer(modifier = Modifier.height(12.dp))

                Card(
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onBackground),
                    border = BorderStroke(width = 1.dp,color = CardStrokeColors),
                    onClick = {
//                    driverHomeVIewModel.succesregdr.value=true
//                    driverHomeVIewModel.succesdisdr.value=false
//                    driverHomeVIewModel.districtchoose.value="to"
//                    navHostController.navigate(DriverBottomScreens.DriverRegions.route)
                        navController.navigate(Screens.Regions.route)
                        homeViewModel.districtchoose.value = "to"
                    }
                ) {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 15.dp)
                            .fillMaxWidth()

                    ) {
                        Text(
                            text = homeViewModel.districtto.value!!,
                            modifier = Modifier.align(Alignment.CenterStart),
                            color = MaterialTheme.colorScheme.tertiary

                        )

                        Icon(
                            painter = painterResource(id = R.drawable.arrowdown),
                            contentDescription = "Done",
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .width(27.dp)
                                .height(45.dp),
                            tint =  MaterialTheme.colorScheme.tertiary
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
                    color = MaterialTheme.colorScheme.tertiary

                )
                Spacer(modifier = Modifier.height(12.dp))

                Row(modifier = Modifier.fillMaxWidth()) {
                    Card(
                        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onBackground),
                        modifier = Modifier.weight(1f),
                        border = BorderStroke(width = 1.dp,color = CardStrokeColors),
                        onClick = {
                            clockchange=true
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
                                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),

                                ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.clockcircle),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(40.dp)
                                        .padding(5.dp),
                                    tint =  MaterialTheme.colorScheme.tertiary
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
                                        color = MaterialTheme.colorScheme.tertiary

                                    )
                                )
                                Spacer(modifier = Modifier.height(7.dp))

                                Text(
                                    text = selectedTimeText,
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        lineHeight = 23.sp,
                                        fontFamily = FontFamily(Font(R.font.mont_bold)),
                                        fontWeight = FontWeight(700),
                                        color = MaterialTheme.colorScheme.tertiary


                                    )
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.width(15.dp))
                    Card(
                        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onBackground),
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                clockchange = false
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
                                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.clockcircle),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(40.dp)
                                        .padding(5.dp),
                                    tint =  MaterialTheme.colorScheme.tertiary
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
                                        color = MaterialTheme.colorScheme.tertiary

                                    )
                                )
                                Spacer(modifier = Modifier.height(7.dp))

                                Text(
                                    text = selectedTimeText2,
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        lineHeight = 23.sp,
                                        fontFamily = FontFamily(Font(R.font.mont_bold)),
                                        fontWeight = FontWeight(700),
                                        color = MaterialTheme.colorScheme.tertiary

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
                    color = MaterialTheme.colorScheme.tertiary

                )
                Spacer(modifier = Modifier.height(12.dp))
                Card(
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onBackground),
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
                            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.passangeway),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(40.dp)
                                    .padding(5.dp),
                                tint =  MaterialTheme.colorScheme.tertiary
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
                                    color = MaterialTheme.colorScheme.tertiary

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
                                    color = MaterialTheme.colorScheme.tertiary

                                )
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Row {
                            Card(
                                elevation = CardDefaults.cardElevation(1.dp),
                                shape = CircleShape,
                                modifier = Modifier.align(Alignment.CenterVertically),
                                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
                                onClick = {
                                    if (passangercount>0){
                                        passangercount--
                                    }

                                }
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.minus),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(40.dp)
                                        .padding(5.dp),
                                    tint =  MaterialTheme.colorScheme.tertiary
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
                                        .padding(5.dp),
                                    tint =  Color.Black
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
                    color = MaterialTheme.colorScheme.tertiary

                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Card(
                        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onBackground),
                        modifier = Modifier

                            .clickable {
                                navController.navigate(Screens.SeatChoose.route)

                            }
                            .weight(1f),
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
                                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.seat),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(40.dp)
                                        .padding(5.dp),
                                    tint =  MaterialTheme.colorScheme.tertiary
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
                                        color = MaterialTheme.colorScheme.tertiary

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
                                        color = MaterialTheme.colorScheme.tertiary

                                    )
                                )
                            }
                        }


                    }

                    Spacer(modifier = Modifier.width(5.dp))
                    Card(
                        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onBackground),
                        modifier = Modifier

                            .clickable {
                                //navHostController.navigate(DriverBottomScreens.DriverSeatChoose.route)

                            }
                            .weight(1f),
                        border = BorderStroke(width = 1.dp,color = CardStrokeColors),
                    ){
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = price,
                            onValueChange = {

                                if (it.length < 10) {
                                    price = it
                                }

                            },
                            placeholder = {
                                Text(text = "UZS")
                            },


                            maxLines = 1,

                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),

                            textStyle = LocalTextStyle.current.copy(
                                textAlign = TextAlign.Start,
                                fontSize = 15.sp
                            ),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent,
                                focusedTextColor = MaterialTheme.colorScheme.tertiary
                                )

                        )
                    }
                }

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(54.dp)
                        .clickable {
                            //navController.navigate(Screens.Orders.route)

                            if (price.isNotEmpty() && homeViewModel.regfromid.value!! > 0 && homeViewModel.disfromid.value!! > 0 && homeViewModel.regtoid.value!! > 0 && homeViewModel.distoid.value!! > 0) {
                                val calendarr = Calendar.getInstance()
                                var year = calendarr[Calendar.YEAR]
                                var month = calendarr[Calendar.MONTH] + 1
                                var day = calendarr[Calendar.DAY_OF_MONTH]

                                var secondr = calendarr[Calendar.SECOND]
                                var starttime = "$year-$month-${day}T$selectedTimeText:${secondr}Z"
                                var endtime = "$year-$month-${day}T$selectedTimeText2:${secondr}Z"
                                homeViewModel.selectedtime1.value = starttime
                                homeViewModel.selectedtime2.value = endtime
                                homeViewModel.price.value = price.toInt()
                                showlogd(funname = "time", text = starttime)
                                showlogd(
                                    funname = "reg dis",
                                    text = "${homeViewModel.regfromid.value} ${homeViewModel.disfromid.value} to ${homeViewModel.regtoid.value} ${homeViewModel.distoid.value}"
                                )
                                showlogd(funname = "price", text = price)
                                showlogd(funname = "endtime", text = endtime)


                                //go request

                                homeViewModel.directionNew(
                                    token = SharefPrefManager
                                        .loadString(TOKEN)
                                        .toString(),
                                    startdate = homeViewModel.selectedtime1.value!!,
                                    enddate = homeViewModel.selectedtime2.value!!,
                                    price = homeViewModel.price.value!!,
                                    fromdisid = homeViewModel.disfromid.value!!,
                                    todisid = homeViewModel.distoid.value!!,
                                    fromregid = homeViewModel.regfromid.value!!,
                                    toregid = homeViewModel.regtoid.value!!
                                )
                                requestcount = 1


                            }


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
                            color = Color.Black


                        )
                    }

                }
                Spacer(modifier = Modifier.height(75.dp))

            }

        }
    }


}