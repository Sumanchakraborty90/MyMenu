package com.bitcanny.office.mymenu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import android.support.v7.app.AppCompatActivity;

public class CartOrderActivity extends ActionBarActivity {

    PlaceOrderSqlHelperDao dao;
    List<OrderToCartAdapterModel> list;
    ListView view;
    Toolbar toolbar;
    Button button;
    List<Map<String,String>> listValue;
    double totalPayableAmt = 0.0;
    TextView sub_total,txt_vat_applied,txt_service_charges,txt_amount_payable;
    private static String MYPREF = "mypref";
    private static String EMAIL = "email";
    private static  String PASSWORD = "password";
    SharedPreferences sharedPreferences;
    Button btn_purchase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_to_cart);
        dao = new PlaceOrderSqlHelperDao(this);
        view = (ListView)findViewById(R.id.list);
        button = (Button) findViewById(R.id.btn_purchase);
        toolbar = (Toolbar) findViewById(R.id.app_tl);
        sub_total = (TextView)findViewById(R.id.sub_total);
        txt_vat_applied = (TextView)findViewById(R.id.txt_vat_applied);
        txt_service_charges = (TextView)findViewById(R.id.txt_service_charges);
        txt_amount_payable = (TextView) findViewById(R.id.txt_amount_payable);

        sharedPreferences = getSharedPreferences(MYPREF, Context.MODE_PRIVATE);

        //sub_total.setText(String.valueOf(GridViewAdapter.mnyAmt));
        sub_total.setText(String.valueOf(sharedPreferences.getString("totalAmt", "")));

        txt_vat_applied.setText("14%");
        txt_service_charges.setText("3%");

        totalPayableAmt =(Double.valueOf(String.valueOf(GridViewAdapter.mnyAmt))+(Double.valueOf(String.valueOf(GridViewAdapter.mnyAmt))*(14/100))+(Double.valueOf(String.valueOf(GridViewAdapter.mnyAmt))*(3/100)));

       // txt_amount_payable.setText(String.valueOf(totalPayableAmt));

        Log.d("jsonSTring",getJson());

        list = new ArrayList<>();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

      //  txt_item_select.setText(String.valueOf(sharedPreferences.getString("selectedItems", "")));
        txt_amount_payable.setText(String.valueOf(sharedPreferences.getString("totalAmt", "")));
       // ResturantInfo.finalorderedItems.addAll(ResturantInfo.orderedItems);
        try {

            //list = Collections.EMPTY_LIST;


            ArrayAdapter adapter = new CartOrderAdapter(CartOrderActivity.this, 0, dao.getAllOrderDetails(),sub_total,txt_amount_payable);
            view.setAdapter(adapter);

        }catch (Exception e){

            e.printStackTrace();
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(sharedPreferences.getString("email", "").equals("") || sharedPreferences.getString("password","").equals("")) {

                    Intent intent = new Intent(CartOrderActivity.this,LogInActivity.class);

                    startActivity(intent);

                }else{


                    Intent intent = new Intent(CartOrderActivity.this,PaymentOption.class);

                    startActivity(intent);

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

        finish();
        startActivity(getIntent());
    }

  public String getJson(){

      String json = "";
      JSONObject jsonObject = new JSONObject();
      try {
          jsonObject.put("user_id",sharedPreferences.getString("email", ""));
          jsonObject.put("resturant_id","resturant_id");
      } catch (JSONException e) {
          e.printStackTrace();
      }
      JSONArray jsonArray = new JSONArray();
      for(int index = 0;index<dao.getAllOrderDetails().size();index++) {

          try {
              JSONObject object = new JSONObject();



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
}
