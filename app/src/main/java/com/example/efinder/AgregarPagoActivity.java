package com.example.efinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;

public class AgregarPagoActivity extends AppCompatActivity {


    private static final String clientID = "AZzHef1WIs-0toT3qDcQps_qcDiZqqCRayO6bnxDbofDzCYpe461sRM_EsDaIkcxLkjKmlu2vz9Zcfog";

    private EditText amountEdt;

    private Button payBtn;

    public static final int PAYPAL_REQUEST_CODE = 123;

public static PayPalConfiguration configuration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_pago_activity);
        amountEdt = findViewById(R.id.idEdtAmount);
        payBtn = findViewById(R.id.idBtnMakePayment);
configuration = new PayPalConfiguration().environment(PayPalConfiguration.ENVIRONMENT_SANDBOX).clientId(clientID);


    }
    public  void onBackButtonClick(View view){
        Intent intent = new Intent(this,PagoActivity.class);
        startActivity(intent);

    }

    public void onClickHacerPago(View view) {
        getPayment();
    }

    private void getPayment() {
        String cantidades = amountEdt.getText().toString();
        PayPalPayment pago = new PayPalPayment(new BigDecimal(String.valueOf(cantidades)),"USD","Code with arvind",PayPalPayment.PAYMENT_INTENT_SALE);


        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, configuration);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT,pago);
        startActivityForResult(intent,PAYPAL_REQUEST_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PAYPAL_REQUEST_CODE){
            PaymentConfirmation paymentConfirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
            if(paymentConfirmation != null){

                try {
                    String detallesPago = paymentConfirmation.toJSONObject().toString();
                    JSONObject objeto = new JSONObject(detallesPago);
                } catch (JSONException e) {
                    Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }else if(requestCode == Activity.RESULT_CANCELED){
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();

            }
        } else if (requestCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
            Toast.makeText(this, "pago inválido", Toast.LENGTH_SHORT).show();
            
        }
    }

}