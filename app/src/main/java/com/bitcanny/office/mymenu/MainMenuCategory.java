package com.bitcanny.office.mymenu;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
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
import java.util.Map;


public class MainMenuCategory extends ActionBarActivity {

    Context context;
    private static String RESTAURANTCATEGORY = "restaurantCategory";
    private static String INFO = "Info";
    private static String CATEGORYID = "CategoryID";
    private static String CATEGORYIMAGEURL = "CategoryImageURL";
    private static String CATEGORYNAME = "CategoryName";
    private static String TYPE = "type";
    ProgressBar bar;
    MenuItem item ;
    String categoryId;
    String categoryImageUrl;
    String categoryName;
    String type;
    String resturantId="1";

    ArrayList<Map<String, String>> arrayList = new ArrayList<>();


    public  ListView listView;
    Toolbar toolbar;

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_main);
        listView = (ListView)findViewById(R.id.list);
        toolbar = (Toolbar) findViewById(R.id.tool);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_lay);
        setSupportActionBar(toolbar);

        new CategoryItems().execute();



        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)getFragmentManager().findFragmentById(R.id.nav_drawer_id);

        drawerFragment.setUi(drawerLayout, toolbar);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainMenuCategory.this,position+"",Toast.LENGTH_LONG).show();
            }
        });
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu_category, menu);
        return true;
    }*/

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



    class CategoryItems extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            try {

                ServiceHandler handler = new ServiceHandler();

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
                }
            } catch (Exception e) {

                e.printStackTrace();

            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
           // bar.setVisibility(View.VISIBLE);

        }

        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
          //  bar.setVisibility(View.GONE);
            if (type.equals("success")) {

                try {
                    ArrayAdapter adapter = new CategoryAdapter(MainMenuCategory.this,R.layout.category_item,arrayList);

                    listView.setAdapter(adapter);



                    //Toast.makeText(CategoryFragment.this, "Success", Toast.LENGTH_LONG).show();
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        }
    }
}
