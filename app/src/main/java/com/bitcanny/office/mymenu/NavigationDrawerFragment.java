package com.bitcanny.office.mymenu;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
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


    PlaceOrderSqlHelperDao dao;


    MyNavigationAdapter adapter;
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
     /*  recyclerView = (RecyclerView) root.findViewById(R.id.my_recycler_view);

        adapter = new MyNavigationAdapter(getActivity(),maps);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));*/

        //getMaps();
        ArrayAdapter adapter = new NavigationDrawerAdapter(getActivity(),0,ResturantEntryActivity.list1);

        listView.setAdapter(adapter);


        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            if(position == 0){
               // drawerLayout1.openDrawer(Gravity.LEFT);
                Intent intent = new Intent(getActivity(),MyMainCategory.class);
                startActivity(intent);



            }else if(position == 2){

                Intent intent = new Intent(getActivity(),MyFavourites.class);
                startActivity(intent);

            }else if(position == 3){

                Intent intent = new Intent(getActivity(),ChefsRecomendation.class);
                startActivity(intent);

            }else if(position == 4){

                Intent intent = new Intent(getActivity(), CartOrderActivity.class);
                startActivity(intent);
            }else if(position == 5){

                Intent intent = new Intent(getActivity(),OrderToCartActivity.class);
                startActivity(intent);
            }else if(position == 6){

                ResturantEntryActivity.list1.clear();
                dao.dropFrntEndMenu();
                dao.createTableFrontEndMenu();
                Intent intent = new Intent(getActivity(),ResturantEntryActivity.class);
                startActivity(intent);
                getActivity().finish();

            }else if(position == 7){

               // dao.dropTable(RESTAURANTNAME);
                Intent intent = new Intent(getActivity(),ResturantEntryActivity.class);
                startActivity(intent);
             getActivity().finish();




            }else if(position == 8){

                Intent intent = new Intent(getActivity(),LogInActivity.class);
                startActivity(intent);


            }else if(position == 9){

                Intent intent = new Intent(getActivity(),RegistrationActivity.class);
                startActivity(intent);

            }



            }
        });
    }

    public void setUi(DrawerLayout drawerLayout,final Toolbar toolbar) {

        drawerLayout1 = drawerLayout;
        drawerToggle = new ActionBarDrawerToggle(getActivity(),drawerLayout1,toolbar,R.string.drawer_open,R.string.drawer_close){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
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


}
