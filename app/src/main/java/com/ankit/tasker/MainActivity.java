package com.ankit.tasker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    public static String about_text;
    public static DbHandler db;
    public static Toast error_toast;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DbHandler(MainActivity.this);
        setContentView(R.layout.activity_main);

        about_text = (String) getString(R.string.about_text);
        error_toast = Toast.makeText(this, "This is the reminder of one of your scheduled tasks, we can't tell which task was it while the app is open because of a bug...", Toast.LENGTH_LONG);
        Button act1_btn = findViewById(R.id.act1);
        this.getSupportActionBar().hide();
        act1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListTask.class);

                // start the activity connect to the specified class
                startActivity(intent);
            }
        });

        Button act2_btn = findViewById(R.id.act2);
        act2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Done_activity.class);
                startActivity(intent);
            }
        });
    }
}