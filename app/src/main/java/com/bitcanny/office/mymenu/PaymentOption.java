package com.bitcanny.office.mymenu;

import android.graphics.Typeface;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentOption extends ActionBarActivity {

    ListView list;
    Toolbar toolbar;
    private String[] paymenyTitles;
    private String[] paymenyIcons;

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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

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
}
