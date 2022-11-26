package com.finder.stotomas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    TextView text;
    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        text= findViewById(R.id.tv_register);
        boton= findViewById(R.id.btnlogin);

        //hacemos uso del setOnclick
        boton.setOnClickListener(view -> {
            Intent intent = new Intent(Login.this,MainActivity.class);
            startActivity(intent);

        });

        text.setOnClickListener(view -> {
            Intent i = new Intent(Login.this, Registrar.class);
            startActivity(i);
        });
    }

}