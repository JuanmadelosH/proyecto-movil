package com.example.proyecto_movil;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;



public class appRegistro extends AppCompatActivity {

    private EditText et_usuario,et_contraseña;
    private Button btn_crear,btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_app_registro);

        et_usuario =(EditText) findViewById(R.id.etRegUser);
        et_contraseña =(EditText) findViewById(R.id.etRegPassword);
        btn_crear =(Button) findViewById(R.id.btnCreateAccount);
        btn_login =(Button) findViewById(R.id.btnGoLogin);

        OnClickListener();


    }

    public void Registrar(){
        AdminSQL adminSQL = new AdminSQL(this,"Adminbd",null,1);
        SQLiteDatabase baseDatos = adminSQL.getWritableDatabase();
        String usuario = et_usuario.getText().toString().trim();
        String contrasena = et_contraseña.getText().toString().trim();
        if(!usuario.isEmpty() && !contrasena.isEmpty()){
            Cursor fila = baseDatos.rawQuery("SELECT nombre FROM Login WHERE nombre= ?",new String[]{usuario}
            );
            if(fila.moveToFirst()){
                Toast.makeText(this, "El usuario ya esta registrado. Intente con otro", Toast.LENGTH_SHORT).show();
                fila.close();
                baseDatos.close();
                return;
            }
            ContentValues registro = new ContentValues();
            registro.put("nombre",usuario);
            registro.put("contrasena",contrasena);

            baseDatos.insert("Login",null,registro);
            baseDatos.close();

            et_usuario.setText("");
            et_contraseña.setText("");

            Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_LONG).show();
        }
    }

    public void OnClickListener(){
        btn_crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Registrar();
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(appRegistro.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}