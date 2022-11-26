package com.finder.stotomas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Registrar extends AppCompatActivity {

    private Spinner spinner_genero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        spinner_genero = findViewById(R.id.spinnerUno);
        String[] generos ={"Hombre", "Mujer"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, generos);
        spinner_genero.setAdapter(adapter);
    }
}