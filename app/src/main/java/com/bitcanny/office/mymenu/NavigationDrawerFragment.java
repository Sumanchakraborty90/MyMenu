package com.bitcanny.office.mymenu;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment {

   /* private static  String FOODINFO = "foodInfo";
    private static  String INFO = "Info";
    private static  String MENUITEMPRICE = "MenuItemPrice";
    private static  String TYPE = "type";
    private static String RESTAURANTRATINGID = "RestaurantRatingID";
    private static String RESTAURANTRATINGRATE = "RestaurantRatingRate";
    private static String RESTAURANTRATINGREVIEW = "RestaurantRatingReview";


    String RestaurantRatingID,RestaurantRatingRate,RestaurantRatingReview,returnValue;*/


    private static String MYPREF = "mypref";
    private static String EMAIL = "email";
    private static  String PASSWORD = "password";
    SharedPreferences sharedPreferences;
    PlaceOrderSqlHelperDao dao;
    List<FrontEndMenuModel> list=Collections.emptyList();
    static ArrayAdapter adapter;

   // MyNavigationAdapter adapter;
    private DrawerLayout drawerLayout1;
    private RecyclerView recyclerView;
    public ActionBarDrawerToggle drawerToggle;
    List<Map<String,String>> maps= Collections.emptyList();
    ListView listView;
    public NavigationDrawerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View root = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);

        listView = (ListView) root.findViewById(R.id.list);

        dao = new PlaceOrderSqlHelperDao(getActivity());
        listView.setVerticalScrollBarEnabled(false);
        listView.setHorizontalScrollBarEnabled(false);

        sharedPreferences = getActivity().getSharedPreferences(MYPREF, Context.MODE_PRIVATE);

     /*  recyclerView = (RecyclerView) root.findViewById(R.id.my_recycler_view);

        adapter = new MyNavigationAdapter(getActivity(),maps);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));*/

        //getMaps();



         adapter = new NavigationDrawerAdapter(getActivity(),0,dao.getAllMenu());

        listView.setAdapter(adapter);

     //   adapter.notifyDataSetChanged();
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

             /*   Drawer drawer;
                drawer = (Drawer)getActivity().getSupportFragmentManager().findFragmentById(R.id.theid);*/
              drawerLayout1.closeDrawer(Gravity.LEFT);
              //  drawerLayout1.updateViewLayout();
                if (position == 0) {
                    // drawerLayout1.openDrawer(Gravity.LEFT);
                    Intent intent = new Intent(getActivity(), MyMainCategory.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);


                } else if (position == 2) {

             /*   Intent intent = new Intent(getActivity(),ChefsRecomendation.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
*/
                    if (dao.getAllOrderDetails().size() > 0) {
                        Intent intent = new Intent(getActivity(), CartOrderActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    } else {

                        Toast.makeText(getActivity(), "Please select atleast one item", Toast.LENGTH_LONG).show();
                    }
                } else if (position == 3) {

                    ResturantEntryActivity.list1.clear();
                    dao.dropFrntEndMenu();
                    dao.createTableFrontEndMenu();
                    Intent intent = new Intent(getActivity(), ResturantEntryActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                    SharedPreferences settings = getActivity().getSharedPreferences("mypref", Context.MODE_PRIVATE);
                    settings.edit().clear().commit();
                    getActivity().deleteDatabase("MenuDb");
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
                    // getActivity().deleteFile(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+"/MenuApp");
                } else if (position == 7) {

                    // dao.dropTable(RESTAURANTNAME);


                } else if (position == 4) {

              /*  Intent intent = new Intent(getActivity(),OrderToCartActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);*/

                    Intent intent = new Intent(getActivity(), MyPreviousResturants.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);


                } else if (position == 5) {


                    if(dao.getAllMenu().get(position).getFront_end_menu_name().equals("Sign In")) {


                        if (sharedPreferences.getString("email", "").equals("") || sharedPreferences.getString("password", "").equals("")) {

                            Intent intent = new Intent(getActivity(), LogInActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);

                            //  updateDrawer();

                            adapter = new NavigationDrawerAdapter(getActivity(),0,dao.getAllMenu());

                            listView.setAdapter(adapter);
                           // drawerLayout1.closeDrawer(listView);

                        } else {

                            putSharedPreference("", "");
                        }

                    }else{
                        Intent intent = new Intent(getActivity(), RegistrationActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }

                } else if (position == 6) {

                    if (sharedPreferences.getString("email", "").equals("") || sharedPreferences.getString("password", "").equals("")) {


                        Intent intent = new Intent(getActivity(), LogInActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivityForResult(intent, 1);




                      //  updateDrawer();

                        adapter = new NavigationDrawerAdapter(getActivity(),0,dao.getAllMenu());

                        listView.setAdapter(adapter);
                       // drawerLayout1.closeDrawer(listView);






                    } else {

                        putSharedPreference("", "");
                    }


                }/*else if(position == 8){





            }
*/

            }
        });
    }

    public void setUi(DrawerLayout drawerLayout,final Toolbar toolbar) {

        drawerLayout1 = drawerLayout;
        drawerToggle = new ActionBarDrawerToggle(getActivity(),drawerLayout1,toolbar,R.string.drawer_open,R.string.drawer_close){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                adapter = new NavigationDrawerAdapter(getActivity(),0,dao.getAllMenu());

                listView.setAdapter(adapter);

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);


                adapter = new NavigationDrawerAdapter(getActivity(),0,dao.getAllMenu());

                listView.setAdapter(adapter);
            }

           /* @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if(slideOffset<0.6) {
                    toolbar.setAlpha(1 - slideOffset);

                }
            }*/
        };


        drawerLayout1.setDrawerListener(drawerToggle);
        drawerLayout1.post(new Runnable() {
            @Override
            public void run() {

                drawerToggle.syncState();
            }
        });

    }



    public List<Map<String,String>> getMaps(){


        maps = new ArrayList<>();
        for(int index=0;index<9;index++) {

            Map<String, String> map = new HashMap<>();

            map.put("text","index");

            maps.add(map);
        }

        return maps;
    }


    public void putSharedPreference(String email,String password){

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email",email);
        editor.putString("password",password);

        editor.commit();

    }
    public static void updateDrawer() {
        adapter.notifyDataSetChanged();
        // OR
      //  mListView.setAdapter(new AdapterShowingTheRightTitles());
    }
}
