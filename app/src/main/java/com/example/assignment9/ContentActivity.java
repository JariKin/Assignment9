package com.example.assignment9;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ContentActivity extends AppCompatActivity {

   Button btn_Logout;
   TextView userinfo;
   TextView timestamp;

   private User user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.content);

        btn_Logout = findViewById(R.id.btnContent);
        userinfo = findViewById(R.id.userInfo);
        timestamp = findViewById(R.id.timestamp);




        Bundle extras = getIntent().getExtras();
        if(extras!=null) {
            userinfo.setText("Welcome in: " + extras.getString("User info"));
            timestamp.setText("Timestamp succesful login: " + extras.getString("Timestamp"));
        }



        btn_Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // switching back to loginscreen
                finish();
            }
        });


    }

}
