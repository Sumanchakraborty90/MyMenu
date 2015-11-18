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
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import android.support.v7.app.AppCompatActivity;

public class CartOrderActivity extends ActionBarActivity {

    PlaceOrderSqlHelperDao dao;
    List<OrderToCartAdapterModel> list;
    ListView view;
    ListView chargesList;
    Toolbar toolbar;
    Button button;
    List<Map<String,String>> listValue;
    Typeface typeface;
    List<TaxChargesModel> chargesValue;
    String OrderMenuItemID ;

    String OrderMenuItemName ;

    String OrderMenuItemQty ;
    List<Map<String,String>> previousOrder = Collections.emptyList();

    List<OrderToCartAdapterModel> previousOrderToInputIntoTable = Collections.emptyList();
    List<OrderToCartAdapterModel> previousOrderToInputIntoTable1 = Collections.emptyList();
    TextView sub_total,txt_vat_applied,txt_service_charges,txt_amount_payable,txt_ttl_bill,txt_sub_total,txt_amt_payable;
    private static String MYPREF = "mypref";
    private static String EMAIL = "email";
    private static  String PASSWORD = "password";
    SharedPreferences sharedPreferences;
    Button btn_purchase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_to_cart);

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
        }
        dao = new PlaceOrderSqlHelperDao(this);
        typeface= Typeface.createFromAsset(getAssets(),"fonts/ufonts.com_century-gothic.ttf");
        chargesValue = new ArrayList<>();
        view = (ListView)findViewById(R.id.list);
        chargesList = (ListView) findViewById(R.id.chargesList);
        button = (Button) findViewById(R.id.btn_purchase);
        double totalAmtLocal = 0.0;
        button.setTypeface(typeface);
        toolbar = (Toolbar) findViewById(R.id.app_tl);
        sub_total = (TextView)findViewById(R.id.sub_total);
        txt_vat_applied = (TextView)findViewById(R.id.txt_vat_applied);
        txt_service_charges = (TextView)findViewById(R.id.txt_service_charges);
        txt_amount_payable = (TextView) findViewById(R.id.txt_amount_payable);
        txt_ttl_bill  = (TextView) findViewById(R.id.txt_ttl_bill);
        txt_sub_total  = (TextView) findViewById(R.id.txt_sub_total);
        txt_amt_payable  = (TextView) findViewById(R.id.txt_amt_payable);
        previousOrder= new ArrayList<>();
        previousOrderToInputIntoTable =  new ArrayList<>();



        previousOrderToInputIntoTable =  dao.getAllOrderDetails();

        sub_total.setTypeface(typeface);
        txt_vat_applied.setTypeface(typeface);
        txt_vat_applied.setTypeface(typeface);
        txt_amount_payable.setTypeface(typeface);
        txt_ttl_bill.setTypeface(typeface);
        txt_sub_total.setTypeface(typeface);
        txt_amt_payable.setTypeface(typeface);


        sharedPreferences = getSharedPreferences(MYPREF, Context.MODE_PRIVATE);

        //sub_total.setText(String.valueOf(GridViewAdapter.mnyAmt));
        sub_total.setText("Rs."+String.valueOf(sharedPreferences.getString("totalAmt", "")));

        chargesValue = dao.getTaxDetails();

        Log.d("previousOrderCar", previousOrder.size() + "");
        txt_vat_applied.setText("14%");
        txt_service_charges.setText("3%");

        //totalPayableAmt =(Double.valueOf(String.valueOf(GridViewAdapter.mnyAmt))+(Double.valueOf(String.valueOf(GridViewAdapter.mnyAmt))*(14/100))+(Double.valueOf(String.valueOf(GridViewAdapter.mnyAmt))*(3/100)));

       // txt_amount_payable.setText(String.valueOf(totalPayableAmt));

        Log.d("jsonSTring",getJson());
        ArrayAdapter adapter = new ChargesAdapter(CartOrderActivity.this,0,chargesValue);
        chargesList.setAdapter(adapter);

        list = new ArrayList<>();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       // ResturantInfo.totalPayableAmt = Double.valueOf(sharedPreferences.getString("totalAmt", ""));
      //  txt_item_select.setText(String.valueOf(sharedPreferences.getString("selectedItems", "")));

       // ResturantInfo.finalorderedItems.addAll(ResturantInfo.orderedItems);

        new GetPreviousOrder().execute(ResturantInfo.globalResturantId, sharedPreferences.getString("tableCode", ""));

    try {

        setAmountPayable(chargesValue, GridViewAdapter.mnyAmt, txt_amount_payable);
       /* Log.d("Size of chargesValue", chargesValue.size()+"");
        for (int index = 0; index < chargesValue.size(); index++) {
            double localTaxAmt=0.0;
            if(Integer.valueOf(chargesValue.get(index).getTax_charges_type()) == 2) {
                localTaxAmt= Double.valueOf(chargesValue.get(index).getTax_charges_amount()) / 100;
                Log.d("localTaxAmt1",localTaxAmt+"");
                MainActivity.totalPayableAmt = MainActivity.totalPayableAmt + (totalAmtLocal * localTaxAmt);
                Log.d("totalPayableAmt1", MainActivity.totalPayableAmt+"");

            }else{


                localTaxAmt =Double.valueOf(chargesValue.get(index).getTax_charges_amount());
                Log.d("localTaxAmt2",localTaxAmt+"");

                if(totalAmtLocal!=0) {
                    MainActivity.totalPayableAmt =  MainActivity.totalPayableAmt + localTaxAmt;
                    Log.d("totalPayableAmt2",  MainActivity.totalPayableAmt + "");
                }else {

                    MainActivity.totalPayableAmt = totalAmtLocal;

                }
            }







        }
        MainActivity.totalPayableAmt =  MainActivity.totalPayableAmt+totalAmtLocal;
        txt_amount_payable.setText("Rs." + String.valueOf(new DecimalFormat("##.##").format( MainActivity.totalPayableAmt)));*/
    }catch (Exception e){

        e.printStackTrace();
    }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(sharedPreferences.getString("email", "").equals("") || sharedPreferences.getString("password","").equals("")) {

                    Intent intent = new Intent(CartOrderActivity.this,LogInActivity.class);

                    startActivity(intent);
                    finish();

                }else{

                    new postJson().execute();
                    Intent intent = new Intent(CartOrderActivity.this,PaymentOption.class);

                    startActivity(intent);
                    finish();

                }
                ResturantEntryActivity.order.clear();



            }
        });
    }
   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cart_order, menu);
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
        if(id == R.id.home){

            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
*/
    @Override
    public void onBackPressed() {
        super.onBackPressed();
      /*  MainActivity.totalPayableAmt =0;
        finish();*/

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(CartOrderActivity.this);
            Intent intent = new Intent(CartOrderActivity.this, MyMainCategory.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent,options.toBundle());
            finish();
        }else{

            Intent intent = new Intent(CartOrderActivity.this, MyMainCategory.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
            finish();

        }

    }

  public String getJson(){

      String json = "";
      JSONObject jsonObject = new JSONObject();
      try {
          jsonObject.put("user_name",sharedPreferences.getString("email", ""));
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

      try {
          jsonObject.put("json",jsonArray);

          json = jsonObject.toString();

      } catch (JSONException e) {
          e.printStackTrace();
      }

      return json;
   }

    class postJson extends AsyncTask<Void,Void,Void>{

        String returnVal;

        @Override
        protected Void doInBackground(Void... params) {

            ServiceHandler handler = new ServiceHandler();
            JsonFunctions functions = new JsonFunctions(handler);
            try {
                String json = functions.postJson(getJson());

                if (json != null) {

                    JSONObject jsonVal = new JSONObject(json);

                    JSONObject orderInfo = jsonVal.getJSONObject("orderInfo");

                    returnVal = orderInfo.getString("type");

                    if(returnVal.equals("success")){


                        Log.d("BitcannySuccess",returnVal);
                    }



                }

            }catch (Exception e){

                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        try {
            if (returnVal.equals("success")) {
                //if(dao.updateItemSelectedToZero() == true) {

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
                    Toast.makeText(CartOrderActivity.this, "You have successfully placed your order", Toast.LENGTH_LONG).show();

                //}

            }
        }catch(Exception e){

            e.printStackTrace();
        }
        }
    }


    public static void setAmountPayable(List<TaxChargesModel> chargesValue,double totalAmtLocal,TextView txt_amount_payable){
        double totalPayableAmt=0.0;
    try{
        for (int index = 0; index < chargesValue.size(); index++) {
            double localTaxAmt=0.0;
            if(Integer.valueOf(chargesValue.get(index).getTax_charges_type()) == 2) {
                localTaxAmt= Double.valueOf(chargesValue.get(index).getTax_charges_amount()) / 100;
                Log.d("localTaxAmt1",localTaxAmt+"");
             totalPayableAmt = totalPayableAmt + (totalAmtLocal * localTaxAmt);
                Log.d("totalPayableAmt1", totalPayableAmt+"");

            }else{


                localTaxAmt =Double.valueOf(chargesValue.get(index).getTax_charges_amount());
                Log.d("localTaxAmt2",localTaxAmt+"");

                if(totalAmtLocal!=0) {
                  totalPayableAmt =  totalPayableAmt + localTaxAmt;
                    Log.d("totalPayableAmt2",  totalPayableAmt + "");
                }else {

                    totalPayableAmt = totalAmtLocal;

                }
            }







        }
       totalPayableAmt =  totalPayableAmt+totalAmtLocal;
        txt_amount_payable.setText("Rs." + String.valueOf(new DecimalFormat("##.##").format(totalPayableAmt)));
    }catch (Exception e){

        e.printStackTrace();
    }

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


          //  {"orderInfo":{"orderItemInfo":[{"OrderMenuID":"26","OrderMenuOrderID":"9","OrderMenuItemID":"1","OrderMenuItemName":"KAIRI PANI","OrderMenuItemQty":"3","OrderMenuItemPrice":"150","OrderMenuAddon":"2015-10-28 11:03:44"},{"OrderMenuID":"27","OrderMenuOrderID":"9","OrderMenuItemID":"3","OrderMenuItemName":"VEG-AU-GRATIN","OrderMenuItemQty":"2","OrderMenuItemPrice":"250","OrderMenuAddon":"2015-10-28 11:03:44"}],"grossAmount":950,"totalQty":5}}

            try {
                if (json != null) {

                    JSONObject jsonObject1 = new JSONObject(json);

                    JSONObject jsonObject =jsonObject1.getJSONObject("orderInfo");

                    JSONArray orderInfo =jsonObject.getJSONArray("orderItemInfo");

                    for(int index = 0;index<orderInfo.length();index++){

                        JSONObject object = orderInfo.getJSONObject(index);

                        OrderMenuItemID = object.getString("OrderMenuItemID");

                        OrderMenuItemName = object.getString("OrderMenuItemName");

                        Log.d("OrderMenuItemName",OrderMenuItemName);

                        OrderMenuItemQty = object.getString("OrderMenuItemQty");

                        String OrderMenuItemPrice= object.getString("OrderMenuItemPrice");

                        String OrderMenuItemID= object.getString("OrderMenuItemID");

                        Log.d("OrderMenuItemQty", OrderMenuItemQty);

                        Map<String,String> map = new HashMap<>();

                        map.put("OrderMenuItemID", OrderMenuItemID);


                        map.put("OrderMenuItemName", OrderMenuItemName);
                        map.put("OrderMenuItemPrice", OrderMenuItemPrice);
                        Log.d("OrderMenuItemQty", OrderMenuItemQty);
                        map.put("OrderMenuItemQty", OrderMenuItemQty);
                        //map.put("FLAG", "1");


                      /*  if(previousOrderToInputIntoTable.contains(OrderMenuItemName)){


                        }else {*/
                        previousOrder.add(map);
                          //  previousOrderToInputIntoTable1.add(new OrderToCartAdapterModel(OrderMenuItemName, OrderMenuItemPrice, "", sharedPreferences.getString("email", ""), "", OrderMenuItemID));


                         /*  for(int index1 = 0; index1<previousOrderToInputIntoTable.size();index1++) {


                               if(!previousOrderToInputIntoTable.get(index1).getOrder_name().contains(previousOrder.get(index).get("OrderMenuItemID")))
                               {

                                   Log.d("previousOrderMENUiD",previousOrder.get(index).get("OrderMenuItemID"));
                                   Log.d("CURENTOrderMENUiD",previousOrderToInputIntoTable.get(index1).getMenu_id());

                                  // dao.addToOrderValue(OrderMenuItemName, OrderMenuItemPrice, "0", "", OrderMenuItemID,"1");

                                  // dao.addToOrderValue(OrderMenuItemName, OrderMenuItemPrice, "0", "", OrderMenuItemID,"1");
                               //    previousOrderToInputIntoTable.add(new OrderToCartAdapterModel(String order_name, String order_item_price, String order_item_quantity, String user_id, String order_item_image_url, String menu_id, String user_order_flag));


                                   previousOrderToInputIntoTable.add(new OrderToCartAdapterModel(previousOrder.get(index).get("OrderMenuItemName"), previousOrder.get(index).get("OrderMenuItemPrice"), "0", sharedPreferences.getString("email", ""), "",previousOrder.get(index).get("OrderMenuItemID"),"1"));

                                   break;
                               }else {






                               }

                          // }


                        }*/

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

            Log.d("previousOrderCa", previousOrderToInputIntoTable.size() + "");
            try {

                //list = Collections.EMPTY_LIST;

              // previousOrderToInputIntoTable1 = checkCart(previousOrder,previousOrderToInputIntoTable);


/*
                List<OrderToCartAdapterModel> newList = new ArrayList<OrderToCartAdapterModel>();

                newList.addAll(dao.getAllOrderDetails());
                newList.addAll(dao.getAllOrderDetailsOfOtherUsers());*/
              //  dao.getAllOrderDetails().addAll(dao.getAllOrderDetailsOfOtherUsers());
                ArrayAdapter adapter1 = new CartOrderAdapter(CartOrderActivity.this, 0, dao.getAllOrderDetails(),sub_total,txt_amount_payable,chargesValue,previousOrder);
                view.setAdapter(adapter1);
              //  totalAmtLocal = Double.valueOf(sharedPreferences.getString("totalAmt", ""));

            }catch (Exception e){

                e.printStackTrace();
            }

        }


    }


    public List<Map<String,String>> checkCart(List<Map<String,String>> otherUserCart,List<OrderToCartAdapterModel> userCart ) {

       // List<OrderToCartAdapterModel> maps = new ArrayList<>(userCart.get());
        List<Map<String, String>> maps1 = new ArrayList<>();
        List<Map<String, String>> maps2 = new ArrayList<>();

        //   dao.addToOrderValue(maps.get(position).get("MenuItemName"), maps.get(position).get("MenuItemPrice"), String.valueOf(value), getFromSdcard("/MenuApp/MenuItemGrid/").get(indexFlag), maps.get(position).get("menu_id"),"0");

     //   maps

      /*  for (int index = 0; index < userCart.size(); index++) {

            Map<String, String> map = new TreeMap<>();


            Log.d("MenuNAme",userCart.get(index).getOrder_name());
            map.put("MenuItemName", userCart.get(index).getOrder_name());
            map.put("MenuItemPrice", userCart.get(index).getOrder_item_price());
            map.put("OrderMenuItemQty", userCart.get(index).getOrder_item_quantity());
            map.put("MenuItemIMage", userCart.get(index).getOrder_item_image_url());
            map.put("menu_id", userCart.get(index).getMenu_id());

          //  dao.addToOrderValue(maps.get(position).get("MenuItemName"), maps.get(position).get("MenuItemPrice"), String.valueOf(value), getFromSdcard("/MenuApp/MenuItemGrid/").get(indexFlag), maps.get(position).get("menu_id"), "0");

               *//* for(int index1 = 0;index1<otherUserCart.size();index1++){

                    //Map<String,String> map = new TreeMap<>();

                    map.put("MenuItemName",otherUserCart.get(index1).get("OrderMenuItemName"));
                    map.put("MenuItemPrice",otherUserCart.get(index1).get("OrderMenuItemPrice"));
                    map.put("OrderMenuItemQty",otherUserCart.get(index1).get("OrderMenuItemQty"));
                    map.put("MenuItemIMage",otherUserCart.get(index1).get(""));
                    map.put("menu_id",otherUserCart.get(index1).get("OrderMenuItemID"));

                }*//*

            maps.add(map);
        }*/

       /* for(int index = 0;index<otherUserCart.size();index++){


            Map<String,String> map = new TreeMap<>();
            for(int index1 = 0;index1<otherUserCart.size();index1++){




                map.put("MenuItemName",otherUserCart.get(index1).get("OrderMenuItemName"));
                map.put("MenuItemPrice",otherUserCart.get(index1).get("OrderMenuItemPrice"));
                map.put("OrderMenuItemQty",otherUserCart.get(index1).get("OrderMenuItemQty"));
                map.put("MenuItemIMage",otherUserCart.get(index1).get(""));
                map.put("menu_id",otherUserCart.get(index1).get("OrderMenuItemID"));

            }
            maps1.add(map);
        }

        maps2.addAll(maps);
        maps2.addAll(maps1);*/

       /* try {
            dao.deleteOrderedItems();
                   *//* for (int index = 0; index < dao.getAllOrderDetails().size(); index++) {
                        //int i=dao.updateSelectedItemsInMenu(dao.getAllOrderDetails().get(index).getOrder_name(),"0");
                       // dao.updateSelectedItems(dao.getAllOrderDetails().get(index).getOrder_name(), "0");
                        //Log.d("updated",i+"");

                    }*//*
        }catch (Exception e){

            e.printStackTrace();
        }
*/
        //for(int index = 0 ;index<maps2.size();index++){

         //   dao.addToOrderValue(maps2.get(index).get("MenuItemName"), maps2.get(index).get("MenuItemPrice"), OrderMenuItemQty, maps2.get(index).get("MenuItemIMage"), maps2.get(index).get("menu_id"),"0");



       // }


        return maps2;

    }
}
