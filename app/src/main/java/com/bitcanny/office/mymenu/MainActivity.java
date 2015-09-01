package com.bitcanny.office.mymenu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends ActionBarActivity {


    private static String MYPREF = "mypref";
    private static String EMAIL = "email";
    private static  String PASSWORD = "password";
    SharedPreferences sharedPreferences;
    List<MenuInfoModel> menuInfoModelList = Collections.emptyList();
    MenuItem menuItem;
    private PopupWindow pwindo;
    static double  mainAmount=0.0;
    static String totalAmtValue = "0.0";
    static String numberOfItemsSelected = "0";
    String user_email,user_password;
    PlaceOrderSqlHelperDao dao;
    private static String CATMENUINFO = "catMenuInfo";
    private static String INFO = "Info";
    private static String MENUITEMDESC = "MenuItemDesc";
    private static String MENUITEMID = "MenuItemID";
    private static String MENUITEMIMAGEURL = "MenuItemImageURL";
    private static String MENUITEMISCHEFRECOMMEND = "MenuItemIsChefRecommend";
    private static String MENUITEMNAME = "MenuItemName";
    private static String MENUITEMPRICE = "MenuItemPrice";
    private static String TAGNAME = "TagName";
    private static String TYPE = "type";
    static Map<Integer,Integer> selectedPositions ;
    SubCategoryImageModel model;

    List<Map<String,String>> imageMap;
      double moneyAmount = 0.0;
    double totalAmt = 0.0;
    String catmenuinfo, Info, MenuItemDesc, MenuItemID, MenuItemImageURL, MenuItemIsChefRecommend, MenuItemName, MenuItemPrice, TagName,
            type;
    ArrayList<Map<String,String>> list = new ArrayList<>();
    List<Integer> listVal = Collections.emptyList();
    ListView view;
    String category_name;
    RelativeLayout grid_val,grid,rel_crt_img,add_to_cart;
    TextView txt_item_select,txt_price_val;
    ImageView img_add_to_cart_arrow;

    ArrayList<String> namesImage ;

    ImageView img_plus ;

    ImageView img_minus ;
    List<OrderToCartAdapterModel> orderToCartAdapterModels;


   private Toolbar toolbar;
    ProgressBar progressBar;
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        orderToCartAdapterModels = new ArrayList<>();
        HorizontalNumberPicker.finalAmount = 0;
        listVal = new ArrayList<>();
        namesImage= new ArrayList<>();
        moneyAmount= 0.0;
        selectedPositions = new HashMap<>();
        toolbar = (Toolbar) findViewById(R.id.tval);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_lay);
        view = (ListView)findViewById(R.id.grid_view);
        grid_val = (RelativeLayout) findViewById(R.id.grid);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        dao = new PlaceOrderSqlHelperDao(this);
        sharedPreferences = getSharedPreferences(MYPREF,Context.MODE_PRIVATE);
        txt_item_select = (TextView)findViewById(R.id.txt_item_select);
        txt_price_val = (TextView) findViewById(R.id.txt_price_val);
        grid = (RelativeLayout) findViewById(R.id.grid);
        img_add_to_cart_arrow = (ImageView) findViewById(R.id.img_add_to_cart_arrow);
        txt_price_val.setText("₹ " + String.valueOf(0));
        add_to_cart = (RelativeLayout) findViewById(R.id.add_to_cart);

    /*    Resources res = getResources();
        Drawable drawable = res.getDrawable(R.color.non_focusable);
    //    LinearLayout linearLayout = (LinearLayout)findViewById(R.id.GameLayout);
      //  linearLayout.setBackgroundDrawable(drawable);
        add_to_cart.setBackground(drawable);*/
       // add_to_cart.setBackgroundColor(Color.parseColor("#e8e8e8"));
        txt_item_select.setText("0");
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)getFragmentManager().findFragmentById(R.id.nav_drawer_id);

        drawerFragment.setUi(drawerLayout, toolbar);
       /* Bundle bundle = getIntent().getExtras();

        category_name = bundle.getString("category_name");*/

        category_name = sharedPreferences.getString("category_name", "");


        try{
        for(int index =0;index<getFromSdcard("/MenuApp/MenuItemGrid/").size();index++ ){

            namesImage.add(index,Utility.getItemImageName(getFromSdcard("/MenuApp/MenuItemGrid/").get(index)));

        }
        }catch (Exception e){

            e.printStackTrace();
        }

        new GetSubCategory().execute();


        // MainActivity.mainAmount +=Double.valueOf( maps.get(position).get("MenuItemPrice"));
      // txt_item_select.setText(String.valueOf(HorizontalNumberPicker.amountMoney + totalAmountMoney));

        try {
           /* txt_item_select.setText(String.valueOf(GridViewAdapter.amount));
            txt_price_val.setText(String.valueOf(GridViewAdapter.mnyAmt));*/
            txt_item_select.setText(String.valueOf(sharedPreferences.getString("selectedItems", "")));
            txt_price_val.setText(String.valueOf(sharedPreferences.getString("totalAmt", "")));
        }catch (Exception e){

            e.printStackTrace();;
        }

        add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,CartOrderActivity.class);

                startActivity(intent);

              /*  getRefinedArrayList(ResturantInfo.orderedItems);

                Log.d("listsize", getRefinedArrayList(ResturantInfo.orderedItems).size()+"");*/



              /*  String temp;
                String temp1;

                ResturantInfo.finalorderedItems.addAll(ResturantInfo.orderedItems);

                for(int i=0;i<ResturantInfo.finalorderedItems.size();i++)
                {
                    temp=ResturantInfo.finalorderedItems.get(i).get("MenuItemName");

                    temp1 = ResturantInfo.finalorderedItems.get(i).get("selectedItems");

                    for(int k=i+1;k<ResturantInfo.finalorderedItems.size();k++)
                    {
                        if(temp.equals(ResturantInfo.finalorderedItems.get(k).get("MenuItemName"))&&temp1.equals(ResturantInfo.finalorderedItems.get(k).get("selectedItems")))
                        {
                            ResturantInfo.finalorderedItems.remove(k);
                        }
                    }

                }*/

                ResturantInfo.finalorderedItems.addAll(ResturantInfo.orderedItems);
                String value;

