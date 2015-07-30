package com.bitcanny.office.mymenu;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by OFFICE on 30-06-2015.
 */
public class NavigationDrawerAdapter extends ArrayAdapter {

    Context context;

    List<Map<String,String>> maps;

    public NavigationDrawerAdapter(Context context, int resource, List<Map<String, String>> maps) {
        super(context, R.layout.navigation_drawer_item, maps);
        this.context = context;
        this.maps = maps;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.navigation_drawer_item,parent,false);

        RelativeLayout rel1 = (RelativeLayout) convertView.findViewById(R.id.rel1);

        RelativeLayout lay2 = (RelativeLayout) convertView.findViewById(R.id.lay2);

        TextView txt_mnu_name= (TextView) convertView.findViewById(R.id.txt_mnu_name);


        if(position == 0){

            rel1.setVisibility(View.VISIBLE);

        }else{

            rel1.setVisibility(View.GONE);

        }
        lay2.setBackgroundResource(R.drawable.focusable_color);
        rel1.setBackgroundResource(R.drawable.white_focus);
        try {


            if (maps.get(position).get("FrontendMenuName").equals("Mood Switch")) {

                lay2.setVisibility(View.GONE);


            } else {

                txt_mnu_name.setText(maps.get(position).get("FrontendMenuName"));
            }

           /* if(maps.get(position).get("logIn").equals("LogIn")) {

                txt_mnu_name.setText(maps.get(position).get("logIn"));

            }*/
        }catch (Exception e){

            e.printStackTrace();
        }
        return convertView;
    }
}
