package com.bitcanny.office.mymenu;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import org.json.JSONObject;

public class RegistrationActivity extends ActionBarActivity {

    EditText txt_user_name,txt_ph_number,email,password,txt_repeat_password;
    String name,emailId,phNo,userPassword,repeatUserPassword;
    Typeface typeface;
    ProgressBar pgr_bar;
    Button submit;
    Toolbar toolbar;
    RelativeLayout main_view,rel;
    ScrollView scrl_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {

            overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);

        }
        toolbar = (Toolbar) findViewById(R.id.app_tl);
        scrl_view= (ScrollView) findViewById(R.id.scrl_view);
        pgr_bar = (ProgressBar) findViewById(R.id.pgr_bar);
        typeface = Typeface.createFromAsset(getAssets(),"fonts/ufonts.com_century-gothic.ttf");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txt_user_name = (EditText) findViewById(R.id.txt_user_name);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        txt_repeat_password = (EditText) findViewById(R.id.txt_repeat_password);
        txt_ph_number = (EditText)findViewById(R.id.txt_ph_number);
        main_view = (RelativeLayout) findViewById(R.id.main_view);
        rel = (RelativeLayout) findViewById(R.id.rel);


        submit = (Button) findViewById(R.id.submit);
        scrl_view.setVerticalScrollBarEnabled(false);
        scrl_view.setHorizontalScrollBarEnabled(false);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(RegistrationActivity.this, R.anim.animation);
                submit.startAnimation(hyperspaceJumpAnimation);
                hideKeyboard();
                new Registration().execute();
            }
        });


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

    class Registration extends AsyncTask<Void,Void,Void>{

        boolean flag = false;
        String returnVal;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            name = txt_user_name.getText().toString();
            phNo = txt_ph_number.getText().toString();
            emailId = email.getText().toString();
            userPassword = password.getText().toString();
            repeatUserPassword = txt_repeat_password.getText().toString();

            flag = getValidation(name,phNo,emailId,userPassword,repeatUserPassword);

            if(flag == true){


                pgr_bar.setVisibility(View.VISIBLE);
            }


        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            try{

                if(returnVal.equals("success")){

                Toast.makeText(RegistrationActivity.this,"Succesfully registered",Toast.LENGTH_LONG).show();

                    Intent intent = getIntent();
                    finish();

                }else {

                    Toast.makeText(RegistrationActivity.this,"Failed to register",Toast.LENGTH_LONG).show();

                }

            }catch (Exception e){

                e.printStackTrace();
            }


        }

        @Override
        protected Void doInBackground(Void... params) {

            ServiceHandler handler = new ServiceHandler();

            JsonFunctions functions = new JsonFunctions(handler);

            try {

                String json = functions.userRegistration(name, emailId, userPassword, phNo);

                if(json!= null){

                    JSONObject object = new JSONObject(json);

                    JSONObject userInfo = object.getJSONObject("userInfo");

                    returnVal = userInfo.getString("type");


                    if(returnVal.equals("success")){


                    }



                }



            }catch (Exception e){


                e.printStackTrace();
            }

            return null;
        }
    }


    public boolean getValidation(String name,String phNo,String email,String password,String repeatPassword) {

        boolean value = false;
        String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        if (name.equals("")) {


            Toast.makeText(RegistrationActivity.this, "Please enter name", Toast.LENGTH_LONG).show();

            return false;
        } else if (phNo.equals("")) {

            Toast.makeText(RegistrationActivity.this, "Please enter phone number", Toast.LENGTH_LONG).show();

            return false;
        } else if (emailId.equals("")) {

            Toast.makeText(RegistrationActivity.this, "Please enter email", Toast.LENGTH_LONG).show();

            return false;
        } else if (!email.matches(EMAIL_REGEX)) {


            Toast.makeText(RegistrationActivity.this, "Please enter proper email", Toast.LENGTH_LONG).show();
            return false;


        } else if (password.equals("")) {

            Toast.makeText(RegistrationActivity.this, "Please enter password", Toast.LENGTH_LONG).show();

            return false;
        } else if (repeatUserPassword.equals("")) {

            Toast.makeText(RegistrationActivity.this, "Please enter repeat password", Toast.LENGTH_LONG).show();

        } else if (!repeatUserPassword.equals(password)){

            Toast.makeText(RegistrationActivity.this, "Password repeat password doesnot match", Toast.LENGTH_LONG).show();

    }else{

            value = true;
            return true;
        }


        return value;
    }

    private void hideKeyboard() {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {

            overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);

        }
    }
}
