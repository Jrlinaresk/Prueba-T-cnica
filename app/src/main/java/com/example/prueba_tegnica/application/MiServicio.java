package com.example.prueba_tegnica.application;
import static com.example.prueba_tegnica.tools.RandomStatusGenerator.generarEstadoAleatorio;
import static com.example.prueba_tegnica.utils.NotificacionPush.mostrarNotificacionLocal;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MiServicio extends Service {
    private static final String ACCION_NOTIFICACION_PUSH = "com.example.prueba_tegnica.ACCION_NOTIFICACION_PUSH";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && ACCION_NOTIFICACION_PUSH.equals(intent.getAction())) {
            // Lógica para mostrar la notificación push
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    mostrarNotificacionLocal(getApplicationContext());
                }
            } catch (Exception e) {
                // Manejar el error aquí, puedes mostrar un Toast o registrar el error
                e.printStackTrace();
                Toast.makeText(this, "Error al mostrar notificación", Toast.LENGTH_SHORT).show();
            }
        }
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

