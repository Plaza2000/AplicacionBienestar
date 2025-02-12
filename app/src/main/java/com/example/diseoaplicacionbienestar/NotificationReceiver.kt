package com.example.diseoaplicacionbienestar

import android.Manifest
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

class NotificacionReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null) return

        // Verificar permiso antes de enviar la notificación
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {

            // Intent para abrir la app cuando el usuario toque la notificación
            val intentAbrirApp = Intent(context, MoodTracker::class.java)
            val pendingIntent = PendingIntent.getActivity(
                context,
                0,
                intentAbrirApp,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            val notification = NotificationCompat.Builder(context, "mood_tracker_channel")
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("Registra tu estado de ánimo")
                .setContentText("Tómate un momento para registrar cómo te sientes hoy 😊")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build()

            val manager = NotificationManagerCompat.from(context)
            manager.notify(1, notification)
        }
    }
}
