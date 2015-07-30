package com.bitcanny.office.mymenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by OFFICE on 21-07-2015.
 */
public class OrderToCartAdapter extends ArrayAdapter {

    Context context;
    List<OrderToCartAdapterModel> maps;

    public OrderToCartAdapter(Context context, int resource, List<OrderToCartAdapterModel> maps) {
        super(context, resource, maps);

        this.context = context ;
        this.maps = maps ;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.order_to_cart,parent,false);

        ImageView img_icon = (ImageView) convertView.findViewById(R.id.img_icon);

        TextView txt_item_name = (TextView) convertView.findViewById(R.id.txt_item_name);

        TextView txt_price = (TextView) convertView.findViewById(R.id.txt_price);

        Spinner spinner = (Spinner) convertView.findViewById(R.id.spinner);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item, getItems());
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        Picasso.with(context).load(JsonFunctions.BASE_URL+maps.get(position).getOrder_item_image_url())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(img_icon);

        txt_item_name.setText(maps.get(position).getOrder_name());
        txt_price.setText("Rs. "+maps.get(position).getOrder_item_price());

        return convertView;
    }

   public List<String> getItems(){
        ArrayList<String> list = new ArrayList<>();
        for (int index = 0;index<10;index++){

            list.add(String.valueOf(index));


        }
    return list;
    }
}
