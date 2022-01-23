package com.example.todo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout ll = (LinearLayout) findViewById(R.id.ll);

        for (int i=0; i <= 10; i++) {
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
            RelativeLayout child_ll = new RelativeLayout(this);//(RelativeLayout) getLayoutInflater().inflate(R.layout.tem, null);
            child_ll.setPadding(50, 0, 50, 0);


            TextView textView = (TextView) getLayoutInflater().inflate(R.layout.tem, null);
            textView.setText("sdjyfgcycudhx");
            textView.setId(i);
            textView.setGravity(Gravity.CENTER_VERTICAL);
            textView.setBackgroundColor(Color.GREEN);

            child_ll.addView(textView);

            Button drop = new Button(this);
            drop.setText(">");
            drop.setMaxWidth(30);
            drop.setBackgroundColor(Color.BLACK);

            child_ll.addView(drop);

            textView.setMinHeight(drop.getHeight());
            textView.setMinimumHeight(drop.getHeight());

            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)drop.getLayoutParams();
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);

            drop.setLayoutParams(params);


            ll.addView(child_ll);

//            RelativeLayout.LayoutParams rparam = (RelativeLayout.LayoutParams) child_ll.getLayoutParams();
//            rparam.leftMargin = 50;
//            rparam.rightMargin = 50;
//            child_ll.setLayoutParams(rparam);


//            TextView txt = (TextView)getLayoutInflater().inflate(R.layout.tem, null);
//            txt.setText("Some text");
//            ll.addView(txt);
        }
    }
}