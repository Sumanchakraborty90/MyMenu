package com.bitcanny.office.mymenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;

/**
 * Created by OFFICE on 29-06-2015.
 */
public class PlaceOrderItemAdapter extends ArrayAdapter {

    Context context;
    List<Map<String,String>> maps;

    public PlaceOrderItemAdapter(Context context, int resource, List<Map<String, String>> maps) {
        super(context, R.layout.place_order_item, maps);
        this.context = context;
        this.maps = maps;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.place_order_item,parent,false);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.img_food);

        TextView fdName = (TextView) convertView.findViewById(R.id.text1);

        TextView plateNumebr = (TextView) convertView.findViewById(R.id.text3);

        TextView fdPrice = (TextView) convertView.findViewById(R.id.text2);

        Picasso.with(context).load(JsonFunctions.BASE_URL+maps.get(position).get("MenuItemImageURL"))
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageView);

        fdName.setText(maps.get(position).get("MenuItemName"));
        plateNumebr.setText("Rs "+maps.get(position).get("MenuItemPrice"));
        fdPrice.setText(maps.get(position).get(""));


        return convertView;
    }
}
