package com.bitcanny.office.mymenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;

/**
 * Created by OFFICE on 20-07-2015.
 */
public class ChefsRecomendationAdapter extends ArrayAdapter {
    Context context;
    List<Map<String,String>> list;

    public ChefsRecomendationAdapter(Context context, int resource, List<Map<String,String>> list) {
        super(context, R.layout.chefs_recomendation_item, list);
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater)context.getApplicationContext().getSystemService(context.LAYOUT_INFLATER_SERVICE);

        convertView = layoutInflater.inflate(R.layout.chefs_recomendation_item,parent,false);

        TextView view = (TextView) convertView.findViewById(R.id.txt_item_name);
        ImageView imageView = (ImageView)convertView.findViewById(R.id.img_icon);

        view.setText(list.get(position).get("MenuItemName"));

        Picasso.with(context)
                .load(JsonFunctions.BASE_URL+list.get(position).get("MenuItemImageURL"))
                .placeholder(R.mipmap.ic_launcher) // optional
                .error(R.mipmap.ic_launcher)
                .transform(new RoundedTransformation(20, 0))// optional
                .into(imageView);


        return convertView;

    }
}
