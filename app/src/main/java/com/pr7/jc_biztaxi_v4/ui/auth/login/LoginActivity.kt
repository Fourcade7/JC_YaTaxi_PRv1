package com.pr7.jc_biztaxi_v4.ui.auth.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.*
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
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.gson.GsonBuilder
import com.pr7.jc_biztaxi_v4.data.model.login.LoginUserResponse
import com.pr7.jc_biztaxi_v4.data.pref.OTPCODE
import com.pr7.jc_biztaxi_v4.data.pref.SharefPrefManager
import com.pr7.jc_biztaxi_v4.ui.auth.otp.OTPActivity
import com.pr7.jc_biztaxi_v4.ui.auth.register.RegisterActivity
import com.pr7.jc_biztaxi_v4.ui.splash.ui.theme.ButtonbackgroundLanguage
import com.pr7.jc_biztaxi_v4.ui.splash.ui.theme.FocusedBorderColor
import com.pr7.jc_biztaxi_v4.ui.splash.ui.theme.StatusBarColor
import com.pr7.jc_biztaxi_v4.utils.showlogd
import com.pr7.jc_biztaxi_v4.utils.statusbarcolorchange
import com.pr7.jc_biztaxi_v4.R
import java.lang.Exception

class LoginActivity : ComponentActivity() {

    val loginViewModel:LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            statusbarcolorchange(window = window, color = StatusBarColor)
            loginScreen()
            val systemUiController = rememberSystemUiController()
           // systemUiController.isStatusBarVisible = false

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun loginScreen() {
    val context= LocalContext.current as Activity
    val loginViewModel:LoginViewModel= viewModel()
    val mLiveDataLoginResponse by loginViewModel.mlivedataLoginResponse.observeAsState()
    val iscomplated by loginViewModel.iscompletedlogin.observeAsState()



    var messageresponse by remember {
        mutableStateOf<String?>("")
    }

    var countopenotp by remember {
        mutableStateOf<Int?>(0)
    }
    var name by remember {
        mutableStateOf("")
    }

    if (countopenotp==1){
        if (SharefPrefManager.loadString(OTPCODE)!=null){
            context.startActivity(Intent(context, OTPActivity::class.java))
            context.finish()
        }

    }
    mLiveDataLoginResponse.let {result->
        result?.onSuccess {
            showlogd(funname = "Login user onSucces", text = it.message.toString())
            showlogd(funname = "Login user onSucces", text = it.otp.toString())
            SharefPrefManager.saveString(OTPCODE,it.otp.toString())
            // messageresponse=it.message.toString()
            messageresponse="Ожидание смс-провайдера"
            countopenotp=countopenotp!!+1

            //SharefPrefManager.saveBoolean(LOGINED,true)
        }
        result?.onFailure {
            try {
                val gson=GsonBuilder().create()
                val gsonparse: LoginUserResponse =gson.fromJson(it.message, LoginUserResponse::class.java)
                if (gsonparse.message!=null){
                    showlogd(funname = "Login user onError 1", text = gsonparse.message.toString())
                    //messageresponse=gsonparse.message.toString()
                    messageresponse="Пользователя с таким номером телефона не существует. Пожалуйста, введите другой номер телефона. или Зарегистрируйтесь"
                    countopenotp=0
                }

                if (gsonparse.phone!=null){
                    showlogd(funname = "Login user onError 2", text = gsonparse.phone[0])
                    //messageresponse=gsonparse.phone[0]
                    messageresponse="Введите действительный номер телефона."
                    countopenotp=0

                }
            }catch(e:Exception) {
               result.onFailure {
                    messageresponse=it.message.toString()
                }

            }

        }
    }



    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()) {
        Spacer(modifier = Modifier.height(35.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo2),
                contentDescription = "logo2",
                modifier = Modifier.size(60.dp)
            )
            Spacer(modifier = Modifier.width(15.dp))
            Text(

                text = stringResource(id = R.string.wetaxi),
                textAlign = TextAlign.Start,
                fontSize = 26.sp,
                fontFamily = FontFamily(Font(R.font.mont_bold))
            )
        }
        Spacer(modifier = Modifier.height(35.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.loginUser),
            textAlign = TextAlign.Start,
            fontSize = 26.sp,
            fontFamily = FontFamily(Font(R.font.mont_bold))
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = stringResource(id = R.string.enterphonenumber),
            textAlign = TextAlign.Start,
            fontSize = 15.sp,
            fontFamily = FontFamily(Font(R.font.mont_light))
        )
        Spacer(modifier = Modifier.height(15.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = name,
            onValueChange = {
                if (it.length < 10) {
                    name = it
                }
            },
            label = {
                Text(text = stringResource(id = R.string.phone))
            },
            placeholder = {
                Text(text = "")
            },
            maxLines = 1,
            singleLine = true,
            leadingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Call, contentDescription = "")
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = if (name.length<9)  Color.Red  else FocusedBorderColor, focusedLabelColor =if (name.length<9)  Color.Red  else FocusedBorderColor)
        )

        Spacer(modifier = Modifier.height(15.dp))


        Text(
            text =if (messageresponse!=null)  messageresponse.toString() else "",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,

            fontFamily = FontFamily(Font(R.font.mont_semibold)),
            fontSize = 14.sp,
            color = Color.Red
        )
        Spacer(modifier = Modifier.height(15.dp))
        if (iscomplated==true){
            CircularProgressIndicator(
                modifier = Modifier.size(50.dp),
                strokeWidth =10.dp
            )
        }




        Spacer(modifier = Modifier.weight(1f))

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .height(54.dp)
                    .clickable {
                        context.startActivity(Intent(context, RegisterActivity::class.java))
                    },
                shape = RoundedCornerShape(15.dp),
                color = StatusBarColor,
            ) {
                Column(verticalArrangement = Arrangement.Center) {

                    Text(
                        text = stringResource(id = R.string.register),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(Font(R.font.mont_semibold)),
                        fontSize = 17.sp,
                        color = Color.White
                    )
                }

            }

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .height(54.dp)
                .clickable {

                    countopenotp = 0

                    loginViewModel.login(name)

                    //if login succes
                    //SharefPrefManager.saveBoolean(LOGINED,true)
                    //if user not registered else otp activity go
                    //context.startActivity(Intent(context, RegisterActivity::class.java))
                    //context.finish()
                },
            shape = RoundedCornerShape(15.dp),
            color = ButtonbackgroundLanguage,
        ) {
            Column(verticalArrangement = Arrangement.Center) {

                Text(
                    text = stringResource(id = R.string.loginUser),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily(Font(R.font.mont_semibold)),
                    fontSize = 17.sp
                )
            }

        }
        Spacer(modifier = Modifier.height(15.dp))

    }
}