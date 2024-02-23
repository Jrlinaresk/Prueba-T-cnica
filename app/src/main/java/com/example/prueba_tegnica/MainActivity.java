package com.example.prueba_tegnica;

import static com.example.prueba_tegnica.utils.NotificacionPush.mostrarNotificacionLocal;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_NOTIFICATIONS_PERMISSION = 1;
    String[] permissions = new String[]{
            Manifest.permission.POST_NOTIFICATIONS
    };

    boolean permission_post_notification = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnEnviarRespuesta = findViewById(R.id.btnEnviarRespuesta);
        btnEnviarRespuesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Verifica si ya se tienen los permisos necesarios
                if (!permission_post_notification) {
                    // Si no se tienen, solicita los permisos
                    requestPermissionNotification();
                } else {
                    // Si ya se tienen, realiza la acción correspondiente
                    mostrarNotificacionLocal(MainActivity.this);
                }
            }
        });
    }

    // Método para solicitar permisos de notificación
    private void requestPermissionNotification() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), permissions[0]) == PackageManager.PERMISSION_GRANTED) {
            permission_post_notification = true;
            Toast.makeText(getApplicationContext(), "Notificaciones permitidas", Toast.LENGTH_SHORT).show();
        } else {
            // Si se necesita mostrar una explicación al usuario
            if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                Log.d("Permission", "Inside else first time don't allow");
            } else {
                // Lanza la solicitud de permisos
                requestPermissionLauncherNotification.launch(permissions[0]);
            }
        }
    }

    // Resultado de la solicitud de permisos de notificación
    private ActivityResultLauncher<String> requestPermissionLauncherNotification = registerForActivityResult(
            new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    permission_post_notification = true;
                } else {
                    permission_post_notification = false;
                    showPermissionDialog("Notification Permission");
                }
            });

    // Método para mostrar un diálogo cuando se deniegan los permisos
    private void showPermissionDialog(String permissionDesc) {
        new AlertDialog.Builder(getApplicationContext())
                .setTitle("Alert for Permission")
                .setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    // Método para realizar acciones que requieran permisos
    @NeedsPermission(Manifest.permission.POST_NOTIFICATIONS)
    public void setupEvents() {
        // Coloca aquí la lógica que requiere permisos
    }

    // Método para mostrar una explicación al usuario sobre por qué se necesitan los permisos
    @OnShowRationale(Manifest.permission.POST_NOTIFICATIONS)
    void showRationaleForNOTIFICATIONS(final PermissionRequest request) {
        new AlertDialog.Builder(getApplicationContext())
                .setTitle(R.string.permission_rationale_title)
                .setMessage(R.string.permission_rationale_message)
                .show();
    }

    // Método llamado después de que el usuario responde a la solicitud de permisos
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_NOTIFICATIONS_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permiso concedido, realiza las acciones que requieren permisos
                setupEvents();
            } else {
                // Permiso denegado, maneja el caso según tu aplicación
            }
        }
    }
}
