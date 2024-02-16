package com.example.efinder;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
public class TemaActivity extends AppCompatActivity {
    private Switch switchModoOscuro,switchZoomPantalla,switchBrilloAutomatico,switchProtectorVista;
    private Button botonGuardar;
    private Spinner spinnerModoPantalla;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tema);
        switchModoOscuro = findViewById(R.id.switchTemaOscuro);
        switchBrilloAutomatico = findViewById(R.id.switchBrilloAutomatico);
        switchProtectorVista = findViewById(R.id.switchProtectorVista);
        switchZoomPantalla = findViewById(R.id.switchZoomPantalla);
        spinnerModoPantalla = findViewById(R.id.spinnerModoPantalla);
        botonGuardar = findViewById(R.id.btnGuardar);
        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarConfiguraciones();
            }
        });
        switchModoOscuro.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    aplicarModoOscuro();
                } else {
                    desactivarModoOscuro();
                }
            }
        });
        switchBrilloAutomatico.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    activarBrilloAutomatico(true);
                } else {
                    activarBrilloAutomatico(false);
                }
            }
        });
        switchProtectorVista.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    activarProtectorVista();
                } else {
                    desactivarProtectorVista();
                }
            }
        });
        switchZoomPantalla.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    activarZoomPantalla();
                } else {
                    desactivarZoomPantalla();
                }
            }
        });
        spinnerModoPantalla.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String modoSeleccionado = (String) parentView.getItemAtPosition(position);
                switch (modoSeleccionado) {
                    case "Modo A":
                        mostrarMensaje("Modo NIGHT seleccionado");
                        break;
                    case "Modo B":
                        mostrarMensaje("Modo DAY seleccionado");
                        break;
                    default:
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }
    private void mostrarMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
    private void desactivarModoOscuro() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        mostrarMensaje("Modo Oscuro Desactivado");
    }
    private void aplicarModoOscuro() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        mostrarMensaje("Modo Oscuro Activado");
    }
    private void activarBrilloAutomatico() {
        try {
            int brilloActual = Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
            Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
            mostrarMensaje("Brillo Automático Activado");
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void desactivarBrilloAutomatico() {
        try {
            Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
            mostrarMensaje("Brillo Automático Desactivado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void activarProtectorVista() {
        try {
            Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);

            mostrarMensaje("Protector de Vista Activado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void desactivarProtectorVista() {
        try {
            Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);

            mostrarMensaje("Protector de Vista Desactivado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void activarZoomPantalla() {
        try {
            Settings.System.putInt(getContentResolver(), Settings.System.FONT_SCALE, 1);

            mostrarMensaje("Zoom de Pantalla Activado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void desactivarZoomPantalla() {
        try {
            Settings.System.putInt(getContentResolver(), Settings.System.FONT_SCALE, 0);

            mostrarMensaje("Zoom de Pantalla Desactivado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void aplicarModoA() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        mostrarMensaje("Modo A seleccionado");
    }
    private void aplicarModoB() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        mostrarMensaje("Modo B seleccionado");
    }
    private void guardarConfiguraciones() {
        boolean modoOscuro = switchModoOscuro.isChecked();
        boolean brilloAutomatico = switchBrilloAutomatico.isChecked();
        boolean protectorVista = switchProtectorVista.isChecked();
        boolean zoomPantalla = switchZoomPantalla.isChecked();
        String modoPantalla = (String) spinnerModoPantalla.getSelectedItem();
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("modoOscuro", modoOscuro);
        editor.putBoolean("brilloAutomatico", brilloAutomatico);
        editor.putBoolean("protectorVista", protectorVista);
        editor.putBoolean("zoomPantalla", zoomPantalla);
        editor.putString("modoPantalla", modoPantalla);
        editor.apply();
        mostrarMensaje("Configuraciones guardadas");
    }
    private void activarBrilloAutomatico(boolean activar) {
        try {
            int modoBrilloActual = Settings.System.getInt(
                    getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS_MODE
            );

            Log.d("Brillo", "Modo de brillo actual: " + modoBrilloActual);

            Settings.System.putInt(
                    getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS_MODE,
                    activar ? Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC : Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL
            );

            Log.d("Brillo", "Modo de brillo cambiado a: " + (activar ? "Automático" : "Manual"));
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
    }
}






