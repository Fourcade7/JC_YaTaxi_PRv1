package com.pr7.jc_biztaxi_v4.ui.auth.registername

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pr7.jc_biztaxi_v4.R
import com.pr7.jc_biztaxi_v4.ui.splash.ui.theme.LayoutbackgroundColors
import androidx.compose.ui.text.TextStyle
import androidx.navigation.NavHostController
import com.pr7.jc_biztaxi_v4.data.model.carinfo.CarListResponse
import com.pr7.jc_biztaxi_v4.data.pref.SharefPrefManager
import com.pr7.jc_biztaxi_v4.data.pref.TOKEN

//@Preview(showBackground = true, showSystemUi = true)
@SuppressLint("MutableCollectionMutableState")
@ExperimentalMaterial3Api
@Composable
fun drivercarList(navHostController: NavHostController,registerNameViewModel:RegisterNameViewModel) {


    //val registerNameViewModel: RegisterNameViewModel = viewModel()

    val mlivedatacarlist by registerNameViewModel.mlivedataCarList.observeAsState()
    val complatedcarlist by registerNameViewModel.iscompletedcarlist.observeAsState()

    registerNameViewModel.getCarlist(SharefPrefManager.loadString(TOKEN).toString())

    var carlist by remember {
        mutableStateOf(ArrayList<CarListResponse>())
    }




    mlivedatacarlist.let { result ->

        result?.onSuccess {
                    carlist=it
        }
        result?.onFailure {

        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()

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

                text = stringResource(id = R.string.listofcars),
                textAlign = TextAlign.Start,
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.mont_semibold)),
                modifier = Modifier.align(Alignment.Center)
            )
        }
        if (carlist.size>0){
            LazyColumn(){
                itemsIndexed(carlist){index: Int, item: CarListResponse ->
                carlistitem(item,registerNameViewModel,navHostController)
                }
            }
        }

    }


}


//@Preview(showBackground = true, showSystemUi = true)
@ExperimentalMaterial3Api
@Composable
fun carlistitem(carListResponse: CarListResponse,registerNameViewModel: RegisterNameViewModel,navHostController: NavHostController) {

    Column {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            onClick = {

                registerNameViewModel.carname.value=carListResponse.name
                registerNameViewModel.carid.value=carListResponse.id
                navHostController.popBackStack()
            }

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Row() {
                    Card(
                        elevation = CardDefaults.cardElevation(1.dp),
                        shape = CircleShape,
                        modifier = Modifier.align(Alignment.CenterVertically),
                        colors = CardDefaults.cardColors(LayoutbackgroundColors)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_directions_car_24),
                            contentDescription = "",
                            modifier = Modifier
                                .size(40.dp)
                                .padding(5.dp)
                        )


                    }
                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = carListResponse.name.toString(),
                        style = TextStyle(
                            fontSize = 12.sp,
                            lineHeight = 23.sp,
                            fontFamily = FontFamily(Font(R.font.mont_light)),
                            fontWeight = FontWeight(600),
                            color = Color(0xFF17334C),

                            ),
                        modifier = Modifier.align(Alignment.CenterVertically),

                        )
                    Spacer(modifier = Modifier.weight(1f))
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
                                .padding(7.dp)
                        )


                    }
                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = carListResponse.car_seats.toString(),
                        style = TextStyle(
                            fontSize = 12.sp,
                            lineHeight = 23.sp,
                            fontFamily = FontFamily(Font(R.font.mont_bold)),
                            fontWeight = FontWeight(600),
                            color = Color(0xFF17334C),

                            ),
                        modifier = Modifier.align(Alignment.CenterVertically),

                        )
                    Spacer(modifier = Modifier.width(10.dp))
                }
            }
        }
    }

}