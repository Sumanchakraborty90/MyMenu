package com.bitcanny.office.mymenu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by OFFICE on 01-07-2015.
 */
public class MyMainCategory extends ActionBarActivity {

    Toolbar toolbar;
    ListView listView;

    Context context;
    private static String RESTAURANTCATEGORY = "restaurantCategory";
    private static String INFO = "Info";
    private static String CATEGORYID = "CategoryID";
    private static String CATEGORYIMAGEURL = "CategoryImageURL";
    private static String CATEGORYNAME = "CategoryName";
    private static String TYPE = "type";


    MenuItem menuItem;
    private static String MYPREF = "mypref";
    private static String EMAIL = "email";
    private static  String PASSWORD = "password";
    private PopupWindow pwindo;


    SharedPreferences sharedPreferences;
    ProgressBar bar;
    MenuItem item ;
    String categoryId;
    String categoryImageUrl;
    String categoryName;
    String type;
    String resturantId="1";
    DrawerLayout drawerLayout;
    ArrayList<Map<String, String>> arrayList = new ArrayList<>();
    RelativeLayout rel_layout;

    String user_email,user_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_main);

        toolbar = (Toolbar) findViewById(R.id.tool);
        listView = (ListView) findViewById(R.id.list);
        bar = (ProgressBar) findViewById(R.id.progressBar1);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_lay);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        rel_layout = (RelativeLayout)findViewById(R.id.list_val);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        new CategoryItems().execute();
        sharedPreferences = getSharedPreferences(MYPREF,Context.MODE_PRIVATE);

        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)getFragmentManager().findFragmentById(R.id.nav_drawer_id);

        drawerFragment.setUi(drawerLayout, toolbar);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent  intent = new Intent(MyMainCategory.this,MainActivity.class);
                startActivity(intent);
            }
        });


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
    }
*/
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

               // initiatePopupWindow();

                rel_layout.setAlpha((float) .27);

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
             bar.setVisibility(View.VISIBLE);

        }

        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            //
            try {
                if (type.equals("success")) {

                    try {
                        bar.setVisibility(View.GONE);
                        ArrayAdapter adapter = new CategoryAdapter(MyMainCategory.this, R.layout.category_item, arrayList);

                        listView.setAdapter(adapter);


                        //Toast.makeText(CategoryFragment.this, "Success", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {

                        e.printStackTrace();
                    }
                }

            } catch (Exception e) {
                Toast.makeText(MyMainCategory.this,"Please check internet",Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
    }

    public void putSharedPreference(String email,String password){

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email",email);
        editor.putString("password", password);

        editor.commit();

    }


   /* private void initiatePopupWindow() {
        try {
// We need to get the instance of the LayoutInflater
            LayoutInflater inflater = (LayoutInflater) MyMainCategory.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View layout = inflater.inflate(R.layout.log_in_popup,
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
                        rel_layout.setAlpha(1);

                        // click = false;
                    }else{
                        Toast.makeText(MyMainCategory.this,"Wrong username or password",Toast.LENGTH_LONG).show();
                        menuItem.setIcon(R.drawable.login148);
                    }

                }
            });


            cross.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pwindo.dismiss();
                    rel_layout.setAlpha(1);
                    // click = false;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
