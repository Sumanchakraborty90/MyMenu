package com.bitcanny.office.mymenu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReviewRatingActivity extends ActionBarActivity {

    private static String MYPREF = "mypref";
    private static String EMAIL = "email";
    private static String PASSWORD = "password";


    //private Toolbar toolbar;
    List<Map<String,String>> maps;

    String RestaurantRatingID,RestaurantRatingRate,RestaurantRatingReview,returnValue,RestaurantRatingAddby;
    private static  String FOODINFO = "foodInfo";
    private static  String INFO = "Info";
    private static  String TYPE = "type";
    private static  String REVIEWINFO = "reviewInfo";
    private static String RESTAURANTRATINGID = "RestaurantRatingID";
    private static String RESTAURANTRATINGRATE = "RestaurantRatingRate";
    private static String RESTAURANTRATINGREVIEW = "RestaurantRatingReview";
    private static String RESTAURANTRATINGADDBY = "RestaurantRatingAddby";
    //private static String TYPE = "type";
    private static String MESSAGE = "message";

    SharedPreferences sharedPreferences;
    Toolbar toolbar;
    ListView list;
    ImageView img_logo;
    TextView txt_item_name,txt_item_price;
    Button btn_save;
    EditText edt_co;
    RatingBar rating_bar;

    String price,imageUrl,food_name,food_description,position,selectedItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_rating);
        toolbar = (Toolbar) findViewById(R.id.app_tl);
        setSupportActionBar(toolbar);
        maps= new ArrayList<>();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sharedPreferences = getSharedPreferences(MYPREF, Context.MODE_PRIVATE);
        list = (ListView)findViewById(R.id.list);
        txt_item_name = (TextView)findViewById(R.id.txt_item_name);
        txt_item_price = (TextView)findViewById(R.id.txt_item_price);
        img_logo = (ImageView)findViewById(R.id.img_logo);
        btn_save = (Button) findViewById(R.id.btn_save);
        edt_co = (EditText) findViewById(R.id.edt_co);
        rating_bar = (RatingBar) findViewById(R.id.rating_bar);

        sharedPreferences = getSharedPreferences(MYPREF, Context.MODE_PRIVATE);
        try {
            price = sharedPreferences.getString("price", "");
            imageUrl = sharedPreferences.getString("image", "");
            food_name = sharedPreferences.getString("food_name", "");
            food_description = sharedPreferences.getString("food_description", "");
            position = sharedPreferences.getString("position", "");
            selectedItems = sharedPreferences.getString("amount", "");
        }catch (Exception e){

            e.printStackTrace();
        }

        txt_item_name.setText(food_name);
        txt_item_price.setText("Rs "+price);



        try {
            Picasso.with(ReviewRatingActivity.this)
                .load(JsonFunctions.BASE_URL + imageUrl)

                    //.load(new File(getFromSdcard("/MenuApp/MenuItemGrid/").get(Integer.valueOf(position))))
                    .placeholder(R.mipmap.ic_launcher) // optional
                    .error(R.mipmap.ic_launcher)
                    .into(img_logo);
        }catch (Exception e){

            e.printStackTrace();
        }

        new GetReviewRating().execute();


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //edt_co.getText().toString();

                new PostReviewRating().execute();
                if(sharedPreferences.getString("email", "").equals("") || sharedPreferences.getString("password","").equals("")) {

                    Intent intent = new Intent(ReviewRatingActivity.this, LogInActivity.class);

                    startActivity(intent);


                }else{

                    edt_co.getText().toString();

                    new PostReviewRating().execute();

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_review_rating, menu);
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


    class GetReviewRating extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {

            ServiceHandler handler = new ServiceHandler();

            JsonFunctions functions = new JsonFunctions(handler);
            try {

                String json = functions.getFoodinfo("2");
                if(json!=null){

                    JSONObject object = new JSONObject(json);

                    JSONObject foodInfo = object.getJSONObject(FOODINFO);

                    JSONObject info = foodInfo.getJSONObject(INFO);

                    //  type = info.getString(TYPE);

                    /// if(type.equals("success")){

                    JSONArray array = info.getJSONArray(REVIEWINFO);

                    for(int index = 0;index <array.length();index++){
                        JSONObject jsonObject = array.getJSONObject(index);

                        RestaurantRatingRate = jsonObject.getString(RESTAURANTRATINGRATE);

                        RestaurantRatingReview = jsonObject.getString(RESTAURANTRATINGREVIEW);

                        Map<String,String> map = new HashMap<>();

                        map.put("RestaurantRatingRate",RestaurantRatingRate);
                        map.put("RestaurantRatingReview",RestaurantRatingReview);

                        Log.d("val", RestaurantRatingReview);
                        map.put("RestaurantRatingAddby",RestaurantRatingAddby);

                        maps.add(map);

                        //}

                    }

                }


            }catch (Exception e){

                e.printStackTrace();
            }
            return null;
        }



        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try {
                ArrayAdapter adapter = new ReviewAdapter(ReviewRatingActivity.this, 0, maps);
                list.setAdapter(adapter);
            }catch (Exception e){

                e.printStackTrace();
            }
        }
    }

    class PostReviewRating extends  AsyncTask<Void,Void,Void>{

        String returnVal;
        Float rating;
        String edt_co_value;

        @Override
        protected Void doInBackground(Void... params) {

            ServiceHandler handler = new ServiceHandler();

            JsonFunctions functions = new JsonFunctions(handler);
            try {
                String json = functions.insertMenuReview(String.valueOf(edt_co_value), String.valueOf(rating), "1", "1", sharedPreferences.getString("email", ""));

                if (json != null) {

                    JSONObject jsonObject = new JSONObject(json);
                    JSONObject reviewInfo = jsonObject.getJSONObject("reviewInfo");

                    returnVal = reviewInfo.getString("type");


                }
            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            rating = rating_bar.getRating();
            edt_co_value = edt_co.getText().toString();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try{

                if(returnVal.equals("success")){

                    Toast.makeText(ReviewRatingActivity.this, "You have successfully added review rating", Toast.LENGTH_SHORT).show();

                }else{

                    Toast.makeText(ReviewRatingActivity.this, "There is something worng try again later", Toast.LENGTH_SHORT).show();

                }

            }catch (Exception e){


                Toast.makeText(ReviewRatingActivity.this, "Please check network", Toast.LENGTH_SHORT).show();

                e.printStackTrace();
            }
        }
    }
}
