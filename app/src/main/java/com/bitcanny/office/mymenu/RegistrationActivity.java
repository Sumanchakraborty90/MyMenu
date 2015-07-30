package com.bitcanny.office.mymenu;

import android.support.v7.app.ActionBarActivity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

public class RegistrationActivity extends ActionBarActivity {

    EditText txt_user_name,txt_ph_number,email,password,txt_repeat_password;
    Button submit;
    Toolbar toolbar;
    RelativeLayout main_view,rel;
    ScrollView scrl_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        toolbar = (Toolbar) findViewById(R.id.app_tl);
        scrl_view= (ScrollView) findViewById(R.id.scrl_view);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txt_user_name = (EditText) findViewById(R.id.txt_user_name);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        txt_repeat_password = (EditText) findViewById(R.id.txt_repeat_password);
        main_view = (RelativeLayout) findViewById(R.id.main_view);
        rel = (RelativeLayout) findViewById(R.id.rel);


        submit = (Button) findViewById(R.id.submit);
        scrl_view.setVerticalScrollBarEnabled(false);
        scrl_view.setHorizontalScrollBarEnabled(false);





    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registration, menu);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
