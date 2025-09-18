package com.example.proyecto_movil;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class appAlimentacion extends AppCompatActivity {
    ScrollView SVPlatos;
    LinearLayout container;
    Button BTN_RestCercanos;
    ArrayList<String> bajoPeso = new ArrayList<String>();
    ArrayList<String> normal = new ArrayList<String>();
    ArrayList<String> sobrepeso = new ArrayList<String>();
    ArrayList<String> obesidad = new ArrayList<String>();
    double imc = 20.1;
    String URL = "www.themealdb.com/api/json/v1/1/filter.php?c=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_alimentacion);
        inicializarComponentes();

        quePeticiones();
    }

    public void inicializarComponentes(){
        SVPlatos = findViewById(R.id.SVPlatos);
        container = findViewById(R.id.container);
        BTN_RestCercanos = findViewById(R.id.BTN_RestCercanos);


        bajoPeso.add("Beef");
        bajoPeso.add("Pork");
        bajoPeso.add("Lamb");
        bajoPeso.add("Pasta");
        bajoPeso.add("Breakfast");


        normal.add("Chicken");
        normal.add("Seafood");
        normal.add("Vegetarian");
        normal.add("Breakfast");
        normal.add("Side");
        normal.add("Starter");


        sobrepeso.add("Chicken");
        sobrepeso.add("Seafood");
        sobrepeso.add("Vegetarian");
        sobrepeso.add("Vegan");
        sobrepeso.add("Side");


        obesidad.add("Vegetarian");
        obesidad.add("Vegan");
        obesidad.add("Seafood");
        obesidad.add("Side");
        obesidad.add("Miscellaneous");
    }

    public void quePeticiones(){
        // se realizan las peticiones necesarias
        if(imc > 0 && imc < 18.5){
            for(int i = 0; i < bajoPeso.size(); i++){
                peticionAPI(bajoPeso.get(i));
            }
        }else if(imc >= 18.5 && imc < 24.9){
            for(int i = 0; i < normal.size(); i++){
                peticionAPI(normal.get(i));
            }
        }else if(imc >= 24.9 && imc < 29.9){
            for(int i = 0; i < sobrepeso.size(); i++){
                peticionAPI(sobrepeso.get(i));
            }
        }else {
            for(int i = 0; i < obesidad.size(); i++){
                peticionAPI(obesidad.get(i));
            }
        }
    }

    public void peticionAPI(String categoria){
        // se realiza la peticion a la API y se ponen en el scrollView
        String url =URL+categoria;

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONArray mealsArray = response.getJSONArray("meals");
                        for (int i = 0; i < mealsArray.length(); i++) {
                            JSONObject mealObj = mealsArray.getJSONObject(i);

                            // Crear vista simple para cada plato
                            LinearLayout itemLayout = new LinearLayout(this);
                            itemLayout.setOrientation(LinearLayout.HORIZONTAL);
                            itemLayout.setPadding(10,10,10,10);

                            ImageView imageView = new ImageView(this);
                            imageView.setLayoutParams(new LinearLayout.LayoutParams(200,200));

                            TextView textView = new TextView(this);
                            textView.setText(mealObj.getString("strMeal"));
                            textView.setTextSize(18);
                            textView.setPadding(20,0,0,0);

                            // Cargar imagen con Glide
                            Glide.with(this)
                                    .load(mealObj.getString("strMealThumb"))
                                    .into(imageView);

                            itemLayout.addView(imageView);
                            itemLayout.addView(textView);

                            container.addView(itemLayout);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Toast.makeText(this, "Error al cargar datos", Toast.LENGTH_SHORT).show()
        );

        queue.add(request);
    }

}