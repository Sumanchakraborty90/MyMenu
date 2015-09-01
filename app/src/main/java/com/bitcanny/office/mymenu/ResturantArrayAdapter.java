package com.bitcanny.office.mymenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by OFFICE on 25-08-2015.
 */
public class ResturantArrayAdapter extends ArrayAdapter {

    List<Map<String,String>> maps = Collections.emptyList();
    Context context;

    public ResturantArrayAdapter(Context context, int resource, List<Map<String,String>> maps) {
        super(context, resource, maps);

        this.context = context;
        this.maps = maps;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.my_previous_resturant_item,parent,false);

        ImageView img_view= (ImageView)convertView.findViewById(R.id.img_view);

        TextView txt_resturant_name= (TextView) convertView.findViewById(R.id.txt_resturant_name);

        TextView txt_address = (TextView)convertView.findViewById(R.id.txt_address);


        Picasso.with(context).load(JsonFunctions.BASE_URL+maps.get(position).get(""))
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(img_view);

        txt_resturant_name.setText(JsonFunctions.BASE_URL + maps.get(position).get(""));

        txt_address.setText(JsonFunctions.BASE_URL+maps.get(position).get(""));

        return convertView;
    }
}
