package com.example.proyecto_movil;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText et_usuario,et_contraseña;
    private Button btn_login,btn_registar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        et_usuario =(EditText) findViewById(R.id.etUser);
        et_contraseña =(EditText) findViewById(R.id.etPassword);
        btn_login =(Button) findViewById(R.id.btnLogin);
        btn_registar =(Button) findViewById(R.id.btnRegister);


        OnClickListener();

    }

    public void inicio_sesion() {
        AdminSQL adminSQL = new AdminSQL(this, "Adminbd", null, 1);
        SQLiteDatabase baseDatos = adminSQL.getReadableDatabase();
        String nombre = et_usuario.getText().toString().trim();
        String contrasena = et_contraseña.getText().toString().trim();

        if (nombre.isEmpty() || contrasena.isEmpty()) {
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }
            Cursor c = null;
            try {
                c = baseDatos.rawQuery(
                        "SELECT contrasena FROM Login WHERE nombre = ?",
                        new String[]{nombre}
                );

                if (!c.moveToFirst()) {
                    Toast.makeText(this, "El usuario no existe", Toast.LENGTH_SHORT).show();
                    return;

                }
                    String contrasenaGuardada = c.getString(0);
                    boolean coincide = contrasena.equals(contrasenaGuardada);

                    if (coincide) {
                        et_usuario.setText("");
                        et_contraseña.setText("");
                        Toast.makeText(this, "Inicio de Sesión Exitoso", Toast.LENGTH_SHORT).show();
                        Siguiente(); // navega de inmediato
                    } else {
                        Toast.makeText(this, "Contraseña Incorrecta", Toast.LENGTH_SHORT).show();
                    }
                }finally {
                if (c != null) c.close();
                baseDatos.close();
                }
    }



    public void OnClickListener(){
        btn_registar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, appRegistro.class);
                startActivity(intent);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inicio_sesion();
            }
        });

    }
    public void Siguiente(){
        Intent intent = new Intent(MainActivity.this, Calculadora_IMC.class);
        startActivity(intent);
    }
}