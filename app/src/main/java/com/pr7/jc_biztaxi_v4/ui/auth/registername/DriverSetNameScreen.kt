package com.pr7.jc_biztaxi_v4.ui.auth.registername

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.navigation.NavHostController
import com.pr7.jc_biztaxi_v4.R
import com.pr7.jc_biztaxi_v4.data.pref.DRIVER
import com.pr7.jc_biztaxi_v4.data.pref.SharefPrefManager
import com.pr7.jc_biztaxi_v4.data.pref.TOKEN
import com.pr7.jc_biztaxi_v4.data.pref.USERNAMED
import com.pr7.jc_biztaxi_v4.ui.home.HomeActivity
import com.pr7.jc_biztaxi_v4.ui.splash.ui.theme.ButtonbackgroundLanguage
import com.pr7.jc_biztaxi_v4.ui.splash.ui.theme.FocusedBorderColor
import androidx.compose.ui.graphics.Color
import com.pr7.jc_biztaxi_v4.ui.splash.ui.theme.Bworn
import com.pr7.jc_biztaxi_v4.ui.splash.ui.theme.LIghtBLue
import com.pr7.jc_biztaxi_v4.ui.splash.ui.theme.Orange


@ExperimentalMaterial3Api
//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun registerdrivernameScreen(navHostController:NavHostController,registerNameViewModel: RegisterNameViewModel) {

    val carnamelivedata by registerNameViewModel.carname.observeAsState()
    val caridlivedata by registerNameViewModel.carid.observeAsState()
    val carcolorlivedata by registerNameViewModel.carcolor.observeAsState()
    val carcolorlanglivedata by registerNameViewModel.carcolorlang.observeAsState()

    val context = LocalContext.current as Activity

    val mlivedatauserinfo by registerNameViewModel.mlivedataUserInfoch.observeAsState()
    val mlivedatacarreg by registerNameViewModel.mlivedatacarreg.observeAsState()
    var complatedcarreg by remember {
        mutableStateOf(false)
    }
    var complateduserchange by remember {
        mutableStateOf(false)
    }

    if (complatedcarreg==true && complateduserchange==true){
        SharefPrefManager.saveBoolean(USERNAMED,true)
        context.startActivity(Intent(context, HomeActivity::class.java))
        context.finish()
    }

    mlivedatacarreg.let {result ->
        result?.onSuccess {
            complatedcarreg=true
        }
        result?.onFailure {

        }
    }
    mlivedatauserinfo.let {result->
        result?.onSuccess {
           complateduserchange=true
        }
        result?.onFailure {

        }
    }



    var name by remember {
        mutableStateOf("")
    }
    var name2 by remember {
        mutableStateOf("")
    }

    var name3 by remember {
        mutableStateOf("")
    }
    var name4 by remember {
        mutableStateOf("")
    }





    Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(35.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Card(
                    modifier = Modifier.size(38.dp),
                    shape = RoundedCornerShape(8.dp),
                    onClick = {

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

                    text = stringResource(id = R.string.register),
                    textAlign = TextAlign.Start,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.mont_semibold)),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Spacer(modifier = Modifier.height(25.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp),
                    text = stringResource(id = R.string.selectcarmodel),
                    textAlign = TextAlign.Start,
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.mont_semibold))
                )
                Spacer(modifier = Modifier.height(5.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = carnamelivedata.toString(),
                    onValueChange = {

                    },
                    placeholder = {
                        Text(text = stringResource(id = R.string.choose))
                    },
                    trailingIcon = {
                        IconButton(onClick = {

                            navHostController.navigate(CarListScreens.CarLIst.route)

                        }) {
                            Icon(painter = painterResource(
                                id = R.drawable.baseline_directions_car_24),
                                contentDescription ="car" )
                        }
                    },
                    maxLines = 1,
                    singleLine = true,

                    //keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = FocusedBorderColor, focusedLabelColor = FocusedBorderColor)
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp),
                    text = stringResource(id = R.string.carcolor),
                    textAlign = TextAlign.Start,
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.mont_semibold))
                )
                Spacer(modifier = Modifier.height(5.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = carcolorlanglivedata.toString(),
                    onValueChange = {
                        if (it.length < 15) {
                            name2 = it
                        }
                    },
                    trailingIcon = {
                        IconButton(onClick = {

                            navHostController.navigate(CarListScreens.CarColor.route)

                        }) {
                            Icon(painter = painterResource(
                                id = R.drawable.baseline_directions_car_24),
                                contentDescription ="car",
                                tint = when(carcolorlivedata){
                                    "red"->{Color.Red}
                                    "blue"->{Color.Blue}
                                    "lightblue"->{LIghtBLue}
                                    "orange"->{Orange}
                                    "white"->{Color.White}
                                    "black"->{Color.Black}
                                    "yellow"->{Color.Yellow}
                                    "brown"->{ Bworn}
                                    "silver"->{Color.LightGray}
                                    else -> {Color.Black}
                                }

                            )
                        }
                    },
                    placeholder = {
                        Text(text = stringResource(id = R.string.choose))
                    },
                    maxLines = 1,
                    singleLine = true,

                    //keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = FocusedBorderColor, focusedLabelColor = FocusedBorderColor)
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp),
                    text = stringResource(id = R.string.name),
                    textAlign = TextAlign.Start,
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.mont_semibold))
                )
                Spacer(modifier = Modifier.height(5.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = name,
                    onValueChange = {
                        if (it.length < 15) {
                            name = it
                        }
                    },
                    placeholder = {
                        Text(text = stringResource(id = R.string.enteryourname))
                    },
                    maxLines = 1,
                    singleLine = true,

                    //keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = FocusedBorderColor, focusedLabelColor = FocusedBorderColor)
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp),
                    text = stringResource(id = R.string.surname),
                    textAlign = TextAlign.Start,
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.mont_semibold))
                )
                Spacer(modifier = Modifier.height(5.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = name2,
                    onValueChange = {
                        if (it.length < 15) {
                            name2 = it
                        }
                    },
                    placeholder = {
                        Text(text = stringResource(id = R.string.enteryoursurname))
                    },
                    maxLines = 1,
                    singleLine = true,

                    //keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = FocusedBorderColor, focusedLabelColor = FocusedBorderColor)
                )
                Spacer(modifier = Modifier.height(5.dp))


                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp),
                    text = stringResource(id = R.string.number),
                    textAlign = TextAlign.Start,
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.mont_semibold))
                )
                Spacer(modifier = Modifier.height(5.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = name3,
                    onValueChange = {
                        if (it.length < 15) {
                            name3 = it
                        }
                    },
                    placeholder = {
                        Text(text = "01X507UB")
                    },
                    maxLines = 1,
                    singleLine = true,

                    //keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = FocusedBorderColor, focusedLabelColor = FocusedBorderColor)
                )
                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp),
                    text = stringResource(id = R.string.driverpassportnumber),
                    textAlign = TextAlign.Start,
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.mont_semibold))
                )
                Spacer(modifier = Modifier.height(5.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = name4,
                    onValueChange = {
                        if (it.length < 15) {
                            name4 = it
                        }
                    },
                    placeholder = {
                        Text(text = "KA1234567")
                    },
                    maxLines = 1,
                    singleLine = true,

                    //keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = FocusedBorderColor, focusedLabelColor = FocusedBorderColor)
                )
                Spacer(modifier = Modifier.height(5.dp))

            }

            Spacer(modifier = Modifier.weight(1f))
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(54.dp)
                    .clickable {
                        registerNameViewModel.careregister(
                            token = SharefPrefManager
                                .loadString(TOKEN)
                                .toString(),
                            carid = caridlivedata!!,
                            carcolor = carcolorlivedata!!,
                            carnumber = name3
                        )
                        registerNameViewModel.changeuserinfo(
                            token = SharefPrefManager
                                .loadString(TOKEN)
                                .toString(),
                            firstname = name,
                            lastname = name2,
                            usertype = DRIVER
                        )

                        //USERNAMED true need

                    },
                shape = RoundedCornerShape(15.dp),
                color = ButtonbackgroundLanguage
            ) {
                Column(verticalArrangement = Arrangement.Center) {

                    Text(
                        text = stringResource(id = R.string.register),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(Font(R.font.mont_semibold)),
                        fontSize = 17.sp,

                        )
                }

            }
            Spacer(modifier = Modifier.height(5.dp))



        }


}