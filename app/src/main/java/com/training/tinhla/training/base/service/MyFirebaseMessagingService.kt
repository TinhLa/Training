package com.training.tinhla.training.base.service

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.training.tinhla.training.R
import com.training.tinhla.training.splashscreen.SplashActivity

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        val intent = Intent(this, SplashActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)
        val notiCompatBuilder = NotificationCompat.Builder(this, "")
        notiCompatBuilder.setContentTitle("FCM NOTIFICATION")
        notiCompatBuilder.setContentText(remoteMessage?.notification?.body)
        notiCompatBuilder.setAutoCancel(false)
        notiCompatBuilder.setSmallIcon(R.mipmap.ic_launcher)
        notiCompatBuilder.setContentIntent(pendingIntent)

        val notiManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notiManager.notify(0, notiCompatBuilder.build())
    }
}