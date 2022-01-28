package com.example.todo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import android.content.Context;

import static android.content.Context.ALARM_SERVICE;

public class NotifyJob {
    public void NotifyJob() {

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void startJob(Context context, String title, String desp, int task_id, int remind_year, int remind_month, int remind_day, int remind_hour, int remind_minute) {
        long diff = 0;
        long diff2 = 0;

        /* Create Job Scheduler*/
//        Calendar c = Calendar.getInstance();
//        String curr_hour = String.valueOf(c.get(Calendar.HOUR));
//        String curr_minute = String.valueOf(c.get(Calendar.MINUTE));
//        String curr_sec = String.valueOf(c.get(Calendar.SECOND));
//
//        SimpleDateFormat date_formate = new SimpleDateFormat("hh:mm:ss");
//        try {
//            Date current_date = date_formate.parse(curr_hour+":"+curr_minute+":"+curr_sec);
//            Date scheduled_date = date_formate.parse(remind_hour+":"+remind_minute+":00");
//
//            diff = scheduled_date.getTime() - current_date.getTime();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//
//        Toast.makeText(context, "Job Scheduled after "+diff/1000+" seconds", Toast.LENGTH_SHORT).show();
        String short_desp = desp;

        if (desp.length() > 20){
            short_desp = desp.substring(0, 20)+"...";
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "CHANNEL_ID_ankit")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(short_desp)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//                .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                .setLights(Color.RED, 3000, 3000)
                .setSound(Settings.System.DEFAULT_ALARM_ALERT_URI);
        builder.setStyle(new NotificationCompat.BigTextStyle().bigText(desp));

        Intent notificationIntent = new Intent(context, NotificationBroadcastReceiver.class ) ;
        notificationIntent.putExtra("notification-id", task_id) ;
        notificationIntent.putExtra("notification" , builder.build()) ;
        PendingIntent pendingIntent = PendingIntent.getBroadcast ( context, task_id, notificationIntent, PendingIntent. FLAG_UPDATE_CURRENT );


        Calendar c = Calendar.getInstance();
        String curr_year = String.valueOf(c.get(Calendar.YEAR));
        String curr_month = String.valueOf(c.get(Calendar.MONTH));
        String curr_day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
        String curr_hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
        String curr_minute = String.valueOf(c.get(Calendar.MINUTE));
        String curr_sec = String.valueOf(c.get(Calendar.SECOND));

        SimpleDateFormat date_formate = new SimpleDateFormat("yyyy:mm:dd:hh:mm:ss");
        try {
            Date current_date = date_formate.parse(curr_year+":"+curr_month+":"+curr_day+":"+curr_hour+":"+curr_minute+":"+curr_sec);
            Date scheduled_date = date_formate.parse(remind_year+":"+remind_month+":"+remind_day+":"+remind_hour+":"+remind_minute+":00");

            diff = scheduled_date.getTime() - current_date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (diff <= 0){
            diff = diff + 86400000;
        }
        Log.d("ALARMMMMM", String.valueOf(diff));


        //Toast.makeText(context, "Job Scheduled after "+diff/1000+" seconds", Toast.LENGTH_SHORT).show();


        AlarmManager am = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        am.setExactAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime()+diff, pendingIntent);


    }
}

