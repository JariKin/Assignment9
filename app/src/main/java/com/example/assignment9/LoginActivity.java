package com.example.assignment9;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {

    EditText loginUsername;
    EditText loginPassword;
    TextView registerScreen;
    Button button_login;
    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // login screen view as main screen
        setContentView(R.layout.login);

        // views
        registerScreen = (TextView) findViewById(R.id.link_to_register);
        button_login = findViewById(R.id.btnLogin);
        loginUsername = findViewById(R.id.loginUserName);
        loginPassword = findViewById(R.id.loginPassword);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name;
                String pass;
                name = loginUsername.getText().toString().trim();
                pass = loginPassword.getText().toString().trim();

                if (name.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Enter Username and Password", Toast.LENGTH_SHORT).show();
                }
                else {
                    checkAsycTask task = new checkAsycTask(LoginActivity.this.getApplication(), name, pass);
                    task.execute();
                }
            }
        });

        registerScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
            }
        });



    }

    public  class checkAsycTask extends AsyncTask<User,Void,User>
    {
        private UserDao userDao;
        private String name;
        private String pass;
        User user;
        Date date = new Date();
        String stringDate = DateFormat.getDateTimeInstance().format(date);

        public checkAsycTask(Application application, String name, String pass) {
            this.name = name;
            this.pass = pass;
            UserDatabase db = UserDatabase.getInstance(application);
            userDao = db.userDao();
        }

        @Override
        protected User doInBackground(User... users) {

            user = userDao.getUser(name,pass);
            return user;
        }

        @Override
        protected void onPostExecute(User user) {
            super.onPostExecute(user);
            if (user == null)
            {
                Toast.makeText(LoginActivity.this,"User not found!",Toast.LENGTH_SHORT).show();
                //String timeStamp = String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
            }
            else {
                Intent intent = new Intent(LoginActivity.this, ContentActivity.class);
                intent.putExtra("User info", name);
                intent.putExtra("Timestamp", stringDate);
                startActivity(intent);
            }
        }
    }
}
