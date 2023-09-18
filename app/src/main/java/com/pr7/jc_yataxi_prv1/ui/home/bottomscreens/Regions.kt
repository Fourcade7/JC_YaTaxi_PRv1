package com.pr7.jc_yataxi_prv1.ui.home.bottomscreens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.pr7.jc_yataxi_prv1.R

//@Preview(showBackground = true, showSystemUi = true)
@ExperimentalMaterial3Api
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun regionsListScreen() {

    //homeViewModel.getAllRegionsCD(token)



//    val arrayRegions : State<ArrayList<RegionsR>?> = homeViewModel.mlivedataAllRegionsCD.observeAsState()
//    val arrayDistrictR : State<ArrayList<DistrictR>?> = homeViewModel.mlivedataAllDistrictRCD.observeAsState()
//    val succesdis: State<Boolean?> = homeViewModel.succesdis.observeAsState()
    val scope= rememberCoroutineScope()


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(35.dp))
        Box(
            modifier = Modifier.fillMaxWidth(),
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

                text = "Manzilni tanlang",
                textAlign = TextAlign.Start,
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.mont_semibold)),
                modifier = Modifier.align(Alignment.Center)
            )
        }


        Spacer(modifier = Modifier.height(15.dp))

//        if (arrayRegions.value!=null){
//
//
//
//            LazyColumn(contentPadding = PaddingValues(bottom = 100.dp)){
//                if (succesdis.value==true){
//                    if (arrayDistrictR.value!=null){
//                        itemsIndexed(arrayDistrictR.value!!){index: Int, item: DistrictR ->
//                            lazyitemdistrict(districtR = item, homeViewModel = homeViewModel, token = token, navHostController = navHostController)
//                        }
//                    }
//
//                    homeViewModel.succesreg.value=false
//                } else{
//                    itemsIndexed(arrayRegions.value!!){index: Int, item: RegionsR ->
//                        lazyselectitem(item,homeViewModel,token)
//                    }
//                }
//
//            }
//        }





    }
}

//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//fun lazyselectitem(regionsR: RegionsR,homeViewModel: HomeViewModel,token: String) {
//    var selecteditem by remember {
//        mutableStateOf(regionsR.selectable)
//    }
//
//    Column(modifier = Modifier
//        .padding(5.dp)
//        .clickable {
//            selecteditem = !selecteditem
//            Log.d("PR77777", "lazyselectitem: ${regionsR.id} ${regionsR.name} ")
//            homeViewModel.getDistrictCD(token, regionsR.id!!.toInt())
//
//            if (homeViewModel.districtchoose.value=="from"){
//                homeViewModel.regfromid.value=regionsR.id
//            }
//            if (homeViewModel.districtchoose.value=="to"){
//                homeViewModel.regtoid.value=regionsR.id
//
//            }
//
//
//        }) {
//        Box(
//            modifier = Modifier
//                .padding(horizontal = 10.dp)
//                .fillMaxWidth()
//
//        ) {
//            Text(
//                text = regionsR.name.toString(),
//                modifier = Modifier.align(Alignment.CenterStart)
//            )
//
//            Icon(
//                painter = painterResource(id = R.drawable.arrowrightregions),
//                contentDescription = "Done",
//                modifier = Modifier
//                    .align(Alignment.CenterEnd)
//                    .size(40.dp)
//            )
//
//
//        }
//        Divider(modifier = Modifier.padding(horizontal = 16.dp))
//    }
//
//}
//
//@Composable
//fun lazyitemdistrict(districtR: DistrictR,homeViewModel: HomeViewModel,token: String,navHostController: NavHostController) {
//
//
//    Column(modifier = Modifier
//        .padding(5.dp)
//        .clickable {
//
//            Log.d("PR77777", "lazyselectitem: ${districtR.id} ${districtR.name} ")
//            if (homeViewModel.districtchoose.value=="from"){
//                homeViewModel.districtfrom.value=districtR.name
//                homeViewModel.disfromid.value=districtR.id
//            }
//            if (homeViewModel.districtchoose.value=="to"){
//                homeViewModel.districtto.value=districtR.name
//                homeViewModel.distoid.value=districtR.id
//            }
//            navHostController.navigate(Screens.Discover.route)
//
//
//        }) {
//        Box(
//            modifier = Modifier
//                .padding(horizontal = 10.dp)
//                .fillMaxWidth()
//
//        ) {
//            Text(
//                text = districtR.name,
//                modifier = Modifier.align(Alignment.CenterStart)
//            )
//
//            Icon(
//                painter = painterResource(id = R.drawable.arrowrightregions),
//                contentDescription = "Done",
//                modifier = Modifier
//                    .align(Alignment.CenterEnd)
//                    .size(40.dp)
//            )
//
//
//
//
//
//
//        }
//        Divider(modifier = Modifier.padding(horizontal = 16.dp))
//
//    }
//
//
//
//}