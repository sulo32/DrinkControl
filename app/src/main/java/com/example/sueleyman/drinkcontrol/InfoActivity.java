package com.example.sueleyman.drinkcontrol;

import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class InfoActivity extends AppCompatActivity {

    String TAG = InfoActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setMovementMethod(new ScrollingMovementMethod());

        ClickOnTheButton();
    }


    public void ClickOnTheButton() {
        Button verbrauch= (Button) findViewById(R.id.verbrauch);
        Button settings = (Button) findViewById(R.id.settings);
        Button week = (Button) findViewById(R.id.week);
        Button station= (Button) findViewById(R.id.waterstation);


        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "habe einstellungen geklickt");
                Intent intent1 = new Intent(InfoActivity.this, SettingsActivity.class);
                startActivity(intent1);
            }
        });
        verbrauch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "habe main geklickt");
                Intent intent2 = new Intent(InfoActivity.this, MainActivity.class);
                startActivity(intent2);
            }
        });
        week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"habe week geklickt");
                Intent intent3 = new Intent(InfoActivity.this, WeekActivity.class);
                startActivity(intent3);
            }
        });
        station.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "habe station geklickt");
                Intent intent4 = new Intent(InfoActivity.this, DrinkStation.class);
                startActivity(intent4);
            }
        });
    }
}