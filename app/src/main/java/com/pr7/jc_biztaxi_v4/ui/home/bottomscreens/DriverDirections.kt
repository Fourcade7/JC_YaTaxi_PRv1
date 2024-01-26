package com.pr7.jc_biztaxi_v4.ui.home.bottomscreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.pr7.jc_biztaxi_v4.R
import com.pr7.jc_biztaxi_v4.data.model.directions_active.DirectionsActiveResponse
import com.pr7.jc_biztaxi_v4.data.pref.SharefPrefManager
import com.pr7.jc_biztaxi_v4.data.pref.THEME_CHANGE
import com.pr7.jc_biztaxi_v4.data.pref.TOKEN
import com.pr7.jc_biztaxi_v4.ui.home.HomeViewModel
import com.pr7.jc_biztaxi_v4.ui.home.ui.theme.JC_YaTaxi_PRv1Theme
import com.pr7.jc_biztaxi_v4.ui.splash.ui.theme.CardBackgroundTransparent
import com.pr7.jc_biztaxi_v4.ui.splash.ui.theme.LayoutbackgroundColors
import java.lang.Exception


@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun driverDirectionsScreen(homeViewModel: HomeViewModel,navHostController:NavHostController) {

    val mlivedataactiveDis by homeViewModel.mlivedataactiveDirection.observeAsState()
    var requestcount by remember {
        mutableStateOf(0)
    }
    if (requestcount == 0) {
        homeViewModel.getActiveDirections(SharefPrefManager.loadString(TOKEN).toString())
    }

//    if (SharefPrefManager.loadBoolean(NOTIFICATION_ORDER)){
//        myAlertDialog(message = SharefPrefManager.loadString(NOTIFICATION_MESSAGE).toString())
//    }

    var themechange by remember {
        mutableStateOf(SharefPrefManager.loadBoolean(THEME_CHANGE))
    }

    JC_YaTaxi_PRv1Theme(darkTheme = themechange) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
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

                text = stringResource(id = R.string.mydirections),
                textAlign = TextAlign.Start,
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.mont_semibold)),
                modifier = Modifier.align(Alignment.Center),
                color = MaterialTheme.colorScheme.tertiary
            )
        }


        Spacer(modifier = Modifier.height(5.dp))

        mlivedataactiveDis.let { result ->

            result?.onSuccess {
                LazyColumn(contentPadding = PaddingValues(bottom = 100.dp)) {
                    itemsIndexed(it) { index, item ->
                        lazyitemtaxis(directionsActiveResponse = item)
                    }
                }
                requestcount = 1
            }
            result?.onFailure {

            }

        }


    }

    }
}


