package com.pr7.jc_yataxi_prv1.data.pref

import android.app.Activity
import android.app.LocaleManager
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Build.VERSION_CODES.TIRAMISU
import android.os.LocaleList
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.pr7.jc_yataxi_prv1.R
import com.pr7.jc_yataxi_prv1.utils.CONTEXT

const val TOKEN = "token"
const val REFRESH_TOKEN = "refresh_token"
const val ONBOARDING = "onboarding"
const val USERTYPE = "usertype"
const val USERTYPESELECTED = "usertypeselected"
const val USERNAMED = "usernamed"
const val DRIVER = "driver"
const val PASSANGER = "client"
const val LOGINED = "logined"
const val OTPCODE = "otpcode"

const val LANGUAGE_UZ = "uz"
const val LANGUAGE_RU = "ru"
const val LANGUAGE_EN = "en"
const val LANGUAGE_COUNT = "count"
const val LANGUAGE_TYPE = "languagetype"
const val USERNAME = "Username"
const val PHONE = "Phone"


object SharefPrefManager {

    fun saveString(key: String, value: String?) {
        val editor = CONTEXT.getSharedPreferences("Pr", ComponentActivity.MODE_PRIVATE)
            .edit() as SharedPreferences.Editor
        editor.putString(key, value)
        editor.commit()
    }

    fun loadString(key: String): String? {
        val sharedPreferences = CONTEXT.getSharedPreferences("Pr", ComponentActivity.MODE_PRIVATE)
        return sharedPreferences.getString(key, null)
    }

    fun saveBoolean(key: String, value: Boolean) {
        val editor = CONTEXT.getSharedPreferences("Pr", ComponentActivity.MODE_PRIVATE)
            .edit() as SharedPreferences.Editor
        editor.putBoolean(key, value)
        editor.commit()
    }

    fun loadBoolean(key: String): Boolean {
        val sharedPreferences = CONTEXT.getSharedPreferences("Pr", ComponentActivity.MODE_PRIVATE)
        return sharedPreferences.getBoolean(key, false)
    }

    fun saveInt(key: String, value: Int) {
        val editor = CONTEXT.getSharedPreferences("Pr", ComponentActivity.MODE_PRIVATE)
            .edit() as SharedPreferences.Editor
        editor.putInt(key, value)
        editor.commit()
    }

    fun loadInt(key: String): Int {
        val sharedPreferences = CONTEXT.getSharedPreferences("Pr", ComponentActivity.MODE_PRIVATE)
        return sharedPreferences.getInt(key, 0)
    }
    fun setLocale(language: String?) {

        if (Build.VERSION.SDK_INT >= TIRAMISU) {
            CONTEXT.getSystemService(LocaleManager::class.java).applicationLocales =
                LocaleList.forLanguageTags(language)
        }
//        else {
//            val appLocale: LocaleListCompat =
//                LocaleListCompat.forLanguageTags(language)
//            AppCompatDelegate.setApplicationLocales(appLocale)
//
//        }


    }




}





