package com.pr7.jc_biztaxi_v4.utils

import android.app.Application

class MyApp constructor():Application(){

    override fun onCreate() {
        super.onCreate()
        CONTEXT=this
       // SharefPrefManager.setLocale(LANGUAGE_RU)
    }
}