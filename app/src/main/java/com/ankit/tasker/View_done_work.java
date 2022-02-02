package com.ankit.tasker;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class View_done_work extends AppCompatActivity {

    Boolean text_updated = false;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_wrok_activity);

        DbHandler db = new DbHandler(View_done_work.this);

        Button mark_done = findViewById(R.id.mark_done);
        mark_done.setText("Mark as UNDONE");

        Button save = findViewById(R.id.save);
        save.setVisibility(View.INVISIBLE);

        int id = Done_activity.task_id;
        ArrayList<Object> task_data = db.get_task_details(id);

        ImageButton delete_task = findViewById(R.id.delete);
        delete_task.setOnClickListener(v -> db.delete_task(id));

        TextView title = findViewById(R.id.titleView);
        title.setText("#" + id);

        EditText title_edit_text = findViewById(R.id.task_title);
        title_edit_text.setText((String) task_data.get(1));
        title_edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                text_updated = true;
                save.setVisibility(View.VISIBLE);

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        EditText desp = findViewById(R.id.task);
        desp.setText((String) task_data.get(2));
        desp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                text_updated = true;
                save.setVisibility(View.VISIBLE);

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        TextView date = findViewById(R.id.date);
        String added_date = (String) task_data.get(4);
        date.setText("Last updated: " + added_date);

        Button back = findViewById(R.id.back);
        back.setOnClickListener(v -> {
//            if (text_updated) {
//                Intent intent = new Intent(View_done_work.this, MainActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
//            }
//            else{
            finish();
//            }
        });

        TextView scheduled_date_view = findViewById(R.id.scheduled_date);
        String scheduled_date = (String) task_data.get(5);

        if (scheduled_date != null) {
            int mnth = Integer.valueOf(scheduled_date.split(" ")[0].split("-")[1]);
            mnth = mnth +1;

            String sch_mnth = String.valueOf(mnth);
            if (mnth < 10){
                sch_mnth = "0"+sch_mnth;
            }
            String yr = scheduled_date.split(" ")[0].split("-")[0];
            String dy = scheduled_date.split(" ")[0].split("-")[2];
            String hr = scheduled_date.split(" ")[1].split(":")[0];
            String mn = scheduled_date.split(" ")[1].split(":")[1];
            scheduled_date_view.setText(String.format("Task was scheduled for: %s-%s-%s %s:%s:00", yr, mnth, dy, hr, mn));
        }



        mark_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                db.mark_task_as_undone(id);
                                Toast.makeText(View_done_work.this, "Task marked as UNDONE", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(View_done_work.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //Do your No progress
                                break;
                        }
                    }
                };
                AlertDialog.Builder ab = new AlertDialog.Builder(View_done_work.this);
                ab.setMessage("Mark this task as UNDONE?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
        });

        ImageButton delete = findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                db.delete_task(id);
                                Toast.makeText(View_done_work.this, "Task deleted", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(View_done_work.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //Do your No progress
                                break;
                        }
                    }
                };
                AlertDialog.Builder ab = new AlertDialog.Builder(View_done_work.this);
                ab.setMessage("Are you sure to delete this task?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
        });

        save.setOnClickListener(v -> {
            db.update_task(id, title_edit_text.getText().toString(), desp.getText().toString());
            Toast.makeText(View_done_work.this, "Task updated", Toast.LENGTH_LONG).show();
        });
    }
}