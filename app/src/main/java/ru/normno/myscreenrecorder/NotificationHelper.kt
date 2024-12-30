package ru.normno.myscreenrecorder

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.getSystemService

object NotificationHelper {

    private const val CHANEL_ID = "screen_recording_chanel"

    fun createNotification(context: Context): Notification {
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(context, CHANEL_ID)
            .setContentText("Screen recording")
            .setContentText("Recording in progress...")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .build()
    }

    fun createNotificationChanel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val serviceChanel = NotificationChannel(
                CHANEL_ID,
                "Screen Recording Chanel",
                NotificationManager.IMPORTANCE_DEFAULT,
            )
            val notificationManager = context.getSystemService<NotificationManager>()
            notificationManager?.createNotificationChannel(serviceChanel)
        }
    }
}