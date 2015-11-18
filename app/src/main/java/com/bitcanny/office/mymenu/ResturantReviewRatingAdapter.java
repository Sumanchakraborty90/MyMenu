package com.bitcanny.office.mymenu;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by OFFICE on 27-08-2015.
 */
public class ResturantReviewRatingAdapter extends ArrayAdapter {

    Context context;
    List<Map<String,String>> maps ;
    Typeface typeface;

    public ResturantReviewRatingAdapter(Context context, int resource, List<Map<String, String>> maps) {
        super(context, resource,maps);
        this.context = context;
        this.maps = maps;
         typeface = Typeface.createFromAsset(context.getAssets(), "fonts/ufonts.com_century-gothic.ttf");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.review_rating_item,parent,false);

        TextView txt_name = (TextView)convertView.findViewById(R.id.txt_name);

        TextView txt_decription = (TextView) convertView.findViewById(R.id.txt_decription);

        RatingBar rating_bar = (RatingBar)convertView.findViewById(R.id.rating_bar);

        RelativeLayout row_item= (RelativeLayout)convertView.findViewById(R.id.row_item);

        row_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        TextView txt_rating = (TextView)convertView.findViewById(R.id.txt_rating);
        txt_name.setTypeface(typeface);
        txt_decription.setTypeface(typeface);
        txt_name.setText(maps.get(position).get("RestaurantRatingAddby"));

        txt_decription.setText(maps.get(position).get("RestaurantRatingReview"));

        rating_bar.setRating(Float.valueOf(maps.get(position).get("RestaurantRatingRate")));

        txt_rating.setText("( "+String.valueOf(maps.get(position).get("RestaurantRatingRate"))+" )");

        return convertView;
    }
}
