package com.example.sueleyman.drinkcontrol;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.sql.SQLException;
import com.example.sueleyman.drinkcontrol.MySQLiteHelper;

/**
 * Created by süleyman on 23.06.2017.
 */

public class DataSource {

    String TAG = DataSource.class.getSimpleName();

    double getrunken;

    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { "ID", "GETRUNKEN",
            "DATUM"};



    public DataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void createEntry(double getrunken, String operator) {
        ContentValues values = new ContentValues();
        values.put("GETRUNKEN", getrunken);
        values.put("DATUM", operator);

        Log.d(TAG,"creatEntry- getrunken: "+ getrunken + "Datum: " + operator);

        long insertId = database.insert("TAGESPLAN", null,
                values);


         Cursor cursor = database.query("TAGESPLAN",allColumns, "ID = " + insertId, null, null, null, null);
         cursor.moveToFirst();


    }


//holt die daten aus datenbank, und sucht dann in der while schleife nach dem ausgewählten datum
    protected void getDate(String date){
        Cursor cursor = database.query("TAGESPLAN", allColumns, null, null, null, null, null);
        cursor.moveToFirst();

        if(cursor.getCount() == 0){
            Log.d(TAG,"nichts drin");
        }
        while (cursor.isAfterLast() == false) {
            Log.d(TAG,date);
            Log.d(TAG,cursor.getString(2));
            if (cursor.getString(2).equals(date)) {
                getrunken = cursor.getDouble(1);
                Log.d(TAG,"getDouble() bei getdate bin drin: " + cursor.getDouble(1) + " " + cursor.getString(2));
            }

            Log.d(TAG,"getDouble() bei getdate: " + cursor.getDouble(1) + " " + cursor.getString(2));
            cursor.moveToNext();
        }
        cursor.close();
    }

    public double getGetrunken(){
        return getrunken;
    }





}
