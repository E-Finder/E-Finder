package com.example.efinder;
import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import com.paypal.android.sdk.payments.PayPalConfiguration;
public class PagoActivity extends AppCompatActivity {
    // Configuración de PayPal
    private static final String CONFIG_ENVIRONMENT = PayPalConfiguration.ENVIRONMENT_SANDBOX; // Cambiar a ENVIRONMENT_PRODUCTION en producción
    private static final String CONFIG_CLIENT_ID = "tu_client_id_de_paypal"; // Reemplazar con tu propio Client ID de PayPal
    private Button btnAgregarPago, btnEditarPago, btnCancelarPago;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago);
       // Toolbar toolbar =  (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
    }
    public void onClickRegistrar(View view) {

        Intent x = new Intent(this, AgregarPagoActivity.class);
        startActivity(x);
    }
public void onClickCancelar(View view){
    finish();
}

    public void onBackButtonClick(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }
}