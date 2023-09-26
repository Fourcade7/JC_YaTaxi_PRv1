package com.pr7.jc_biztaxi_v4.ui.auth.otp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.gson.GsonBuilder
import com.pr7.jc_biztaxi_v4.R
import com.pr7.jc_biztaxi_v4.data.model.otp.OTPUserResponse
import com.pr7.jc_biztaxi_v4.data.pref.DRIVER
import com.pr7.jc_biztaxi_v4.data.pref.LOGINED
import com.pr7.jc_biztaxi_v4.data.pref.OTPCODE
import com.pr7.jc_biztaxi_v4.data.pref.PASSANGER
import com.pr7.jc_biztaxi_v4.data.pref.REFRESH_TOKEN
import com.pr7.jc_biztaxi_v4.data.pref.SharefPrefManager
import com.pr7.jc_biztaxi_v4.data.pref.TOKEN
import com.pr7.jc_biztaxi_v4.data.pref.USERNAMED
import com.pr7.jc_biztaxi_v4.data.pref.USERTYPE
import com.pr7.jc_biztaxi_v4.data.pref.USERTYPESELECTED
import com.pr7.jc_biztaxi_v4.ui.auth.login.LoginActivity
import com.pr7.jc_biztaxi_v4.ui.change.ChangeActivity
import com.pr7.jc_biztaxi_v4.ui.splash.ui.theme.ButtonbackgroundLanguage
import com.pr7.jc_biztaxi_v4.ui.splash.ui.theme.FocusedBorderColor
import com.pr7.jc_biztaxi_v4.ui.splash.ui.theme.StatusBarColor
import com.pr7.jc_biztaxi_v4.utils.showlogd
import com.pr7.jc_biztaxi_v4.utils.statusbarcolorchange
import java.lang.Exception

class OTPActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            statusbarcolorchange(window = window, color = StatusBarColor)
            otpverifyScreen()
        }
    }
}


@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun otpverifyScreen() {
    val context = LocalContext.current as Activity

    val otpviewModel: OTPViewModel = viewModel()
    val mlivedataOtpUserResponse by otpviewModel.mlivedataOtpResponse.observeAsState()
    val mlivedatauserinfo by otpviewModel.mlivedataUserInfo.observeAsState()
    val iscomplatedotp by otpviewModel.iscompletedotp.observeAsState()


    var otp1 by remember {
        mutableStateOf("")
    }
    var messageresponse by remember {
        mutableStateOf<String?>("")
    }
    var countopenotp by remember {
        mutableStateOf<Int?>(0)
    }

    //showlogd(funname = "otp activity", text = SharefPrefManager.loadString(OTPCODE).toString())
    if (countopenotp == 1) {
        if (SharefPrefManager.loadString(OTPCODE) != null) {
            context.startActivity(Intent(context, ChangeActivity::class.java))
            context.finish()
        }

    }

    mlivedataOtpUserResponse.let { result ->
        result?.onSuccess {

            showlogd(funname = "OTP user onSucces", text = it.access.toString())
            SharefPrefManager.saveString(TOKEN, it.access.toString())
            SharefPrefManager.saveString(REFRESH_TOKEN, it.refresh.toString())
            SharefPrefManager.saveBoolean(LOGINED, true)

            messageresponse = "Подожди"
            countopenotp = countopenotp!! + 1
            if (it.created == false) {
                SharefPrefManager.saveBoolean(USERNAMED, true)
                SharefPrefManager.saveBoolean(USERTYPESELECTED, true)
                otpviewModel.getuserinfo(token = it.access.toString())
            }
            // messageresponse=it.message.toString()


            //SharefPrefManager.saveBoolean(LOGINED,true)
        }
        result?.onFailure {
            try {
                val gson = GsonBuilder().create()
                val gsonparse: OTPUserResponse =
                    gson.fromJson(it.message, OTPUserResponse::class.java)
                if (gsonparse.message != null) {
                    showlogd(funname = "OTP user onError 1", text = gsonparse.message.toString())
                    //messageresponse=gsonparse.message.toString()
                    messageresponse = "неправильно введен код"
                    countopenotp = 0
                }

                if (gsonparse.code != null) {
                    showlogd(funname = "OTP user onError 2", text = gsonparse.code[0])
                    //messageresponse=gsonparse.phone[0]
                    messageresponse = "Убедитесь, что это поле содержит не менее 6 символов."
                    countopenotp = 0

                }
            } catch (e: Exception) {
                result.onFailure {
                    messageresponse = it.message.toString()
                }

            }
        }
    }

    mlivedatauserinfo.let { result ->

        result?.onSuccess {
            showlogd(funname = "userinfo",it.user_type.toString())
            showlogd(funname = "userinfo firstname",it.first_name.toString())
            if (it.user_type.toString()== PASSANGER){

                SharefPrefManager.saveBoolean(USERTYPESELECTED, true)
                SharefPrefManager.saveString(USERTYPE, PASSANGER)
            }else{
                SharefPrefManager.saveBoolean(USERTYPESELECTED, true)
                SharefPrefManager.saveString(USERTYPE, DRIVER)

            }

        }
        result?.onFailure {

        }

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
                    context.startActivity(Intent(context, LoginActivity::class.java))
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

                text = stringResource(id = R.string.otpverification),
                textAlign = TextAlign.Start,
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.mont_semibold)),
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.height(35.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.confirmyournumber),
            textAlign = TextAlign.Start,
            fontSize = 26.sp,
            fontFamily = FontFamily(Font(R.font.mont_bold))
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = stringResource(id = R.string.otpcodesent),
            textAlign = TextAlign.Start,
            fontSize = 15.sp,
            fontFamily = FontFamily(Font(R.font.mont_light))
        )
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = otp1,
                onValueChange = {

                    if (it.length < 7) {
                        otp1 = it
                    }

                },
                placeholder = {
                    //Text(text = stringResource(id = R.string.sms6number))
                },
                label = {
                    Text(text = stringResource(id = R.string.sms6number))
                },
                maxLines = 1,

                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = FocusedBorderColor,
                    focusedLabelColor = FocusedBorderColor,
                    unfocusedBorderColor = if (otp1.length == 1) FocusedBorderColor else Color.Black
                ),
                textStyle = LocalTextStyle.current.copy(
                    textAlign = TextAlign.Center,
                    fontSize = 15.sp
                )
            )
            Spacer(modifier = Modifier.width(10.dp))


        }
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = if (messageresponse != null) messageresponse.toString() else "",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,

            fontFamily = FontFamily(Font(R.font.mont_semibold)),
            fontSize = 14.sp,
            color = Color.Red
        )
        Spacer(modifier = Modifier.height(15.dp))
        if (iscomplatedotp == true) {
            CircularProgressIndicator(
                modifier = Modifier.size(50.dp),
                strokeWidth = 10.dp
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(54.dp)
                .clickable {
                    countopenotp = 0

                    otpviewModel.otpverify(otp1)

//                    val intent = Intent(context, ChangeActivity::class.java)
//                    context.startActivity(intent)
//                    context.finish()
                },
            shape = RoundedCornerShape(15.dp),
            color = ButtonbackgroundLanguage
        ) {
            Column(verticalArrangement = Arrangement.Center) {

                Text(
                    text = stringResource(id = R.string.next),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily(Font(R.font.mont_semibold)),
                    fontSize = 17.sp,

                    )
            }

        }
        Spacer(modifier = Modifier.height(15.dp))
    }

}