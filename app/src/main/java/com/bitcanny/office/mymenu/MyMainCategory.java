package com.bitcanny.office.mymenu;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by OFFICE on 01-07-2015.
 */
public class MyMainCategory extends ActionBarActivity {

    Toolbar toolbar;
    ListView listView;
    List<String> nameImage =Collections.emptyList();
    String grossAmount;
    String totalQty;


    String OrderMenuItemPrice;
    String OrderMenuItemID ;

    String OrderMenuItemName ;

    String OrderMenuItemQty ;
    Context context;
    private static String RESTAURANTCATEGORY = "restaurantCategory";
    private static String INFO = "Info";
    private static String CATEGORYID = "CategoryID";
    private static String CATEGORYIMAGEURL = "CategoryImageURL";
    private static String CATEGORYNAME = "CategoryName";
    private static String TYPE = "type";


    MenuItem menuItem;
    private static String MYPREF = "mypref";
    private static String EMAIL = "email";
    private static  String PASSWORD = "password";
    private PopupWindow pwindo;


    SharedPreferences sharedPreferences;
    ProgressBar bar;
    MenuItem item ;
    String categoryId;
    String categoryImageUrl;
    String categoryName;
    String type;
    String resturantId="1";
    DrawerLayout drawerLayout;
    boolean viewFlag = false;
    ArrayList<Map<String, String>> arrayList = new ArrayList<>();

    List<Map<String,String>> menuDetailsList = Collections.emptyList();

    List<CategoryModel> categoryModelArrayList = Collections.emptyList();
    RelativeLayout rel_layout;
    PlaceOrderSqlHelperDao dao;
    TextView txt_item_select;
    TextView txt_price_val;
    RelativeLayout add_to_cart;
    SwipeRefreshLayout swipeRefreshLayout;
    List<Map<String,String>> previousOrder = Collections.emptyList();

