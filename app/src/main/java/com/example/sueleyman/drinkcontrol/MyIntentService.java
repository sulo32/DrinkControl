package com.example.sueleyman.drinkcontrol;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by süleyman on 02.07.2017.
 */

public class MyIntentService extends IntentService {

    private Handler mHandler;
    String TAG = MyIntentService.class.getSimpleName();

    SharedPreferences pref;
    SharedPreferences.Editor prefEditor;

    private Boolean checked=false;
    private Boolean checked1 =false;
    private Boolean checked2 = false;

    private Boolean checkNoti = false;

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        mHandler = new Handler();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void onHandleIntent(Intent intent) {


        Log.i(TAG, "Service hat gestartet");
        pref = getSharedPreferences("mySettings", 0);
        prefEditor = pref.edit();

        checked = pref.getBoolean("uhr12", checked);
        checked1 = pref.getBoolean("uhr15", checked1);
        checked2 = pref.getBoolean("uhr19", checked2);
        Log.i(TAG, " " + checked + " " + checked1 + " " + checked2);

        Log.v(TAG, "In onHandleIntent. Message will be printed after 10sec");
        if (intent != null) {
            synchronized (this) {
                try {
                    wait(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.v(TAG, "Service started");
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Service Completed" , Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
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
}

