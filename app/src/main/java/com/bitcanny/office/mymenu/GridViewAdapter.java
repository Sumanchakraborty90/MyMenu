package com.bitcanny.office.mymenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by OFFICE on 17-06-2015.
 */
public class GridViewAdapter extends BaseAdapter  {

    private Context mContext;
    private List<Map<String,String>> maps;



    public GridViewAdapter(Context mContext, List<Map<String, String>> maps) {
        this.mContext = mContext;
        this.maps = maps;
    }

    @Override
    public int getCount() {
        return maps.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater infalInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = infalInflater.inflate(R.layout.grid_row_item,parent,false);

        ImageView grid_img = (ImageView)convertView.findViewById(R.id.grid_img);



        TextView txt_food_name = (TextView)convertView.findViewById(R.id.txt_food_name);

        TextView food_price = (TextView) convertView.findViewById(R.id.food_price);

        ImageView img_view1 = (ImageView)convertView.findViewById(R.id.img_view1);

        ImageView img_view2 = (ImageView)convertView.findViewById(R.id.img_view2);

        ImageView img_view3 = (ImageView)convertView.findViewById(R.id.img_view3);

        ImageView img_view4 = (ImageView)convertView.findViewById(R.id.img_view4);

        ImageView img_view5 = (ImageView)convertView.findViewById(R.id.img_view5);

        ImageView img_view6 = (ImageView)convertView.findViewById(R.id.img_view6);

        Picasso.with(mContext)
                .load(JsonFunctions.BASE_URL+maps.get(position).get("MenuItemImageURL"))
                .placeholder(R.mipmap.ic_launcher) // optional
                .error(R.mipmap.ic_launcher)
        .transform(new RoundedTransformation(20, 0))// optional
                .into(grid_img);

        img_view1.setImageResource(R.drawable.veg);
        img_view2.setImageResource(R.drawable.non_veg);
        img_view3.setImageResource(R.drawable.fish);
        img_view4.setImageResource(R.drawable.high_chilli);
        img_view5.setImageResource(R.drawable.chef_reco);
        img_view6.setImageResource(R.drawable.high_rated);

        txt_food_name.setText(maps.get(position).get("MenuItemName"));
        food_price.setText("Rs "+maps.get(position).get("MenuItemPrice"));





        return convertView;
    }
}
