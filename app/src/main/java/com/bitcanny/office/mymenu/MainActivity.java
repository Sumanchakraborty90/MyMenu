package com.bitcanny.office.mymenu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.daimajia.slider.library.SliderTypes.BaseSliderView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


public class MainActivity extends ActionBarActivity {


    private static String MYPREF = "mypref";
    private static String EMAIL = "email";
    private static  String PASSWORD = "password";
    SharedPreferences sharedPreferences;

    MenuItem menuItem;
    private PopupWindow pwindo;
    String user_email,user_password;

    private static String CATMENUINFO = "catMenuInfo";
    private static String INFO = "Info";
    private static String MENUITEMDESC = "MenuItemDesc";
    private static String MENUITEMID = "MenuItemID";
    private static String MENUITEMIMAGEURL = "MenuItemImageURL";
    private static String MENUITEMISCHEFRECOMMEND = "MenuItemIsChefRecommend";
    private static String MENUITEMNAME = "MenuItemName";
    private static String MENUITEMPRICE = "MenuItemPrice";
    private static String TAGNAME = "TagName";
    private static String TYPE = "type";
    String catmenuinfo, Info, MenuItemDesc, MenuItemID, MenuItemImageURL, MenuItemIsChefRecommend, MenuItemName, MenuItemPrice, TagName,
            type;
    ArrayList<Map<String,String>> list = new ArrayList<>();
    GridView view;

    RelativeLayout grid_val;

   private Toolbar toolbar;
    ProgressBar progressBar;
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.tval);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_lay);
        view = (GridView)findViewById(R.id.grid_view);
        grid_val = (RelativeLayout) findViewById(R.id.grid);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        sharedPreferences = getSharedPreferences(MYPREF,Context.MODE_PRIVATE);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)getFragmentManager().findFragmentById(R.id.nav_drawer_id);

        drawerFragment.setUi(drawerLayout, toolbar);

        new GetSubCategory().execute();


    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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


    class GetSubCategory extends AsyncTask<Void,Void,Void>{

        String returnType;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... params) {

            ServiceHandler handler = new ServiceHandler();
            JsonFunctions functions = new JsonFunctions(handler);

            try {
                String json = functions.getMenuInformation("1", "1");

                if(json!=null){

                    JSONObject object = new JSONObject(json);

                    JSONObject object1 = object.getJSONObject(CATMENUINFO);

                    JSONArray array = object1.getJSONArray(INFO);

                    for(int index = 0; index<array.length() ; index++){

                        JSONObject jsonObject = array.getJSONObject(index);

                        MenuItemDesc = jsonObject.getString(MENUITEMDESC);
                        MenuItemID = jsonObject.getString(MENUITEMID);
                        MenuItemImageURL = jsonObject.getString(MENUITEMIMAGEURL);
                        MenuItemIsChefRecommend = jsonObject.getString(MENUITEMISCHEFRECOMMEND);
                        MenuItemName = jsonObject.getString(MENUITEMNAME);
                        MenuItemPrice = jsonObject.getString(MENUITEMPRICE);
                        TagName =jsonObject.getString(TAGNAME);

                        Log.d("val",MenuItemDesc);
                        Map<String,String> map =new HashMap<>();

                        map.put(MENUITEMDESC,MenuItemDesc);
                        map.put(MENUITEMID,MenuItemID);
                        map.put(MENUITEMIMAGEURL,MenuItemImageURL);
                        map.put(MENUITEMISCHEFRECOMMEND,MenuItemIsChefRecommend);
                        map.put(MENUITEMNAME,MenuItemName);
                        map.put(MENUITEMPRICE,MenuItemPrice);
                        map.put(TAGNAME,TagName);

                        list.add(map);

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
            progressBar.setVisibility(View.GONE);
            view.setAdapter(new GridViewAdapter(MainActivity.this, list));

            view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                for(int index=0;index<list.size();index++) {

                    if(index == position) {

                        Intent intent = new Intent(MainActivity.this, PlaceOrderActivity.class);

                        intent.putExtra("price",list.get(index).get("MenuItemPrice"));
                        intent.putExtra("image",list.get(index).get(MENUITEMIMAGEURL));
                        intent.putExtra("food_name",list.get(index).get(MENUITEMNAME));
                        intent.putExtra("food_description",list.get(index).get(MENUITEMDESC));

                        startActivity(intent);

                    }
                }
                }
            });
        }
    }



   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_resturant_info, menu);

        if(sharedPreferences.getString("email", "").equals("1") || sharedPreferences.getString("password","").equals("1")){
            menu.findItem(R.id.action_logIn).setIcon(R.drawable.login248);
            menu.findItem(R.id.action_logIn).setTitle("Log Out");
        }

        return true;
    }*/

    public void putSharedPreference(String email,String password){

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email",email);
        editor.putString("password",password);

        editor.commit();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        menuItem = item;
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logIn) {

           /* if(click == false){*/

            if(sharedPreferences.getString("email", "").equals("") || sharedPreferences.getString("password","").equals("")) {

                //initiatePopupWindow();

                grid_val.setAlpha((float) .27);

            }else if(sharedPreferences.getString("email", "").equals("1") || sharedPreferences.getString("password","").equals("1")){
                sharedPreferences.edit().clear().commit();
                item.setIcon(R.drawable.login148);
            }
          /*  }else{
                {
                    item.setIcon(R.drawable.login148);
                    resturant_info.setAlpha(1);
                    click = false;

                }

            }
*/


            return true;
        }



        return super.onOptionsItemSelected(item);
    }







    /*private void initiatePopupWindow() {
        try {
// We need to get the instance of the LayoutInflater
            LayoutInflater inflater = (LayoutInflater) MainActivity.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.log_in_popup,
                    (ViewGroup) findViewById(R.id.pop_up_lay));
            pwindo = new PopupWindow(layout, 350, 450, true);
            pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);

            final EditText email = (EditText)layout.findViewById(R.id.email);
            final EditText password = (EditText)layout.findViewById(R.id.password);

            final ImageView cross = (ImageView) layout.findViewById(R.id.cross);

            Button submit = (Button)layout.findViewById(R.id.submit);

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    user_email = email.getText().toString();
                    user_password = password.getText().toString();

                    if(user_email.equals("1") && user_email.equals("1")){
                        putSharedPreference(user_email,user_password);
                        pwindo.dismiss();
                        menuItem.setIcon(R.drawable.login248);
                        grid_val.setAlpha(1);

                        // click = false;
                    }else{
                        Toast.makeText(MainActivity.this, "Wrong username or password", Toast.LENGTH_LONG).show();
                        menuItem.setIcon(R.drawable.login148);
                    }

                }
            });


            cross.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pwindo.dismiss();
                    grid_val.setAlpha(1);
                    // click = false;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
