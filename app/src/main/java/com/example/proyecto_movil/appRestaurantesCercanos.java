package com.example.proyecto_movil;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.maps.MapView;

public class appRestaurantesCercanos extends AppCompatActivity {
    MapView MVRestaurantes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_restaurantes_cercanos);
        inicializarComponentes();

    }

    public void inicializarComponentes(){
        MVRestaurantes = findViewById(R.id.MVRestaurantes);
    }

}