/*
                for()
                HashSet hs = new HashSet();
                hs.addAll(ResturantInfo.finalorderedItems);
                ResturantInfo.finalorderedItems.clear();
                ResturantInfo.finalorderedItems.addAll(hs);   */


                PlaceOrderSqlHelperDao dao = new PlaceOrderSqlHelperDao(MainActivity.this);

                orderToCartAdapterModels = dao.getAllOrderDetails();

                for (int index = 0;index<dao.getAllOrderDetails().size();index++){

                    value = dao.getAllOrderDetails().get(index).getOrder_name();
                    if(value == dao.getAllOrderDetails().get(index).getOrder_item_quantity()){

                        ResturantInfo.orderedItems.remove(index);

                    }


                }
                for (int index = 0;index<dao.getAllOrderDetails().size();index++) {


                    //   for(int index1 = 0;index<ResturantInfo.orderedItems.get(index).size())

                    Log.d("itemName", orderToCartAdapterModels.get(index).getOrder_name());
                    Log.d("itemquantity", orderToCartAdapterModels.get(index).getOrder_item_quantity());
                    //Log.d("selecteditems", ResturantInfo.finalorderedItems.get(index).get("selectedItems"));
                    // }
                }
            }
        });
    /*    img_add_to_cart_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rel_crt_img.setBackgroundResource(R.drawable.increment_decrement_focus);
                Intent intent = new Intent(MainActivity.this,CartOrderActivity.class);

                startActivity(intent);

              *//*  getRefinedArrayList(ResturantInfo.orderedItems);

                Log.d("listsize", getRefinedArrayList(ResturantInfo.orderedItems).size()+"");*//*



              *//*  String temp;
                String temp1;

                ResturantInfo.finalorderedItems.addAll(ResturantInfo.orderedItems);

                for(int i=0;i<ResturantInfo.finalorderedItems.size();i++)
                {
                    temp=ResturantInfo.finalorderedItems.get(i).get("MenuItemName");

                    temp1 = ResturantInfo.finalorderedItems.get(i).get("selectedItems");

                    for(int k=i+1;k<ResturantInfo.finalorderedItems.size();k++)
                    {
                        if(temp.equals(ResturantInfo.finalorderedItems.get(k).get("MenuItemName"))&&temp1.equals(ResturantInfo.finalorderedItems.get(k).get("selectedItems")))
                        {
                            ResturantInfo.finalorderedItems.remove(k);
                        }
                    }

                }*//*

                ResturantInfo.finalorderedItems.addAll(ResturantInfo.orderedItems);
                String value;

*//*
                for()
                HashSet hs = new HashSet();
                hs.addAll(ResturantInfo.finalorderedItems);
                ResturantInfo.finalorderedItems.clear();
                ResturantInfo.finalorderedItems.addAll(hs);   *//*


                PlaceOrderSqlHelperDao dao = new PlaceOrderSqlHelperDao(MainActivity.this);

                orderToCartAdapterModels = dao.getAllOrderDetails();

              for (int index = 0;index<dao.getAllOrderDetails().size();index++){

                    value = dao.getAllOrderDetails().get(index).getOrder_name();
                    if(value == dao.getAllOrderDetails().get(index).getOrder_item_quantity()){

                        ResturantInfo.orderedItems.remove(index);

                    }


                }
                for (int index = 0;index<dao.getAllOrderDetails().size();index++) {


                    //   for(int index1 = 0;index<ResturantInfo.orderedItems.get(index).size())

                    Log.d("itemName", orderToCartAdapterModels.get(index).getOrder_name());
                    Log.d("itemquantity", orderToCartAdapterModels.get(index).getOrder_item_quantity());
                    //Log.d("selecteditems", ResturantInfo.finalorderedItems.get(index).get("selectedItems"));
                    // }
                }
            }
        });*/
       /* grid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                txt_item_select.setText(String.valueOf(HorizontalNumberPicker.finalAmount));
                txt_price_val.setText(String.valueOf(HorizontalNumberPicker.finalAmount));
            }
        });*/

   /*     view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Toast.makeText(MainActivity.this,position+" value",Toast.LENGTH_LONG).show();
             *//*   ImageView img_plus = (ImageView) view.findViewById(R.id.btn_plus);

                ImageView img_minus = (ImageView) view.findViewById(R.id.btn_minus);


                img_plus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        txt_item_select.setText(HorizontalNumberPicker.finalAmount+1);
                    }
                });

                img_minus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        txt_item_select.setText(HorizontalNumberPicker.finalAmount-1);
                    }
                });
*//*


            }
        });*/
        /*for(int index = 0;index<list.size();index++){





            ResturantEntryActivity.DownloadFromUrl("/MenuApp/MenuItemGrid/",index+".jpg",JsonFunctions.BASE_URL +list.get(index).get("MenuItemImageURL") );





        }*/

    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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


    class GetSubCategory extends AsyncTask<Void,Void,Void>{

        String returnType;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... params) {

            ServiceHandler handler = new ServiceHandler();
            JsonFunctions functions = new JsonFunctions(handler);

            try {
                /*String json = functions.getMenuInformation("1", "1");

                if(json!=null){

                    JSONObject object = new JSONObject(json);

                    JSONObject object1 = object.getJSONObject(CATMENUINFO);

                    JSONArray array = object1.getJSONArray(INFO);

                    for(int index = 0; index<array.length() ; index++){

                        JSONObject jsonObject = array.getJSONObject(index);

                        MenuItemDesc = jsonObject.getString(MENUITEMDESC);
                        MenuItemID = jsonObject.getString(MENUITEMID);
                        MenuItemImageURL = jsonObject.getString(MENUITEMIMAGEURL);
                        MenuItemIsChefRecommend = jsonObject.getString(MENUITEMISCHEFRECOMMEND);
                        MenuItemName = jsonObject.getString(MENUITEMNAME);
                        MenuItemPrice = jsonObject.getString(MENUITEMPRICE);
                        TagName =jsonObject.getString(TAGNAME);

                        Log.d("val",MenuItemDesc);*/





                    menuInfoModelList = dao.getMenuDetails(category_name);

                    for (int index = 0; index < menuInfoModelList.size(); index++) {

                        Log.d("menu description", menuInfoModelList.get(index).getMenuItemDesc());
                        Map<String,String> map = new HashMap<>();
                        map.put("avg_rating",menuInfoModelList.get(index).getAvg_rating());
                        map.put("MenuItemDesc",menuInfoModelList.get(index).getMenuItemDesc());
                        //map.put("category_id",menuInfoModelList.get(index).category_i)
                        map.put("MenuItemImageURL", menuInfoModelList.get(index).getMenuItemImageURL());

                      /*  if(menuInfoModelList.size()< getFromSdcard("/MenuApp/MenuItemGrid/").size()) {*/

                              //  String value = dao.getSubcategoryImage(String.valueOf(menuInfoModelList.get(index).getMenuItemName()));

                     /*   if(value.equals(null)){*/


                     //   for(int index1 = 0;index1<getFromSdcard("/MenuApp/MenuItemGrid/").size();index1++) {

                            // Log.d("all images......",getFromSdcard("/MenuApp/MenuItemGrid/").get(position));
                          /*  boolean value = Utility.getItemImageName(getFromSdcard("/MenuApp/MenuItemGrid/").get(index1)).contains(menuInfoModelList.get(index).getMenuItemName());
                            if (value == false) {*/

                        if(!namesImage.contains(menuInfoModelList.get(index).getMenuItemName())) {
                            ResturantEntryActivity.DownloadFromUrl("/MenuApp/MenuItemGrid/", menuInfoModelList.get(index).getMenuItemName() + ".jpg", JsonFunctions.BASE_URL + menuInfoModelList.get(index).getMenuItemImageURL());
                            Log.d("all images......", menuInfoModelList.get(index).getMenuItemName());

                        }
                         /*   }
                        }*/
                           /* model = new SubCategoryImageModel("/MenuApp/MenuItemGrid/"+ menuInfoModelList.get(index).getMenuItemName() + ".jpg",menuInfoModelList.get(index).getMenuItemName());
                            dao.addToSubCategoryImage(model);

                        }*/

                        map.put("MenuItemIsChefRecommend",menuInfoModelList.get(index).getMenuItemIsChefRecommend());
                        map.put("MenuItemName",menuInfoModelList.get(index).getMenuItemName());
                        map.put("MenuItemPrice",menuInfoModelList.get(index).getMenuItemPrice());
                        map.put("TagName",menuInfoModelList.get(index).getTagName());



                        list.add(map);

                    }




            }catch (Exception e){

                e.printStackTrace();
            }

            return null;
        }



        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);

            for(int index=0;index<list.size();index++){

                listVal.add(1);
            }

            GridViewAdapter adapter =  new GridViewAdapter(MainActivity.this, list,listVal,txt_item_select,txt_price_val);
            view.setAdapter(adapter);




            try {
              //  txt_item_select.setText(HorizontalNumberPicker.finalAmount);

                adapter.notifyDataSetChanged();
            }catch (Exception e){

                e.printStackTrace();
            }
