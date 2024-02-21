package com.example.prueba_tegnica;

import static com.example.prueba_tegnica.utils.NotificacionPush.mostrarNotificacionLocal;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            Button btnEnviarRespuesta = findViewById(R.id.btnEnviarRespuesta);
            btnEnviarRespuesta.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onClick(View v) {
                    mostrarNotificacionLocal(MainActivity.this);
                }
            });
        } catch (Exception e) {
            // Manejo de errores: puedes imprimir el error o mostrar un mensaje al usuario
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "Error en la aplicación", Toast.LENGTH_SHORT).show();
        }
    }

}