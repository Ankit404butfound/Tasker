package com.example.todo;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class View_wrok_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_wrok_activity);

        DbHandler db = new DbHandler(View_wrok_activity.this);

        int id = MainActivity2.task_id;
        ArrayList<Object> task_data = db.get_task_details(id);

        ImageButton delete_task = findViewById(R.id.delete);

        TextView title = findViewById(R.id.titleView);
        title.setText("#"+id);

        EditText title_edit_text = findViewById(R.id.task_title);
        title_edit_text.setText((String) task_data.get(1));

        EditText desp = findViewById(R.id.task);
        desp.setText((String) task_data.get(2));

        TextView date = findViewById(R.id.date);
        String added_date = (String) task_data.get(4);
        date.setText("Last updated: "+added_date);
        Toast.makeText(getBaseContext(),"Selected"+id, Toast.LENGTH_SHORT).show();
    }
}