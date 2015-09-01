package com.bitcanny.office.mymenu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResturantReviewRating extends ActionBarActivity {


    ProgressBar pgr_bar;
    ListView list;
    static String RestaurantRatingAddby = "RestaurantRatingAddby";
    static String RestaurantRatingRate = "RestaurantRatingRate";
    static String RestaurantRatingReview = "RestaurantRatingReview";
    String restaurantratingaddby,restaurantratingrate,restaurantratingreview;
    List<Map<String,String>> maps;
    String resturantId;
    Toolbar toolbar;
    TextView txt_review,txt_rate,txt_cmnt,btn_submit;
    RatingBar rting_bar;
    Typeface typeface;
    String review,rating,userId="1";
    private static String MYPREF = "mypref";
    private static String EMAIL = "email";
    private static String PASSWORD = "password";
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resturant_review_rating);
        typeface = Typeface.createFromAsset(getAssets(), "fonts/ufonts.com_century-gothic.ttf");
        pgr_bar = (ProgressBar) findViewById(R.id.pgr_bar);
        list = (ListView)findViewById(R.id.list);
        toolbar= (Toolbar)findViewById(R.id.app_tl);
        txt_review = (TextView) findViewById(R.id.txt_review);
        txt_rate = (TextView) findViewById(R.id.txt_rate);
        txt_cmnt = (TextView) findViewById(R.id.txt_cmnt);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        rting_bar = (RatingBar) findViewById(R.id.rting_bar);
        sharedPreferences = getSharedPreferences(MYPREF, Context.MODE_PRIVATE);


        txt_review.setTypeface(typeface);
        txt_rate.setTypeface(typeface);
        txt_cmnt.setTypeface(typeface);
        btn_submit.setTypeface(typeface);
        maps = new ArrayList<>();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Bundle bundle = getIntent().getExtras();
        resturantId = bundle.getString("Resturant_Id");

        new GetReviewRating().execute();

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (sharedPreferences.getString("email", "").equals("") || sharedPreferences.getString("password", "").equals("")) {

                    // initiatePopupWindow();
                    // resturant_info.setAlpha((float) .27);
                    //  rel_val.setAlpha((float) .27);

                    Intent intent = new Intent(ResturantReviewRating.this, LogInActivity.class);
                    startActivity(intent);
                }else {
                    new SubmitReviewRatingForResturant().execute();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_resturant_review_rating, menu);
        return true;
    }

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
    class GetReviewRating extends AsyncTask<Void,Void,Void>{

        String returnVal;
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try{

                if(returnVal.equals("success")){



                    pgr_bar.setVisibility(View.GONE);

                    ArrayAdapter adapter = new ResturantReviewRatingAdapter(ResturantReviewRating.this,0,maps);


                    list.setAdapter(adapter);



                }else{

                    pgr_bar.setVisibility(View.GONE);

                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pgr_bar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... params) {

            ServiceHandler handler = new ServiceHandler();
            JsonFunctions functions = new JsonFunctions(handler);

            try {
                String json = functions.getResturantReviewRating(resturantId);

                if (json != null) {

                    JSONObject object = new JSONObject(json);

                    JSONObject reviewInfo= object.getJSONObject("reviewInfo");

                    returnVal = reviewInfo.getString("type");

                    if(returnVal.equals("success")){

                        JSONArray Info = reviewInfo.getJSONArray("Info");

                        for(int index = 0;index<Info.length();index++){

                            JSONObject jsonObject =Info.getJSONObject(index);

                            restaurantratingaddby  = jsonObject.getString(RestaurantRatingAddby);
                            restaurantratingrate = jsonObject.getString(RestaurantRatingRate);

                            Log.d("restaurantratingrate",restaurantratingrate);
                            restaurantratingreview = jsonObject.getString(RestaurantRatingReview);

                            Map<String,String> map = new HashMap<>();

                            map.put(RestaurantRatingAddby,restaurantratingaddby);
                            map.put(RestaurantRatingRate,restaurantratingrate);
                            map.put(RestaurantRatingReview,restaurantratingreview);

                            maps.add(map);
                        }


                    }



                }
            }catch (Exception e){

                e.printStackTrace();
            }
            return null;
        }
    }


    class SubmitReviewRatingForResturant extends AsyncTask<Void,Void,Void>{

        String returnVal;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            review = txt_cmnt.getText().toString();
            rating = String.valueOf(rting_bar.getRating());



        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            try{

                if(returnVal.equals("success")){


                    Toast.makeText(ResturantReviewRating.this, "You have successfully submitted resturant review...", Toast.LENGTH_LONG).show();



                }else{

                    Toast.makeText(ResturantReviewRating.this, "Some error occured please try again...", Toast.LENGTH_LONG).show();

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
                String json = functions.insertResturantReview(review, rating, resturantId, userId);

                if(json!=null){

                    JSONObject jsonObject  = new JSONObject(json);

                    JSONObject reviewInfo = jsonObject.getJSONObject("reviewInfo");

                    returnVal = reviewInfo.getString("type");
                    if(returnVal.equals("success")){






                    }






                }

            }catch (Exception e){


                e.printStackTrace();
            }
            return null;
        }
    }
}
