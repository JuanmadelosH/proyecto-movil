package com.example.proyecto_movil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class appRecomendacion extends AppCompatActivity {

    private Button btn_alimentacion,btn_ejercicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_app_recomendacion);
        btn_alimentacion =(Button) findViewById(R.id.btnAlimentacion);
        btn_ejercicio=(Button) findViewById(R.id.btnEjercicio);

        OnClickListener();

    }
    public void OnClickListener(){
        btn_alimentacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(appRecomendacion.this, appAlimentacion.class);
                startActivity(intent);
            }
        });
        btn_ejercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(appRecomendacion.this, appEjercicio.class);
                startActivity(intent);
            }
        });
    }
}