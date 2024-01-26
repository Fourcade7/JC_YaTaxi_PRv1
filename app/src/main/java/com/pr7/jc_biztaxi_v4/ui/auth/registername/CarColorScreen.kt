package com.pr7.jc_biztaxi_v4.ui.auth.registername

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.pr7.jc_biztaxi_v4.data.model.carinfo.CarColorModel
import com.pr7.jc_biztaxi_v4.ui.splash.ui.theme.LayoutbackgroundColors


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun carColorScreen(registerNameViewModel: RegisterNameViewModel,navHostController:NavHostController) {

    Column(modifier = Modifier.fillMaxSize()) {
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

                text = stringResource(id = R.string.carcolor),
                textAlign = TextAlign.Start,
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.mont_semibold)),
                modifier = Modifier.align(Alignment.Center)
            )
        }

        val array= arrayOf(
           CarColorModel(carcolor = "red",R.drawable.lens_red),
           CarColorModel(carcolor = "blue",R.drawable.lens_blue),
           CarColorModel(carcolor = "lightblue",R.drawable.lens_lightblue),
           CarColorModel(carcolor = "orange",R.drawable.lens_orange),
           CarColorModel(carcolor = "white",R.drawable.lens_white),
           CarColorModel(carcolor = "black",R.drawable.lens_black),
           CarColorModel(carcolor = "yellow",R.drawable.lens_yellow),
           CarColorModel(carcolor = "silver",R.drawable.lens_silver),
           CarColorModel(carcolor = "brown",R.drawable.lens_brown),
        )

        val array2= arrayOf(
            stringResource(id = R.string.red),
            stringResource(id = R.string.blue),
            stringResource(id = R.string.lightblue),
            stringResource(id = R.string.orange),
            stringResource(id = R.string.white),
            stringResource(id = R.string.black),
            stringResource(id = R.string.yellow),
            stringResource(id = R.string.silver),
            stringResource(id = R.string.brown),
        )



        LazyColumn(){
            itemsIndexed(array){index: Int, item: CarColorModel ->
                //carlistitem(item,registerNameViewModel,navHostController)
                carcoloritem(colorModel = item, registerNameViewModel,navHostController,array2,index)
            }
        }
    }

}


//@Preview(showBackground = true, showSystemUi = true)
@ExperimentalMaterial3Api
@Composable
fun carcoloritem(colorModel: CarColorModel,registerNameViewModel: RegisterNameViewModel,navHostController:NavHostController,array2: Array<String>,position:Int) {

    Column {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            onClick = {

//                registerNameViewModel.carname.value=carListResponse.name
                registerNameViewModel.carcolor.value=colorModel.carcolor
                registerNameViewModel.carcolorlang.value=array2[position]
                navHostController.popBackStack()
            }

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Row() {

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = array2[position],
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
                        Image(
                            painter = painterResource(id = colorModel.colorImage!!),
                            contentDescription = "",
                            modifier = Modifier
                                .size(40.dp)
                                .padding(7.dp)
                        )


                    }
                    Spacer(modifier = Modifier.width(10.dp))

                }
            }
        }
    }

}