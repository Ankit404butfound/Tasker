package com.ankit.tasker;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.ArrayList;
import java.util.Random;

public class FirebaseMessageReceiver extends FirebaseMessagingService {
    DbHandler db = new DbHandler(this);

    @Override
    public void onNewToken(String token) {
        Log.d("TAG", "New token: " + token);
        new NetworkManager().execute(Globals.register_device_url(token));
        db.save_token(token);
        Globals.TOKEN = token;
        // TODO: Implement this method to send any registration to your app's servers.
//        sendRegistrationToServer(token); //As I understand it, you need to implement it yourself.
    }
    // Override onMessageReceived() method to extract the
    // title and
    // body from the message passed in FCM
    @Override
    public void
    onMessageReceived(RemoteMessage remoteMessage) {
        // First case when notifications are received via
        // data event
        // Here, 'title' and 'message' are the assumed names
        // of JSON
        // attributes. Since here we do not have any data
        // payload, This section is commented out. It is
        // here only for reference purposes.
        Log.e("DATAAAAA", remoteMessage.getData().get("title") + " " + remoteMessage.getData().get("message"));
        Log.d("Triggeredddd", "dlkch");
        MainActivity.error_toast.show();

//        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                switch (which){
//                    case DialogInterface.BUTTON_POSITIVE:
//                        break;
//
//                    case DialogInterface.BUTTON_NEGATIVE:
//                        //Do your No progress
//                        break;
//                }
//            }
//        };
//        AlertDialog.Builder ab = new AlertDialog.Builder(FirebaseMessageReceiver.this);

    }
//        showNotification(remoteMessage.getData().get("title"), remoteMessage.getData().get("message"));
//
//
//        // Second case when notification payload is
//        // received.
////        if (remoteMessage.getNotification() != null) {
////            // Since the notification is received directly from
////            // FCM, the title and the body can be fetched
////            // directly as below.
////            showNotification(
////                    remoteMessage.getNotification().getTitle(),
////                    remoteMessage.getNotification().getBody());
////        }
//    }
//
//    // Method to get the custom Design for the display of
//    // notification.
//
//
//    // Method to display the notifications
//    public void showNotification(String title, String desp) {
//        String short_desp = desp;
//        if (desp.length() > 20){
//            short_desp = desp.substring(0, 20)+"...";
//        }
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "CHANNEL_ID_ankit")
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle(title)
//                .setContentText(short_desp)
//                .setPriority(NotificationCompat.PRIORITY_HIGH);
//        builder.setStyle(new NotificationCompat.BigTextStyle().bigText(desp));
//
//        NotificationManager notificationManager = (NotificationManager)getApplicationContext().getSystemService(Context. NOTIFICATION_SERVICE );
//
//        if (android.os.Build.VERSION. SDK_INT >= android.os.Build.VERSION_CODES. O ) {
//            int importance = NotificationManager. IMPORTANCE_HIGH ;
//            NotificationChannel notificationChannel = new NotificationChannel( "CHANNEL_ID_ankit" , "NOTIFICATION_CHANNEL_NAME" , importance) ;
//            notificationChannel.enableVibration(true);
//            notificationChannel.setVibrationPattern(new long[] { 100, 100  });
//            assert notificationManager != null;
//            notificationManager.createNotificationChannel(notificationChannel) ;
//        }
//        notificationManager.notify(new Random().nextInt(100000), builder.build()) ;
//        // Pass the intent to switch to the MainActivity
////        ArrayList<java.lang.Object> task = MainActivity.db.get_scheduled_task();
////
////        int task_id = (int) task.get(0);
////        String title = String.valueOf(task.get(1));
////        String message = String.valueOf(task.get(2));
//
////        Intent intent
////                = new Intent(this, MainActivity.class);
////        // Assign channel ID
////        String channel_id = "notification_channel";
////        // Here FLAG_ACTIVITY_CLEAR_TOP flag is set to clear
////        // the activities present in the activity stack,
////        // on the top of the Activity that is to be launched
////        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
////        // Pass the intent to PendingIntent to start the
////        // next Activity
////        PendingIntent pendingIntent
////                = PendingIntent.getActivity(
////                this, 0, intent,
////                PendingIntent.FLAG_ONE_SHOT);
////
////        // Create a Builder object using NotificationCompat
////        // class. This will allow control over all the flags
////        NotificationCompat.Builder builder
////                = new NotificationCompat
////                .Builder(getApplicationContext(),
////                channel_id)
////                .setSmallIcon(R.mipmap.ic_launcher)
////                .setContentTitle("test")
////                .setContentText("test")
////                .setPriority(NotificationCompat.PRIORITY_HIGH);
////
////        // A customized design for the notification can be
////        // set only for Android versions 4.1 and above. Thus
////        // condition for the same is checked here.
////
////        // Create an object of NotificationManager class to
////        // notify the
////        // user of events that happen in the background.
////        NotificationManager notificationManager
////                = (NotificationManager) getSystemService(
////                Context.NOTIFICATION_SERVICE);
////        // Check if the Android Version is greater than Oreo
////        if (Build.VERSION.SDK_INT
////                >= Build.VERSION_CODES.O) {
////            NotificationChannel notificationChannel
////                    = new NotificationChannel(
////                    channel_id, "web_app",
////                    NotificationManager.IMPORTANCE_HIGH);
////            notificationManager.createNotificationChannel(
////                    notificationChannel);
////        }
////
////        notificationManager.notify(0, builder.build());
////        MainActivity.db.mark_task_as_not_scheduled(task_id);
//    }
//}
}