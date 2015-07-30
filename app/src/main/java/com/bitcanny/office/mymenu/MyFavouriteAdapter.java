package com.bitcanny.office.mymenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;

/**
 * Created by OFFICE on 29-06-2015.
 */
public class MyFavouriteAdapter extends ArrayAdapter{


    Context context;
    List<Map<String,String>> maps;

    public MyFavouriteAdapter(Context context, int resource, List<Map<String, String>> maps) {
        super(context, R.layout.my_favourite_item, maps);
        this.context = context;
        this.maps = maps;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.my_favourite_item,parent,false);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.img_food);

        TextView  textView = (TextView) convertView.findViewById(R.id.txt_name_of_fd);

        TextView textView1 = (TextView) convertView.findViewById(R.id.txt_price);

        Picasso.with(context).load(JsonFunctions.BASE_URL+maps.get(position).get("MenuItemImageURL"))
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageView);

        textView.setText(maps.get(position).get("MenuItemName"));
        textView1.setText("Rs "+maps.get(position).get("MenuItemPrice"));

        return convertView;
    }
}
