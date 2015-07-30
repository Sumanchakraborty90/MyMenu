package com.bitcanny.office.mymenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by OFFICE on 23-06-2015.
 */
public class ReviewAdapter extends ArrayAdapter {

    Context context;
    List<Map<String,String>> maps ;

    public ReviewAdapter(Context context, int resource, List<Map<String, String>> maps) {
        super(context, R.layout.review_item, maps);
        this.context = context;
        this.maps = maps;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater infalInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView  = (View)infalInflater.inflate(R.layout.review_item,parent,false);

        TextView textView = (TextView)convertView.findViewById(R.id.txt_name);
        TextView textView1 = (TextView)convertView.findViewById(R.id.txt_review);
        RatingBar bar = (RatingBar)convertView.findViewById(R.id.rating);

        textView.setText(maps.get(position).get("RestaurantRatingAddby"));
        textView1.setText(maps.get(position).get("RestaurantRatingReview"));
        bar.setRating(Float.valueOf(maps.get(position).get("RestaurantRatingRate")));


        return convertView;
    }
}
