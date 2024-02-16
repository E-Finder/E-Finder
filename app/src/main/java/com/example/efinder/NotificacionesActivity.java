package com.example.efinder;
import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class NotificacionesActivity extends AppCompatActivity {
    private static final int PERMISSIONS_REQUEST_SMS = 123;
    private static final String PREFS_NAME_C = "CorreoPrefs";
    private static final String KEY_BLOQUEAR_CORREOS = "bloquearCorreos";
    private static final String TAG = "NotificacionesActivity";
    private static final String PREFS_NAME_N = "NotificacionesPrefs";
    private static final String KEY_RECIBIR_NOTIFICACIONES_PUSH = "recibirNotificacionesPush";
    private SharedPreferences sharedPreferences;
    private Switch switchSMS,switchBloquearCorreos, switchNotificacionesPush;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificaciones);
       // Toolbar toolbar =  (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        switchBloquearCorreos = findViewById(R.id.switchBloquearCorreos);
        switchSMS = findViewById(R.id.switchSMS);
        switchNotificacionesPush = findViewById(R.id.switchNotificacionesPush);
        sharedPreferences = getSharedPreferences(PREFS_NAME_C, Context.MODE_PRIVATE);
        sharedPreferences = getSharedPreferences(PREFS_NAME_N, Context.MODE_PRIVATE);
        switchBloquearCorreos.setChecked(obtenerEstadoBloqueoCorreos());
        switchNotificacionesPush.setChecked(obtenerEstadoNotificacionesPush());
        switchSMS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    solicitarPermisosSMS();
                } else {
                    Toast.makeText(NotificacionesActivity.this, "Has optado por no recibir SMS", Toast.LENGTH_SHORT).show();
                }
            }
        });
        switchBloquearCorreos.setChecked(obtenerEstadoBloqueoCorreos());
        switchBloquearCorreos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dejarDeRecibirCorreos();
                } else {
                    habilitarRecepcionCorreos();
                }
            }
        });
        switchNotificacionesPush.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Aquí puedes agregar la lógica para suscribirse a las notificaciones push
                    Log.d(TAG, "Recibiendo notificaciones push...");
                    suscribirseNotificacionesPush();
                } else {
                    // Aquí puedes agregar la lógica para dejar de recibir notificaciones push
                    Log.d(TAG, "Dejando de recibir notificaciones push...");
                    dejarDeRecibirNotificacionesPush();
                }
            }
        });
    }


    private void solicitarPermisosSMS() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) !=
                    PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS},
                        PERMISSIONS_REQUEST_SMS);
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_REQUEST_SMS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permisos para recibir SMS concedidos", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permisos para recibir SMS denegados", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void dejarDeRecibirCorreos() {
        Log.d(TAG, "Bloqueando correos electrónicos...");

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_BLOQUEAR_CORREOS, true);
        editor.apply();
    }

    private void habilitarRecepcionCorreos() {
        Log.d(TAG, "Habilitando la recepción de correos electrónicos...");

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_BLOQUEAR_CORREOS, false);
        editor.apply();
    }
    private boolean obtenerEstadoBloqueoCorreos() {
        return sharedPreferences.getBoolean(KEY_BLOQUEAR_CORREOS, false);
    }
    private void suscribirseNotificacionesPush() {
        // Aquí puedes agregar la lógica para suscribirse a las notificaciones push
        // (por ejemplo, suscribirse a un tema específico en FCM)
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_RECIBIR_NOTIFICACIONES_PUSH, true);
        editor.apply();
    }

    private void dejarDeRecibirNotificacionesPush() {
        // Aquí puedes agregar la lógica para dejar de recibir notificaciones push
        // (por ejemplo, dejar de suscribirse a un tema específico en FCM)
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_RECIBIR_NOTIFICACIONES_PUSH, false);
        editor.apply();
    }

    private boolean obtenerEstadoNotificacionesPush() {
        return sharedPreferences.getBoolean(KEY_RECIBIR_NOTIFICACIONES_PUSH, false);
    }
}

