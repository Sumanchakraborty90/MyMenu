package com.bitcanny.office.mymenu;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.Collections;
import java.util.List;

public class OrderToCartActivity extends ActionBarActivity {

    PlaceOrderSqlHelperDao dao;
    List<OrderToCartAdapterModel> list;
    ListView view;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_to_cart);
        dao = new PlaceOrderSqlHelperDao(this);
        view = (ListView)findViewById(R.id.list);
        button = (Button) findViewById(R.id.btn_purchase);
        try {
            list = dao.getAllOrderDetails();
            //list = Collections.EMPTY_LIST;
            ArrayAdapter adapter = new OrderToCartAdapter(OrderToCartActivity.this, 0, list);
            view.setAdapter(adapter);
        }catch (Exception e){

            e.printStackTrace();
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_order_to_cart, menu);
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
}
