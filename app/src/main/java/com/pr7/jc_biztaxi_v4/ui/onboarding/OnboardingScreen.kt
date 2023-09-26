package com.pr7.jc_biztaxi_v4.ui.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pr7.jc_biztaxi_v4.R

@ExperimentalFoundationApi
@Composable
fun onBoardingScreen(pagerState: PagerState) {
    val images = arrayOf(
        R.drawable.onboard1,
        R.drawable.onboard2,
        R.drawable.onboard3
    )
    val desc = arrayOf(
        stringResource(id = R.string.simple_and_speed),
        stringResource(id = R.string.easy_and_fast),
        stringResource(id = R.string.save_moneytime)
    )
    val desc2 = arrayOf(
        stringResource(id = R.string.onboard1),
        stringResource(id = R.string.onboard2),
        stringResource(id = R.string.onboard3)
    )

    HorizontalPager(state = pagerState) {page: Int ->
        onBoardingitem(imagearray = images, pagenumber = page,desc=desc,desc2=desc2)
    }

}



@Composable
fun onBoardingitem(imagearray:Array<Int>,pagenumber:Int,desc:Array<String>,desc2:Array<String>) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Image(
            painter = painterResource(id = imagearray.get(pagenumber)),
            contentDescription = "onboard1",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text =desc.get(pagenumber),
            textAlign = TextAlign.Center,
            fontSize = 26.sp,
            fontFamily = FontFamily(Font(R.font.mont_bold))
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text =desc2.get(pagenumber),
            textAlign = TextAlign.Center,
            fontSize = 15.sp,
            fontFamily = FontFamily(Font(R.font.mont_light))
        )

    }

}