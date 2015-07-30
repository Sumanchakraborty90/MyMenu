package com.bitcanny.office.mymenu;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PlaceOrderActivity extends ActionBarActivity {

    private Toolbar toolbar;
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

    ProgressDialog dialog;

    List<Map<String,String>> maps;

    PlaceOrderSqlHelperDao dao ;
    String RestaurantRatingID,RestaurantRatingRate,RestaurantRatingReview,returnValue,RestaurantRatingAddby;

    String comment;

    private static String MYPREF = "mypref";
    private static String EMAIL = "email";
    private static String PASSWORD = "password";

    SharedPreferences sharedPreferences;

    String user_email,user_password;

    MenuItem menuItem;

    private PopupWindow pwindo;

    String type;

    ProgressBar progressBar;
    ImageView img_view1,open_img1,open_img2,open_img3,img_add;
    TextView txt_fd_price,txt_fd_name,abt_the_food,txt_abt;
    EditText edt_name,edt_comment;
    LinearLayout rel_lay6;
    RelativeLayout rel_lay4,rel_val,rel_lay2,rel_lay3,rel_lay5;
    Button submit;
    String imageUrl,price,food_name,food_description;
    boolean imgFlag1=false,imgFlag2=false,imgFlag3=false;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        toolbar = (Toolbar) findViewById(R.id.tbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

      //  getSupportActionBar().setDisplayShowHomeEnabled(true);
        dao = new PlaceOrderSqlHelperDao(this);
        img_view1 = (ImageView) findViewById(R.id.img_view1);
        txt_fd_price = (TextView) findViewById(R.id.txt_fd_price);
        txt_fd_name = (TextView) findViewById(R.id.txt_fd_name);
        abt_the_food =(TextView) findViewById(R.id.abt_the_food);
        txt_abt =(TextView) findViewById(R.id.txt_abt);
        progressBar = (ProgressBar) findViewById(R.id.prg_bar);
        open_img1 = (ImageView)findViewById(R.id.open_img1);
        open_img2 = (ImageView) findViewById(R.id.open_img2);
        open_img3 = (ImageView)findViewById(R.id.open_img3);
        rel_lay6 = (LinearLayout) findViewById(R.id.rel_lay6);
       // edt_name = (EditText) findViewById(R.id.edt_name);
        edt_comment = (EditText)findViewById(R.id.edt_comment);
        rel_lay4 = (RelativeLayout) findViewById(R.id.rel_lay4);
        listView = (ListView) findViewById(R.id.list);
        img_add = (ImageView) findViewById(R.id.img_add);
        rel_val = (RelativeLayout) findViewById(R.id.rel_val);
        submit = (Button) findViewById(R.id.btn_submit);
        rel_lay2 = (RelativeLayout) findViewById(R.id.rel_lay2);
        rel_lay3= (RelativeLayout) findViewById(R.id.rel_lay3);
        rel_lay5= (RelativeLayout) findViewById(R.id.rel_lay5);
        maps  = new ArrayList<>();

        Bundle bundle = getIntent().getExtras();
        sharedPreferences = getSharedPreferences(MYPREF, Context.MODE_PRIVATE);
        price = bundle.getString("price");
        imageUrl  = bundle.getString("image");
        food_name= bundle.getString("food_name");
        food_description =bundle.getString("food_description");

        txt_fd_price.setText("Rs " + price);
        txt_fd_name.setText(food_name);
        txt_abt.setText(food_description);


        //OrderToCartAdapterModel model = new OrderToCartAdapterModel();




        Picasso.with(PlaceOrderActivity.this)
                .load(JsonFunctions.BASE_URL + imageUrl)
                .placeholder(R.mipmap.ic_launcher) // optional
                .error(R.mipmap.ic_launcher)
                .into(img_view1);

        progressBar.setVisibility(View.GONE);

        new GetReviewRating().execute();



        rel_lay2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgFlag1 = getImageStatus(open_img1, imgFlag1);

                if (imgFlag1 == false) {

                    txt_abt.setVisibility(View.GONE);
                } else {

                    txt_abt.setVisibility(View.VISIBLE);
                }
            }
        });

        img_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             /*   if(sharedPreferences.getString("email", "").equals("") || sharedPreferences.getString("password","").equals("")) {

                    initiatePopupWindow();

                    rel_val.setAlpha((float) .27);

                }else if(sharedPreferences.getString("email", "").equals("1") || sharedPreferences.getString("password","").equals("1")) {
*/
                try {
                   // dao.createTable();
                    Map<String,String> map = new HashMap<String, String>();

                    map.put("order_name",food_name);
                    map.put("order_item_price",price);
                    map.put("order_item_quantity","1");
                    map.put("user_id",sharedPreferences.getString("email", ""));
                    map.put("order_item_image_url",imageUrl);

                    ResturantEntryActivity.order.add(map);


                    dao.addToOrder(new OrderToCartAdapterModel(food_name, price, "1", sharedPreferences.getString("email", ""), imageUrl));
                    Intent intent = new Intent(PlaceOrderActivity.this, CartOrderActivity.class);
                    startActivity(intent);
                }catch (Exception e){

                    e.printStackTrace();
                }
              /*  }*/
            }
        });
        rel_lay3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgFlag2 = getImageStatus(open_img2,imgFlag2);

                if(imgFlag2 == false){
                    rel_lay4.setVisibility(View.GONE);

                }else{

                    rel_lay4.setVisibility(View.VISIBLE);
                }

            }
        });


        rel_lay5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgFlag3 = getImageStatus(open_img3,imgFlag3);

                if(imgFlag3 == false){
                    rel_lay6.setVisibility(View.GONE);

                }else{

                    rel_lay6.setVisibility(View.VISIBLE);
                }
            }
        });

       // new GetReviewRating().execute();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(sharedPreferences.getString("email", "").equals("") || sharedPreferences.getString("password","").equals("")) {

                    //initiatePopupWindow();

                    rel_val.setAlpha((float) .27);

                }else if(sharedPreferences.getString("email", "").equals("1") || sharedPreferences.getString("password","").equals("1")){


                    comment = edt_comment.getText().toString();
                    new AddMenuReview().execute();
                   // sharedPreferences.edit().clear().commit();

                }

            }
        });
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_resturant_info, menu);

        if(sharedPreferences.getString("email", "").equals("1") || sharedPreferences.getString("password","").equals("1")){
            menu.findItem(R.id.action_logIn).setIcon(R.drawable.login248);
            menu.findItem(R.id.action_logIn).setTitle("Log Out");
        }

        return true;
    }*/

    public void putSharedPreference(String email,String password){

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email",email);
        editor.putString("password", password);

        editor.commit();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        menuItem = item;
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logIn) {

           /* if(click == false){*/

            if(sharedPreferences.getString("email", "").equals("") || sharedPreferences.getString("password","").equals("")) {

                //initiatePopupWindow();

                rel_val.setAlpha((float) .27);

            }else if(sharedPreferences.getString("email", "").equals("1") || sharedPreferences.getString("password","").equals("1")){
               sharedPreferences.edit().clear().commit();
                item.setIcon(R.drawable.login148);
            }
          /*  }else{
                {
                    item.setIcon(R.drawable.login148);
                    resturant_info.setAlpha(1);
                    click = false;

                }

            }
*/


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

   /* public  void initiatePopupWindow() {
        try {
// We need to get the instance of the LayoutInflater
            LayoutInflater inflater = (LayoutInflater) PlaceOrderActivity.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.log_in_popup,
                    (ViewGroup) findViewById(R.id.pop_up_lay));
            pwindo = new PopupWindow(layout, 350, 450, true);
            pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);

           // YoYo.with(Techniques.SlideInUp).duration(4000).playOn(layout);


            final EditText email = (EditText)layout.findViewById(R.id.email);
            final EditText password = (EditText)layout.findViewById(R.id.password);

            final ImageView cross = (ImageView) layout.findViewById(R.id.cross);

            Button submit = (Button)layout.findViewById(R.id.submit);

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    user_email = email.getText().toString();
                    user_password = password.getText().toString();

                    if(user_email.equals("1") && user_email.equals("1")){
                        putSharedPreference(user_email,user_password);
                        pwindo.dismiss();
                        menuItem.setIcon(R.drawable.login248);
                        rel_val.setAlpha(1);

                        // click = false;
                    }else{

                        Toast.makeText(PlaceOrderActivity.this, "Wrong username or password", Toast.LENGTH_LONG).show();
                        menuItem.setIcon(R.drawable.login148);

                    }

                }
            });


            cross.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pwindo.dismiss();
                    rel_val.setAlpha(1);
                    // click = false;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/


/*    private void initiateAddToCartWindow() {
        try {
// We need to get the instance of the LayoutInflater
            LayoutInflater inflater = (LayoutInflater) PlaceOrderActivity.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.pop_up_menu,
                    (ViewGroup) findViewById(R.id.pop_up_lay));
            pwindo = new PopupWindow(layout,350,450,true);
            pwindo.showAtLocation(layout, Gravity.BOTTOM, 0, 0);

            // YoYo.with(Techniques.SlideInUp).duration(4000).playOn(layout);


            final EditText email = (EditText)layout.findViewById(R.id.email);
            final EditText password = (EditText)layout.findViewById(R.id.password);

            final ImageView cross = (ImageView) layout.findViewById(R.id.cross);

            Button submit = (Button)layout.findViewById(R.id.submit);

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    user_email = email.getText().toString();
                    user_password = password.getText().toString();

                    if(user_email.equals("1") && user_email.equals("1")){
                        putSharedPreference(user_email,user_password);
                        pwindo.dismiss();
                        menuItem.setIcon(R.drawable.login248);
                        rel_val.setAlpha(1);

                        // click = false;
                    }else{

                        Toast.makeText(PlaceOrderActivity.this, "Wrong username or password", Toast.LENGTH_LONG).show();
                        menuItem.setIcon(R.drawable.login148);

                    }

                }
            });


            cross.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pwindo.dismiss();
                    rel_val.setAlpha(1);
                    // click = false;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public  boolean getImageStatus(ImageView imageView,boolean flag){

        if( flag==false){
            flag= true;
            imageView.setBackgroundResource(R.drawable.exp48);

        }else{

            flag= false;
            imageView.setBackgroundResource(R.drawable.col48);

        }

    return flag;
    }


    class GetReviewRating extends AsyncTask<Void,Void,Void>{

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

                            Map<String,String>  map = new HashMap<>();

                            map.put("RestaurantRatingRate",RestaurantRatingRate);
                            map.put("RestaurantRatingReview",RestaurantRatingReview);

                            Log.d("val",RestaurantRatingReview);
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
              ArrayAdapter adapter = new ReviewAdapter(PlaceOrderActivity.this,0,maps);
                listView.setAdapter(adapter);
      }
    }

    class AddMenuReview extends AsyncTask<Void,Void,Void>{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(PlaceOrderActivity.this);
            dialog.setCancelable(false);
            dialog.setMessage("Please Wait...");
            dialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

           if( returnValue.equals("success")){

                dialog.dismiss();

                Toast.makeText(PlaceOrderActivity.this,"You have successfully submitted your review",Toast.LENGTH_LONG).show();
            }
       }

        @Override
        protected Void doInBackground(Void... params) {

            ServiceHandler handler = new ServiceHandler();

            JsonFunctions functions = new JsonFunctions(handler);

            try {
                String json = functions.insertMenuReview(comment, "4", "1", "1", "1");

                if (json != null) {

                    JSONObject object = new JSONObject(json);

                    JSONObject reviewInfo = object.getJSONObject(REVIEWINFO);

                    returnValue = reviewInfo.getString(TYPE);

                }

            }catch (Exception e) {

            e.printStackTrace();
            }
                return null;
            }
        }
}
