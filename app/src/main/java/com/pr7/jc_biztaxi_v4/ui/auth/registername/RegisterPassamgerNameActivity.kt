package com.pr7.jc_biztaxi_v4.ui.auth.registername

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
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pr7.jc_biztaxi_v4.R
import com.pr7.jc_biztaxi_v4.data.pref.GENDER
import com.pr7.jc_biztaxi_v4.data.pref.MAN
import com.pr7.jc_biztaxi_v4.data.pref.PASSANGER
import com.pr7.jc_biztaxi_v4.data.pref.SharefPrefManager
import com.pr7.jc_biztaxi_v4.data.pref.TOKEN
import com.pr7.jc_biztaxi_v4.data.pref.USERNAMED
import com.pr7.jc_biztaxi_v4.data.pref.WOMAN
import com.pr7.jc_biztaxi_v4.ui.change.ChangeActivity
import com.pr7.jc_biztaxi_v4.ui.home.HomeActivity
import com.pr7.jc_biztaxi_v4.ui.splash.ui.theme.ButtonbackgroundLanguage
import com.pr7.jc_biztaxi_v4.ui.splash.ui.theme.FocusedBorderColor
import com.pr7.jc_biztaxi_v4.ui.splash.ui.theme.StatusBarColor
import com.pr7.jc_biztaxi_v4.utils.showlogd
import com.pr7.jc_biztaxi_v4.utils.statusbarcolorchange

@OptIn(ExperimentalMaterial3Api::class)
class RegisterPassamgerNameActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            statusbarcolorchange(window = window, color = StatusBarColor)

            registerpassangernameScreen()
        }
    }
}


@ExperimentalMaterial3Api
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun registerpassangernameScreen() {
    var checked by remember {
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current as Activity

    val registerNameViewModel: RegisterNameViewModel = viewModel()

    val userinfochane by registerNameViewModel.mlivedataUserInfoch.observeAsState()


    var counter by remember {
        mutableStateOf(0)
    }
    if (counter == 1) {
        //need request for set user name
        context.startActivity(Intent(context, HomeActivity::class.java))
        context.finish()
    }

    userinfochane.let { result ->

        result?.onSuccess {
            SharefPrefManager.saveBoolean(USERNAMED, true)
            showlogd(funname = "NAMED", text = " COMPLATED")
            counter = counter + 1
        }

    }


    var name by remember {
        mutableStateOf("")
    }
    var name2 by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        //Spacer(modifier = Modifier.height(35.dp))
        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Card(
                modifier = Modifier.size(38.dp),
                shape = RoundedCornerShape(8.dp),
                onClick = {
                    context.startActivity(Intent(context, ChangeActivity::class.java))
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

                text = stringResource(id = R.string.register),
                textAlign = TextAlign.Start,
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.mont_semibold)),
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.height(55.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp),
            text = stringResource(id = R.string.name),
            textAlign = TextAlign.Start,
            fontSize = 15.sp,
            fontFamily = FontFamily(Font(R.font.mont_semibold))
        )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = name,
            onValueChange = {
                if (it.length < 15) {
                    name = it
                }
            },
            placeholder = {
                Text(text = stringResource(id = R.string.enteryourname))
            },
            maxLines = 1,
            singleLine = true,

            //keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = FocusedBorderColor,
                focusedLabelColor = FocusedBorderColor
            )
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp),
            text = stringResource(id = R.string.surname),
            textAlign = TextAlign.Start,
            fontSize = 15.sp,
            fontFamily = FontFamily(Font(R.font.mont_semibold))
        )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = name2,
            onValueChange = {
                if (it.length < 15) {
                    name2 = it
                }
            },
            placeholder = {
                Text(text = stringResource(id = R.string.enteryoursurname))
            },
            maxLines = 1,
            singleLine = true,

            //keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = FocusedBorderColor,
                focusedLabelColor = FocusedBorderColor
            )
        )
        Spacer(modifier = Modifier.height(15.dp))

        RadioButtonSample3()
        Spacer(modifier = Modifier.height(15.dp))




        Spacer(modifier = Modifier.weight(1f))
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(54.dp)
                .clickable {
                    if (name.length > 0 && name2.length > 0) {
                        registerNameViewModel.changeuserinfo(
                            token = SharefPrefManager
                                .loadString(TOKEN)
                                .toString(),
                            firstname = name,
                            lastname = name2,
                            usertype = PASSANGER,
                            gender = when (SharefPrefManager
                                .loadString(GENDER)
                                .toString()) {
                                "Male" -> {
                                    "male"
                                }

                                "Мужской" -> {
                                    "male"
                                }

                                "Erkak" -> {
                                    "male"
                                }

                                "Женский" -> {
                                    "female"
                                }

                                "Female" -> {
                                    "female"
                                }

                                "Ayol" -> {
                                    "female"
                                }

                                else -> {
                                    "male"
                                }
                            }
                        )
                    }

                    showlogd(
                        "GENDER",
                        text = SharefPrefManager
                            .loadString(GENDER)
                            .toString()
                    )


                },
            shape = RoundedCornerShape(15.dp),
            color = ButtonbackgroundLanguage
        ) {
            Column(verticalArrangement = Arrangement.Center) {

                Text(
                    text = stringResource(id = R.string.save),
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


@Composable
fun KindRadioGroup(
    mItems: List<String>,
    selected: String,
    setSelected: (selected: String) -> Unit,
) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            mItems.forEach { item ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selected == item,
                        onClick = {
                            setSelected(item)
                        },
                        enabled = true,
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color.Black
                        )
                    )
                    Text(text = item, modifier = Modifier.padding(start = 8.dp))
                }
            }
        }

}


