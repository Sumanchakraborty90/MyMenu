package com.bitcanny.office.mymenu;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyPreviousResturants extends ActionBarActivity {

    List<Map<String,String>> maps;
    ListView listView;
    String resturantName,resturantAddress,resturantCode,resturantUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_previous_resturants);
        maps = new ArrayList<>();
        listView = (ListView) findViewById(R.id.list);




    }

    @Override
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
    }
    class GetPreviousResturantsInfo extends AsyncTask<Void,Void,Void>{

        String returnValue;


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);


        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {

            ServiceHandler handler = new ServiceHandler();

            JsonFunctions functions = new JsonFunctions(handler);
            try {
                String json = functions.getPreviousResturant();

                if (json != null) {

                    JSONObject object = new JSONObject(json);

                    JSONObject resturantInfo = object.getJSONObject("resturantInfo");

                    JSONObject Info = resturantInfo.getJSONObject("Info");

                    JSONArray resturant = Info.getJSONArray("resturant");

                    for(int index = 0; index<resturant.length();index++){

                        JSONObject jsonObject  = resturant.getJSONObject(index);

                        resturantName= jsonObject.getString("resturantName");
                        resturantAddress = jsonObject.getString("resturantAddress");
                        resturantCode = jsonObject.getString("resturantCode");
                        resturantUrl= jsonObject.getString("resturantUrl");

                        Map<String,String> map = new HashMap<>();

                        map.put("resturantName",resturantName);
                        map.put("resturantAddress",resturantAddress);
                        map.put("resturantCode",resturantCode);
                        map.put("resturantUrl",resturantUrl);

                        maps.add(map);

                    }

                }

            }catch (Exception e){

                e.printStackTrace();
            }
            return null;
        }
    }
}
