package com.pr7.jc_yataxi_prv1.ui.auth.register

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
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
import com.pr7.jc_yataxi_prv1.R
import com.pr7.jc_yataxi_prv1.data.model.login.LoginUserResponse
import com.pr7.jc_yataxi_prv1.data.model.register.RegisterUserResponse
import com.pr7.jc_yataxi_prv1.data.pref.OTPCODE
import com.pr7.jc_yataxi_prv1.data.pref.SharefPrefManager
import com.pr7.jc_yataxi_prv1.ui.auth.otp.OTPActivity
import com.pr7.jc_yataxi_prv1.ui.auth.register.ui.theme.JC_YaTaxi_PRv1Theme
import com.pr7.jc_yataxi_prv1.ui.splash.ui.theme.ButtonbackgroundLanguage
import com.pr7.jc_yataxi_prv1.ui.splash.ui.theme.FocusedBorderColor
import com.pr7.jc_yataxi_prv1.ui.splash.ui.theme.StatusBarColor
import com.pr7.jc_yataxi_prv1.utils.showlogd
import com.pr7.jc_yataxi_prv1.utils.statusbarcolorchange

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            statusbarcolorchange(window = window, color = StatusBarColor)
            registerScreen()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun registerScreen() {
    val context= LocalContext.current as Activity
   val registerViewModel:RegisterViewModel= viewModel()
    val mlivedataRegisterUserResponse by registerViewModel.mlivedataRegisterResponse.observeAsState()
    val iscomplatedreg by registerViewModel.iscompletedregister.observeAsState()


    var name by remember {
        mutableStateOf("")
    }
    var messageresponse by remember {
        mutableStateOf<String?>("")
    }
    var countopenotp by remember {
        mutableStateOf<Int?>(0)
    }
    if (countopenotp==1){
        if (SharefPrefManager.loadString(OTPCODE)!=null){
            context.startActivity(Intent(context, OTPActivity::class.java))
            context.finish()
        }

    }


    mlivedataRegisterUserResponse.let {result ->
        result?.onSuccess {
            showlogd(funname = "Register user onSucces", text = it.message.toString())
            showlogd(funname = "Register user onSucces", text = it.otp.toString())
            SharefPrefManager.saveString(OTPCODE,it.otp.toString())

            messageresponse="Ожидание смс-провайдера"
            countopenotp=countopenotp!!+1
        }
        result?.onFailure {
            try {
                val gson= GsonBuilder().create()
                val gsonparse: RegisterUserResponse =gson.fromJson(it.message, RegisterUserResponse::class.java)
                if (gsonparse.message!=null){
                    showlogd(funname = "Register user onError 1", text = gsonparse.message.toString())

                    //messageresponse=gsonparse.message.toString()
                    messageresponse="Пользователя с таким номером телефона не существует. Пожалуйста, введите другой номер телефона. или Зарегистрируйтесь"
                    //isnotregistered=true
                    countopenotp=0

                }

                if (gsonparse.phone!=null){
                    showlogd(funname = "Register user onError 2", text = gsonparse.phone[0])
                    //messageresponse=gsonparse.phone[0]
                    messageresponse="Введите действительный номер телефона."
                    //isnotregistered=false
                    countopenotp=0

                }
            }catch (e:Exception){
                messageresponse=it.message.toString()
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

                text = stringResource(id = R.string.yataxi),
                textAlign = TextAlign.Start,
                fontSize = 26.sp,
                fontFamily = FontFamily(Font(R.font.mont_bold))
            )
        }
        Spacer(modifier = Modifier.height(35.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.register),
            textAlign = TextAlign.Start,
            fontSize = 26.sp,
            fontFamily = FontFamily(Font(R.font.mont_bold))
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text =stringResource(id = R.string.enterphonenumber),
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
                Text(text = "+998")
            },
            maxLines = 1,
            singleLine = true,
            leadingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Call, contentDescription = "")
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = if (name.length<9)  Color.Red  else FocusedBorderColor,
                focusedLabelColor = if (name.length<9)  Color.Red  else FocusedBorderColor,
            )
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

        if (iscomplatedreg==true){
            CircularProgressIndicator(
                modifier = Modifier.size(50.dp),
                strokeWidth =10.dp
            )
        }

        Spacer(modifier = Modifier.weight(1f))
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(54.dp)
                .clickable {
                    registerViewModel.registerUser(phone = name)
                    countopenotp=0

                },
            shape = RoundedCornerShape(15.dp),
            color = ButtonbackgroundLanguage,
        ) {
            Column(verticalArrangement = Arrangement.Center) {

                Text(
                    text = stringResource(id = R.string.register),
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
