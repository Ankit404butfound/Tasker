package com.example.todo;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.RequiresApi;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Done_activity extends AppCompatActivity {
    public static int task_id;
    private final View.OnClickListener child_select_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            task_id = v.getId();
            Intent intent = new Intent(Done_activity.this, View_done_work.class);
            startActivity(intent);
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        DbHandler db = new DbHandler(this);

        Button add_button = findViewById(R.id.add);
        add_button.setVisibility(View.INVISIBLE);

        LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
        TextView title = findViewById(R.id.titleView);
        title.setText(R.string.title_done);
        Button btn = findViewById(R.id.back);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ArrayList<ArrayList<Object>> all_tasks = db.get_all_task_details("d");

        ll.post(() -> {
            for (int i=0; i<all_tasks.size(); i++) {
                System.out.println(all_tasks);
                Log.d("todo","szkjhcudjhsjghiuahgduyg");
                ArrayList<Object> task = all_tasks.get(i);
                RelativeLayout child_ll = (RelativeLayout) getLayoutInflater().inflate(R.layout.tem, null);
                child_ll.setId((Integer) task.get(0));
                Log.d("todotodo", "ididiid"+(Integer) task.get(0));
                child_ll.setPadding(20, 20, 20, 10);
                child_ll.setOnClickListener(Done_activity.this.child_select_listener);
                TextView chile_txt_view = child_ll.findViewById(R.id.textv);

                String boldText = task.get(1)+" #" + (Integer) task.get(0);
                String normalText;
                if (task.get(2).toString().length() >= 20) {
                    normalText = "\n" + task.get(2).toString().substring(0, 20) + "...";
                }
                else{
                    normalText = "\n" + task.get(2).toString();
                }
                SpannableString str = new SpannableString(boldText + normalText);
                str.setSpan(new StyleSpan(Typeface.BOLD), 0, boldText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                str.setSpan(new RelativeSizeSpan(1.2f), 0, boldText.length(), 0);
                chile_txt_view.setText(str);
                chile_txt_view.setTextColor(Color.parseColor("#d6fff2"));

                View viewDivider = new View(Done_activity.this);
                viewDivider.setBackgroundColor(Color.parseColor("#5e5e5e"));
                int dividerHeight = (int) (getResources().getDisplayMetrics().density * 1); // 1dp to pixels
                viewDivider.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dividerHeight));

                ll.addView(child_ll);
                ll.addView(viewDivider);
            }
        });
    }
}