package com.bitcanny.office.mymenu;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ResturantInfo extends ActionBarActivity implements BaseSliderView.OnSliderClickListener {
    private SliderLayout mDemoSlider;
    List<Map<String, String>> list = Collections.emptyList();
    List<Map<String, String>> list2 = Collections.emptyList();
    List<CategoryModel> categoryList = Collections.emptyList();
    static List<Map<String, String>> orderedItems = Collections.emptyList();
    static List<Map<String, String>> finalorderedItems = Collections.emptyList();

    List<Map<String, String>> maps = Collections.emptyList();

    static String RestaurantRatingAddby = "RestaurantRatingAddby";
    static String RestaurantRatingRate = "RestaurantRatingRate";
    static String RestaurantRatingReview = "RestaurantRatingReview";
    String restaurantratingaddby, restaurantratingrate, restaurantratingreview;


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
    TextView txt_abt_details, txt_more;
    boolean click = false;
    private PopupWindow pwindo;
    LinearLayout resturant_info, lin_submit;
    PlaceOrderSqlHelperDao dao;
    Button btn_submit;
    Menu Optmenu;
    ListView list_view;
    RatingBar ratingBar;

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resturant_main);

        dao = new PlaceOrderSqlHelperDao(this);
        restList = dao.getResturantDetails();

        maps = new ArrayList<>();
        Log.d("list size", restList.size() + "");
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        orderedItems = new ArrayList<>();
        finalorderedItems = new ArrayList<>();
        pgr_bar = (ProgressBar) findViewById(R.id.pgr_bar);
        int width = size.x;
        int height = size.y;
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        txt_abt_details = (TextView) findViewById(R.id.txt_abt_details);
        txt_address_details = (TextView) findViewById(R.id.txt_address_details);
        txt_phone1_details = (TextView) findViewById(R.id.txt_phone1_details);
        txt_phone2_details = (TextView) findViewById(R.id.txt_phone2_details);
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
        rel5 = (RelativeLayout) findViewById(R.id.rel5);
        rel11 = (RelativeLayout) findViewById(R.id.rel11);
        view1 = (View) findViewById(R.id.view1);
        view2 = (View) findViewById(R.id.view2);
        lin_submit = (LinearLayout) findViewById(R.id.lin_submit);
        scrl_view = (ScrollView) findViewById(R.id.scrl_view);
        btn_open_menu = (Button) findViewById(R.id.btn_open_menu);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        rel_val = (RelativeLayout) findViewById(R.id.rel_val);
        ratingBar = (RatingBar) findViewById(R.id.rating_bar);
        sharedPreferences = getSharedPreferences(MYPREF, Context.MODE_PRIVATE);
        rel_more = (RelativeLayout) findViewById(R.id.rel_more);
        txt_more = (TextView) findViewById(R.id.txt_more);


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
            restaurantPhone1 = restList.get(0).getRestaurantPhone1();
            restaurantPhone2 = restList.get(0).getRestaurantPhone2();
            restaurantPostCode = restList.get(0).getRestaurantPostCode();
            restaurantState = restList.get(0).getRestaurantState();
            restaurantAddress = restList.get(0).getRestaurantAddress();
            restaurantDescription = restList.get(0).getRestaurantDescription();

            Log.d("val", restaurantName + "/" + restaurantDescription + "/" + restaurantPostCode + "/" + restaurantState + "/" + restaurantPhone1 + "/" + restaurantPhone2);
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


        try {
            //  ResturantEntryActivity.DownloadFromUrl("/MenuApp/Map","0.jpg","https://maps.googleapis.com/maps/api/staticmap?center=" + getString(restaurantName) + getString(restaurantAddress) + getString(restaurantState) + getString(restaurantPostCode) + "&zoom=13&size=800x400&maptype=roadmap&markers=color:blue%7Clabel:S%7C" + lat + "," + lng+"");


            Picasso.with(ResturantInfo.this)
                    .load(new File(getFromSdcard("/MenuApp/menu/image/").get(0)))

                            //  .load("https://maps.googleapis.com/maps/api/staticmap?center="+restaurantAddress+"&zoom=13&size=600x300&maptype=roadmap&markers=color:blue%7Clabel:S%7C"+lat+","+lng)
                    .placeholder(R.mipmap.ic_launcher) // optional

                    .error(R.mipmap.ic_launcher)

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

                Intent intent = new Intent(ResturantInfo.this, ResturantMap.class);

                intent.putExtra("lat", lat);
                intent.putExtra("lng", lng);

                startActivity(intent);


            }
        });
        btn_open_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ResturantInfo.this, MyMainCategory.class);
                startActivity(intent);
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

                Intent intent = new Intent(ResturantInfo.this, ResturantReviewRating.class);

                intent.putExtra("Resturant_Id", resturantId);
                startActivity(intent);

            }
        });

        rel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                relLayFlag1 = getImageStatus(img_arrow, relLayFlag1);

                if (relLayFlag1 == false) {
                    txt_abt_details.setVisibility(View.GONE);
                    view1.setVisibility(View.GONE);

                } else {

                    txt_abt_details.setVisibility(View.VISIBLE);
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
                    rel5.setVisibility(View.GONE);
                    view2.setVisibility(View.GONE);

                } else {

                    rel5.setVisibility(View.VISIBLE);
                   /* ObjectAnimator anim = ObjectAnimator.ofFloat(rel5, "y", 10f);
                    anim.setDuration(1000);
                    anim.start();*/
                    view2.setVisibility(View.GONE);
                }

            }
        });


        rel10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                relLayFlag3 = getImageStatus(img_arrow2, relLayFlag3);

                if (relLayFlag3 == false) {
                    list_view.setVisibility(View.GONE);
                    rel_more.setVisibility(View.GONE);
                    pgr_bar.setVisibility(View.GONE);


                } else {

                    list_view.setVisibility(View.VISIBLE);
                    rel_more.setVisibility(View.VISIBLE);
                    //  pgr_bar.setVisibility(View.VISIBLE);
                    new GetReviewRating().execute();
                   /* ObjectAnimator anim = ObjectAnimator.ofFloat(list_view, "y", 50f);
                    anim.setDuration(1000);
                    anim.start();*/

                }
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

                    Intent intent = new Intent(ResturantInfo.this, LogInActivity.class);
                    startActivity(intent);
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
        if (id == R.id.action_logIn) {

           /* if(click == false){*/

            if (sharedPreferences.getString("email", "").equals("") || sharedPreferences.getString("password", "").equals("")) {

                //initiatePopupWindow();

                resturant_info.setAlpha((float) .27);

            } else if (sharedPreferences.getString("email", "").equals("1") || sharedPreferences.getString("password", "").equals("1")) {
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

                ResturantEntryActivity.DownloadFromUrl("/MenuApp/menu/image/", "map.jpg", "https://maps.googleapis.com/maps/api/staticmap?center=" + getString(restaurantName) + getString(restaurantAddress) + getString(restaurantState) + getString(restaurantPostCode) + "&zoom=13&size=800x400&maptype=roadmap&markers=color:blue%7Clabel:S%7C" + lat + "," + lng + "");
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

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try {

                if (returnVal.equals("success")) {


                    pgr_bar.setVisibility(View.GONE);

                    ArrayAdapter adapter = new ResturantInfoReviewRatingAdapter(ResturantInfo.this, 0, maps);
                    list_view.setAdapter(adapter);


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
                String json = functions.getResturantReviewRating(resturantId);

                if (json != null) {

                    JSONObject object = new JSONObject(json);

                    JSONObject reviewInfo = object.getJSONObject("reviewInfo");

                    returnVal = reviewInfo.getString("type");

                    if (returnVal.equals("success")) {

                        JSONArray Info = reviewInfo.getJSONArray("Info");

                        for (int index = 0; index < 2; index++) {

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
}