//@Preview(showSystemUi = true, showBackground = true)
@ExperimentalMaterial3Api
@Composable
fun lazyitemtaxis(directionsActiveResponse: DirectionsActiveResponse) {
    val context = LocalContext.current
    var themechange by remember {
        mutableStateOf(SharefPrefManager.loadBoolean(THEME_CHANGE))
    }

    JC_YaTaxi_PRv1Theme(darkTheme = themechange) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onBackground)
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Card(
                            elevation = CardDefaults.cardElevation(1.dp),
                            shape = CircleShape,
                            modifier = Modifier.align(Alignment.CenterVertically),
                            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.arrow2xright),
                                contentDescription = "driver",
                                modifier = Modifier
                                    .size(60.dp)
                                    .rotate(90f),
                                tint = MaterialTheme.colorScheme.tertiary
                            )
                        }

                        Spacer(modifier = Modifier.width(10.dp))

                        Column(
                            modifier = Modifier.align(Alignment.CenterVertically)
                        ) {
                            Text(
                                text = directionsActiveResponse.from_district.name,
                                style = TextStyle(
                                    fontSize = 13.sp,
                                    lineHeight = 23.sp,
                                    fontFamily = FontFamily(Font(R.font.mont_bold)),
                                    fontWeight = FontWeight(600),
                                    color = MaterialTheme.colorScheme.tertiary,

                                    ),
                                maxLines = 1

                            )
                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = directionsActiveResponse.to_district.name,
                                style = TextStyle(
                                    fontSize = 13.sp,
                                    lineHeight = 23.sp,
                                    fontFamily = FontFamily(Font(R.font.mont_bold)),
                                    fontWeight = FontWeight(600),
                                    color = MaterialTheme.colorScheme.tertiary,
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
                                    painterResource(id = R.drawable.baseline_more_vert_24),
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
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.timealeave),
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.mont_light)),
                                fontWeight = FontWeight(600),
                                color = MaterialTheme.colorScheme.tertiary,
                                textAlign = TextAlign.Center,

                                ),

                            )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = directionsActiveResponse.end_date,
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.mont_bold)),
                                fontWeight = FontWeight(600),
                                color = MaterialTheme.colorScheme.tertiary,
                                textAlign = TextAlign.Center,

                                ),

                            )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.price),
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.mont_light)),
                                fontWeight = FontWeight(600),
                                color = MaterialTheme.colorScheme.tertiary,
                                textAlign = TextAlign.Center,
                            )
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "${directionsActiveResponse.price} UZS",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.mont_bold)),
                                fontWeight = FontWeight(600),
                                color = MaterialTheme.colorScheme.tertiary,
                                textAlign = TextAlign.Center,

                                ),

                            )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.numberofpassengers),
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.mont_light)),
                                fontWeight = FontWeight(600),
                                color = MaterialTheme.colorScheme.tertiary,
                                textAlign = TextAlign.Center,
                            )
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = directionsActiveResponse.passengers.toString(),
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.mont_bold)),
                                fontWeight = FontWeight(600),
                                color = MaterialTheme.colorScheme.tertiary,
                                textAlign = TextAlign.Center,

                                ),

                            )
                    }

                    Spacer(modifier = Modifier.height(10.dp))


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.numberofpassengers),
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.mont_light)),
                                fontWeight = FontWeight(600),
                                color = MaterialTheme.colorScheme.tertiary,
                                textAlign = TextAlign.Center,
                            )
                        )
                        Spacer(modifier = Modifier.weight(1f))

                        Image(
                            painter = painterResource(
                                try {
                                    if (directionsActiveResponse.orders!![0].gender.toString()=="male"){
                                        R.drawable.man

                                    }else if (directionsActiveResponse.orders!![0].gender.toString()=="female"){
                                       R.drawable.woman

                                    }else{
                                        R.drawable.seat
                                    }

                                }catch (e:Exception){
                                    R.drawable.seat

                                }
                            ),
                            contentDescription = "asdf",
                            modifier = Modifier.size(30.dp)
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Image(
                            painter = painterResource(
                                try {
                                    if (directionsActiveResponse.orders!![1].gender.toString()=="male"){
                                        R.drawable.man

                                    }else if (directionsActiveResponse.orders!![1].gender.toString()=="female"){
                                        R.drawable.woman

                                    }else{
                                        R.drawable.seat
                                    }

                                }catch (e:Exception){
                                    R.drawable.seat

                                }
                            ),
                            contentDescription = "asdf",
                            modifier = Modifier.size(30.dp)
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Image(
                            painter = painterResource(
                                try {
                                    if (directionsActiveResponse.orders!![2].gender.toString()=="male"){
                                        R.drawable.man

                                    }else if (directionsActiveResponse.orders!![2].gender.toString()=="female"){
                                        R.drawable.woman

                                    }else{
                                        R.drawable.seat
                                    }

                                }catch (e:Exception){
                                    R.drawable.seat

                                }
                            ),
                            contentDescription = "asdf",
                            modifier = Modifier.size(30.dp)
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Image(
                            painter = painterResource(
                                try {
                                    if (directionsActiveResponse.orders!![3].gender.toString()=="male"){
                                        R.drawable.man

                                    }else if (directionsActiveResponse.orders!![3].gender.toString()=="female"){
                                        R.drawable.woman

                                    }else{
                                        R.drawable.seat
                                    }

                                }catch (e:Exception){
                                    R.drawable.seat

                                }
                            ),
                            contentDescription = "asdf",
                            modifier = Modifier.size(30.dp)
                        )
                        Spacer(modifier = Modifier.height(5.dp))

                    }

                    Spacer(modifier = Modifier.height(10.dp))






                }
            }
        }
    }


}