/*try {
                txt_item_select.setText(HorizontalNumberPicker.finalAmount);

                adapter.notifyDataSetChanged();
            }catch (Exception e){

                e.printStackTrace();
            }
*//*try {
                txt_item_select.setText(HorizontalNumberPicker.finalAmount);

                adapter.notifyDataSetChanged();
            }catch (Exception e){

                e.printStackTrace();
            }

*/


            view.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,  int position, long id) {


                    for (int index1 = 0; index1 < list.size(); index1++) {

                        if(index1 == position){
                        /*final HorizontalNumberPicker numberPicker = (HorizontalNumberPicker) parent.findViewById(R.id.horizontal_number_picker);

                        img_plus = (ImageView) numberPicker.findViewById(R.id.btn_plus);

                        img_minus = (ImageView) numberPicker.findViewById(R.id.btn_minus);

                        RelativeLayout rel_amount = (RelativeLayout) view.findViewById(R.id.rel_amount);

                        if (numberPicker.getAmount() > 0) {
                            MainActivity.selectedPositions.put(position, position);
                        }*/


                      /*  img_plus.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {


                                txt_item_select.setText(String.valueOf(HorizontalNumberPicker.finalAmount));

                                txt_price_val.setText("₹ " + HorizontalNumberPicker.amountMoney);

                                Log.d("plus","plus");

                           *//* // for(int)
                            Log.d("selectedItems", GridViewAdapter.selectedItems + "");

                            for (int index = 0; index < GridViewAdapter.selectedItems.size(); index++) {

                                double amount = 0;
                                for (int key : GridViewAdapter.selectedItems.keySet()) {
                                    double amount1 = 0;
                                    Log.d("key : ", key + "");

                                    amount1 = amount1 + Double.valueOf(GridViewAdapter.selectedItems.get(key) * Double.valueOf(menuInfoModelList.get(key).getMenuItemPrice()));
                                    amount = amount + amount1;


                                    Log.d("value : ", GridViewAdapter.selectedItems.get(key) + "");
                                }


                                if (amount < 0) {
                                    amount = 0;
                                    txt_price_val.setText("₹ " + amount);

                                }
                            }*//*
                                return false;
                            }
                        });*/

                       /* img_minus.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {


                                txt_item_select.setText(String.valueOf(HorizontalNumberPicker.finalAmount));

                                txt_price_val.setText("₹ " + HorizontalNumberPicker.amountMoney);


                                Log.d("minus", "minus");
                                // for(int)
                           *//* Log.d("selectedItems", GridViewAdapter.selectedItems + "");

                            for (int index = 0; index < GridViewAdapter.selectedItems.size(); index++) {

                                double amount = 0;
                                for (int key : GridViewAdapter.selectedItems.keySet()) {
                                    double amount1 = 0;
                                    Log.d("key : ", key + "");

                                    amount1 = amount1 + Double.valueOf(GridViewAdapter.selectedItems.get(key) * Double.valueOf(menuInfoModelList.get(key).getMenuItemPrice()));
                                    amount = amount + amount1;


                                    Log.d("value : ", GridViewAdapter.selectedItems.get(key) + "");
                                }


                                if (amount < 0) {
                                    amount = 0;
                                    txt_price_val.setText("₹ " + amount);

                                }
                            }*//**//* Log.d("selectedItems", GridViewAdapter.selectedItems + "");

                            for (int index = 0; index < GridViewAdapter.selectedItems.size(); index++) {

                                double amount = 0;
                                for (int key : GridViewAdapter.selectedItems.keySet()) {
                                    double amount1 = 0;
                                    Log.d("key : ", key + "");

                                    amount1 = amount1 + Double.valueOf(GridViewAdapter.selectedItems.get(key) * Double.valueOf(menuInfoModelList.get(key).getMenuItemPrice()));
                                    amount = amount + amount1;


                                    Log.d("value : ", GridViewAdapter.selectedItems.get(key) + "");
                                }


                                if (amount < 0) {
                                    amount = 0;
                                    txt_price_val.setText("₹ " + amount);

                                }
                            }*//*
                                return false;
                            }
                        });*/

                   /* numberPicker.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {


                            txt_item_select.setText(String.valueOf(HorizontalNumberPicker.finalAmount));

                            // for(int)
                            Log.d("selectedItems", GridViewAdapter.selectedItems + "");

                            for (int index = 0; index < GridViewAdapter.selectedItems.size(); index++) {

                                double amount = 0;
                                for (int key : GridViewAdapter.selectedItems.keySet()) {
                                    double amount1 = 0;
                                    Log.d("key : ", key + "");


                                    amount1 = amount1 + Double.valueOf(GridViewAdapter.selectedItems.get(key) * Double.valueOf(menuInfoModelList.get(key).getMenuItemPrice()));
                                    amount = amount + amount1;



                                    Log.d("value : ", GridViewAdapter.selectedItems.get(key) + "");
                                }


                                if(amount<0) {
                                    amount = 0;
                                    txt_price_val.setText("₹ " + amount);

                                }
                            }
                            return false;
                        }
                    });*/

                  /*  rel_amount.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {


                            txt_item_select.setText(String.valueOf(HorizontalNumberPicker.finalAmount));

                            // for(int)
                            Log.d("selectedItems", GridViewAdapter.selectedItems + "");

                            for (int index = 0; index < GridViewAdapter.selectedItems.size(); index++) {

                                double amount = 0;
                                for (int key : GridViewAdapter.selectedItems.keySet()) {
                                    double amount1 = 0;
                                    Log.d("key : ", key + "");

                                    amount1 = amount1 + Double.valueOf(GridViewAdapter.selectedItems.get(key) * Double.valueOf(menuInfoModelList.get(key).getMenuItemPrice()));
                                    amount = amount + amount1;


                                    Log.d("value : ", GridViewAdapter.selectedItems.get(key) + "");
                                }


                                if (amount < 0) {
                                    amount = 0;
                                    txt_price_val.setText("₹ " + amount);

                                }
                            }
                            return false;
                        }

                    });

                    txt_item_select.setText(String.valueOf(HorizontalNumberPicker.finalAmount));

                    // for(int)
                    Log.d("selectedItems", GridViewAdapter.selectedItems + "");

                    for (int index = 0; index < GridViewAdapter.selectedItems.size(); index++) {

                        double amount = 0;
                        for (int key : GridViewAdapter.selectedItems.keySet()) {
                            double amount1 = 0;
                            Log.d("key : ", key + "");

                            amount1 = amount1 + Double.valueOf(GridViewAdapter.selectedItems.get(key) * Double.valueOf(menuInfoModelList.get(key).getMenuItemPrice()));
                            amount = amount + amount1;


                            Log.d("value : ", GridViewAdapter.selectedItems.get(key) + "");
                        }

                        txt_price_val.setText("₹ " + amount);
                    }*/

                 /*   txt_item_select.setText(String.valueOf(HorizontalNumberPicker.finalAmount));

                   // for(int)
                    Log.d("selectedItems", GridViewAdapter.selectedItems+"");

                    for (int index = 0;index<GridViewAdapter.selectedItems.size();index++) {

                        double amount = 0;
                        for (int key : GridViewAdapter.selectedItems.keySet()) {
                            double amount1 = 0;
                            Log.d("key : ", key + "");

                            amount1 = amount1 + Double.valueOf(GridViewAdapter.selectedItems.get(key) * Double.valueOf(menuInfoModelList.get(key).getMenuItemPrice()));
                            amount = amount+amount1;


                            Log.d("value : ", GridViewAdapter.selectedItems.get(key) + "");
                        }

                        txt_price_val.setText("₹ " + amount);
                    }*/
                  /*  img_plus.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {

                            Log.d("total Value", HorizontalNumberPicker.finalAmount + 1 + "");
                          //  moneyAmount =  Double.valueOf(menuInfoModelList.get(position).getMenuItemPrice());
                            moneyAmount = 0.0;
                            moneyAmount =(Double.valueOf(menuInfoModelList.get(position).getMenuItemPrice())*(numberPicker.getAmount()));
                           // totalAmt =totalAmt+ moneyAmount;
                            Log.d("total money", getPriceAddValue(moneyAmount)+ "");
                            txt_price_val.setText("₹ " + String.valueOf(getPriceAddValue(moneyAmount)));
                            txt_item_select.setText(String.valueOf(HorizontalNumberPicker.finalAmount + 1));
                            return false;
                        }
                    });*/



                  /*  img_plus.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {

                            Log.d("total Value", HorizontalNumberPicker.finalAmount + 1 + "");
                            //  moneyAmount =  Double.valueOf(menuInfoModelList.get(position).getMenuItemPrice());
                            moneyAmount = 0.0;
                            moneyAmount =(Double.valueOf(menuInfoModelList.get(position).getMenuItemPrice())*(numberPicker.getAmount()));
                            totalAmt =totalAmt+ moneyAmount;
                            Log.d("total money", numberPicker.getAmount() + "");
                            txt_price_val.setText("₹ " + String.valueOf(totalAmt));
                            txt_item_select.setText(String.valueOf(HorizontalNumberPicker.finalAmount + 1));
                            return false;
                        }
                    });*/
                  /* img_plus.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Log.d("total Value", HorizontalNumberPicker.finalAmount + 1 + "");
                            //  moneyAmount =  Double.valueOf(menuInfoModelList.get(position).getMenuItemPrice());
                            moneyAmount = 0.0;
                            moneyAmount =(Double.valueOf(menuInfoModelList.get(position).getMenuItemPrice())*(numberPicker.getAmount()));
                            totalAmt =totalAmt+ moneyAmount;
                            Log.d("total money", numberPicker.getAmount() + "");
                            txt_price_val.setText("₹ " + String.valueOf(totalAmt));
                            txt_item_select.setText(String.valueOf(HorizontalNumberPicker.finalAmount + 1));


                        }
                    });
*//*
                    img_minus.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {

                            Log.d("total Value", HorizontalNumberPicker.finalAmount - 1 + "");
                            moneyAmount = 0.0;
                            moneyAmount = (Double.valueOf(menuInfoModelList.get(position).getMenuItemPrice()) * (numberPicker.getAmount()));
                            Log.d("total money", getPriceMinValue(moneyAmount) + "");
                            // moneyAmount = moneyAmount*(numberPicker.getAmount()-1);
                            txt_price_val.setText("₹ " + String.valueOf(getPriceMinValue(moneyAmount)));

                            // txt_price_val.setText("₹ " + String.valueOf(moneyAmount));
                            txt_item_select.setText(String.valueOf(HorizontalNumberPicker.finalAmount - 1));
                            return false;
                        }
                    });
*/

                   /* img_minus.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Log.d("total Value", HorizontalNumberPicker.finalAmount -1 + "");
                            moneyAmount = moneyAmount-Integer.valueOf(menuInfoModelList.get(position).getMenuItemPrice());

                            Log.d("total money",moneyAmount + "");
                            txt_price_val.setText("₹ " + String.valueOf(moneyAmount));
                            txt_item_select.setText(String.valueOf(HorizontalNumberPicker.finalAmount-1));


                        }
                    });*/


                   /*     for (int index = 0; index < MainActivity.selectedPositions.size(); index++) {


                            try {


                     *//*     moneyAmount = moneyAmount - Double.valueOf(menuInfoModelList.get(index).getMenuItemPrice());

                          Log.d("total money", moneyAmount + "");
                          txt_price_val.setText("₹ " + String.valueOf(moneyAmount*(HorizontalNumberPicker.finalAmount - 1)));*//*
                                // Log.d("total Value", HorizontalNumberPicker.finalAmount + "");

                        *//*moneyAmount = moneyAmount + Integer.valueOf(menuInfoModelList.get(position).getMenuItemPrice());*//*
                     *//*     //  moneyAmount=moneyAmount*HorizontalNumberPicker.finalAmount;
                          Log.d("total money", moneyAmount + "");

                          moneyAmount =moneyAmount+ Double.valueOf(menuInfoModelList.get(index).getMenuItemPrice());
                          txt_price_val.setText("₹ " + String.valueOf(moneyAmount));
                          txt_item_select.setText(String.valueOf(HorizontalNumberPicker.finalAmount));*//*

                                //Log.d("val", id + "");

                            } catch (Exception e) {

                                e.printStackTrace();
                            }

                            Log.d("value", MainActivity.selectedPositions.get(index) + "");
                        }*/

                        // moneyAmount = moneyAmount+Double.valueOf(menuInfoModelList.get(position).getMenuItemPrice());

                        //  Log.d("total money",moneyAmount + "");
/*
                     img_plus = (ImageView)view.findViewById(R.id.btn_plus);

                     img_minus = (ImageView) view.findViewById(R.id.btn_minus);*/


                  /*  numberPicker.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {*/



                      /*  }
                    });*/


              /*  if(view.getId() == R.id.btn_plus) {*/

                  /*  if(id ==Integer.valueOf(view.findViewById(R.id.btn_plus).toString())){

                        Log.d("total Value", HorizontalNumberPicker.finalAmount + 1 + "");
                    }*/
/*
                    img_plus.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                        }
                    });

                    img_plus.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                           Log.d("total Value", HorizontalNumberPicker.finalAmount -1 + "");
                            moneyAmount = moneyAmount-Integer.valueOf(menuInfoModelList.get(position).getMenuItemPrice());

                            Log.d("total money",moneyAmount + "");
                            txt_price_val.setText(String.valueOf(moneyAmount));
                            txt_item_select.setText(String.valueOf(HorizontalNumberPicker.finalAmount -1));


                        }
                    });*/
                        // Log.d("value",view.get+"");
                        // Toast.makeText(MainActivity.this,position+" value",Toast.LENGTH_LONG).show();

                    }
                }}
            });
        }
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
    }*/

    public void putSharedPreference(String email,String password){

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email",email);
        editor.putString("password",password);

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

                grid_val.setAlpha((float) .27);

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







    /*private void initiatePopupWindow() {
        try {
// We need to get the instance of the LayoutInflater
            LayoutInflater inflater = (LayoutInflater) MainActivity.this
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
                        grid_val.setAlpha(1);

                        // click = false;
                    }else{
                        Toast.makeText(MainActivity.this, "Wrong username or password", Toast.LENGTH_LONG).show();
                        menuItem.setIcon(R.drawable.login148);
                    }

                }
            });


            cross.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pwindo.dismiss();
                    grid_val.setAlpha(1);
                    // click = false;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

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
    public TextView getTextViewReference(){

        return txt_item_select;
    }

    double getPriceAddValue(Double amt){

        totalAmt = totalAmt+amt;
        return totalAmt;

    }

    double getPriceMinValue(Double amt){

        totalAmt = totalAmt-amt;
        return totalAmt;

    }

    public  List<Map<String,String>> getRefinedArrayList(List<Map<String,String>> list){

        Map<String,String> selectedValue;
        List<Map<String,String>> list1 = list;

        for(int index = 0;index<list1.size()-1;index++){


            if(list.get(index).get("MenuItemName")==list.get(index+1).get("MenuItemName")){

                continue;


            }else{

                selectedValue = new HashMap<>();

                selectedValue.put("MenuItemName", list.get(index).get("MenuItemName"));
                selectedValue.put("MenuItemPrice", list.get(index).get("MenuItemPrice"));
                selectedValue.put("MenuItemImageURL", list.get(index).get("MenuItemImageURL"));
                // selectedValue.put("selectedItems", String.valueOf(numberPicker.getAmount() - 1));
                list1.add(selectedValue);

            }

        }


        return list1;
    }
}
