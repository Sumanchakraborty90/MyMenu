package com.bitcanny.office.mymenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

/**
 * Created by OFFICE on 03-07-2015.
 */
public class ResturantInfoReviewRatingAdapter extends ArrayAdapter {

    Context context;
    List<Map<String,String>> maps;


    public ResturantInfoReviewRatingAdapter(Context context, int resource, List<Map<String,String>> maps) {
        super(context, resource, maps);

        this.context = context;
        this.maps = maps;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.review_rating_view,parent,false);

        TextView txt_name= (TextView)convertView.findViewById(R.id.txt_name);

        TextView txt_review = (TextView) convertView.findViewById(R.id.txt_review);

        RatingBar rt_bar = (RatingBar)convertView.findViewById(R.id.rt_bar);

       // txt_name.setText(maps.get(position).get("RestaurantRatingRate"));
        txt_name.setVisibility(View.GONE);
        txt_review.setText(maps.get(position).get("RestaurantRatingReview"));
        rt_bar.setRating((Float.valueOf(maps.get(position).get("RestaurantRatingRate"))));

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context,position+"",Toast.LENGTH_LONG).show();
            }
        });

        return convertView;
    }
}
