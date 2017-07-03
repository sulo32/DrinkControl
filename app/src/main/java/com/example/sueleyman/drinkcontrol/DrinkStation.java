package com.example.sueleyman.drinkcontrol;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DrinkStation extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String TAG = DrinkStation.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_station);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ClickOnTheButton();
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(48.481985, 9.186799);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Bibliothek Hochschule Reutlingen"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,15));
    }


    public void ClickOnTheButton() {
        Button verbrauch= (Button) findViewById(R.id.verbrauch);
        Button info = (Button) findViewById(R.id.info);
        Button week = (Button) findViewById(R.id.week);
        Button settings = (Button) findViewById(R.id.settings);


        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "habe einstellungen geklickt");
                Intent intent1 = new Intent(DrinkStation.this, InfoActivity.class);
                startActivity(intent1);
            }
        });
        verbrauch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "habe main geklickt");
                Intent intent2 = new Intent(DrinkStation.this, MainActivity.class);
                startActivity(intent2);
            }
        });
        week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"habe week geklickt");
                Intent intent3 = new Intent(DrinkStation.this, WeekActivity.class);
                startActivity(intent3);
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "habe settings geklickt");
                Intent intent4 = new Intent(DrinkStation.this, SettingsActivity.class);
                startActivity(intent4);
            }
        });
    }
}
