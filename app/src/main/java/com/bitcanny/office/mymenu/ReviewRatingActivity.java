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
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
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

public class ReviewRatingActivity extends ActionBarActivity {

    private static String MYPREF = "mypref";
    private static String EMAIL = "email";
    private static String PASSWORD = "password";
    PlaceOrderSqlHelperDao dao;
    PopupWindow mPopupWindow;
    TextView txt_offer;
    String offer_id,title,description,reviewImageUrl,value,type;
    List<Map<String,String>> sharedDocumnents;
    RatingBar rting_bar;
    boolean flag =false;
    //private Toolbar toolbar;
    List<Map<String,String>> maps;

    TextView txt_provide_review_rating;
    ImageView img_show;
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
    String cacheKey = "";

    SharedPreferences sharedPreferences;
    Toolbar toolbar;
    ListView list;
    ImageView img_logo;
    TextView txt_item_name,txt_item_price,txt_rating,txt_comment;
    Button btn_save;
    EditText edt_co,edit_text;

    RatingBar rating_bar;
    RelativeLayout activity_review_rating;
    String price,imageUrl,food_name,food_description,position,selectedItems,menu_id;
    Typeface typeface;
    SwipeRefreshLayout swipeRefreshLayout;
    ImageView img_view11,img_view22,img_view3,img_view4,img_view5,img_view6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_rating);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
        }
        toolbar = (Toolbar) findViewById(R.id.app_tl);
        setSupportActionBar(toolbar);
        dao = new PlaceOrderSqlHelperDao(this);
        maps= new ArrayList<>();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        typeface =Typeface.createFromAsset(getAssets(), "fonts/ufonts.com_century-gothic.ttf");
        sharedPreferences = getSharedPreferences(MYPREF, Context.MODE_PRIVATE);
        list = (ListView)findViewById(R.id.list);
        txt_item_name = (TextView)findViewById(R.id.txt_item_name);
        txt_item_price = (TextView)findViewById(R.id.txt_item_price);
        txt_rating= (TextView) findViewById(R.id.txt_rating);
        txt_comment= (TextView) findViewById(R.id.txt_comment);
        activity_review_rating =(RelativeLayout)findViewById(R.id.activity_review_rating);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);

        txt_rating.setTypeface(typeface);
        txt_comment.setTypeface(typeface);
        sharedDocumnents= new ArrayList<>();
        txt_item_name.setTypeface(typeface);
        txt_item_price.setTypeface(typeface);
        img_logo = (ImageView)findViewById(R.id.img_logo);
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_save.setTypeface(typeface);


        img_view11 = (ImageView)findViewById(R.id.img_view11);
        img_view22 = (ImageView)findViewById(R.id.img_view22);
        img_view3 = (ImageView)findViewById(R.id.img_view3);
        img_view4 = (ImageView)findViewById(R.id.img_view4);
        img_view5 = (ImageView)findViewById(R.id.img_view5);
        img_view6 = (ImageView)findViewById(R.id.img_view6);
       /* edt_co = (EditText) findViewById(R.id.edt_co);
        rating_bar = (RatingBar) findViewById(R.id.rating_bar);*/
        edit_text= (EditText)findViewById(R.id.edit_text);

        sharedPreferences = getSharedPreferences(MYPREF, Context.MODE_PRIVATE);
        try {
            price = sharedPreferences.getString("price", "");
            imageUrl = sharedPreferences.getString("image", "");
            food_name = sharedPreferences.getString("food_name", "");
            food_description = sharedPreferences.getString("food_description", "");
            position = sharedPreferences.getString("position", "");
            selectedItems = sharedPreferences.getString("amount", "");
            menu_id = sharedPreferences.getString("menu_id", "");
        }catch (Exception e){

            e.printStackTrace();
        }

        //showPopupWindow();
        txt_item_name.setText(food_name);
        txt_item_price.setText("Rs " + price);



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


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new GetReviewRating().execute();
            }
        });


        for(int index = 0;index<dao.getTagDetails(menu_id).size();index++){

              /*  Log.d("clili",tagInfoModels.get(index).getChili()+"");
                Log.d("veg",tagInfoModels.get(index).getVeg()+"");
                Log.d("NonVeg",tagInfoModels.get(index).getNonveg()+"");
                Log.d("FIsh",tagInfoModels.get(index).getFish()+"");*/

            try {


                // for(int index = 0;index<tagInfoModels.size();index++){
                // if (tagInfoModels.get(index).getChili().equals("1")) {

                if (dao.getTagDetails(menu_id).get(index).getChili().equals("1")) {
                    img_view4.setVisibility(View.VISIBLE);
                    img_view4.setImageResource(R.drawable.high_chilli);


                }
                if (dao.getTagDetails(menu_id).get(index).getFish().equals("1")) {
                    img_view3.setVisibility(View.VISIBLE);
                    img_view3.setImageResource(R.drawable.fish);

                }

                if (dao.getTagDetails(menu_id).get(index).getVeg().equals("1")) {
                    img_view11.setVisibility(View.VISIBLE);
                    img_view11.setImageResource(R.drawable.veg);

                }

                if (dao.getTagDetails(menu_id).get(index).getNonveg().equals("1")) {
                    img_view22.setVisibility(View.VISIBLE);
                    img_view22.setImageResource(R.drawable.non_veg);

                }
                //   }
                //  }
            }catch (Exception e){

                e.printStackTrace();
            }


        }

        list.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                int position = firstVisibleItem+visibleItemCount;
                int limit = totalItemCount;
                int totalItems = maps.size();

              /*  Log.i("TOTAL WORLD ITEMS", String.valueOf(totalItems));
                Log.i("LIMIT :::::::::::: ", String.valueOf(limit));
                Log.i("POSITION ::::::::::::", String.valueOf(position));
                Log.i("REFRESHING :::::::::::::::", String.valueOf(swipeRefreshLayout.isRefreshing()));*/

                if(position>=limit && totalItemCount>0 && !swipeRefreshLayout.isRefreshing() && position <totalItems){
                   /* swipeRefreshLayout.setRefreshing(true);
                    //In the below method I made my call to my ws...
                    onRefresh();*/

                    new GetReviewRating().execute();
                }

            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // new PostReviewRating().execute();
                if(sharedPreferences.getString("email", "").equals("") || sharedPreferences.getString("password","").equals("")) {

                    Intent intent = new Intent(ReviewRatingActivity.this, LogInActivity.class);

                    startActivity(intent);


                }else{
                    // showPopupWindow();

                   // edt_co.getText().toString();
                    if(flag == false) {
                        activity_review_rating.setAlpha((float) .27);
                       showPopupWindow();
                        flag =true;
                        // new SubmitReviewRatingForResturant().execute();
                    }else{

                        activity_review_rating.setAlpha(1);
                        flag = false;
                    }

                    // new PostReviewRating().execute();

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

                Log.d("menu_id",sharedPreferences.getString("menu_id", ""));
                String json = functions.getMenuReviewRating(ResturantInfo.globalResturantId,sharedPreferences.getString("menu_id", ""),cacheKey);

                if(json!=null){

                    JSONObject object = new JSONObject(json);



                    JSONObject reviewInfo = object.getJSONObject("reviewInfo");

                    try {

                        cacheKey = reviewInfo.getString("cacheKey");
                        Log.d("cacheKey", cacheKey);

                    }catch (Exception e){


                            e.printStackTrace();

                    }
                  //  JSONObject info = foodInfo.getJSONObject(INFO);

                    //  type = info.getString(TYPE);

                    /// if(type.equals("success")){

                    JSONArray array = reviewInfo.getJSONArray("Info");

                    for(int index = 0;index <array.length();index++){
                        JSONObject jsonObject = array.getJSONObject(index);

                        RestaurantRatingRate = jsonObject.getString(RESTAURANTRATINGRATE);

                        RestaurantRatingReview = jsonObject.getString(RESTAURANTRATINGREVIEW);

                        RestaurantRatingAddby = jsonObject.getString(RESTAURANTRATINGADDBY);

                        Map<String,String> map = new HashMap<>();

                        map.put("RestaurantRatingRate",RestaurantRatingRate);
                        map.put("RestaurantRatingReview", RestaurantRatingReview);

                        Log.d("RestaurantRatingReview", RestaurantRatingReview);
                        map.put("RestaurantRatingAddby", RestaurantRatingAddby);
                   //     Log.d("RestaurantRatingAddby", RestaurantRatingAddby);

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
                swipeRefreshLayout.setRefreshing(false);
                ArrayAdapter adapter = new ReviewAdapter(ReviewRatingActivity.this, 0, maps);
                list.setAdapter(adapter);
            }catch (Exception e){

                e.printStackTrace();
            }
        }
    }

    class PostReviewRating extends  AsyncTask<Void,Void,Void>{

        String returnVal;
        String rating;
        String edt_co_value;
        int val=0;

        @Override
        protected Void doInBackground(Void... params) {

            if(val == 2) {
                ServiceHandler handler = new ServiceHandler();

                JsonFunctions functions = new JsonFunctions(handler);
                try {

                    Log.d("manu_id", sharedPreferences.getString("menu_id", ""));
                    String json = functions.insertMenuReview(String.valueOf(edt_co_value), String.valueOf(rating), ResturantInfo.globalResturantId, sharedPreferences.getString("menu_id", ""), sharedPreferences.getString("email", ""));

                    if (json != null) {

                        JSONObject jsonObject = new JSONObject(json);
                        JSONObject reviewInfo = jsonObject.getJSONObject("reviewInfo");

                        returnVal = reviewInfo.getString("type");


                    }
                } catch (Exception e) {

                    e.printStackTrace();
                }

            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            rating = String.valueOf(rating_bar.getRating());
            edt_co_value = edt_co.getText().toString();

            val = validateReviewRating(rating, edt_co_value);

            if(val == 0) {

                Toast.makeText(ReviewRatingActivity.this,"Please give rating", Toast.LENGTH_LONG).show();

            }else if(val == 1){

                Toast.makeText(ReviewRatingActivity.this,"Please give review", Toast.LENGTH_LONG).show();

            }else{




            }

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try{

                if(returnVal.equals("success")){

                    mPopupWindow.dismiss();
                    activity_review_rating.setAlpha(1);
                    flag = false;
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
        new  GetMenuReviewOffer().execute();
        LayoutInflater mLayoutInflater=LayoutInflater.from(ReviewRatingActivity.this);

        View mView=mLayoutInflater.inflate(R.layout.popup_review_rating,null/*(ViewGroup)activity_review_rating*/);

        mPopupWindow=new PopupWindow(mView,  WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);

        // mPopupWindow=new PopupWindow(mView,LayoutParams.WRAP_CONTENT);

       mPopupWindow.showAtLocation(mView, Gravity.CENTER, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);

        mPopupWindow.setContentView(mView);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(),
                ""));

        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(false);
        mPopupWindow.setOutsideTouchable(false);
        //  mPopupWindow.setOutsideTouchable(true);


        edt_co = (EditText)mView.findViewById(R.id.edit_text);

        rating_bar  = (RatingBar) mView.findViewById(R.id.rting_bar);

        Button btn_submit_rating= (Button)mView.findViewById(R.id.btn_submit_rating);
        ImageView btn_cross= (ImageView)mView.findViewById(R.id.btn_cross);
      /*  mBtnCancel=(Button) mView.findViewById(R.id.btn_cancel);

        mBtnCancel.setOnClickListener(this);*/

        txt_provide_review_rating = (TextView)mView.findViewById(R.id.txt_provide_review_rating);
        txt_offer= (TextView)mView.findViewById(R.id.txt_offer);

        img_show= (ImageView)mView.findViewById(R.id.img_show);

        Drawable d = new ColorDrawable(Color.WHITE);

        d.setAlpha(130);



        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());

        mPopupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                mPopupWindow.dismiss();
                activity_review_rating.setAlpha(1);
                return true;
            }

        });

        btn_cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // review = edit_text.getText().toString();

                mPopupWindow.dismiss();
                activity_review_rating.setAlpha(1);
                flag = false;

            }
        });


        //mPopupWindow.showAsDropDown(mBtnPopUpWindow, 0, 0, Gravity.LEFT);

        getWindow().setBackgroundDrawable(d);


        btn_submit_rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // review = edit_text.getText().toString();


               new PostReviewRating().execute();

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

    class GetMenuReviewOffer extends  AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }



        @Override
        protected Void doInBackground(Void... params) {

            ServiceHandler handler = new ServiceHandler();
            JsonFunctions functions = new JsonFunctions(handler);

            try{

                String json = functions.getMenuReviewOffer(sharedPreferences.getString("menu_id", ""));

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
                    Log.d("imageUrlResturant", sharedDocumnents.get(index).get("imageUrl"));
                    Picasso.with(ReviewRatingActivity.this)
                            .load(/*JsonFunctions.BASE_URL +*/ sharedDocumnents.get(index).get("imageUrl"))
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
