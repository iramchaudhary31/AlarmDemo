package com.example.lastalarm

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Vibrator
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.Switch
import android.widget.TimePicker
import androidx.core.app.NotificationCompat

class MyAlarm : BroadcastReceiver() {

    companion object {
        private const val MY_NOTIFICATION_ID = 1
    }
    var notificationManager: NotificationManager? = null
    var myNotification: Notification? = null


    override fun onReceive(context: Context, intent: Intent) {


        val mediaPlayer =
            MediaPlayer.create(context, Settings.System.DEFAULT_RINGTONE_URI)
        mediaPlayer.start()

        // to add viberation on ringtone
        val vibrator = context
            .getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(3000)

        // to add alarm notification
        val myIntent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, myIntent, 0)

        myNotification = NotificationCompat.Builder(context)
            .setContentTitle("Wake UP!!!")
            .setContentText("Alarm is belling")
            .setTicker("Notification!")
            .setWhen(System.currentTimeMillis())
            .setDefaults(Notification.FLAG_INSISTENT)
            .setAutoCancel(true)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .build()

        notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager!!.notify(MY_NOTIFICATION_ID, myNotification)
    }

    }

