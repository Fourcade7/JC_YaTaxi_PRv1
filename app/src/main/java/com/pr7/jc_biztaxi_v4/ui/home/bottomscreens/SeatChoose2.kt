package com.pr7.jc_biztaxi_v4.ui.home.bottomscreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.pr7.jc_biztaxi_v4.R
import com.pr7.jc_biztaxi_v4.data.model.newseat.DataSeat
import com.pr7.jc_biztaxi_v4.data.model.newseat.NewSeat
import com.pr7.jc_biztaxi_v4.data.pref.SharefPrefManager
import com.pr7.jc_biztaxi_v4.data.pref.THEME_CHANGE
import com.pr7.jc_biztaxi_v4.data.pref.TOKEN
import com.pr7.jc_biztaxi_v4.ui.home.HomeViewModel
import com.pr7.jc_biztaxi_v4.ui.home.ui.theme.JC_YaTaxi_PRv1Theme
import com.pr7.jc_biztaxi_v4.ui.splash.ui.theme.CardBackgroundTransparent
import com.pr7.jc_biztaxi_v4.utils.showlogd

@ExperimentalMaterial3Api
//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun seeatChooseScreen2(homeViewModel: HomeViewModel,navController: NavController) {

    val mlivedatagetDirectionsR by homeViewModel.mlivedataGetDirections.observeAsState()
    val mlivedatanewseats by homeViewModel.mlivedatanewseat.observeAsState()



    //val seatschoosed= mutableListOf<Int>()
    val seatchoosed by remember {
        mutableStateOf(mutableListOf<Int>())
    }
    val seatlist by remember {
        mutableStateOf(mutableListOf<Int>())
    }
    val dataseatlist by remember {
        mutableStateOf(mutableListOf<DataSeat>())
    }

    var seatchose1 by remember {
        mutableStateOf(false)
    }
    var seatchose2 by remember {
        mutableStateOf(false)
    }
    var seatchose3 by remember {
        mutableStateOf(false)
    }
    var seatchose4 by remember {
        mutableStateOf(false)
    }

    var radiochose1 by remember {
        mutableStateOf(true)
    }
    var radiochose2 by remember {
        mutableStateOf(true)
    }
    var radiochose3 by remember {
        mutableStateOf(true)
    }
    var radiochose4 by remember {
        mutableStateOf(true)
    }

    var openDialog by remember { mutableStateOf(false) }

    mlivedatanewseats.let { result ->
        result?.onSuccess {
            showlogd(funname = "new Seat", text = " Succesfuly ")


        }
        result?.onFailure {

        }
    }


    var themechange by remember {
        mutableStateOf(SharefPrefManager.loadBoolean(THEME_CHANGE))
    }

    JC_YaTaxi_PRv1Theme(darkTheme = themechange) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                //Spacer(modifier = Modifier.height(35.dp))
                Box(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Card(
                        modifier = Modifier.size(38.dp),
                        shape = RoundedCornerShape(8.dp),
                        onClick = {
                            navController.popBackStack()

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

                        text = stringResource(id = R.string.chooseaseat),
                        textAlign = TextAlign.Start,
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.mont_semibold)),
                        modifier = Modifier.align(Alignment.Center),
                        color = MaterialTheme.colorScheme.tertiary

                    )

                    Card(
                        modifier = Modifier
                            .size(38.dp)
                            .align(Alignment.TopEnd),
                        shape = RoundedCornerShape(8.dp),
                        onClick = {
                            openDialog=true


                        },
                        colors = CardDefaults.cardColors(CardBackgroundTransparent),
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.round_done_24),
                                contentDescription = "logo2",
                                modifier = Modifier
                                    .size(20.dp)
                            )
                        }
                    }

                }
                if (openDialog) {
                    if (seatchose1){
                        dataseatlist.add(DataSeat(gender = if (radiochose1) "male" else "female", seat_id = homeViewModel.seatid1.value))
                        seatlist.add(homeViewModel.seatid1.value!!)
                    }
                    if (seatchose2){
                        dataseatlist.add(DataSeat(gender = if (radiochose2) "male" else "female", seat_id = homeViewModel.seatid2.value))
                        seatlist.add(homeViewModel.seatid2.value!!)
                    }
                    if (seatchose3){
                        dataseatlist.add(DataSeat(gender = if (radiochose3) "male" else "female", seat_id = homeViewModel.seatid3.value))
                        seatlist.add(homeViewModel.seatid3.value!!)
                    }
                    if (seatchose4){
                        dataseatlist.add(DataSeat(gender = if (radiochose4) "male" else "female", seat_id = homeViewModel.seatid4.value))
                        seatlist.add(homeViewModel.seatid4.value!!)
                    }

                    showlogd(funname = "dataseatlist",dataseatlist.joinToString())
                    showlogd(funname = "seatlist",seatlist.joinToString())
                    showlogd(funname = "direction", homeViewModel.directionid.value!!.toString())
                    AlertDialog(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .fillMaxWidth().background(MaterialTheme.colorScheme.background), // corner rounded//not working
                        onDismissRequest = {

                        },

                        title = { Text(text = stringResource(id = R.string.seatreservation), color = MaterialTheme.colorScheme.tertiary) },
                        text = { Text(text = seatchoosed.joinToString(), color = MaterialTheme.colorScheme.tertiary) },
                        confirmButton = {
                            Button(onClick = {
                                openDialog = false
                                homeViewModel.newSeat(
                                    SharefPrefManager.loadString(TOKEN).toString(),
                                    newSeat = NewSeat(dataseatlist,homeViewModel.directionid.value,seatlist)
                                )


                            }) {
                                Text(text = stringResource(id = R.string.yes), color = Color.White)
                            }
                        },
                        dismissButton = {
                            Button(onClick = { openDialog = false
                                seatlist.clear()
                            }) {
                                Text(text = stringResource(id = R.string.no), color = Color.White)
                            }
                        }
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                Image(
                    painter = painterResource(id = R.drawable.carseat2),
                    contentDescription = "ko`k moshin",
                    modifier = Modifier
                        .weight(1f)
                        .align(alignment = Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onBackground)
                ) {
                    Row(modifier = Modifier
                        .background(Color.Unspecified),
                        verticalAlignment = Alignment.CenterVertically) {

                        Checkbox(
                            checked = seatchose1,
                            onCheckedChange = {
                                seatchose1=it
                                if (seatchose1){
                                    seatchoosed.add(1)

                                }else{
                                    if (seatchoosed.isNotEmpty()){
                                        seatchoosed.remove(1)


                                    }
                                }
                            },
                            colors =CheckboxDefaults.colors(MaterialTheme.colorScheme.tertiary, checkmarkColor = MaterialTheme.colorScheme.onSurface)
                        )
                        Text(
                            text = "1-${stringResource(id = R.string.seat)}",
                            color = MaterialTheme.colorScheme.tertiary
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        RadioButton(selected = radiochose1, onClick = {
                            radiochose1=!radiochose1
                        })
                        Text(text = stringResource(id = R.string.male),
                            color = MaterialTheme.colorScheme.tertiary)


                        RadioButton(selected = !radiochose1, onClick = {
                            radiochose1=!radiochose1
                        })
                        Text(text = stringResource(id = R.string.female),
                            color = MaterialTheme.colorScheme.tertiary)
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                }
                Spacer(modifier = Modifier.height(5.dp))
                Card(modifier = Modifier.fillMaxWidth(),  colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onBackground)) {
                    Row(modifier = Modifier
                        .background(Color.Unspecified),
                        verticalAlignment = Alignment.CenterVertically) {

                        Checkbox(
                            checked = seatchose2,
                            onCheckedChange = {
                                seatchose2=it
                                if (seatchose2){
                                    seatchoosed.add(2)

                                }else{
                                    if (seatchoosed.isNotEmpty()){
                                        seatchoosed.remove(2)


                                    }
                                }
                            },
                            colors =CheckboxDefaults.colors(MaterialTheme.colorScheme.tertiary, checkmarkColor = MaterialTheme.colorScheme.onSurface)
                        )
                        Text(text = "2-${stringResource(id = R.string.seat)}",
                            color = MaterialTheme.colorScheme.tertiary)

                        Spacer(modifier = Modifier.weight(1f))

                        RadioButton(selected = radiochose2, onClick = {
                            radiochose2=!radiochose2
                        })
                        Text(text = stringResource(id = R.string.male),
                            color = MaterialTheme.colorScheme.tertiary)


                        RadioButton(selected = !radiochose2, onClick = {
                            radiochose2=!radiochose2
                        })
                        Text(text = stringResource(id = R.string.female),
                            color = MaterialTheme.colorScheme.tertiary)
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                }
                Spacer(modifier = Modifier.height(5.dp))
                Card(modifier = Modifier.fillMaxWidth(),colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onBackground)) {
                    Row(modifier = Modifier
                        .background(Color.Unspecified),
                        verticalAlignment = Alignment.CenterVertically) {

                        Checkbox(
                            checked = seatchose3,
                            onCheckedChange = {
                                seatchose3=it
                                if (seatchose3){
                                    seatchoosed.add(3)

                                }else{
                                    if (seatchoosed.isNotEmpty()){
                                        seatchoosed.remove(3)


                                    }
                                }
                            },
                            colors =CheckboxDefaults.colors(MaterialTheme.colorScheme.tertiary, checkmarkColor = MaterialTheme.colorScheme.onSurface)
                        )
                        Text(text = "3-${stringResource(id = R.string.seat)}",
                            color = MaterialTheme.colorScheme.tertiary)

                        Spacer(modifier = Modifier.weight(1f))

                        RadioButton(selected = radiochose3, onClick = {
                            radiochose3=!radiochose3
                        })
                        Text(text = stringResource(id = R.string.male),
                            color = MaterialTheme.colorScheme.tertiary)


                        RadioButton(selected = !radiochose3, onClick = {
                            radiochose3=!radiochose3
                        })
                        Text(text = stringResource(id = R.string.female),
                            color = MaterialTheme.colorScheme.tertiary)
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                }
                Spacer(modifier = Modifier.height(5.dp))
                Card(modifier = Modifier.fillMaxWidth(),colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onBackground)) {
                    Row(modifier = Modifier
                        .background(Color.Unspecified),
                        verticalAlignment = Alignment.CenterVertically) {

                        Checkbox(
                            checked = seatchose4,
                            onCheckedChange = {
                                seatchose4=it
                                if (seatchose4){
                                    seatchoosed.add(4)

                                }else{
                                    if (seatchoosed.isNotEmpty()){
                                        seatchoosed.remove(4)
                                    }
                                }
                            },
                            colors =CheckboxDefaults.colors(MaterialTheme.colorScheme.tertiary, checkmarkColor = MaterialTheme.colorScheme.onSurface)
                        )
                        Text(text = "4-${stringResource(id = R.string.seat)}",
                            color = MaterialTheme.colorScheme.tertiary)

                        Spacer(modifier = Modifier.weight(1f))

                        RadioButton(selected = radiochose4, onClick = {
                            radiochose4=!radiochose4
                        })
                        Text(text = stringResource(id = R.string.male),
                            color = MaterialTheme.colorScheme.tertiary)


                        RadioButton(selected = !radiochose4, onClick = {
                            radiochose4=!radiochose4
                        })
                        Text(text = stringResource(id = R.string.female),
                            color = MaterialTheme.colorScheme.tertiary)
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                }

                Spacer(modifier = Modifier.height(75.dp))

            }
        }

    }


}

@Composable
fun radioButtonSampleselect() {
    val radioOptions = listOf(stringResource(id = R.string.male), stringResource(id = R.string.female))
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0] ) }
    Row(verticalAlignment = Alignment.CenterVertically) {
        radioOptions.forEach { text ->
            Row(
                Modifier
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = {
                            onOptionSelected(text)
                        }
                    ),
                verticalAlignment = Alignment.CenterVertically

            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = { onOptionSelected(text) }
                )
                Text(
                    text = text,
                )
            }
        }
    }
}