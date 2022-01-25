package com.example.todo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    public static int task_id;
    DbHandler db = new DbHandler(this);
    private final View.OnClickListener child_select_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            task_id = id;
            Intent intent = new Intent(MainActivity2.this, View_wrok_activity.class);
            startActivity(intent);

        }
    };

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
        Button back_btn = findViewById(R.id.back);
        back_btn.setOnClickListener(v -> finish());

        Button add_work_btn = findViewById(R.id.add);
        add_work_btn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity2.this, Add_Work.class);
            startActivity(intent);
        });

        ArrayList<ArrayList<Object>> all_tasks = db.get_all_task_details();

        ll.post(() -> {
            for (int i=0; i<all_tasks.size(); i++) {
                System.out.println(all_tasks);
                Log.d("todo","szkjhcudjhsjghiuahgduyg");
                ArrayList<Object> task = all_tasks.get(i);
                RelativeLayout child_ll = (RelativeLayout) getLayoutInflater().inflate(R.layout.tem, null);
                child_ll.setId((Integer) task.get(0));
                Log.d("todotodo", "ididiid"+(Integer) task.get(0));
                child_ll.setPadding(20, 20, 20, 10);
                child_ll.setOnClickListener(MainActivity2.this.child_select_listener);
                TextView chile_txt_view = child_ll.findViewById(R.id.textv);

                String boldText = task.get(1)+" #" + (Integer) task.get(0);
                String normalText = "\n"+task.get(2).toString().substring(0, 20)+"...";
                SpannableString str = new SpannableString(boldText + normalText);
                str.setSpan(new StyleSpan(Typeface.BOLD), 0, boldText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                str.setSpan(new RelativeSizeSpan(1.2f), 0, boldText.length(), 0);
                chile_txt_view.setText(str);
                chile_txt_view.setTextColor(Color.parseColor("#d6fff2"));

                View viewDivider = new View(MainActivity2.this);
                viewDivider.setBackgroundColor(Color.parseColor("#5e5e5e"));
                int dividerHeight = (int) (getResources().getDisplayMetrics().density * 1); // 1dp to pixels
                viewDivider.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dividerHeight));

                ll.addView(child_ll);
                ll.addView(viewDivider);
            }
        });
    }
}

//            LinearLayout child_ll = new LinearLayout(this);
//            child_ll.setBackgroundColor(0xFF12FF);
//
//            TextView txt = new TextView(this);//(TextView) getLayoutInflater().inflate(R.layout.tem, null);
//            String text = "";
//            for (int j=0; j <= i; j++){
//                text += "Some text";
//            }
//            txt.setText("Some text");
//            child_ll.addView(txt);
//
//            Button drop = new Button(this);
//            drop.setText(">");
//            child_ll.addView(drop);
//
//
//            TextView textView = (TextView) getLayoutInflater().inflate(R.layout.tem, null);
//            textView.setText("sdjyfgcycudhx");
//            textView.setId(i);
//            textView.setGravity(Gravity.CENTER_VERTICAL);
//            textView.setBackgroundColor(Color.GREEN);
//            textView.findViewById(R.id.)

//            child_ll.addView(textView);
//
//            Button drop = new Button(this);
//            drop.setText(">");
//            drop.setMaxWidth(30);
//            drop.setBackgroundColor(Color.BLACK);
//
//            child_ll.addView(drop);
//
//            textView.setMinHeight(drop.getHeight());
//            textView.setMinimumHeight(drop.getHeight());
//
//            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)drop.getLayoutParams();
//            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
//
//            drop.setLayoutParams(params);


//            RelativeLayout.LayoutParams rparam = (RelativeLayout.LayoutParams) child_ll.getLayoutParams();
//            rparam.leftMargin = 50;
//            rparam.rightMargin = 50;
//            child_ll.setLayoutParams(rparam);


//            TextView txt = (TextView)getLayoutInflater().inflate(R.layout.tem, null);
//            txt.setText("Some text");
//            ll.addView(txt);