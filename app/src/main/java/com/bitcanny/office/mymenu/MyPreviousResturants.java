package com.bitcanny.office.mymenu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyPreviousResturants extends ActionBarActivity {

    List<Map<String,String>> maps;
    ListView listView;
    String resturantName,resturantAddress,resturantCode,resturantUrl;
    private static String MYPREF = "mypref";
    private static String EMAIL = "email";
    private static  String PASSWORD = "password";
    ProgressBar pgr_bar;
    SharedPreferences sharedPreferences;
    Toolbar toolbar;
    Typeface typeface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_previous_resturants);
        toolbar = (Toolbar) findViewById(R.id.app_tl);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sharedPreferences = getSharedPreferences(MYPREF, Context.MODE_PRIVATE);
        maps = new ArrayList<>();
        listView = (ListView) findViewById(R.id.list);
        pgr_bar= (ProgressBar) findViewById(R.id.pgr_bar);
        typeface = Typeface.createFromAsset(getAssets(),"fonts/ufonts.com_century-gothic.ttf");
   /* try {
        if (!sharedPreferences.getString("email", "").equals("")) {
*/
            new GetPreviousResturantsInfo().execute();

        /*} else {

            Intent intent = new Intent(MyPreviousResturants.this, LogInActivity.class);
            startActivity(intent);

        }

    }catch (Exception e){

        e.printStackTrace();
    }*/
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_previous_resturants, menu);
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
    class GetPreviousResturantsInfo extends AsyncTask<Void,Void,Void>{

        String returnValue;


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if(returnValue.equals("success")) {
                pgr_bar.setVisibility(View.GONE);
                ArrayAdapter adapter = new MyPreviousResturantsAdapter(MyPreviousResturants.this,0,maps);

                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                        Intent intent = new Intent(MyPreviousResturants.this,ResturantEntryActivity.class);
                        intent.putExtra("resturant_code",maps.get(position).get("resturantCode"));
                        SharedPreferences settings =getSharedPreferences("mypref", Context.MODE_PRIVATE);
                        settings.edit().clear().commit();
                        deleteDatabase("MenuDb");
                        try {
                            File fdelete = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/MenuApp");
                            if (fdelete.exists()) {
                                if (fdelete.delete()) {
                                    System.out.println(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/MenuApp");
                                } else {
                                    System.out.println(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/MenuApp");
                                }
                            }
                        } catch (Exception e) {

                            e.printStackTrace();
                        }
                        startActivity(intent);

                    }
                });
            }else{

                pgr_bar.setVisibility(View.GONE);
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
                String json = functions.getPreviousresturantapi(sharedPreferences.getString("email", ""));

                if (json != null) {

                    JSONObject object = new JSONObject(json);

                    JSONObject resturantInfo = object.getJSONObject("restaurantInfo");

                 //   JSONObject Info = resturantInfo.getJSONObject("Info");

                    returnValue = resturantInfo.getString("type");

                    if(returnValue.equals("success")) {

                        JSONArray Info = resturantInfo.getJSONArray("Info");

                        for (int index = 0; index < Info.length(); index++) {

                            JSONObject jsonObject = Info.getJSONObject(index);

                            resturantName = jsonObject.getString("RestaurantName");
                            resturantAddress = jsonObject.getString("RestaurantAddress");
                            resturantCode = jsonObject.getString("RestaurantCode");
                            resturantUrl = jsonObject.getString("RestaurantLogImage");

                            Map<String, String> map = new HashMap<>();

                            map.put("resturantName", resturantName);
                            map.put("resturantAddress", resturantAddress);
                            map.put("resturantCode", resturantCode);
                            map.put("resturantUrl", resturantUrl);

                            maps.add(map);

                        }
                    }

                }

            }catch (Exception e){

                e.printStackTrace();
            }
            return null;
        }
    }
}
