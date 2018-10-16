package com.training.tinhla.training.base.service

import android.app.PendingIntent
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.support.v4.app.TaskStackBuilder
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.training.tinhla.training.R
import com.training.tinhla.training.base.model.constant.CONSTANT.Companion.EXTRA_ACTIVITY
import com.training.tinhla.training.splashscreen.SplashActivity

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(s: String?) {
        super.onNewToken(s)
        Log.d("LOG", "token: " + s)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {

        val intent = Intent(this, SplashActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            retreiveDataForIntent(remoteMessage?.toIntent(), this)
        }

        val pendingIntent:PendingIntent? = TaskStackBuilder.create(this).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val builder = NotificationCompat.Builder(this, "").apply {
            setContentTitle("HANH NOTIFICATION")
            setContentText(remoteMessage?.notification?.body)
            setAutoCancel(false)
            setSmallIcon(R.mipmap.ic_launcher)
            setContentIntent(pendingIntent)
        }
        with(NotificationManagerCompat.from(this)) {
            notify(0, builder.build())
        }
    }

    private fun retreiveDataForIntent(remoteIntent: Intent?, intent: Intent) {
        if (remoteIntent != null && remoteIntent.extras != null) {
            val extras = remoteIntent.extras
            if (extras.containsKey(EXTRA_ACTIVITY)) {
                val activityID = extras.getString(EXTRA_ACTIVITY)
                intent.putExtra(EXTRA_ACTIVITY, activityID)
            }
        }
    }
}