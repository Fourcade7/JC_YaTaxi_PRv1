package com.pr7.jc_biztaxi_v4.ui.home.bottomscreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pr7.jc_biztaxi_v4.R

@ExperimentalMaterial3Api
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun seeatChooseScreen() {

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

                text = stringResource(id = R.string.chooseaseat),
                textAlign = TextAlign.Start,
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.mont_semibold)),
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.height(55.dp))
        Image(
            painter = painterResource(id = R.drawable.carseat),
            contentDescription = "ko`k moshin",
            modifier = Modifier
                .weight(2f)
                .align(alignment = Alignment.CenterHorizontally)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.3f)
        ) {
            Image(
                painter = painterResource(if (seatchose1)  R.drawable.seatchoosed else R.drawable.seatemptyed),
                contentDescription = "ko`k moshin",
                modifier = Modifier
                    .width(60.dp)
                    .height(100.dp)
                    .align(alignment = Alignment.End)
                    .clickable {
                        seatchose1 = !seatchose1
                    }
            )
            Spacer(modifier = Modifier.width(15.dp))
            Box(
                modifier = Modifier.fillMaxSize(),
            ) {
                Image(
                    painter = painterResource(if (seatchose2)  R.drawable.seatchoosed else R.drawable.seatemptyed),
                    contentDescription = "ko`k moshin",
                    modifier = Modifier
                        .width(60.dp)
                        .height(100.dp)
                        .align(Alignment.CenterStart)
                        .clickable {
                            seatchose2 = !seatchose2
                        }

                )
                Image(
                    painter = painterResource(if (seatchose3)  R.drawable.seatchoosed else R.drawable.seatemptyed),
                    contentDescription = "ko`k moshin",
                    modifier = Modifier
                        .width(60.dp)
                        .height(100.dp)
                        .align(alignment = Alignment.Center)
                        .clickable {
                            seatchose3 = !seatchose3
                        }
                )
                Image(
                    painter = painterResource(if (seatchose4)  R.drawable.seatchoosed else R.drawable.seatemptyed),
                    contentDescription = "ko`k moshin",
                    modifier = Modifier
                        .width(60.dp)
                        .height(100.dp)
                        .align(alignment = Alignment.CenterEnd)
                        .clickable {
                            seatchose4 = !seatchose4
                        }

                )
            }
        }

        Spacer(modifier = Modifier.height(55.dp))

    }

}