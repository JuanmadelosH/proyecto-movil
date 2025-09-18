package com.example.proyecto_movil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Calculadora_IMC extends AppCompatActivity {

    private EditText et_altura,et_peso;
    private TextView recomendacion;
    private Button Info;
    private String imcForm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculadora_imc);

        et_altura=(EditText) findViewById(R.id.etHeight);
        et_peso=(EditText) findViewById(R.id.etWeight);
        recomendacion =(TextView) findViewById(R.id.tvRecommendation);
        Info=(Button) findViewById(R.id.btnMoreInfo);

        Info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                masDetalle();
            }
        });



    }
    public void Calcular(View view){
        double altura = Double.parseDouble(et_altura.getText().toString());
        double peso = Double.parseDouble(et_peso.getText().toString());
        double imc =peso/(altura*altura);

        imcForm = String.format("%.2f",imc);
        if(imc < 18.5){
            recomendacion.setText(imcForm +" Tienes un Peso Bajo");
        } else if (imc>=18.5 && imc < 24.9) {
            recomendacion.setText(imcForm +" Tienes un Peso Normal");
        } else if (imc >= 24.9 && imc < 29.9) {
            recomendacion.setText(imcForm +" Tienes Sobrepeso");
        }else{
            recomendacion.setText(imcForm +" Tienes Obesidad");
        }

    }
    public void masDetalle(){
        Intent intent = new Intent(Calculadora_IMC.this, appRecomendacion.class);
        intent.putExtra("imc",imcForm);
        startActivity(intent);

    }
}