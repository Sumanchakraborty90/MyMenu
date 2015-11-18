package com.bitcanny.office.mymenu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResturantReviewRating extends ActionBarActivity {

    PopupWindow mPopupWindow;
    ProgressBar pgr_bar;
    String comment,cacheKey="";
    ImageView img_show;
    String offer_id,title,description,imageUrl,value,type;
    TextView txt_offer;
    TextView txt_provide_review_rating;
    List<Map<String,String>> sharedDocumnents;
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
    EditText edit_text;
    String review,rating,userId="1";
    private static String MYPREF = "mypref";
    private static String EMAIL = "email";
    private static String PASSWORD = "password";
    SharedPreferences sharedPreferences;
    RelativeLayout resturant_review_rating;
    boolean flag = false;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
        }
        setContentView(R.layout.activity_resturant_review_rating);
        typeface = Typeface.createFromAsset(getAssets(), "fonts/ufonts.com_century-gothic.ttf");
        pgr_bar = (ProgressBar) findViewById(R.id.pgr_bar);
        sharedDocumnents= new ArrayList<>();
        list = (ListView) findViewById(R.id.list);
        toolbar = (Toolbar) findViewById(R.id.app_tl);
        txt_review = (TextView) findViewById(R.id.txt_review);
        txt_rate = (TextView) findViewById(R.id.txt_rate);
        txt_cmnt = (TextView) findViewById(R.id.txt_cmnt);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        // rting_bar = (RatingBar) findViewById(R.id.rting_bar);
        //edit_text= (EditText)findViewById(R.id.edit_text);
        resturant_review_rating = (RelativeLayout) findViewById(R.id.resturant_review_rating);
        sharedPreferences = getSharedPreferences(MYPREF, Context.MODE_PRIVATE);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);


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


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new GetReviewRating().execute();
            }


        });
