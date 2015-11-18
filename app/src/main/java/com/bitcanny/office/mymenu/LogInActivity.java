package com.bitcanny.office.mymenu;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

/**
 * Created by OFFICE on 27-07-2015.
 */
public class LogInActivity extends ActionBarActivity {

    String user_email,user_password;
    private static String MYPREF = "mypref";
    private static String EMAIL = "email";
    private static  String PASSWORD = "password";
    SharedPreferences sharedPreferences;
    ProgressBar progressBar;

    Toolbar toolbar;
    EditText email;
    EditText password;
    TextView textView,txt_registration;
    RelativeLayout relative_lay_fb;
    Button submit;
    CardView card_view_fb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in_popup);

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
        }
        toolbar = (Toolbar) findViewById(R.id.app_tl);

        setSupportActionBar(toolbar);
      //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPreferences = getSharedPreferences(MYPREF, Context.MODE_PRIVATE);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        progressBar = (ProgressBar)findViewById(R.id.pgr_bar);
        textView = (TextView) findViewById(R.id.txt_log_in);
        txt_registration= (TextView) findViewById(R.id.txt_registration);
        //card_view_fb = (CardView)findViewById(R.id.card_view_fb);
        relative_lay_fb= (RelativeLayout)findViewById(R.id.relative_lay_fb);

        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/ufonts.com_century-gothic.ttf");

        textView.setTypeface(tf);


       // final ImageView cross = (ImageView) findViewById(R.id.cross);

         submit = (Button)findViewById(R.id.submit);

        txt_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LogInActivity.this);
                    Intent intent = new Intent(LogInActivity.this, RegistrationActivity.class);

                    startActivity(intent,options.toBundle());

                }else{

                    Intent intent = new Intent(LogInActivity.this, RegistrationActivity.class);

                    startActivity(intent);
                    overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);

                }
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(LogInActivity.this, R.anim.animation);
                submit.startAnimation(hyperspaceJumpAnimation);
                hideKeyboard();
                new LogIn().execute();

               // Log.d("valid:",value+"");

               /* if (user_email.equals("1") && user_email.equals("1")) {



                    // click = false;
                } else {
                    Toast.makeText(LogInActivity.this, "Wrong username or password", Toast.LENGTH_LONG).show();
                   // menuItem.setIcon(R.drawable.login148);
                }*/

            }
        });
        relative_lay_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LogInActivity.this,FaceBookActivity.class);
                startActivity(intent);
            }
        });
    }


    public void putSharedPreference(String email,String password){

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email",email);
        editor.putString("password",password);

        editor.commit();

    }

    class LogIn extends AsyncTask<String,String,JSONObject>{

        String returnVal;
        boolean value;


        @Override
        protected JSONObject doInBackground(String... params) {



            if(value == true){


                ServiceHandler handler = new ServiceHandler();
                JsonFunctions jsonFunctions = new JsonFunctions(handler);

                try {
                    String json = jsonFunctions.logInAuthentication(user_email, user_password);

                    if (json != null) {

                        JSONObject jsonObject = new JSONObject(json);

                        JSONObject userInfo = jsonObject.getJSONObject("userInfo");

                        returnVal = userInfo.getString("type");

                        if(returnVal.equals("success")){


                            putSharedPreference(user_email, user_password);

                           /* Intent intent = getIntent();
                            finish();*/
                        }else{


                           // Toast.makeText(LogInActivity.this, "Wrong username or password", Toast.LENGTH_LONG).show();

                        }





                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            user_email = email.getText().toString();
            user_password = password.getText().toString();

             value =  getValidation(user_email,user_password);

            if(value == true){

                progressBar.setVisibility(View.VISIBLE);
            }

        }


        @Override
        protected void onPostExecute(JSONObject aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);

            try {
                if (returnVal.equals("success")) {
                    Toast.makeText(LogInActivity.this,"You are Successfully logged in..",Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);

                    Intent intent = new Intent();

                    setResult(1, intent);
                    finish();
                    //finish();

                } else {

                    Toast.makeText(LogInActivity.this,"Your credentials are wrong",Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }catch (Exception e){

                e.printStackTrace();
            }



        }
    }


    public boolean getValidation(String email,String password){

        boolean value =false;
        String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        if(email.equals("")){

            Toast.makeText(LogInActivity.this, "Please enter email", Toast.LENGTH_LONG).show();

        }else if(!email.matches(EMAIL_REGEX)){



            Toast.makeText(LogInActivity.this, "Please enter proper email", Toast.LENGTH_LONG).show();
            return false;


        }else if (password.equals("")) {

            Toast.makeText(LogInActivity.this, "Please enter password", Toast.LENGTH_LONG).show();

            return false;
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

        Intent intent = new Intent(LogInActivity.this,ResturantInfo.class);

        startActivity(intent);
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
        }

    }
}
