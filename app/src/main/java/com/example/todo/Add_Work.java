package com.example.todo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;

import android.provider.Settings;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import android.widget.DatePicker;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.Random;

public class Add_Work extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

    private int remind_year = 0;
    private int remind_month = 0;
    private int remind_day = 0;
    private int remind_hour = 0;
    private int remind_minute = 0;
    private static NotifyJob notifyJob = new NotifyJob();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_work);

        DbHandler db = new DbHandler(this);

        Button back = findViewById(R.id.back);
        back.setOnClickListener(v -> finish());

        Button save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                EditText title = findViewById(R.id.task_title);
                String task_title = title.getText().toString();

                EditText desp = findViewById(R.id.task_descp);
                String task_desp = desp.getText().toString();

                if (task_title.matches("")) {
                    Toast.makeText(Add_Work.this, "Task title can not be empty", Toast.LENGTH_LONG).show();

                }
                else {
                    if (remind_year == 0 && remind_month == 0 && remind_day == 0) {
                        db.add_task(task_title, task_desp);
                        Toast.makeText(Add_Work.this, "Task created", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Add_Work.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    } else {
                        String scheduled_date = String.format("%s-%s-%s %s:%s:00", remind_year, remind_month, remind_day, remind_hour, remind_minute);
                        int task_id = db.add_scheduled_task(task_title, task_desp, scheduled_date);
                        Toast.makeText(Add_Work.this, "Task created and scheduled", Toast.LENGTH_LONG).show();
                        notifyJob.startJob(Add_Work.this, task_title, task_desp, task_id, remind_year, remind_month, remind_day, remind_hour, remind_minute);

                        //                    notifyJob.startJob(Add_Work.this, task_id, remind_year, remind_month, remind_day, remind_hour, remind_minute);
                        Intent intent = new Intent(Add_Work.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                }
            }
        });

        ImageButton datetime_picker = findViewById(R.id.alarm);
        datetime_picker.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(Add_Work.this, Add_Work.this, year, month,day);
            datePickerDialog.show();
        });

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        remind_year = year;
        remind_month = month;
        remind_day = dayOfMonth;
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(Add_Work.this, Add_Work.this, hour, minute, DateFormat.is24HourFormat(this));
        timePickerDialog.show();

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        remind_hour = hourOfDay;
        remind_minute = minute;
        TextView scheduled_time = findViewById(R.id.scheduled_time);
        String rmd_mnth = String.valueOf(remind_month+1);
        if (remind_month < 10){
            rmd_mnth = "0"+rmd_mnth;
        }
        scheduled_time.setText(String.format("Schedule task for: %s-%s-%s %s:%s:00", remind_year, rmd_mnth, remind_day, remind_hour, remind_minute));
    }
}