package com.example.sueleyman.drinkcontrol;

import android.content.Intent;
import android.icu.util.Calendar;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.SimpleDateFormat;

import static com.example.sueleyman.drinkcontrol.R.id.calendarView;

public class WeekActivity extends AppCompatActivity {

    String TAG = WeekActivity.class.getSimpleName();
    MaterialCalendarView mvc;
    TextView ergebnis;
    private DataSource datasource;

    SimpleDateFormat zeitformat = new SimpleDateFormat("dd.MM");
    java.util.Calendar kalender = java.util.Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);
        datasource = new DataSource(this);

        mvc= (MaterialCalendarView)findViewById(calendarView);
        ergebnis= (TextView)findViewById(R.id.ergebnis);

        setCalender();
        ClickOnTheButton();

       mvc.setOnDateChangedListener(new OnDateSelectedListener() {
           @Override
           public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
               Log.d(TAG,"das ist das zeitformat datum: " + zeitformat.format(kalender.getTime()));
String selectDate= ""+date.getDay()+".0"+(date.getMonth()+1);
               Log.d(TAG,"das ist das String selectedDate: " + selectDate);
               try{
                datasource.open();
                datasource.getDate(selectDate);
                datasource.close();
                ergebnis.setText(String.valueOf(datasource.getGetrunken()) + " Liter");
           }
    catch (Exception ex) {
        Toast.makeText(WeekActivity.this, ex.toString(), Toast.LENGTH_SHORT).show();
           }

           }
       });
    }


public void setCalender(){

    mvc.state().edit()
            .setFirstDayOfWeek(Calendar.MONDAY)
            .setMinimumDate(CalendarDay.from(2017, 05, 26))
            .setMaximumDate(CalendarDay.from(2018, 12, 30))
            .setCalendarDisplayMode(CalendarMode.WEEKS)
            .commit();
}

    public void ClickOnTheButton() {
        Button verbrauch= (Button) findViewById(R.id.verbrauch);
        Button info = (Button) findViewById(R.id.info);
        Button settings= (Button) findViewById(R.id.settings);
        Button station= (Button) findViewById(R.id.waterstation);

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"habe einstellungen geklickt");
                Intent intent2 = new Intent(WeekActivity.this, SettingsActivity.class);
                startActivity(intent2);
            }
        });
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "habe info geklickt");
                Intent intent3 = new Intent(WeekActivity.this, InfoActivity.class);
                startActivity(intent3);
            }
        });
        verbrauch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "habe main geklickt");
                Intent intent = new Intent(WeekActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        station.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "habe station geklickt");
                Intent intent4 = new Intent(WeekActivity.this, DrinkStation.class);
                startActivity(intent4);
            }
        });

    }
}
