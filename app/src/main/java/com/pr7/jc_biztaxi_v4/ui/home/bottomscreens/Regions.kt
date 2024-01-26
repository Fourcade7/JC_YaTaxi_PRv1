package com.pr7.jc_biztaxi_v4.ui.home.bottomscreens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.pr7.jc_biztaxi_v4.R
import com.pr7.jc_biztaxi_v4.data.model.regions.DistrictsResponse
import com.pr7.jc_biztaxi_v4.data.model.regions.RegionsResponse
import com.pr7.jc_biztaxi_v4.data.pref.SharefPrefManager
import com.pr7.jc_biztaxi_v4.data.pref.THEME_CHANGE
import com.pr7.jc_biztaxi_v4.data.pref.TOKEN
import com.pr7.jc_biztaxi_v4.ui.home.HomeViewModel
import com.pr7.jc_biztaxi_v4.ui.home.ui.theme.JC_YaTaxi_PRv1Theme
import com.pr7.jc_biztaxi_v4.ui.splash.ui.theme.CardBackgroundTransparent
import com.pr7.jc_biztaxi_v4.utils.showlogd

//@Preview(showBackground = true, showSystemUi = true)
@ExperimentalMaterial3Api
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun regionsListScreen(navHostController:NavHostController,homeViewModel: HomeViewModel) {


    val mlivedataAllregions by homeViewModel.mlivedataGetAllRegions.observeAsState()
    val mlivedataAlldisctircts by homeViewModel.mlivedataGetAllDistricts.observeAsState()
    val changeregdis by homeViewModel.changeregdis.observeAsState()

   // homeViewModel.changeregdis.value=true

    val scope= rememberCoroutineScope()


    homeViewModel.getAllRegions(SharefPrefManager.loadString(TOKEN)!!)

    var themechange by remember {
        mutableStateOf(SharefPrefManager.loadBoolean(THEME_CHANGE))
    }



    JC_YaTaxi_PRv1Theme(darkTheme = themechange) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
        ) {
            //Spacer(modifier = Modifier.height(35.dp))
           Column(
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(16.dp)
           ) {
               Box(
                   modifier = Modifier.fillMaxWidth(),
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

                       text = stringResource(id = R.string.wherearewegoing),
                       textAlign = TextAlign.Start,
                       fontSize = 20.sp,
                       fontFamily = FontFamily(Font(R.font.mont_semibold)),
                       modifier = Modifier.align(Alignment.Center),
                       color = MaterialTheme.colorScheme.tertiary
                   )
               }


               Spacer(modifier = Modifier.height(15.dp))


               if (changeregdis!!){
                   mlivedataAllregions.let {result ->
                       result?.onSuccess {
                           LazyColumn(contentPadding = PaddingValues(bottom = 100.dp)){
                               itemsIndexed(it){index: Int, item: RegionsResponse ->
                                   lazyselectitem(item,homeViewModel)
                               }

                           }
                       }
                       result?.onFailure {

                       }
                   }
               }else{
                   mlivedataAlldisctircts.let {result ->
                       result?.onSuccess {
                           LazyColumn(contentPadding = PaddingValues(bottom = 100.dp)){
                               itemsIndexed(it){index: Int, item: DistrictsResponse ->
                                   lazyitemdistrict(item,homeViewModel, navHostController)
                               }

                           }
                       }
                       result?.onFailure {

                       }
                   }
               }
           }



        }
    }

}

//@Preview(showSystemUi = true, showBackground = true)
@Composable
fun lazyselectitem(regionsResponse: RegionsResponse,homeViewModel: HomeViewModel) {
//    var selecteditem by remember {
//        mutableStateOf(regionsR.selectable)
//    }

    var themechange by remember {
        mutableStateOf(SharefPrefManager.loadBoolean(THEME_CHANGE))
    }

    JC_YaTaxi_PRv1Theme(darkTheme = themechange) {
        Column(modifier = Modifier
            .padding(5.dp)
            .clickable {
                homeViewModel.changeregdis.value = false
//            Log.d("PR77777", "lazyselectitem: ${regionsR.id} ${regionsR.name} ")
                homeViewModel.getAllDistrictss(
                    SharefPrefManager
                        .loadString(TOKEN)
                        .toString(), regionsResponse.id!!
                )
//
                if (homeViewModel.districtchoose.value == "from") {
                    homeViewModel.regfromid.value = regionsResponse.id
                }
                if (homeViewModel.districtchoose.value == "to") {
                    homeViewModel.regtoid.value = regionsResponse.id

                }


            },
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 2.dp)
                    .fillMaxWidth()

            ) {
                Text(
                    text = regionsResponse.name.toString(),
                    modifier = Modifier.align(Alignment.CenterStart),
                    color = MaterialTheme.colorScheme.tertiary
                )

                Icon(
                    painter = painterResource(id = R.drawable.arrowrightregions),
                    contentDescription = "Done",
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .size(40.dp),
                    tint = MaterialTheme.colorScheme.tertiary
                )


            }
            Divider(modifier = Modifier.padding(horizontal = 2.dp))

        }

    }


}

@Composable
fun lazyitemdistrict(districtR: DistrictsResponse,homeViewModel: HomeViewModel,navHostController: NavHostController) {

    var themechange by remember {
        mutableStateOf(SharefPrefManager.loadBoolean(THEME_CHANGE))
    }

    JC_YaTaxi_PRv1Theme(darkTheme = themechange) {
        Column(modifier = Modifier
            .padding(5.dp)
            .clickable {

                if (homeViewModel.districtchoose.value == "from") {
                    homeViewModel.districtfrom.value = districtR.name
                    homeViewModel.disfromid.value = districtR.id
                }
                if (homeViewModel.districtchoose.value == "to") {
                    homeViewModel.districtto.value = districtR.name
                    homeViewModel.distoid.value = districtR.id
                }
                homeViewModel.changeregdis.value = true
                navHostController.popBackStack()


            }) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 2.dp)
                    .fillMaxWidth()

            ) {
                Text(
                    text = districtR.name.toString(),
                    modifier = Modifier.align(Alignment.CenterStart),
                    color = MaterialTheme.colorScheme.tertiary
                )

                Icon(
                    painter = painterResource(id = R.drawable.arrowrightregions),
                    contentDescription = "Done",
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .size(40.dp),
                    tint = MaterialTheme.colorScheme.tertiary
                )






            }
            Divider(modifier = Modifier.padding(horizontal =2.dp))

        }
    }





}