/*
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh items
                refreshItems();
            }
        });

    void refreshItems() {
        // Load items
        // ...

        // Load complete
        onItemsLoadComplete();
    }

    void onItemsLoadComplete() {
        // Update the adapter and notify data set changed
        // ...

        // Stop refresh animation
        swipeRefreshLayout.setRefreshing(false);
    }
});*/


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
                    if(flag == false) {
                        resturant_review_rating.setAlpha((float) .27);
                        showPopupWindow();
                        flag =true;
                        // new SubmitReviewRatingForResturant().execute();
                    }else{

                        resturant_review_rating.setAlpha(1);
                        flag = false;
                    }



                }
            }
        });

    }

    /*@Override
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
    }*/
    class GetReviewRating extends AsyncTask<Void,Void,Void>{

        String returnVal;
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try{

                if(returnVal.equals("success")){


                    swipeRefreshLayout.setRefreshing(false);
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
                String json = functions.getResturantReviewRating(resturantId,cacheKey);

                if (json != null) {

                    JSONObject object = new JSONObject(json);

                    JSONObject reviewInfo= object.getJSONObject("reviewInfo");

                    cacheKey=reviewInfo.getString("cacheKey");


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
        int val = 0;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            review = edit_text.getText().toString();
            rating = String.valueOf(rting_bar.getRating());
            val = validateReviewRating(rating,review);

            if(val == 0) {

                Toast.makeText(ResturantReviewRating.this,"Please give rating", Toast.LENGTH_LONG).show();

            }else if(val == 1){

                Toast.makeText(ResturantReviewRating.this,"Please give review", Toast.LENGTH_LONG).show();

            }else{




            }



        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            try{

                if(returnVal.equals("success")){

                    mPopupWindow.dismiss();
                    resturant_review_rating.setAlpha(1);
                    flag = false;
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


            if(val == 2) {

                ServiceHandler handler = new ServiceHandler();

                JsonFunctions functions = new JsonFunctions(handler);

                try {
                    Log.d("user_id", sharedPreferences.getString("email", ""));
                    String json = functions.insertResturantReview(review, rating, resturantId, sharedPreferences.getString("email", ""));

                    if (json != null) {

                        JSONObject jsonObject = new JSONObject(json);

                        JSONObject reviewInfo = jsonObject.getJSONObject("reviewInfo");

                        returnVal = reviewInfo.getString("type");
                        if (returnVal.equals("success")) {


                        }


                    }

                } catch (Exception e) {


                    e.printStackTrace();
                }
            }
            return null;
        }
    }


    private void showPopupWindow(){

     /*   LayoutInflater inflater = (LayoutInflater)PlayActivity.this.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);

        View layout = inflater.inflate(R.layout.popup_hlp, (ViewGroup)findViewById(R.id.popup));

        Display display = getWindowManager().getDefaultDisplay();

        ImageButton cross = (ImageButton) layout.findViewById(R.id.cross);


        cross.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                popUpWindow.dismiss();

            }
        });

        int scrWidth = display.getWidth();

        int scrHeight = (display.getHeight());

        int width =rel.getWidth();

        int height =rel.getHeight();

        popUpWindow = new PopupWindow(layout,scrWidth,scrHeight ,true);

        popUpWindow.showAtLocation(layout, Gravity.TOP, scrWidth,scrHeight);

        popUpWindow.setBackgroundDrawable(new BitmapDrawable());

        popUpWindow.setOutsideTouchable(true);

        popUpWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                popUpWindow.dismiss();

                return true;
            }

        });

    }catch(Exception e){

        e.printStackTrace();

    }
*/


        new GetResturantReviewOffer().execute();
        LayoutInflater mLayoutInflater=LayoutInflater.from(ResturantReviewRating.this);

        View mView=mLayoutInflater.inflate(R.layout.popup_review_rating,null /*(ViewGroup)resturant_review_rating*/);

        mPopupWindow=new PopupWindow(mView,  LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);

        // mPopupWindow=new PopupWindow(mView,LayoutParams.WRAP_CONTENT);

        mPopupWindow.showAtLocation(mView, Gravity.CENTER, LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);

        mPopupWindow.setContentView(mView);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(),
                ""));

        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(false);
        mPopupWindow.setOutsideTouchable(false);
      //  mPopupWindow.setOutsideTouchable(true);


        edit_text = (EditText)mView.findViewById(R.id.edit_text);

        rting_bar = (RatingBar) mView.findViewById(R.id.rting_bar);

        Button btn_submit_rating= (Button)mView.findViewById(R.id.btn_submit_rating);
        ImageView btn_cross= (ImageView)mView.findViewById(R.id.btn_cross);
         txt_provide_review_rating = (TextView)mView.findViewById(R.id.txt_provide_review_rating);
        txt_offer= (TextView)mView.findViewById(R.id.txt_offer);

        img_show= (ImageView)mView.findViewById(R.id.img_show);






      /*  mBtnCancel=(Button) mView.findViewById(R.id.btn_cancel);

        mBtnCancel.setOnClickListener(this);*/

        Drawable d = new ColorDrawable(Color.WHITE);

        d.setAlpha(130);



        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());

        mPopupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                mPopupWindow.dismiss();
                resturant_review_rating.setAlpha(1);
                return true;
            }

        });

        btn_cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // review = edit_text.getText().toString();

                mPopupWindow.dismiss();
                resturant_review_rating.setAlpha(1);
                flag = false;

            }
        });


        //mPopupWindow.showAsDropDown(mBtnPopUpWindow, 0, 0, Gravity.LEFT);

        getWindow().setBackgroundDrawable(d);


        btn_submit_rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // review = edit_text.getText().toString();


                new SubmitReviewRatingForResturant().execute();

                //finish();
                Intent  intent= getIntent();
            }
        });

       /* mPopupWindow.setOnDismissListener(new AutoCompleteTextView.OnDismissListener() {



           @Override

            public void onDismiss() {



                Drawable d = new ColorDrawable(Color.WHITE);



                getWindow().setBackgroundDrawable(d);

            }

        });*/

    }

    public int validateReviewRating(String rating,String review){

        int localFlag ;

       /* review = edit_text.getText().toString();
        rating = String.valueOf(rting_bar.getRating());*/

        if(rating.equals("")){


            localFlag= 0;
            return localFlag;

        }else if(review.equals("")){


            localFlag= 1;
            return localFlag;
        }else{

            localFlag= 2;
            return localFlag;
        }




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
        }

    }

    class GetResturantReviewOffer extends  AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }



        @Override
        protected Void doInBackground(Void... params) {

            ServiceHandler handler = new ServiceHandler();
            JsonFunctions functions = new JsonFunctions(handler);

            try{

                String json = functions.getResturantReviewOffer(ResturantInfo.globalResturantId);

                JSONObject  jsonObject = new JSONObject(json);

                JSONObject offerInfo = jsonObject.getJSONObject("offerInfo");

                offer_id = offerInfo.getString("offer_id");
                title= offerInfo.getString("title");
                description =offerInfo.getString("description");
                imageUrl = offerInfo.getString("imageUrl");
                value = offerInfo.getString("value");
                type= offerInfo.getString("type");

                Map<String,String> map = new HashMap<>();

                map.put("offer_id",offer_id);
                map.put("title",title);
                map.put("description",description);
                map.put("imageUrl",imageUrl);
                map.put("value",value);
                map.put("type",type);

                sharedDocumnents.add(map);





            }catch (Exception e){


                e.printStackTrace();
            }






            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try {
                for (int index = 0; index < sharedDocumnents.size(); index++) {

                    txt_provide_review_rating.setText(sharedDocumnents.get(index).get("title"));
                    txt_offer.setText(sharedDocumnents.get(index).get("description"));
                  //  Log.d("imageUrlResturant",maps.get(index).get("imageUrl"));
                    Picasso.with(ResturantReviewRating.this)
                            .load(/*JsonFunctions.BASE_URL +*/ sharedDocumnents .get(index).get("imageUrl"))
                   /* .load(new File(getFromSdcard("/MenuApp/MenuItemGrid/").get(index)))*/
                            .placeholder(R.mipmap.ic_launcher) // optional
                            .error(R.mipmap.ic_launcher)
                            .transform(new RoundedCorner(4, 0))// optional
                            .into(img_show);


                }

            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }


}