    String user_email,user_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_main);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
        }
        dao = new PlaceOrderSqlHelperDao(this);
        toolbar = (Toolbar) findViewById(R.id.tool);
        listView = (ListView) findViewById(R.id.list);
        previousOrder = new ArrayList<>();
        bar = (ProgressBar) findViewById(R.id.progressBar1);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_lay);
        txt_item_select= (TextView) findViewById(R.id.txt_item_select);
        txt_price_val = (TextView) findViewById(R.id.txt_price_val);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        rel_layout = (RelativeLayout)findViewById(R.id.list_val);
        add_to_cart= (RelativeLayout) findViewById(R.id.add_to_cart);
        sharedPreferences = getSharedPreferences(MYPREF,Context.MODE_PRIVATE);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    try {
        Bundle bundle = getIntent().getExtras();
        viewFlag = bundle.getBoolean("view_flag");

       putViewFlagSharedPreference(viewFlag);
    }catch (Exception e){

        e.printStackTrace();
    }

        try {

            viewFlag = Boolean.valueOf(sharedPreferences.getString("viewFlag", ""));

            if (viewFlag == false) {


                add_to_cart.setVisibility(View.GONE);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        nameImage = new ArrayList<>();


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new GetPreviousOrder().execute(ResturantInfo.globalResturantId, sharedPreferences.getString("tableCode", ""));
            }
        });

        try {

/*
            txt_price_val.setText(String.valueOf(GridViewAdapter.amount));
            txt_item_select.setText(String.valueOf(GridViewAdapter.mnyAmt));*/

            txt_item_select.setText(String.valueOf(sharedPreferences.getString("selectedItems", "")));
            txt_price_val.setText("Rs. "+String.valueOf(sharedPreferences.getString("totalAmt", "")));
         /*   txt_item_select.setText(String.valueOf(sharedPreferences.getString("selectedItems", "")));
            txt_price_val.setText(String.valueOf(sharedPreferences.getString("totalAmt", "")));*/


        }catch (Exception e){


            e.printStackTrace();


        }


        add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MyMainCategory.this);

                    Log.d("selectedItems",dao.getSelectedItemsSummation()+"");
                    if(dao.getSelectedItemsSummation()>0) {
                        Intent intent = new Intent(MyMainCategory.this, CartOrderActivity.class);
                        startActivity(intent,options.toBundle());
                        finish();
                    }else{

                        Toast.makeText(MyMainCategory.this, "Please order at least one item", Toast.LENGTH_LONG).show();

                    }
                   // Intent intent = new Intent(MyMainCategory.this,CartOrderActivity.class) ;




                }else{

                    if(dao.getSelectedItemsSummation()>0) {
                        Intent intent = new Intent(MyMainCategory.this, CartOrderActivity.class);
                        startActivity(intent);
                        finish();
                    }else{

                        Toast.makeText(MyMainCategory.this, "Please order at least one item", Toast.LENGTH_LONG).show();

                    }
                    overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);

                }


            }
        });

        try {
            for (int index = 0; index < getFromSdcard("/MenuApp/MenuCategory/").size(); index++) {

                try {


                    Log.d("MenuCategory1",Utility.getItemImageName(getFromSdcard("/MenuApp/MenuCategory/").get(index)));
                    nameImage.add(index,Utility.getItemImageName(getFromSdcard("/MenuApp/MenuCategory/").get(index)));


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }catch (Exception e){

            e.printStackTrace();
        }
        //for demo only.
     /* try {
          Thread.sleep(2000);
          getAllDataFromUrl();


      }catch (Exception e){

          e.printStackTrace();;
      }*/


        /*try {

            new Thread(new Runnable() {
                public void run() {
                    //ResturantEntryActivity.DownloadFromUrl("/MenuApp/MenuCategory/", index + ".jpg", JsonFunctions.BASE_URL + categoryModelArrayList.get(index).getCategoryImageURL());
                    getAllDataFromUrl();

                }
            }).start();
        }catch (Exception e){

            e.printStackTrace();
        }*/

        new CategoryItems().execute();
        menuDetailsList = new ArrayList<>();


        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)getFragmentManager().findFragmentById(R.id.nav_drawer_id);



        drawerFragment.setUi(drawerLayout, toolbar);



        categoryModelArrayList = dao.getCategory();


       /* for (int index = 0; index < categoryModelArrayList.size(); index++) {

            Log.d("category_name", categoryModelArrayList.get(index).getCategoryName());
            Log.d("category_name", categoryModelArrayList.get(index).getCategoryImageURL());

            Map<String, String> map = new HashMap<>();

            map.put(CATEGORYID, String.valueOf(index));

            map.put(CATEGORYIMAGEURL, categoryModelArrayList.get(index).getCategoryImageURL());

                      *//*  for(int index = 0;index<arrayList.size();index++){
*//*

            ResturantEntryActivity.DownloadFromUrl("/MenuApp/MenuCategory/",index+".jpg",JsonFunctions.BASE_URL +categoryModelArrayList.get(index).getCategoryImageURL() );


                     *//*   }*//*

            map.put(CATEGORYNAME, categoryModelArrayList.get(index).getCategoryName());

            arrayList.add(map);*/

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MyMainCategory.this);
                    view.setBackgroundResource(R.drawable.increment_decrement_focus);
                    Intent intent = new Intent(MyMainCategory.this, MainActivity.class);

                    // intent.putExtra("category_name",arrayList.get(position).get(CATEGORYNAME));
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    putCategory(arrayList.get(position).get(CATEGORYNAME));
                    overridePendingTransition(R.anim.abc_fade_out, R.anim.abc_fade_in);
                    intent.putExtra("view_flag", viewFlag);
                   // startActivity(intent);


                    startActivity(intent,options.toBundle());

                    finish();

                }else{

                    view.setBackgroundResource(R.drawable.increment_decrement_focus);
                    Intent intent = new Intent(MyMainCategory.this, MainActivity.class);

                    // intent.putExtra("category_name",arrayList.get(position).get(CATEGORYNAME));
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    putCategory(arrayList.get(position).get(CATEGORYNAME));
                    overridePendingTransition(R.anim.abc_fade_out, R.anim.abc_fade_in);
                    intent.putExtra("view_flag",viewFlag);
                    startActivity(intent);
                    overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                    finish();
                }



            }
        });




    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_resturant_info, menu);

        if(sharedPreferences.getString("email", "").equals("1") || sharedPreferences.getString("password","").equals("1")){
            menu.findItem(R.id.action_logIn).setIcon(R.drawable.login248);
            menu.findItem(R.id.action_logIn).setTitle("Log Out");
        }

        return true;
    }
