package com.finder.stotomas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;

public class LuisaActivity extends AppCompatActivity {

    //usamos el mapbox
    MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luisa);
            mapView = findViewById(R.id.mapView);
            mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS);

        }
    }