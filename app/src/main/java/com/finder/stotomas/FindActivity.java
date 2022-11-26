package com.finder.stotomas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.Toast;

public class FindActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor proximitySensor;
    private SensorEventListener proximitySensorListener;
    Button boton, botonDos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);

        sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
        boton = findViewById(R.id.btnUno);
        botonDos = findViewById(R.id.btnDos);

        boton.setOnClickListener(view -> {
            Intent e = new Intent(this, AnitaActivity.class);
            startActivity(e);
        });

        botonDos.setOnClickListener(view -> {
            Intent e = new Intent(this, LuisaActivity.class);
            startActivity(e);
        });

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        if(proximitySensor == null) {
            Log.e("TAG", "El sensor de proximidad no está disponible.");
            finish();
        }
        proximitySensorListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if(sensorEvent.values[0] >= proximitySensor.getMaximumRange()) {
                    String mensaje = "Hey, cuidado con presionar tan rápido y cerca, hay un sensor vigilando";
                    Toast toast = Toast.makeText(FindActivity.this, mensaje, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.START, 90, 0);
                    toast.show();
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };


    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(proximitySensorListener, proximitySensor, 2 * 1000 * 1000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(proximitySensorListener);
    }





}