package com.example.sueleyman.drinkcontrol;


import android.content.SharedPreferences;
import android.graphics.Color;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    double dayGoal=0;
    double getrunken=0;
    double result;
    TextView textView;
    TextView goal;
    TextView toGoal;
    String TAG = MainActivity.class.getSimpleName();

    SharedPreferences pref;
    SharedPreferences.Editor prefEditor;

    SimpleDateFormat zeitformat = new SimpleDateFormat("HH:mm");
    SimpleDateFormat datumausgabe = new SimpleDateFormat("dd.MM");
    Calendar kalender = Calendar.getInstance();

    private DataSource datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datasource = new DataSource(this);


        pref= this.getSharedPreferences("prefdatei1",MODE_PRIVATE);
        prefEditor= pref.edit();


        löschen();
        loadDayGoal();
        ChangePic(dayGoal);

        textView = (TextView) findViewById(R.id.aktuellerVerbrauch);
        toGoal = (TextView) findViewById(R.id.toGoalResult);
        textView.setText((String.valueOf(dayGoal)) + " L");
        result= 2-dayGoal;
        toGoal.setText((String.valueOf(result))+ " L");


       ClickOnTheButton();


    }


public void ClickOnTheButton(){
    Button info = (Button) findViewById(R.id.info);
    Button settings = (Button) findViewById(R.id.settings);
    Button week = (Button) findViewById(R.id.week);
    Button station= (Button) findViewById(R.id.waterstation);

    settings.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d(TAG,"habe einstellungen geklickt");
            Intent intent1 = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent1);
        }
    });
    info.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d(TAG,"habe info geklickt");
            Intent intent2 = new Intent(MainActivity.this, InfoActivity.class);
            startActivity(intent2);
        }
    });
    week.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d(TAG,"habe week geklickt");
            Intent intent3 = new Intent(MainActivity.this, WeekActivity.class);
            startActivity(intent3);
        }
    });
    station.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d(TAG, "habe station geklickt");
            Intent intent4 = new Intent(MainActivity.this, DrinkStation.class);
            startActivity(intent4);
        }
    });
}

public void ToGoal(){

    toGoal = (TextView) findViewById(R.id.toGoalResult);
    TextView text= (TextView) findViewById(R.id.toGoal);
    if (getrunken>=2){
        toGoal.setVisibility(View.INVISIBLE);
        text.setVisibility(View.INVISIBLE);
    }
    else {
        result = 2 - getrunken;
        toGoal.setText((String.valueOf(result)) + " L");
    }

}

    //addiert wasser und ändert das bild
public void addWater(View view) {
    textView = (TextView) findViewById(R.id.aktuellerVerbrauch);

        getrunken = getrunken + 0.25;

    textView.setText((String.valueOf(getrunken)) + " L");

    ToGoal();
    ChangePic(getrunken);

    saveDayGoal(getrunken);

    try {
        datasource.open();
        datasource.createEntry(getrunken, datumausgabe.format(kalender.getTime()));
        datasource.close();
    }
    catch (Exception ex) {
        Toast.makeText(this,ex.toString(), Toast.LENGTH_LONG).show();
    }

    }

public void ChangePic(Double getrunken){

    ImageView imageView = (ImageView) findViewById(R.id.imageView);
    textView = (TextView) findViewById(R.id.aktuellerVerbrauch);
    goal= (TextView) findViewById(R.id.goal);

    if (getrunken == 0) {
        imageView.setImageResource(R.drawable.bottle_); }
    else if (getrunken == 0.25) {
        imageView.setImageResource(R.drawable.bottle_0_25);
        textView.setTextColor(Color.RED);
    } else if (getrunken == 0.5) {
        imageView.setImageResource(R.drawable.bottle_0_5);
        textView.setTextColor(Color.RED);
    } else if (getrunken == 0.75) {
        imageView.setImageResource(R.drawable.bottle_0_75);
        textView.setTextColor(Color.RED);
    } else if (getrunken == 1) {
        imageView.setImageResource(R.drawable.bottle_1);
        textView.setTextColor(Color.YELLOW);
    } else if (getrunken == 1.25) {
        imageView.setImageResource(R.drawable.bottle_1_25);
        textView.setTextColor(Color.YELLOW);
    } else if (getrunken == 1.5) {
        imageView.setImageResource(R.drawable.bottle_1_5);
        textView.setTextColor(Color.YELLOW);
    } else if (getrunken == 1.75) {
        imageView.setImageResource(R.drawable.bottle_1_75);
        textView.setTextColor(Color.YELLOW);
    } else{
        imageView.setImageResource(R.drawable.bottle_2);
        textView.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
        goal.setVisibility(View.VISIBLE);
    }
}


public void saveDayGoal(Double getrunken){

 prefEditor.putString("MyKey2",Double.valueOf(getrunken).toString());
 prefEditor.commit();
    Log.d(TAG,"save shared preference: "+ Double.parseDouble(pref.getString("MyKey2",Double.valueOf(getrunken).toString())));
}

public void loadDayGoal(){
    dayGoal = Double.parseDouble(pref.getString("MyKey2",Double.valueOf(getrunken).toString()));
    getrunken=dayGoal;
    ChangePic(getrunken);
}

public void löschen(){
    if (zeitformat.format(kalender.getTime()).equals("07:51")){
        prefEditor.remove("MyKey2");
        prefEditor.commit();

        Log.d(TAG,"gelöscht "+ zeitformat.format(kalender.getTime()));
    }

}


}
