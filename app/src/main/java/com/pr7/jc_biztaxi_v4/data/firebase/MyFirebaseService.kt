package com.pr7.jc_biztaxi_v4.data.firebase

import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.GsonBuilder
import com.pr7.jc_biztaxi_v4.R
import com.pr7.jc_biztaxi_v4.data.model.notificationfirebase.FirebaseNotifyResponse
import com.pr7.jc_biztaxi_v4.data.model.notificationfirebase.FirebaseNotifyResponseForClient
import com.pr7.jc_biztaxi_v4.data.pref.DRIVER
import com.pr7.jc_biztaxi_v4.data.pref.DataStoreManager
import com.pr7.jc_biztaxi_v4.data.pref.NOTIFICATION_MESSAGE
import com.pr7.jc_biztaxi_v4.data.pref.NOTIFICATION_ORDER
import com.pr7.jc_biztaxi_v4.data.pref.NOTIFICATION_SEAT_ID
import com.pr7.jc_biztaxi_v4.data.pref.SharefPrefManager
import com.pr7.jc_biztaxi_v4.data.pref.USERTYPE
import com.pr7.jc_biztaxi_v4.ui.home.HomeActivity
import com.pr7.jc_biztaxi_v4.utils.CONTEXT
import com.pr7.jc_biztaxi_v4.utils.showlogd

class MyFirebaseService(
    // private val context: Context
) : FirebaseMessagingService() {
    var dataStoreManager: DataStoreManager=DataStoreManager(CONTEXT)

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        showlogd(funname = "Firebase SERVICE", text = token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        simplenotification(message)
        showlogd(funname = "Firebase NOTIFICATION MESSAGE",message.notification?.title.toString())
    }

    fun simplenotification(message: RemoteMessage){

        val intent = Intent(this, HomeActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        var messagetitle=""
        if (SharefPrefManager.loadString(USERTYPE).toString()== DRIVER){
            try {
                val gson = GsonBuilder().create()
                val gsonparse: FirebaseNotifyResponse = gson.fromJson(message.notification?.body.toString(), FirebaseNotifyResponse::class.java)
                messagetitle="${gsonparse.from_direction} \n${gsonparse.to_direction}\nПассажиры: ${gsonparse.clients.size}"
                showlogd(funname = "json parce: -> ",messagetitle)
                SharefPrefManager.saveString(NOTIFICATION_MESSAGE,messagetitle)
                SharefPrefManager.saveString(NOTIFICATION_SEAT_ID,gsonparse.seatorder_id.toString())
                SharefPrefManager.saveBoolean(NOTIFICATION_ORDER,true)

                SharefPrefManager.saveBoolean(NOTIFICATION_ORDER,true)
                val intenthome = Intent(this, HomeActivity::class.java)
                CONTEXT.startActivity(intent)

            }catch (e:Exception){}
        }else{
            try {
                val gson = GsonBuilder().create()
                val gsonparse: FirebaseNotifyResponseForClient = gson.fromJson(message.notification?.body.toString(), FirebaseNotifyResponseForClient::class.java)
                messagetitle="${gsonparse.from_direction} \n${gsonparse.to_direction}\nСтатус: ${gsonparse.status}"
                showlogd(funname = "json parce: -> ",messagetitle)
                SharefPrefManager.saveString(NOTIFICATION_MESSAGE,messagetitle)
                //SharefPrefManager.saveString(NOTIFICATION_SEAT_ID,gsonparse.seatorder_id.toString())
                SharefPrefManager.saveBoolean(NOTIFICATION_ORDER,true)

                SharefPrefManager.saveBoolean(NOTIFICATION_ORDER,true)
                val intenthome = Intent(this, HomeActivity::class.java)
                CONTEXT.startActivity(intent)

            }catch (e:Exception){}
        }


        val builder = NotificationCompat.Builder(this, "CHANNEL_ID")
            .setSmallIcon(R.drawable.logo)
            .setContentTitle(message.notification?.title)
            //.setContentText("$messagetitle")
            .setStyle(NotificationCompat.BigTextStyle().bigText(if (messagetitle.isEmpty()) message.notification?.body else messagetitle))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            // Set the intent that will fire when the user taps the notification
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        val notificationManagerCompat = NotificationManagerCompat.from(this)
        if (ActivityCompat.checkSelfPermission(
                this,
               android.Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) { return }
        notificationManagerCompat.notify(0, builder)
    }

}