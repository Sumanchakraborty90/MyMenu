package com.bitcanny.office.mymenu;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class ResturantInfo extends ActionBarActivity implements BaseSliderView.OnSliderClickListener {
    private SliderLayout mDemoSlider;
    String tableCode;
    TextView txt_share;
    String offer_id,title,description,imageUrl,value,type;
    List<Map<String,String>> sharedDocumnents;
    TextView txt_share_content;
    ProgressDialog dialog;
    private int lastPosition = -1;
    List<Map<String, String>> list = Collections.emptyList();
    List<Map<String, String>> list2 = Collections.emptyList();
    List<CategoryModel> categoryList = Collections.emptyList();
    static List<Map<String, String>> orderedItems = Collections.emptyList();
    static List<Map<String, String>> finalorderedItems = Collections.emptyList();
    static String resturantNameGlobal="";
    List<Map<String, String>> maps = Collections.emptyList();
    PopupWindow mPopupWindow;

    static String RestaurantRatingAddby = "RestaurantRatingAddby";
    static String RestaurantRatingRate = "RestaurantRatingRate";
    static String RestaurantRatingReview = "RestaurantRatingReview";
    String restaurantratingaddby, restaurantratingrate, restaurantratingreview;
    boolean boolFlag = false;
     EditText edt_lbl_code;


    Toolbar toolbar;
    TextView txt_address_details, txt_phone1_details, txt_phone2_details;
    ImageView img_map, slide_img;
    Button btn_open_menu;
    RelativeLayout rel3, rel4, rel10, rel5, rel11, rel_Submit;
    View view1, view2;

    // list of file paths


    private String[] FilePathStrings;
    private String[] FileNameStrings;
    // private File[] listFile;

    File file;

    boolean relLayFlag1 = false, relLayFlag2 = false, relLayFlag3 = false, relLayFlag4 = false;

    ///////////////////////////////////SHARED PREFERENCE\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    private static String MYPREF = "mypref";
    private static String EMAIL = "email";
    private static String PASSWORD = "password";
    SharedPreferences sharedPreferences;

    ///////////////////////////////////SHARED PREFERENCE ENDED\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    private static String RESTURANTINFO = "restaurantInfo";
    private static String INFO = "Info";
    //private static String IMAGEINFO = "imageInfo";
    private static String MENUINFO = "menuInfo";
    private static String RESTAURANTADDRESS = "RestaurantAddress";
    private static String RESTAURANTCITY = "RestaurantCity";
    private static String RESTAURANTID = "RestaurantID";
    private static String RESTAURANTLOGIMAGE = "RestaurantLogImage";
    private static String RESTAURANTNAME = "RestaurantName";
    private static String RESTAURANTPHONE1 = "RestaurantPhone1";
    private static String RESTAURANTPHONE2 = "RestaurantPhone2";
    private static String RESTAURANTPOSTCODE = "RestaurantPostCode";
    private static String RESTAURANTSTATE = "RestaurantState";
    private static String MenuItemDesc = "MenuItemDesc";
    private static String LATLONGINFO = "latLongInfo";
    private static String RESTAURANTDESCRIPTION = "RestaurantDescription";
    private static String LAT = "lat";
    private static String LNG = "lng";
     static String globalResturantId ;
    String resturantId;
    String restaurantLogImage;
    String restaurantName;
    String restaurantPhone1;
    String restaurantPhone2;
    String restaurantPostCode;
    String restaurantState;
    String restaurantAddress;
    String restaurantDescription;
    String lat, lng;
    RelativeLayout rel_val, rel_more;
    List<ResturantModel> restList = Collections.emptyList();
    String user_email, user_password;
    ScrollView scrl_view;

    MenuItem menuItem;
    ResturantEntryActivity activity = new ResturantEntryActivity();
    MenuInflater menuInflater;
    ImageView img_arrow, img_arrow1, img_arrow2, img_arrow3;
    ProgressBar pgr_bar;
    TextView txt_abt_details, txt_more,txt_abt_us_hd,txt_locate_us_hd,txt_review_rating_hd,txt_comments,txt_avg_rating;
    boolean click = false;
    private PopupWindow pwindo;
    LinearLayout resturant_info, lin_submit;
    PlaceOrderSqlHelperDao dao;
    Button btn_submit,btn_start_order;
    Menu Optmenu;
    ListView list_view;
    RatingBar ratingBar;
    Typeface typeface;
    DrawerLayout drawerLayout;
    boolean a= false;
    ImageButton fb_login;
    CardView card_view_txt;
    int heightScreen,widthScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.resturant_main);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {

            overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);

        }






        dao = new PlaceOrderSqlHelperDao(this);
        restList = dao.getResturantDetails();
        typeface = Typeface.createFromAsset(getAssets(), "fonts/ufonts.com_century-gothic.ttf");
        maps = new ArrayList<>();
        sharedDocumnents= new ArrayList<>();
        Log.d("list size", restList.size() + "");
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
       // display.getSize(size);
        orderedItems = new ArrayList<>();
        finalorderedItems = new ArrayList<>();
        pgr_bar = (ProgressBar) findViewById(R.id.pgr_bar);
        int width = size.x;
        int height = size.y;
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        txt_abt_details = (TextView) findViewById(R.id.txt_abt_details);
        card_view_txt= (CardView) findViewById(R.id.card_view_txt);
        btn_start_order = (Button) findViewById(R.id.btn_start_order);
        txt_abt_details.setTypeface(typeface);
        txt_address_details = (TextView) findViewById(R.id.txt_address_details);
        txt_address_details.setTypeface(typeface);
        txt_phone1_details = (TextView) findViewById(R.id.txt_phone1_details);
        txt_phone1_details.setTypeface(typeface);
        txt_phone2_details = (TextView) findViewById(R.id.txt_phone2_details);
        txt_phone2_details.setTypeface(typeface);
        mDemoSlider = (SliderLayout) findViewById(R.id.slider);
        img_map = (ImageView) findViewById(R.id.img_map);
        btn_open_menu = (Button) findViewById(R.id.btn_open_menu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_lay);
        resturant_info = (LinearLayout) findViewById(R.id.resturant_info);
        list_view = (ListView) findViewById(R.id.list_view);
        rel3 = (RelativeLayout) findViewById(R.id.rel3);
        rel4 = (RelativeLayout) findViewById(R.id.rel4);
        rel10 = (RelativeLayout) findViewById(R.id.rel10);
        img_arrow = (ImageView) findViewById(R.id.img_arrow);
        img_arrow1 = (ImageView) findViewById(R.id.img_arrow1);
        img_arrow2 = (ImageView) findViewById(R.id.img_arrow2);
        img_arrow3 = (ImageView) findViewById(R.id.img_arrow3);
        txt_abt_details = (TextView) findViewById(R.id.txt_abt_details);
        fb_login = (ImageButton)findViewById(R.id.fb_login);
        txt_abt_details.setTypeface(typeface);
        rel5 = (RelativeLayout) findViewById(R.id.rel5);
        rel11 = (RelativeLayout) findViewById(R.id.rel11);
        view1 = (View) findViewById(R.id.view1);
        view2 = (View) findViewById(R.id.view2);
        lin_submit = (LinearLayout) findViewById(R.id.lin_submit);
        scrl_view = (ScrollView) findViewById(R.id.scrl_view);
        btn_open_menu = (Button) findViewById(R.id.btn_open_menu);
        btn_open_menu.setTypeface(typeface);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_submit.setTypeface(typeface);
        rel_val = (RelativeLayout) findViewById(R.id.rel_val);
        ratingBar = (RatingBar) findViewById(R.id.rating_bar);
        sharedPreferences = getSharedPreferences(MYPREF, Context.MODE_PRIVATE);
        rel_more = (RelativeLayout) findViewById(R.id.rel_more);
        txt_more = (TextView) findViewById(R.id.txt_more);
     //   Log.d("json",getJson());
        txt_abt_us_hd = (TextView) findViewById(R.id.txt_abt_us_hd);
        txt_locate_us_hd = (TextView) findViewById(R.id.txt_locate_us_hd);
        txt_review_rating_hd =(TextView) findViewById(R.id.txt_review_rating_hd);
        txt_comments= (TextView) findViewById(R.id.txt_comments);
        txt_avg_rating = (TextView) findViewById(R.id.txt_avg_rating);

        txt_more.setTypeface(typeface);
        txt_abt_us_hd.setTypeface(typeface);
        txt_locate_us_hd.setTypeface(typeface);
        txt_review_rating_hd.setTypeface(typeface);
        txt_comments.setTypeface(typeface);
        txt_avg_rating.setTypeface(typeface);
        btn_start_order.setTypeface(typeface);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        heightScreen = displaymetrics.heightPixels;
        widthScreen = displaymetrics.widthPixels;

       /* try {
            Transition exitTrans = new Explode();
            getWindow().setExitTransition(exitTrans);

            Transition reenterTrans = new Slide();
            getWindow().setReenterTransition(reenterTrans);

            Transition enterTrans = new Explode();
            getWindow().setEnterTransition(enterTrans);

            Transition returnTrans = new Slide();
            getWindow().setReturnTransition(returnTrans);

        }catch (Exception e){

            e.printStackTrace();
        }*/
        //putSharedPreferenceTotalAmtAndSelectedItems("0","0");

     /*   new CountDownTimer(10000000, 1) {

            public void onTick(long millisUntilFinished) {
                changeColor();
            }

            public void onFinish() {
               // mTextField.setText("done!");
            }
        }.start();*/


        try {


        /*undle bundle = getIntent().getExtras();
        resturantId = bundle.getString(RESTAURANTID);
        restaurantLogImage = bundle.getString(RESTAURANTLOGIMAGE);*/
       /* restaurantName = bundle.getString(RESTAURANTNAME);
        restaurantPhone1 = bundle.getString(RESTAURANTPHONE1);
        restaurantPhone2 = bundle.getString(RESTAURANTPHONE2);
        restaurantPostCode = bundle.getString(RESTAURANTPOSTCODE);
        restaurantState = bundle.getString(RESTAURANTSTATE);
        restaurantAddress = bundle.getString(RESTAURANTADDRESS);
        restaurantDescription = bundle.getString(RESTAURANTDESCRIPTION);*/
        /*lat = bundle.getString(LAT);
        lng = bundle.getString(LNG);*/

            List<LatLngModel> latLnglist = dao.getLatLng();
            Log.d("latlngsize", latLnglist.size() + "");

            for (int i = 0; i < latLnglist.size(); i++) {

                lat = latLnglist.get(i).getLatitude();
                lng = latLnglist.get(i).getLongitude();

            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            //  dao.addResturantDetails(new ResturantModel(restaurantLogImage,restaurantName,resturantId,
            // restaurantPhone1,restaurantPhone2,restaurantPostCode,restaurantAddress,restaurantState,restaurantDescription));

            resturantId = restList.get(0).getResturantId();
            restaurantName = restList.get(0).getRestaurantName();
            resturantNameGlobal = restaurantName;
            restaurantPhone1 = restList.get(0).getRestaurantPhone1();
            restaurantPhone2 = restList.get(0).getRestaurantPhone2();
            restaurantPostCode = restList.get(0).getRestaurantPostCode();
            restaurantState = restList.get(0).getRestaurantState();
            restaurantAddress = restList.get(0).getRestaurantAddress();
            restaurantDescription = restList.get(0).getRestaurantDescription();
            globalResturantId = resturantId;
            Log.d("val", restaurantName + "/" + restaurantDescription + "/" + restaurantPostCode + "/" + restaurantState + "/" + restaurantPhone1 + "/" + restaurantPhone2+"/"+restaurantAddress);
            txt_abt_details.setText(restaurantDescription);
            txt_address_details.setText(restaurantAddress + "," + restaurantPostCode + "," + restaurantState);
            txt_phone1_details.setText(restaurantPhone1);
            txt_phone2_details.setText(restaurantPhone2);


        } catch (Exception e) {

            e.printStackTrace();
        }


        //getFromSdcard();
        setSupportActionBar(toolbar);
        list = ResturantEntryActivity.list2;
        list2 = ResturantEntryActivity.list3;

       /* if(list.size() == 0){

            list = BarCodeActivity.list2;
            list2 = BarCodeActivity.list3;

        }*/

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(restaurantName);

        scrl_view.setHorizontalScrollBarEnabled(false);
        scrl_view.setVerticalScrollBarEnabled(false);
        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.nav_drawer_id);

        drawerFragment.setUi(drawerLayout, toolbar);
       /* try {
            if (getFromSdcard("/MenuApp/menu/image/").size() != list.size()) {
*/
        new DownLoadFromServer().execute();
          /*  }
        }catch (Exception e){

            e.printStackTrace();
        }*/

        if( sharedPreferences.getString("tableCode", "").equals("")) {

            btn_start_order.setText("Start Order");

        }else{

            btn_start_order.setText("Continue Order");

        }

        btn_start_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if( sharedPreferences.getString("tableCode", "").equals("")) {
                showOrderPopupWindow();
            }else{

                btn_start_order.setText("Continue Order");
                try {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(ResturantInfo.this);
                        Intent intent = new Intent(ResturantInfo.this, MyMainCategory.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        // TransitionManager.beginDelayedTransition(mRootView, new);
                        //overridePendingTransition(R.anim.abc_slide_out_bottom, R.anim.abc_slide_in_bottom);
                        intent.putExtra("view_flag", true);
                        startActivity(intent, options.toBundle());
                    } else {
                        Intent intent = new Intent(ResturantInfo.this, MyMainCategory.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        // TransitionManager.beginDelayedTransition(mRootView, new);
                        //overridePendingTransition(R.anim.abc_slide_out_bottom, R.anim.abc_slide_in_bottom);
                        intent.putExtra("view_flag", true);
                        startActivity(intent);
                        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                    }


                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
            }
        });


        try {
            //  ResturantEntryActivity.DownloadFromUrl("/MenuApp/Map","0.jpg","https://maps.googleapis.com/maps/api/staticmap?center=" + getString(restaurantName) + getString(restaurantAddress) + getString(restaurantState) + getString(restaurantPostCode) + "&zoom=13&size=800x400&maptype=roadmap&markers=color:blue%7Clabel:S%7C" + lat + "," + lng+"");


            Picasso.with(ResturantInfo.this)
                    .load(new File(getFromSdcard("/MenuApp/menu/image/").get(0)))

                            //  .load("https://maps.googleapis.com/maps/api/staticmap?center="+restaurantAddress+"&zoom=13&size=600x300&maptype=roadmap&markers=color:blue%7Clabel:S%7C"+lat+","+lng)
                    .placeholder(R.mipmap.ic_launcher) // optional

                    .error(R.mipmap.ic_launcher)
                    .fit().centerCrop()
                    .transform(new RoundedTransformation(20, 0))// optional
                    .into(img_map);

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {


           /* list_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });*/

            for (int index = 0; index < getFromSdcard("/MenuApp/Resturant/").size(); index++) {
                com.daimajia.slider.library.SliderTypes.TextSliderView textSliderView = new com.daimajia.slider.library.SliderTypes.TextSliderView(ResturantInfo.this);
                // initialize a SliderLayout
                textSliderView
                        .description("")
                        /*.image(JsonFunctions.BASE_URL + list.get(index).get("RestaurantImageUrl"))*/
                        .image(new File(getFromSdcard("/MenuApp/Resturant/").get(index)))
                        .setScaleType(BaseSliderView.ScaleType.Fit)
                        .setOnSliderClickListener(ResturantInfo.this);

                //add your extra information
           /* textSliderView.getBundle()
                    .putString("extra","");*/
                mDemoSlider.stopAutoCycle();
                mDemoSlider.addSlider(textSliderView);
            }
            mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
            mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
            mDemoSlider.setCustomAnimation(new com.daimajia.slider.library.Animations.DescriptionAnimation());
            mDemoSlider.setDuration(6000);
        } catch (Exception e) {

            e.printStackTrace();

        }


        img_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(ResturantInfo.this);
                    Intent intent = new Intent(ResturantInfo.this, ResturantMap.class);

                    intent.putExtra("lat", lat);
                    intent.putExtra("lng", lng);
                    intent.putExtra("title", restaurantName);
                    intent.putExtra("desc", restaurantDescription);
                    startActivity(intent,options.toBundle());
                }else{

                   // ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(ResturantInfo.this);
                    Intent intent = new Intent(ResturantInfo.this, ResturantMap.class);

                    intent.putExtra("lat", lat);
                    intent.putExtra("lng", lng);
                    intent.putExtra("title", restaurantName);
                    intent.putExtra("desc", restaurantDescription);
                    startActivity(intent);
                    overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                }


            }
        });

       /* if(sharedPreferences.getString("tableCode","").equals("") ){

            Log.d("tblCd",tableCode);
            btn_open_menu.setText("Continue Order");

        }
*/
        btn_open_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setupWindowAnimations();

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(ResturantInfo.this);
                        Intent intent = new Intent(ResturantInfo.this, MyMainCategory.class);

                        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        overridePendingTransition(R.anim.abc_slide_in_bottom, R.anim.abc_fade_in);
                        intent.putExtra("view_flag", false);
                        startActivity(intent,options.toBundle());

                    }else{


                    Intent intent = new Intent(ResturantInfo.this, MyMainCategory.class);

                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

                    intent.putExtra("view_flag", false);
                    startActivity(intent);
                    overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);

                    }
            }
        });

        rel_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        txt_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* Intent intent = new Intent(ResturantInfo.this, ResturantReviewRating.class);

                intent.putExtra("Resturant_Id", resturantId);
                startActivity(intent);*/

            }
        });

        rel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                relLayFlag1 = getImageStatus(img_arrow, relLayFlag1);

                if (relLayFlag1 == false) {


                    Animation animation = AnimationUtils.loadAnimation(ResturantInfo.this,  R.anim.abc_slide_in_top );
                    txt_abt_details.startAnimation(animation);
                    Animation animation1 = AnimationUtils.loadAnimation(ResturantInfo.this,  R.anim.abc_slide_in_top );
                    card_view_txt.startAnimation(animation1);

                    txt_abt_details.setVisibility(View.GONE);
                    view1.setVisibility(View.GONE);
                    card_view_txt.setVisibility(View.GONE);

                } else {


                    Animation animation = AnimationUtils.loadAnimation(ResturantInfo.this,  R.anim.down_from_top );
                    txt_abt_details.startAnimation(animation);
                    Animation animation1 = AnimationUtils.loadAnimation(ResturantInfo.this,  R.anim.down_from_top );
                    card_view_txt.startAnimation(animation1);
                    txt_abt_details.setVisibility(View.VISIBLE);
                    card_view_txt.setVisibility(View.VISIBLE);
                   /* ObjectAnimator anim = ObjectAnimator.ofFloat(txt_abt_details, "y", 50f);
                    anim.setDuration(1000);
                    anim.start();*/
                    view1.setVisibility(View.GONE);
                }

            }
        });

        rel4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                relLayFlag2 = getImageStatus(img_arrow1, relLayFlag2);

                if (relLayFlag2 == false) {

                    Animation animation = AnimationUtils.loadAnimation(ResturantInfo.this,  R.anim.abc_slide_in_top );
                    rel5.startAnimation(animation);
                    rel5.setVisibility(View.GONE);
                    view2.setVisibility(View.GONE);

                } else {

                    Animation animation = AnimationUtils.loadAnimation(ResturantInfo.this,  R.anim.down_from_top );
                    rel5.startAnimation(animation);


                  //  lastPosition = position;
                    rel5.setVisibility(View.VISIBLE);
                   /* ObjectAnimator anim = ObjectAnimator.ofFloat(rel5, "y", 10f);
                    anim.setDuration(1000);
                    anim.start();*/
                    view2.setVisibility(View.GONE);
                }

            }
        });

        fb_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             /*   Intent intent = new Intent(ResturantInfo.this,FaceBookActivity.class);

                intent.putExtra("Item_name",restaurantName);
                intent.putExtra("Desc_item",restaurantDescription);
                intent.putExtra("MenuItemImageURL",JsonFunctions.BASE_URL + dao.getSliderImageUrl().get(0).getSlider_image_url());
                startActivity(intent);*/

            }
        });
        rel10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(ResturantInfo.this);
                    Intent intent = new Intent(ResturantInfo.this, ResturantReviewRating.class);

                    intent.putExtra("Resturant_Id", resturantId);
                    startActivity(intent,options.toBundle());

                }else{

                    Intent intent = new Intent(ResturantInfo.this, ResturantReviewRating.class);

                    intent.putExtra("Resturant_Id", resturantId);
                    startActivity(intent);
                    overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);

                }
               /* relLayFlag3 = getImageStatus(img_arrow2, relLayFlag3);

                if (relLayFlag3 == false) {
                    list_view.setVisibility(View.GONE);
                    rel_more.setVisibility(View.GONE);
                    pgr_bar.setVisibility(View.GONE);
                    txt_comments.setVisibility(View.GONE);
                    txt_avg_rating.setVisibility(View.GONE);

                } else {

                    list_view.setVisibility(View.GONE);
                    rel_more.setVisibility(View.VISIBLE);
                    txt_comments.setVisibility(View.VISIBLE);
                    txt_avg_rating.setVisibility(View.VISIBLE);
                    //  pgr_bar.setVisibility(View.VISIBLE);
                    new GetReviewRating().execute();
                   *//* ObjectAnimator anim = ObjectAnimator.ofFloat(list_view, "y", 50f);
                    anim.setDuration(1000);
                    anim.start();*//*

                }*/
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //  btn_submit.setBackgroundResource(R.drawable.white_focus);
                if (sharedPreferences.getString("email", "").equals("") || sharedPreferences.getString("password", "").equals("")) {

                    // initiatePopupWindow();
                    // resturant_info.setAlpha((float) .27);
                    //  rel_val.setAlpha((float) .27);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(ResturantInfo.this);
                        Intent intent = new Intent(ResturantInfo.this, LogInActivity.class);
                        startActivity(intent,options.toBundle());
                    }else{
                        Intent intent = new Intent(ResturantInfo.this, LogInActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);

                    }
                } else if (sharedPreferences.getString("email", "").equals("1") || sharedPreferences.getString("password", "").equals("1")) {


                    /*comment = edt_comment.getText().toString();
                    new AddMenuReview().execute();*/
                    // sharedPreferences.edit().clear().commit();

                }
            }
        });

        rel11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                relLayFlag4 = getImageStatus(img_arrow3, relLayFlag4);

                if (relLayFlag4 == false) {
                    lin_submit.setVisibility(View.GONE);


                } else {

                    lin_submit.setVisibility(View.VISIBLE);

                   /* ObjectAnimator anim = ObjectAnimator.ofFloat(lin_submit, "y", 50f);
                    anim.setDuration(1000);
                    anim.start();*/

                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_resturant_info, menu);

      /*  if(sharedPreferences.getString("email", "").equals("1") || sharedPreferences.getString("password","").equals("1")){
            menu.findItem(R.id.action_logIn).setIcon(R.drawable.login248);
            menu.findItem(R.id.action_logIn).setTitle("Log Out");
        }*/

        return true;
    }

    public void putSharedPreference(String email, String password) {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", email);
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
        if (id == R.id.action_share) {
            if(boolFlag == false) {

                showPopupWindow();
               // rel_val.setAlpha((float).45);
                boolFlag =true;

            }



            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSliderClick(BaseSliderView baseSliderView) {

    }






   /* private void initiatePopupWindow() {
        try {
// We need to get the instance of the LayoutInflater
            LayoutInflater inflater = (LayoutInflater) ResturantInfo.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.log_in_popup,
                    (ViewGroup) findViewById(R.id.pop_up_lay));
            pwindo = new PopupWindow(layout, 350, 450, true);
            pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);

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
                        resturant_info.setAlpha(1);

                       // click = false;
                    }else{
                        Toast.makeText(ResturantInfo.this, "Wrong username or password", Toast.LENGTH_LONG).show();
                        menuItem.setIcon(R.drawable.login148);
                    }

                }
            });


            cross.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pwindo.dismiss();
                    resturant_info.setAlpha(1);
                   // click = false;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
*/

    public boolean getImageStatus(ImageView imageView, boolean flag) {

        if (flag == false) {
            flag = true;
            imageView.setBackgroundResource(R.drawable.exp48);

        } else {

            flag = false;
            imageView.setBackgroundResource(R.drawable.col48);

        }

        return flag;
    }


    public String getString(String val) {

        String retVal = val.replace(" ", "+");
        return retVal;

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //ResturantEntryActivity.list1.clear();
       /* dao.dropFrntEndMenu();
        dao.createTableFrontEndMenu();*/
        // dao.dropTable(RESTAURANTNAME);
        //  dao.dropTable("menu");
        //  dao.dropTable("category");
        // dao.createTableCategory();
        //dao.dropTable("latitudelongitude");

        /*dao.createTableResturant();*/

        //System.exit(0);
      /* new MyDialog().execute();*/

        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);

        // android.os.Process.killProcess(android.os.Process.myPid());

        //finish();
    }


    public List<String> getFromSdcard(String path) {
        ArrayList<String> f = new ArrayList<String>();
        File[] listFile;
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + path);

        if (file.isDirectory()) {
            listFile = file.listFiles();


            for (int i = 0; i < listFile.length; i++) {

                f.add(listFile[i].getAbsolutePath());

                Log.d("val", listFile[i].getAbsolutePath());

            }
        }

        return f;
    }

    class DownLoadFromServer extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... params) {

            try {

                if(getFromSdcard("/MenuApp/MenuItemGrid/").size()==0) {
                    ResturantEntryActivity.DownloadFromUrl("/MenuApp/menu/image/", "map.jpg", "https://maps.googleapis.com/maps/api/staticmap?center=" + getString(restaurantName) + getString(restaurantAddress) + getString(restaurantState) + getString(restaurantPostCode) + "&zoom=17&size=" + widthScreen + "x" + heightScreen + "&maptype=roadmap&markers=color:blue%7Clabel:+" + "," + getString(restaurantName) + "," + "%7C" + lat + "," + lng + "");

                }
            } catch (Exception e) {

                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dao.close();
    }

    class MyDialog extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... params) {
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);

            AlertDialog.Builder builder = new AlertDialog.Builder(ResturantInfo.this);
            //Uncomment the below code to Set the message and title from the strings.xml file
            //builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);

            //Setting message manually and performing action on button click
            builder.setMessage("Do you want to close this application ?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            moveTaskToBack(true);
                            android.os.Process.killProcess(android.os.Process.myPid());
                            System.exit(1);
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //  Action for 'NO' Button
                            dialog.cancel();
                        }
                    });

            //Creating dialog box
            AlertDialog alert = builder.create();
            //Setting the title manually
            alert.setTitle("AlertDialogExample");
            alert.show();

         /*   Builder alertDialogBuilder = new Builder(ResturantInfo.this);
            alertDialogBuilder.setTitle("Exit Application?");
            alertDialogBuilder
                    .setMessage("Click yes to exit!")
                    .setCancelable(false)
                    .setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    moveTaskToBack(true);
                                    android.os.Process.killProcess(android.os.Process.myPid());
                                    System.exit(1);
                                }
                            })

                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            dialog.cancel();
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();


        }
    }*/

        }
    }

   /* @Override
    protected void onStop() {


        super.onStop();


    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();

       // new MyDialog().execute();
    }*/

    class GetReviewRating extends AsyncTask<Void, Void, Void> {

        String returnVal;
        JSONArray Info;

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try {

                if (returnVal.equals("success")) {


                    pgr_bar.setVisibility(View.GONE);


                    txt_comments.setText("Comments: "+String.valueOf(Info.length()));
                    double a=0;
                    for(int index = 0;index<maps.size();index++){

                       a = a+ Double.valueOf(maps.get(index).get(RestaurantRatingRate));


                    }

                    a = a/maps.size();
                    txt_avg_rating.setText("Rating: "+String.valueOf(a));
                   // ArrayAdapter adapter = new ResturantInfoReviewRatingAdapter(ResturantInfo.this, 0, maps);
                    //list_view.setAdapter(adapter);


                } else {

                    pgr_bar.setVisibility(View.GONE);

                }

            } catch (Exception e) {
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
                String json = functions.getResturantReviewRating(resturantId,"");

                if (json != null) {

                    JSONObject object = new JSONObject(json);

                    JSONObject reviewInfo = object.getJSONObject("reviewInfo");

                    returnVal = reviewInfo.getString("type");

                    if (returnVal.equals("success")) {

                        Info = reviewInfo.getJSONArray("Info");

                        for (int index = 0; index < 1; index++) {

                            JSONObject jsonObject = Info.getJSONObject(index);

                            restaurantratingaddby = jsonObject.getString(RestaurantRatingAddby);
                            restaurantratingrate = jsonObject.getString(RestaurantRatingRate);

                            Log.d("restaurantratingrate", restaurantratingrate);
                            restaurantratingreview = jsonObject.getString(RestaurantRatingReview);

                            Map<String, String> map = new HashMap<>();

                            map.put(RestaurantRatingAddby, restaurantratingaddby);
                            map.put(RestaurantRatingRate, restaurantratingrate);
                            map.put(RestaurantRatingReview, restaurantratingreview);

                            maps.add(map);
                        }


                    }


                }
            } catch (Exception e) {

                e.printStackTrace();
            }
            return null;
        }
    }

   /* private void setupWindowAnimations() {
        Explode explode = new Explode();
        explode.setDuration(2000);
        getWindow().setExitTransition(explode);
    }*/




    private void changeColor(){
      //  ImageView image = (ImageView) findViewById(R.id.imageView1);

        Random rnd = new Random();
      //  Color[] colors = {new Color(255,0,0),new Color(255,255,255),new Color(0,0,255)};
        int color = Color.argb(255, rnd.nextInt(254), rnd.nextInt(254), rnd.nextInt(254));
        Bitmap sourceBitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.abc_ic_menu_share_mtrl_alpha);
        changeBitmapColor(sourceBitmap, fb_login, color);

    }

    private void changeBitmapColor(Bitmap sourceBitmap, ImageView image, int color) {

        Bitmap resultBitmap = Bitmap.createBitmap(sourceBitmap, 0, 0,
                sourceBitmap.getWidth() - 1, sourceBitmap.getHeight() - 1);
        Paint p = new Paint();
        ColorFilter filter = new LightingColorFilter(color, 1);
        p.setColorFilter(filter);
        image.setImageBitmap(resultBitmap);

        Canvas canvas = new Canvas(resultBitmap);
        canvas.drawBitmap(resultBitmap, 0, 0, p);
    }

    private void showPopupWindow(){

       new ResturantOffer().execute();
        LayoutInflater mLayoutInflater=LayoutInflater.from(ResturantInfo.this);

        View mView=mLayoutInflater.inflate(R.layout.share_popup, null /*(ViewGroup)resturant_review_rating*/);



        mPopupWindow=new PopupWindow(mView,  WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);

        // mPopupWindow=new PopupWindow(mView,LayoutParams.WRAP_CONTENT);

        mPopupWindow.showAtLocation(mView, Gravity.CENTER, 0, 0);

        mPopupWindow.setContentView(mView);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(),
                ""));

        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(false);
        mPopupWindow.setOutsideTouchable(false);
        //  mPopupWindow.setOutsideTouchable(true);
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(ResturantInfo.this, R.anim.animation);
        mView.startAnimation(hyperspaceJumpAnimation);


        txt_share_content = (TextView)mView.findViewById(R.id.txt_share_content);
        final RelativeLayout fb_pop= (RelativeLayout)mView.findViewById(R.id.fb_pop);
        txt_share= (TextView)mView.findViewById(R.id.txt_share);

      //  Button btn_submit_rating= (Button)mView.findViewById(R.id.btn_submit_rating);
        ImageView btn_close= (ImageView)mView.findViewById(R.id.btn_close);
      /*  mBtnCancel=(Button) mView.findViewById(R.id.btn_cancel);

        mBtnCancel.setOnClickListener(this);*/

        CardView card_view3 = (CardView)mView.findViewById(R.id.card_view3);

        CardView card_view4 = (CardView)mView.findViewById(R.id.card_view4);

        Drawable d = new ColorDrawable(Color.WHITE);

        d.setAlpha(130);



        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());

        mPopupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                mPopupWindow.dismiss();
                rel_val.setAlpha(1);
                return true;
            }

        });

        card_view3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(ResturantInfo.this);
                    Intent intent = new Intent(ResturantInfo.this, FaceBookActivity.class);

                    intent.putExtra("Item_name", restaurantName);
                    intent.putExtra("Desc_item", restaurantDescription);
                    intent.putExtra("MenuItemImageURL", JsonFunctions.BASE_URL + dao.getSliderImageUrl().get(0).getSlider_image_url());
                    startActivity(intent, options.toBundle());

                    mPopupWindow.dismiss();

                } else {

                    Intent intent = new Intent(ResturantInfo.this, FaceBookActivity.class);

                    intent.putExtra("Item_name", restaurantName);
                    intent.putExtra("Desc_item", restaurantDescription);
                    intent.putExtra("MenuItemImageURL", JsonFunctions.BASE_URL + dao.getSliderImageUrl().get(0).getSlider_image_url());
                    startActivity(intent);
                    overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                    mPopupWindow.dismiss();

                }


            }
        });

        card_view4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, "I Love You.");
                    sendIntent.setType("text/plain");
                    sendIntent.setPackage("com.whatsapp");
                    startActivity(sendIntent);

                }catch (Exception e){

                    Toast.makeText(ResturantInfo.this,"Please install whatsapp",Toast.LENGTH_LONG).show();
                    mPopupWindow.dismiss();
                    e.printStackTrace();
                }
            }
        });
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // review = edit_text.getText().toString();

                mPopupWindow.dismiss();
                rel_val.setAlpha(1);
                fb_pop.setAlpha(1);
                boolFlag = false;

            }
        });


        //mPopupWindow.showAsDropDown(mBtnPopUpWindow, 0, 0, Gravity.LEFT);

        getWindow().setBackgroundDrawable(d);





    }


    private void showOrderPopupWindow(){


        LayoutInflater mLayoutInflater=LayoutInflater.from(ResturantInfo.this);

        View mView=mLayoutInflater.inflate(R.layout.table_code_popup, null /*(ViewGroup)resturant_review_rating*/);



        mPopupWindow=new PopupWindow(mView,  WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);

        // mPopupWindow=new PopupWindow(mView,LayoutParams.WRAP_CONTENT);

        mPopupWindow.showAtLocation(mView, Gravity.CENTER, 0, 0);

        mPopupWindow.setContentView(mView);
        /*mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(),
                ""));
*/

        //  mPopupWindow.setOutsideTouchable(true);
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(ResturantInfo.this, R.anim.animation);
        mView.startAnimation(hyperspaceJumpAnimation);


        TextView txt_share_content = (TextView)mView.findViewById(R.id.txt_share_content);
        final RelativeLayout fb_pop= (RelativeLayout)mView.findViewById(R.id.fb_pop);

        //  Button btn_submit_rating= (Button)mView.findViewById(R.id.btn_submit_rating);
        ImageView btn_close= (ImageView)mView.findViewById(R.id.btn_cross);

        final Button btn_start_order = (Button) mView.findViewById(R.id.btn_start_order);
        TextView txt_rest_title = (TextView)mView.findViewById(R.id.txt_rest_title);
        edt_lbl_code = (EditText) mView.findViewById(R.id.edt_lbl_code);

        txt_rest_title.setText(restaurantName);
        final String resturant_Code = "";
      /*  mBtnCancel=(Button) mView.findViewById(R.id.btn_cancel);

        mBtnCancel.setOnClickListener(this);*/

     /*   CardView card_view3 = (CardView)mView.findViewById(R.id.card_view3);

        CardView card_view4 = (CardView)mView.findViewById(R.id.card_view4);*/

        Drawable d = new ColorDrawable(Color.WHITE);

        d.setAlpha(130);

       // fb_pop.setAlpha((float) .78);

        btn_start_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableCode= edt_lbl_code.getText().toString();
                new CheckResturantOrder().execute(tableCode);
            }
        });

        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());

        mPopupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                mPopupWindow.dismiss();
                rel_val.setAlpha(1);
                return true;
            }

        });

     /*   card_view3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(ResturantInfo.this);
                    Intent intent = new Intent(ResturantInfo.this, FaceBookActivity.class);

                    intent.putExtra("Item_name", restaurantName);
                    intent.putExtra("Desc_item", restaurantDescription);
                    intent.putExtra("MenuItemImageURL", JsonFunctions.BASE_URL + dao.getSliderImageUrl().get(0).getSlider_image_url());
                    startActivity(intent, options.toBundle());

                } else {

                    Intent intent = new Intent(ResturantInfo.this, FaceBookActivity.class);

                    intent.putExtra("Item_name", restaurantName);
                    intent.putExtra("Desc_item", restaurantDescription);
                    intent.putExtra("MenuItemImageURL", JsonFunctions.BASE_URL + dao.getSliderImageUrl().get(0).getSlider_image_url());
                    startActivity(intent);
                    overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);


                }


            }
        });

        card_view4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                    sendIntent.setType("text/plain");
                    sendIntent.setPackage("com.whatsapp");
                    startActivity(sendIntent);

                }catch (Exception e){

                    Toast.makeText(ResturantInfo.this,"Please install whatsapp",Toast.LENGTH_LONG).show();
                    mPopupWindow.dismiss();
                    e.printStackTrace();
                }
            }
        });*/
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // review = edit_text.getText().toString();

                mPopupWindow.dismiss();

                boolFlag = false;

            }
        });


        //mPopupWindow.showAsDropDown(mBtnPopUpWindow, 0, 0, Gravity.LEFT);

        getWindow().setBackgroundDrawable(d);





    }

    public void  putSharedPreferenceTotalAmtAndSelectedItems(String selectedItems,String totalAmt){

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("selectedItems", selectedItems);
        editor.putString("totalAmt", totalAmt);

        editor.commit();

    }

 class  CheckResturantOrder extends AsyncTask<String,Void,Void> {

     String type;


     @Override
     protected void onPreExecute() {
         super.onPreExecute();

         dialog = new ProgressDialog(ResturantInfo.this);
         dialog.setMessage("Authenticating");
         dialog.setCancelable(false);
         dialog.show();

     }


     @Override
     protected Void doInBackground(String... params) {


         ServiceHandler handler = new ServiceHandler();

         JsonFunctions functions = new JsonFunctions(handler);

         tableCode = params[0];

         String json = functions.authenticateOrderCode(globalResturantId,tableCode );

         try {

             JSONObject jsonObject = new JSONObject(json);

             type = jsonObject.getString("type");


         } catch (Exception e) {

             e.printStackTrace();

         }


         return null;
     }


     @Override
     protected void onPostExecute(Void aVoid) {
         super.onPostExecute(aVoid);
         mPopupWindow.dismiss();
         dialog.dismiss();
         if (type.equals("success")) {
             putSharedPreferenceTableCode(tableCode);
             //putSharedPreferenceTableNumber();
             btn_start_order.setText("Continue Order");
             try {

                 if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                     ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(ResturantInfo.this);
                     Intent intent = new Intent(ResturantInfo.this, MyMainCategory.class);
                     intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                     // TransitionManager.beginDelayedTransition(mRootView, new);
                     //overridePendingTransition(R.anim.abc_slide_out_bottom, R.anim.abc_slide_in_bottom);
                     intent.putExtra("view_flag", true);
                     startActivity(intent, options.toBundle());
                 } else {
                     Intent intent = new Intent(ResturantInfo.this, MyMainCategory.class);
                     intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                     // TransitionManager.beginDelayedTransition(mRootView, new);
                     //overridePendingTransition(R.anim.abc_slide_out_bottom, R.anim.abc_slide_in_bottom);
                     intent.putExtra("view_flag", true);
                     startActivity(intent);
                     overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                 }


             } catch (Exception e) {

                 e.printStackTrace();
             }
           /* class FireMissilesDialogFragment extends DialogFragment {
                @Override
                public Dialog onCreateDialog(Bundle savedInstanceState) {
                    // Use the Builder class for convenient dialog construction
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Proceed ?")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // FIRE ZE MISSILES!

                                   // if(edt_lbl_code.getText().toString().equals(resturant_Code) ) {


                                        try {

                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(ResturantInfo.this);
                                                Intent intent = new Intent(ResturantInfo.this, MyMainCategory.class);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                                // TransitionManager.beginDelayedTransition(mRootView, new);
                                                //overridePendingTransition(R.anim.abc_slide_out_bottom, R.anim.abc_slide_in_bottom);
                                                intent.putExtra("view_flag", true);
                                                startActivity(intent, options.toBundle());
                                            } else {
                                                Intent intent = new Intent(ResturantInfo.this, MyMainCategory.class);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                                // TransitionManager.beginDelayedTransition(mRootView, new);
                                                //overridePendingTransition(R.anim.abc_slide_out_bottom, R.anim.abc_slide_in_bottom);
                                                intent.putExtra("view_flag", true);
                                                startActivity(intent);
                                                overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                                            }


                                        } catch (Exception e) {

                                            e.printStackTrace();
                                        }
                                    }


                            }).show();
                          *//*  .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // User cancelled the dialog
                                }
                            });*//*
                    // Create the AlertDialog object and return it
                    return builder.create();
                }
            }
        }else {


         *//*   Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(ResturantInfo.this, R.anim.animation);
            btn_start_order.startAnimation(hyperspaceJumpAnimation);
            Toast.makeText(ResturantInfo.this, "Wrong Table Code", Toast.LENGTH_LONG);*//*


        }*/

         }else{

             Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(ResturantInfo.this, R.anim.animation);
             btn_start_order.startAnimation(hyperspaceJumpAnimation);
             Toast.makeText(ResturantInfo.this, "Wrong Table Code", Toast.LENGTH_LONG);

         }
     }
 }

    class GenerateTableCode extends  AsyncTask<Void,Void,Void>{

        String type,tableCode;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(ResturantInfo.this);
            dialog.setMessage("Authenticating");
            dialog.setCancelable(false);
            dialog.show();
        }



        @Override
        protected Void doInBackground(Void... params) {

           ServiceHandler handler = new ServiceHandler();

            JsonFunctions functions = new JsonFunctions(handler);

            String json = functions.getTableCode(globalResturantId,tableCode);

            try{

                if(json!=null){

                    JSONObject jsonObject = new JSONObject(json);

                    type = jsonObject.getString("type");

                    tableCode = jsonObject.getString("tableCode");




                }



            }catch (Exception e){

                e.printStackTrace();
            }





            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            //new CheckResturantOrder().execute(tableCode);


        }
    }
    class ResturantOffer extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }


        @Override
        protected Void doInBackground(Void... params) {

            ServiceHandler handler = new ServiceHandler();

            JsonFunctions functions = new JsonFunctions(handler);

            String json = functions.GetResturantShareOffer(globalResturantId);

            try{

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


               // Toast.makeText(ResturantInfo.this,"No connection",Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }


            return null;
        }



        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try {

               for(int index = 0;index<sharedDocumnents.size();index++) {
                    txt_share_content.setText(sharedDocumnents.get(index).get("description"));
                    txt_share.setText(sharedDocumnents.get(index).get("title"));
               }
            }catch (Exception e){

                e.printStackTrace();
            }

        }
    }


    public void  putSharedPreferenceTableCode(String tableCode){

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("tableCode", tableCode);


        editor.commit();

    }


    public void  putSharedPreferenceTableNumber(String tableNumber){

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("tableNumber", tableNumber);


        editor.commit();

    }


    public String getJson(){



        String json = "";
        JSONObject jsonObject = new JSONObject();



        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        JSONObject jsonObject3 = new JSONObject();

      /*  "email": "john.doe@example.com",
                "first_name": "John",
                "last_name": "Doe",
                "username": "john.doe",*/


        try{
            jsonObject3.put("first_name","John");
            jsonObject3.put("address_1","John");
            jsonObject3.put("address_2","John");
            jsonObject3.put("city","John");
            jsonObject3.put("state","John");
            jsonObject3.put("postcode","John");
            jsonObject3.put("country","John");
            jsonObject3.put("email","John");
            jsonObject3.put("phone","John");

            jsonObject2.put("payment_details","");
            jsonObject2.put("payment_details",jsonObject3);
            jsonObject1.put("order",jsonObject2);

            jsonObject2.put("shipment_details","");
            jsonObject2.put("shipment_details",jsonObject3);

            jsonObject2.put("email","john.doe@example.com");
            jsonObject2.put("first_name","John");
            jsonObject2.put("last_name","Doe");
            jsonObject2.put("username","john.doe");
            jsonObject1.put("customer",jsonObject2);

        }catch (Exception e){


            e.printStackTrace();
        }



   /*    try {

            jsonObject.put("order",sharedPreferences.getString("email", ""));
            jsonObject.put("resturant_id",ResturantInfo.globalResturantId);
            jsonObject.put("table_code",sharedPreferences.getString("tableCode", ""));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray jsonArray = new JSONArray();
        for(int index = 0;index<dao.getAllOrderDetails().size();index++) {

            try {
                JSONObject object = new JSONObject();



                object.put("order_item_id",dao.getAllOrderDetails().get(index).getMenu_id());
                object.put("order_item_name",dao.getAllOrderDetails().get(index).getOrder_name());
                object.put("order_item_price",dao.getAllOrderDetails().get(index).getOrder_item_price());
                object.put("order_item_quantity",dao.getAllOrderDetails().get(index).getOrder_item_quantity());



                jsonArray.put(object)  ;
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
*/
        try {
            jsonObject.put("json",jsonObject1);

            json = jsonObject1.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json;
    }
}
