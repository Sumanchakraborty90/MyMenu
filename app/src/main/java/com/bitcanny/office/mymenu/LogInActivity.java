package com.bitcanny.office.mymenu;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by OFFICE on 27-07-2015.
 */
public class LogInActivity extends ActionBarActivity {

    String user_email,user_password;
    private static String MYPREF = "mypref";
    private static String EMAIL = "email";
    private static  String PASSWORD = "password";
    SharedPreferences sharedPreferences;

    Toolbar toolbar;
    EditText email;
    EditText password;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in_popup);
        toolbar = (Toolbar) findViewById(R.id.app_tl);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPreferences = getSharedPreferences(MYPREF, Context.MODE_PRIVATE);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);

       // final ImageView cross = (ImageView) findViewById(R.id.cross);

         submit = (Button)findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user_email = email.getText().toString();
                user_password = password.getText().toString();


                if (user_email.equals("1") && user_email.equals("1")) {
                    putSharedPreference(user_email, user_password);


                    // click = false;
                } else {
                    Toast.makeText(LogInActivity.this, "Wrong username or password", Toast.LENGTH_LONG).show();
                   // menuItem.setIcon(R.drawable.login148);
                }

            }
        });
    }

    public void putSharedPreference(String email,String password){

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email",email);
        editor.putString("password",password);

        editor.commit();

    }
}
