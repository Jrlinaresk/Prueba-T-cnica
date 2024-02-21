package com.example.prueba_tegnica.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.example.prueba_tegnica.R;
import com.example.prueba_tegnica.tools.RandomStatusGenerator;

public class NotificacionPush {
    private static final int NOTIFICATION_ID = 1;
    private static final String CHANNEL_ID = "prueba_tegnica";
    private static final String CHANNEL_NAME = "Canal de Notificación";
    private static final int CHANNEL_IMPORTANCE = NotificationManager.IMPORTANCE_HIGH;
    private static final int CHANNEL_LIGHT_COLOR = Color.BLUE;
    private static final int CHANNEL_VISIBILITY = Notification.VISIBILITY_PUBLIC;

    public static void mostrarNotificacionLocal(Context context) {
        try {
            // Obtener el servicio de notificación
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            // Crear un canal de notificación (necesario para versiones de Android >= Oreo)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                buildChannel(notificationManager);
            }

            // Configurar la notificación
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                    .setSmallIcon(RandomStatusGenerator.generarEstadoAleatorio(context).getImagenResId())
                    .setContentTitle("Respuesta")
                    .setContentText(RandomStatusGenerator.generarEstadoAleatorio(context).getEstado())
                    .setPriority(NotificationCompat.PRIORITY_HIGH);

            // Mostrar la notificación
            notificationManager.notify(NOTIFICATION_ID, builder.build());
        } catch (Exception e) {
            // Manejar el error aquí, puedes mostrar un Toast o registrar el error
            e.printStackTrace();
            // Por ejemplo, mostrar un Toast
            // Toast.makeText(context, "Error al mostrar notificación", Toast.LENGTH_SHORT).show();
        }
    }

    private static void buildChannel(NotificationManager manager) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel chan = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, CHANNEL_IMPORTANCE);
                chan.setLightColor(CHANNEL_LIGHT_COLOR);
                chan.setImportance(CHANNEL_IMPORTANCE);
                chan.setLockscreenVisibility(CHANNEL_VISIBILITY);
                manager.createNotificationChannel(chan);
            }
        } catch (Exception e) {
            // Manejar el error aquí, puedes mostrar un Toast o registrar el error
            e.printStackTrace();
            // Por ejemplo, mostrar un Toast
            // Toast.makeText(context, "Error al crear canal de notificación", Toast.LENGTH_SHORT).show();
        }
    }
}
