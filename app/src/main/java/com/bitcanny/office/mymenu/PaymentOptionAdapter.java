package com.bitcanny.office.mymenu;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by OFFICE on 28-08-2015.
 */
public class PaymentOptionAdapter extends ArrayAdapter {

    Context context;
    List<Map<String,String>> Items;
    List<Map<String,String>> Icons;
    Typeface typeface;

    public PaymentOptionAdapter(Context context, int resource, List<Map<String,String>> Items,List<Map<String,String>> Icons) {
        super(context, resource,Items);
        this.context = context;
        this.Items = Items;
        this.Icons = Icons;
        typeface = Typeface.createFromAsset(context.getAssets(),"fonts/ufonts.com_century-gothic.ttf");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.payment_option_item,parent,false);

        ImageView img_logo = (ImageView) convertView.findViewById(R.id.img_logo);

        TextView txt_item_name = (TextView) convertView.findViewById(R.id.txt_item_name);

        txt_item_name.setTypeface(typeface);
    try {


        img_logo.setBackgroundResource(Integer.valueOf(Icons.get(position).get(String.valueOf(position))));
    }catch (Exception e){
        e.printStackTrace();
    }

        txt_item_name.setText(Items.get(position).get(String.valueOf(position)));

        return convertView;
    }
}