@Composable
fun RadioButtonSample3() {
    val list = listOf(stringResource(id = R.string.male), stringResource(id = R.string.female))
    val (selected, setSelected) = remember { mutableStateOf("") }
    KindRadioGroup(mItems = list, selected = selected, setSelected = setSelected)
    showlogd("KIND radio group", text = selected)
    if (selected.isNotEmpty()) {
        SharefPrefManager.saveString(GENDER, selected)
    }
}

@Composable
fun RadioButtonSample() {
    val radioOptions =
        listOf(stringResource(id = R.string.male), stringResource(id = R.string.female))
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
    //showlogd("radio button gender:",selectedOption)


    Row(verticalAlignment = Alignment.CenterVertically) {
        radioOptions.forEach { text ->
            Row(
                Modifier
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = {
//                            showlogd("row button gender",selectedOption)
//                            SharefPrefManager.saveString(GENDER, selectedOption)
//                            onOptionSelected(text)

                            //SharefPrefManager.saveString(GENDER, WOMAN)
                        }
                    ),
                verticalAlignment = Alignment.CenterVertically

            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = {
                        showlogd("radio button gender", selectedOption)
                        SharefPrefManager.saveString(GENDER, selectedOption)
                        onOptionSelected(text)


                    }
                )
                Text(
                    text = text,
                )
            }
        }
    }
}


@Composable
fun RadioButtonSample2() {
    val radioOptions =
        listOf(stringResource(id = R.string.male), stringResource(id = R.string.female))
    var selected by remember { mutableStateOf(true) }
    //showlogd("radio button gender:",selectedOption)
    // SharefPrefManager.saveString(GENDER, MAN)

    Row(
        verticalAlignment = Alignment.CenterVertically,

        ) {

        RadioButton(
            selected = (selected),
            onClick = {
                showlogd("radio button gender", MAN)
                SharefPrefManager.saveString(GENDER, MAN)

                selected = !selected

            }
        )
        Text(
            text = stringResource(id = R.string.male),
        )
        RadioButton(
            selected = (!selected),
            onClick = {

                selected = !selected
                showlogd("radio button gender", WOMAN)
                SharefPrefManager.saveString(GENDER, WOMAN)


            }
        )
        Text(
            text = stringResource(id = R.string.female),
        )


    }
}

