package com.pr7.jc_yataxi_prv1.utils

import android.app.Application
import com.pr7.jc_yataxi_prv1.data.pref.LANGUAGE_RU
import com.pr7.jc_yataxi_prv1.data.pref.SharefPrefManager

class MyApp constructor():Application() {

    override fun onCreate() {
        super.onCreate()
        CONTEXT=this
       // SharefPrefManager.setLocale(LANGUAGE_RU)
    }
}