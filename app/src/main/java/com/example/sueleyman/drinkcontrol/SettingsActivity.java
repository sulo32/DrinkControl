package com.example.sueleyman.drinkcontrol;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Switch;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SettingsActivity extends AppCompatActivity {

    private Switch notification;
    private Boolean checkNoti = false;
    private CheckBox uhr12, uhr15, uhr19;
    private Boolean checked=false;
    private Boolean checked1 =false;
    private Boolean checked2 = false;

    private Context context;
    String TAG = SettingsActivity.class.getSimpleName();

    SimpleDateFormat zeitformat = new SimpleDateFormat("HH:mm");
    Calendar kalender = Calendar.getInstance();

    SharedPreferences pref;
    SharedPreferences.Editor prefEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

       // Intent intent = new Intent(this, MyIntentService.class);
       // startService(intent);

        pref= getSharedPreferences("mySettings",0);
        prefEditor= pref.edit();

        notification = (Switch) findViewById(R.id.notification);
        uhr12 = (CheckBox) findViewById(R.id.Uhr12);
        uhr15 = (CheckBox) findViewById(R.id.Uhr15);
        uhr19 = (CheckBox) findViewById(R.id.Uhr19);
        loadCheckboxData();
        notification.setChecked(pref.getBoolean("benachrichtigung",checkNoti));

        context= this;
        Log.d(TAG, "aktuelle Zeit:" + zeitformat.format(kalender.getTime()));

        ClickOnTheButton();
        notification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    prefEditor.putBoolean("benachrichtigung", true);
                    prefEditor.commit();

                    if (uhr12.isChecked()){
                        Notifi(context, "ich bin eine benachrichtigung um 12:00 Uhr");
                    }
                }
                else   prefEditor.putBoolean("benachrichtigung", false);
                       prefEditor.commit();
            }
        });
        uhr12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Log.d(TAG, "ich bin bei clickoncheckbox drin 12uhr");
                    prefEditor.putBoolean("uhr12", true);
                } else
                    prefEditor.putBoolean("uhr12", false);
                    prefEditor.commit();
            }
        });
        uhr15.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Log.d(TAG, "ich bin bei clickoncheckbox drin 15uhr");
                    prefEditor.putBoolean("uhr15", true);
                } else
                    prefEditor.putBoolean("uhr15", false);
                prefEditor.commit();
            }
        });
        uhr19.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Log.d(TAG, "ich bin bei clickoncheckbox drin 19uhr");
                    prefEditor.putBoolean("uhr19", true);
                } else
                    prefEditor.putBoolean("uhr19", false);
                prefEditor.commit();
            }
        });
    }

    public void loadCheckboxData(){
        checked= pref.getBoolean("uhr12",checked);
        checked1= pref.getBoolean("uhr15",checked1);
        checked2= pref.getBoolean("uhr19",checked2);

        uhr12.setChecked(checked);
        uhr15.setChecked(checked1);
        uhr19.setChecked(checked2);
    }

    public void Notifi(Context context, String Nachricht){
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.bottle)
                        .setContentTitle("DrinkControl")
                        .setContentText(Nachricht);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(666, mBuilder.build());
    }


    public void ClickOnTheButton() {
        Button verbrauch= (Button) findViewById(R.id.verbrauch);
        Button info = (Button) findViewById(R.id.info);
        Button week = (Button) findViewById(R.id.week);
        Button station= (Button) findViewById(R.id.waterstation);


        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "habe einstellungen geklickt");
                Intent intent1 = new Intent(SettingsActivity.this, InfoActivity.class);
                startActivity(intent1);
            }
        });
        verbrauch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "habe main geklickt");
                Intent intent2 = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(intent2);
            }
        });
        week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"habe week geklickt");
                Intent intent3 = new Intent(SettingsActivity.this, WeekActivity.class);
                startActivity(intent3);
            }
        });
        station.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "habe station geklickt");
                Intent intent4 = new Intent(SettingsActivity.this, DrinkStation.class);
                startActivity(intent4);
            }
        });
    }



}
