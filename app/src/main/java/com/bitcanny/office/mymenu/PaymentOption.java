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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentOption extends ActionBarActivity {

    ListView list;
    Toolbar toolbar;
    private String[] paymenyTitles;
    private String[] paymenyIcons;
    private static String MYPREF = "mypref";
    private static String EMAIL = "email";
    private static  String PASSWORD = "password";
    SharedPreferences sharedPreferences;
    List<Map<String,String>> Items;
    List<Map<String,String>> Icons;
    Typeface typeface;
    TextView txt_amt_payable,txt_caption,txt_price,txt_get_voucher,txt_enter_voucher_code;
    Button btn_apply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_option);
        toolbar = (Toolbar)findViewById(R.id.app_tl);
        list = (ListView) findViewById(R.id.list);
        txt_amt_payable= (TextView) findViewById(R.id.txt_amt_payable);
        txt_caption= (TextView) findViewById(R.id.txt_caption);
        txt_price= (TextView) findViewById(R.id.txt_price);
        txt_get_voucher= (TextView) findViewById(R.id.txt_get_voucher);
        txt_enter_voucher_code= (TextView) findViewById(R.id.txt_enter_voucher_code);
        typeface = Typeface.createFromAsset(getAssets(),"fonts/ufonts.com_century-gothic.ttf");
        sharedPreferences = getSharedPreferences(MYPREF, Context.MODE_PRIVATE);
        txt_amt_payable.setTypeface(typeface);
        txt_caption.setTypeface(typeface);
        txt_price.setTypeface(typeface);
        txt_get_voucher.setTypeface(typeface);
        txt_enter_voucher_code.setTypeface(typeface);



        Items = new ArrayList<>();
        Icons = new ArrayList<>();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        paymenyTitles = getResources().getStringArray(R.array.payment_items);
        paymenyIcons =  getResources().getStringArray(R.array.payment_icons);

        Items = getMapItems(paymenyTitles);
        Icons = getMapItems(paymenyIcons);

        ArrayAdapter adapter = new PaymentOptionAdapter(PaymentOption.this,0,Items,Icons);

        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(PaymentOption.this,position+"",Toast.LENGTH_LONG).show();

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_payment_option, menu);
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

            new EndOrder().execute();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
/* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_payment_option, menu);
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
*/
   public List<Map<String,String>> getMapItems(String[] items){

       List<Map<String,String>> maps = new ArrayList<>();
       for(int index = 0 ;index<items.length;index++){

          //  Map<String,String> stringMapMap = new HashMap<>();

           Map<String,String> map = new HashMap<>();
           map.put(String.valueOf(index),items[index]);


          // stringMapMap.put(String.valueOf(index),map);

           maps.add(map);

       }

       return maps;
   }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
      /*  MainActivity.totalPayableAmt =0;
        finish();*/

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(PaymentOption.this);
            Intent intent = new Intent(PaymentOption.this, MyMainCategory.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent,options.toBundle());
            finish();
        }else{

            Intent intent = new Intent(PaymentOption.this, MyMainCategory.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
            finish();

        }

    }

    class EndOrder extends AsyncTask<Void,Void,Void>{

        String returnType;
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if(returnType.equals("success")){

                putSharedItemsAmtPreference("0", "0");
              //  SharedPreferences settings = getSharedPreferences("MyPreferences", 0);
                if(sharedPreferences.contains("tableCode")) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove("tableCode");
                    editor.commit();
                }
                Toast.makeText(PaymentOption.this,"You have successfully completed your order",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(PaymentOption.this,ResturantInfo.class);

                startActivity(intent);
                finish();

            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {

            ServiceHandler handler = new ServiceHandler();
            JsonFunctions functions = new JsonFunctions(handler);

            String json = functions.endOrder(ResturantInfo.globalResturantId, sharedPreferences.getString("tableCode", ""));

            if(json!= null){

                try {
                    JSONObject jsonObject = new JSONObject(json);

                    returnType = jsonObject.getString("type");









                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            return null;
        }
    }


    public void  putSharedItemsAmtPreference(String selectedItems,String totalAmt){

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("selectedItems",selectedItems);
        editor.putString("totalAmt", totalAmt);

        editor.commit();

    }

    public void  putSharedPreferenceTableCode(String tableCode){

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("tableCode", tableCode);


        editor.commit();

    }
}
