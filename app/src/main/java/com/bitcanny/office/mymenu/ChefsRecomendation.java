package com.bitcanny.office.mymenu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChefsRecomendation extends ActionBarActivity {

    public static String CHEFSRECOMENDATIONINFO = "chefsRecomendationInfo";
    public static String INFO = "Info";
    public static String MENUITEMDESC = "MenuItemDesc";
    public static String MENUITEMID = "MenuItemID";
    public static String MENUITEMIMAGEURL = "MenuItemImageURL";
    public static String MENUITEMISCHEFRECOMMEND = "MenuItemIsChefRecommend";
    public static String MENUITEMNAME = "MenuItemName";
    public static String MENUITEMPRICE = "MenuItemPrice";
    public static String TAGID = "TagID";
    public static String TAGNAME = "TagName";
    public static String TYPE = "type";
    Toolbar toolbar;

    PlaceOrderSqlHelperDao dao;
    String MenuItemImageURL,MenuItemName,MenuItemPrice,returnVal;
    List<Map<String,String>> maps = new ArrayList<>();

    ProgressBar progressBar;
    ListView listView;
    SharedPreferences sharedPreferences;
    private static String MYPREF = "mypref";
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chefs_recomendation_main);
        dao = new PlaceOrderSqlHelperDao(this);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        listView = (ListView)findViewById(R.id.list);
        toolbar = (Toolbar) findViewById(R.id.app_tl);

        sharedPreferences = getSharedPreferences(MYPREF, Context.MODE_PRIVATE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        new GetChefsRecomendation().execute();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            /*    for(int index= 0; index<dao.getMenuDetailsRespectToChefsRecomendation().size() ; index++){

                    if(index == position){*/

                        Intent intent = new Intent(ChefsRecomendation.this, MainActivity.class);

                        // intent.putExtra("category_name",arrayList.get(position).get(CATEGORYNAME));

                        putCategory(dao.getMenuDetailsRespectToChefsRecomendation().get(position).getCategory_name());
                        startActivity(intent);
                   /* }*/




                /*}*/

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chefs_recomendation, menu);
        return true;
    }

    public void putCategory(String categoryName){

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("category_name",categoryName);


        editor.commit();

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

        class GetChefsRecomendation extends AsyncTask<Void,Void,Void>{



            @Override
            protected Void doInBackground(Void... params) {

           /*     ServiceHandler handler = new ServiceHandler();
                JsonFunctions jsonFunctions = new JsonFunctions(handler);
                try {
                    String json = jsonFunctions.getChefsrecommendation("1");

                    JSONObject object = new JSONObject(json);

                    JSONObject chefsRecomendationInfo =  object.getJSONObject(CHEFSRECOMENDATIONINFO);

                    returnVal = chefsRecomendationInfo.getString(TYPE);

                    JSONArray Infoval = chefsRecomendationInfo.getJSONArray(INFO);

                    for(int index = 0;index<Infoval.length();index++){

                        JSONObject object1 = Infoval.getJSONObject(index);

                        MenuItemImageURL = object1.getString(MENUITEMIMAGEURL);
                        MenuItemName = object1.getString(MENUITEMNAME);
                        MenuItemPrice = object1.getString(MENUITEMPRICE);

                        Map<String,String> map = new HashMap<>();

                        map.put(MENUITEMIMAGEURL,MenuItemImageURL);
                        map.put(MENUITEMNAME,MenuItemName);
                        map.put(MENUITEMPRICE,MenuItemPrice);

                        Log.d("val", MenuItemImageURL);
                        Log.d("val1",MenuItemName);
                        Log.d("val2",MenuItemPrice);
                        maps.add(map);

                    }




                }catch (Exception e){
                    //Toast.makeText(ChefsRecomendation.this,"No Network Please Check Internet",Toast.LENGTH_LONG).show();
                     e.printStackTrace();
                }*/


               // dao.getMenuDetailsRespectToChefsRecomendation();

                for(int index = 0;index<dao.getMenuDetailsRespectToChefsRecomendation().size();index++){


                    Map<String,String> map = new HashMap<>();

                    map.put(MENUITEMIMAGEURL, dao.getMenuDetailsRespectToChefsRecomendation().get(index).getMenuItemImageURL());

                    if(dao.getMenuDetailsRespectToChefsRecomendation().size()!=getFromSdcard("/MenuApp/MenuCategory/chefsRecomendation").size()) {
                        ResturantEntryActivity.DownloadFromUrl("/MenuApp/MenuCategory/chefsRecomendation", index + ".jpg", JsonFunctions.BASE_URL + dao.getMenuDetailsRespectToChefsRecomendation().get(index).getMenuItemImageURL());
                    }
                    map.put(MENUITEMNAME,dao.getMenuDetailsRespectToChefsRecomendation().get(index).getMenuItemName());
                    map.put(MENUITEMPRICE,dao.getMenuDetailsRespectToChefsRecomendation().get(index).getMenuItemPrice());
                    //map.put("")


                    maps.add(map);

                }

                return null;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
               /* if(returnVal.equals("success")) {
*/
                    progressBar.setVisibility(View.GONE);

                    ArrayAdapter adapter =new ChefsRecomendationAdapter(ChefsRecomendation.this,0, maps);
                listView.setAdapter(adapter);

             /*  }*/
            }
        }

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

                Log.d("val", listFile[i].getAbsolutePath());

            }
        }

        return  f;
    }
}
