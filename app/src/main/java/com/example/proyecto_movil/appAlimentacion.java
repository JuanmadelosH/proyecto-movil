package com.example.proyecto_movil;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class appAlimentacion extends AppCompatActivity {
    TextView TV_Alimentacion;
    Button BTN_RestCercanos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_alimentacion);
        inicializarComponentes();

    }

    public void inicializarComponentes(){
        TV_Alimentacion = findViewById(R.id.TV_RecomendacionAlimentacion);
        BTN_RestCercanos = findViewById(R.id.BTN_RestCercanos);
    }

    public void peticionAPI(){

    }

}