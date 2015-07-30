package com.bitcanny.office.mymenu;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MyFavourites extends ActionBarActivity {

    private static String FAVOURITEINFO = "favouriteInfo";
    private static String INFO = "Info";
    private static String MENUITEMIMAGEURL="MenuItemImageURL";
    private static String MENUITEMPRICE = "MenuItemPrice";
    private static String MENUITEMNAME = "MenuItemName";
    private static String TYPE = "type";


    String MenuItemImageURL,MenuItemPrice,MenuItemName,type,returnVal;

    public Toolbar toolbar;
    public ListView listView;
    public ProgressBar pgr_bar;
    List<Map<String,String>> maps = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favourites);

        toolbar = (Toolbar) findViewById(R.id.app_val);
        listView = (ListView) findViewById(R.id.list);
        pgr_bar = (ProgressBar) findViewById(R.id.pgr_bar);
        setSupportActionBar(toolbar);


        new GetMyFavourites().execute();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MyFavourites.this,position+"",Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_favourites, menu);
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


    class GetMyFavourites extends AsyncTask<Void,Void,Void>{



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pgr_bar.setVisibility(View.VISIBLE);
        }


        @Override
        protected Void doInBackground(Void... params) {


            try {
                ServiceHandler handler = new ServiceHandler();

                JsonFunctions functions = new JsonFunctions(handler);

                String json = functions.getFavouritemenu("1", "1", "1");

                if(json != null) {

                    JSONObject object = new JSONObject(json);

                    JSONObject jsonObject = object.getJSONObject(FAVOURITEINFO);



                    returnVal = jsonObject.getString(TYPE);

                    if(returnVal.equals("success")){

                    JSONArray info = jsonObject.getJSONArray(INFO);

                    for(int index = 0; index <info.length();index++) {

                        JSONObject object1 = info.getJSONObject(index);

                        MenuItemImageURL = object1.getString(MENUITEMIMAGEURL);
                        MenuItemPrice = object1.getString(MENUITEMPRICE);
                        MenuItemName = object1.getString(MENUITEMNAME);



                        Map<String,String> map = new HashMap<>();

                        map.put("MenuItemImageURL",MenuItemImageURL);
                        map.put("MenuItemPrice",MenuItemPrice);
                        map.put("MenuItemName",MenuItemName);

                        maps.add(map);
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

            if(returnVal.equals("success")){

                pgr_bar.setVisibility(View.GONE);


                ArrayAdapter arrayAdapter = new MyFavouriteAdapter(MyFavourites.this,R.layout.my_favourite_item,maps);

                listView.setAdapter(arrayAdapter);

            }


        }
    }
}
