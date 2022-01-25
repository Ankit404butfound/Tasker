package com.example.todo;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class View_done_work extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_wrok_activity);

        Button mark_done_btn = findViewById(R.id.mark_done);
        mark_done_btn.setText("Mark as UNDONE");

        Toast.makeText(getBaseContext(),"Selected"+Done_activity.task_id, Toast.LENGTH_SHORT).show();
    }
}