*/
    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        menuItem = item;
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logIn) {

           *//* if(click == false){*//*

            if(sharedPreferences.getString("email", "").equals("") || sharedPreferences.getString("password","").equals("")) {

               // initiatePopupWindow();

                rel_layout.setAlpha((float) .27);

            }else if(sharedPreferences.getString("email", "").equals("1") || sharedPreferences.getString("password","").equals("1")){
                sharedPreferences.edit().clear().commit();
                item.setIcon(R.drawable.login148);
            }

          *//*  }else{
                {
                    item.setIcon(R.drawable.login148);
                    resturant_info.setAlpha(1);
                    click = false;

                }

            }
*//*


            return true;
        }



        return super.onOptionsItemSelected(item);
    }*/
    class CategoryItems extends AsyncTask<Void, Void, Void> {


        public void  getAllData(){


            try {
                categoryModelArrayList = dao.getCategory();


                for (int index = 0; index < categoryModelArrayList.size(); index++) {

                    Log.d("category_name", categoryModelArrayList.get(index).getCategoryName());
                    Log.d("category_name", categoryModelArrayList.get(index).getCategoryImageURL());

                    Map<String, String> map = new HashMap<>();

                    map.put(CATEGORYID, String.valueOf(index));

                    map.put(CATEGORYIMAGEURL, categoryModelArrayList.get(index).getCategoryImageURL());

                       /* for(int index1 = 0;index1<arrayList.size();index1++){*/


                        if(!nameImage.contains(categoryModelArrayList.get(index).getCategoryName())) {


                            ResturantEntryActivity.DownloadFromUrl("/MenuApp/MenuCategory/", categoryModelArrayList.get(index).getCategoryName() + ".jpg", JsonFunctions.BASE_URL + categoryModelArrayList.get(index).getCategoryImageURL());

                        }


                    /*    }*/

                    map.put(CATEGORYNAME, categoryModelArrayList.get(index).getCategoryName());

                    arrayList.add(map);

                }
            }catch (Exception e){

                e.printStackTrace();
            }
        }
        @Override
        protected Void doInBackground(Void... params) {

            try {

               /* ServiceHandler handler = new ServiceHandler();

                JsonFunctions functions = new JsonFunctions(handler);

                String json = functions.getCategory(String.valueOf(resturantId));

                JSONObject jsonObject = new JSONObject(json);

                JSONObject restaurantCategory = jsonObject.getJSONObject(RESTAURANTCATEGORY);

                type = restaurantCategory.getString(TYPE);

                if (type.equals("success")) {

                    JSONArray Info = restaurantCategory.getJSONArray(INFO);

                    //JSONArray array = new JSONArray(Info);

                    for (int index = 0; index < Info.length(); index++) {

                        JSONObject object1 = Info.getJSONObject(index);

                        categoryId = object1.getString(CATEGORYID);

                        categoryImageUrl = object1.getString(CATEGORYIMAGEURL);

                        categoryName = object1.getString(CATEGORYNAME);

                        Map<String, String> map = new HashMap<>();

                        map.put(CATEGORYID, categoryId);

                        map.put(CATEGORYIMAGEURL, categoryImageUrl);

                        map.put(CATEGORYNAME, categoryName);

                        arrayList.add(map);

                    }
                }*/

                getAllData();

            } catch (Exception e) {

                e.printStackTrace();

            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
             bar.setVisibility(View.VISIBLE);

        }

        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            //
         /*   try {*/
               /* if (type.equals("success")) {
*/
                    try {
                        bar.setVisibility(View.GONE);
                        ArrayAdapter adapter = new CategoryAdapter(MyMainCategory.this, R.layout.category_item, arrayList);

                        listView.setAdapter(adapter);


                        //Toast.makeText(CategoryFragment.this, "Success", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {

                        e.printStackTrace();
                    }


          /*  } catch (Exception e) {
                Toast.makeText(MyMainCategory.this,"Please check internet",Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }*/
        }
    }

    public void putSharedPreference(String email,String password){

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email",email);
        editor.putString("password", password);

        editor.commit();

    }
    public void putCategory(String categoryName){

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("category_name",categoryName);


        editor.commit();

    }

   /* private void initiatePopupWindow() {
        try {
// We need to get the instance of the LayoutInflater
            LayoutInflater inflater = (LayoutInflater) MyMainCategory.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View layout = inflater.inflate(R.layout.log_in_popup,
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
                        rel_layout.setAlpha(1);

                        // click = false;
                    }else{
                        Toast.makeText(MyMainCategory.this,"Wrong username or password",Toast.LENGTH_LONG).show();
                        menuItem.setIcon(R.drawable.login148);
                    }

                }
            });


            cross.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pwindo.dismiss();
                    rel_layout.setAlpha(1);
                    // click = false;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public  void  getAllDataFromUrl(){


        try {
            categoryModelArrayList = dao.getCategory();


            for (int index = 0; index < categoryModelArrayList.size(); index++) {

                Log.d("category_name", categoryModelArrayList.get(index).getCategoryName());
                Log.d("category_name", categoryModelArrayList.get(index).getCategoryImageURL());

                Map<String, String> map = new HashMap<>();

                map.put(CATEGORYID, String.valueOf(index));

                map.put(CATEGORYIMAGEURL, categoryModelArrayList.get(index).getCategoryImageURL());

                      /*  for(int index = 0;index<arrayList.size();index++){
*/

                ResturantEntryActivity.DownloadFromUrl("/MenuApp/MenuCategory/",index+".jpg",JsonFunctions.BASE_URL +categoryModelArrayList.get(index).getCategoryImageURL() );


                     /*   }*/

                map.put(CATEGORYNAME, categoryModelArrayList.get(index).getCategoryName());

                arrayList.add(map);

            }
        }catch (Exception e){

            e.printStackTrace();
        }

        //System.exit(0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dao.close();
    }

    public List<String> getFromSdcard(String path)
    {
        ArrayList<String> f = new ArrayList<String>();
        File[] listFile;
        File file= new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+path);

        if (file.isDirectory())
        {
            listFile = file.listFiles();


            for (int i = 0; i < listFile.length; i++)
            {

                f.add(listFile[i].getAbsolutePath());

                Log.d("val",listFile[i].getAbsolutePath());

            }
        }

        return  f;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();


        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
        }

    }

    public void putViewFlagSharedPreference(boolean viewFlag){

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("viewFlag", String.valueOf(viewFlag));
        //   editor.putString("password",password);

        editor.commit();

    }

    class GetPreviousOrder extends AsyncTask<String,Void,Void>{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... params) {

            ServiceHandler handler = new ServiceHandler();

            JsonFunctions functions = new JsonFunctions(handler);

            String json = functions.getPreviousOrder(params[0],params[1]);


            //  {"orderInfo":{"orderItemInfo":[{"OrderMenuID":"1","OrderMenuOrderID":"1","OrderMenuItemID":"1","OrderMenuItemName":"KAIRI PANI","OrderMenuItemQty":"6","OrderMenuItemPrice":"150","OrderMenuAddon":"2015-10-15 06:30:14"},{"OrderMenuID":"2","OrderMenuOrderID":"1","OrderMenuItemID":"2","OrderMenuItemName":"KESARIA LASSI","OrderMenuItemQty":"1","OrderMenuItemPrice":"100","OrderMenuAddon":"2015-10-15 06:30:35"}],"grossAmount":1000,"totalQty":7}}
            try {
                if (json != null) {

                    JSONObject jsonObject = new JSONObject(json);


                    JSONObject orderInfoData = jsonObject.getJSONObject("orderInfo");

                    JSONArray orderInfo =orderInfoData.getJSONArray("orderItemInfo");

                     grossAmount = orderInfoData.getString("grossAmount");
                     totalQty = orderInfoData.getString("totalQty");

                    putSharedItemsAmtPreference(totalQty,grossAmount);


                    for(int index = 0;index<orderInfo.length();index++) {

                        JSONObject object = orderInfo.getJSONObject(index);

                        OrderMenuItemID = object.getString("OrderMenuItemID");

                        OrderMenuItemName = object.getString("OrderMenuItemName");

                        Log.d("OrderMenuItemName", OrderMenuItemName);

                        OrderMenuItemQty = object.getString("OrderMenuItemQty");

                        Log.d("OrderMenuItemQty", OrderMenuItemQty);

                        OrderMenuItemPrice = object.getString("OrderMenuItemPrice");

                        String OrderMenuItemID = object.getString("OrderMenuItemID");

                        Map<String, String> map = new HashMap<>();

                        map.put("OrderMenuItemID", OrderMenuItemID);
                        map.put("OrderMenuItemName", OrderMenuItemName);
                        map.put("OrderMenuItemQty", OrderMenuItemQty);

                        // map.put("OrderMenuItemID", OrderMenuItemID);


                        // map.put("OrderMenuItemName", OrderMenuItemName);
                        map.put("OrderMenuItemPrice", OrderMenuItemPrice);

                        previousOrder.add(map);

                        if (dao.getAllOrderDetails().size() < previousOrder.size()) {

                            Log.d("refreshedMyMainMenu","val");
                            dao.removeIfExists(previousOrder.get(index).get("OrderMenuItemName"));
                            dao.addToOrderValue(previousOrder.get(index).get("OrderMenuItemName"), previousOrder.get(index).get("OrderMenuItemPrice"), OrderMenuItemQty, "", previousOrder.get(index).get("OrderMenuItemID"), "1");
                        }

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
            MainActivity.showFlag = false;
            try {
                for (int index = 0; index < dao.getAllOrderDetails().size(); index++) {
                    int i = dao.updateSelectedItemsInMenu(dao.getAllOrderDetails().get(index).getOrder_name(), "0");
                    // dao.updateSelectedItems(dao.getAllOrderDetails().get(index).getOrder_name(),"0");
                    Log.d("updated", i + "");

                }
            }catch (Exception e){

                e.printStackTrace();
            }

            try {
                dao.deleteOrderedItems();
                   /* for (int index = 0; index < dao.getAllOrderDetails().size(); index++) {
                        //int i=dao.updateSelectedItemsInMenu(dao.getAllOrderDetails().get(index).getOrder_name(),"0");
                       // dao.updateSelectedItems(dao.getAllOrderDetails().get(index).getOrder_name(), "0");
                        //Log.d("updated",i+"");

                    }*/
            }catch (Exception e){

                e.printStackTrace();
            }
            txt_item_select.setText(String.valueOf(sharedPreferences.getString("selectedItems", "")));
            txt_price_val.setText("Rs. " + String.valueOf(sharedPreferences.getString("totalAmt", "")));
            swipeRefreshLayout.setRefreshing(false);


        }


    }

    public void  putSharedItemsAmtPreference(String selectedItems,String totalAmt){

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("selectedItems", selectedItems);
        editor.putString("totalAmt", totalAmt);

        editor.commit();

    }
}
