package com.example.assignment9;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {
    EditText newusername;
    EditText newpassword;
    TextView bckToLogin;
    Button btn_register;
    UserDao userDao;
    List<User> myList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // register as contentview
        setContentView(R.layout.register);

        newusername = findViewById(R.id.newusername);
        newpassword = findViewById(R.id.newpassword);
        btn_register = findViewById(R.id.btnRegister);
        bckToLogin = findViewById(R.id.link_to_login);

        UserDatabase db = UserDatabase.getInstance(this.getApplication());
        userDao = db.userDao();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = newusername.getText().toString().trim();
                String pass = newpassword.getText().toString().trim();
                if (username.isEmpty()|| pass.isEmpty()){
                    Toast.makeText(RegisterActivity.this,"Enter all fields",Toast.LENGTH_SHORT).show();
                }
                else {
                    new adduserAsyncTask(username,pass).execute();
                }

            }
        });
        new showuserAsyctask().execute();





        bckToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    public class adduserAsyncTask extends AsyncTask<Void,Void,Boolean>
    {
        // Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        private  String username;
        private String pass;
        private User new_user;
        UserDao userDao;
        private Boolean is_new;
        public adduserAsyncTask(String username,String pass) {
            this.username = username;
            this.pass =pass;
            UserDatabase db = UserDatabase.getInstance(RegisterActivity.this.getApplication());
            this.userDao = db.userDao();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            new_user = userDao.getUser(username,pass);
            if (new_user == null) {
                userDao.inserUser(new User(username,pass));
                is_new = true;
            }
            else {
                is_new=false;
            }
            return is_new;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (is_new)
            {
                Toast.makeText(RegisterActivity.this,"User Added",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(RegisterActivity.this,"User Already Exists",Toast.LENGTH_SHORT).show();
            }
        }
    }


    public  class showuserAsyctask extends  AsyncTask<Void,Void,Void>
    {
        UserDao userDao;

        public showuserAsyctask() {
            UserDatabase db = UserDatabase.getInstance(RegisterActivity.this.getApplication());
            this.userDao = db.userDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            myList = userDao.getAllUsers();
            for (int i=0;i<myList.size();i++)
            {
                Log.d("______","id       : " + myList.get(i).getId());
                Log.d("______","username : " + myList.get(i).getUsername());
                Log.d("______","password : " + myList.get(i).getPassword());
            }
            return null;
        }
    }
}
