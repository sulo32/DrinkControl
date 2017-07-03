package com.example.sueleyman.drinkcontrol;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by süleyman on 23.06.2017.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "datenTageszufuhr.db";
    private static final int DATABASE_VERSION = 1;


    private static final String TABLE_CREATE_TAGESPLAN = ""
            +"create table TAGESPLAN("
            +"  ID integer primary key autoincrement, "
            +   "GETRUNKEN double, "
            + 	"DATUM int)";


    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(TABLE_CREATE_TAGESPLAN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS SCANITEM");
        onCreate(db);
    }
}
