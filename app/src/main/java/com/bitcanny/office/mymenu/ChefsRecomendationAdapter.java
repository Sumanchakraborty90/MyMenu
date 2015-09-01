package com.bitcanny.office.mymenu;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
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
        try {
            Picasso.with(context)
                    //.load(JsonFunctions.BASE_URL+list.get(position).get("MenuItemImageURL"))
                    .load(new File(getFromSdcard("/MenuApp/MenuCategory/chefsRecomendation").get(position)))
                    .placeholder(R.mipmap.ic_launcher) // optional
                    .error(R.mipmap.ic_launcher)
                    .transform(new RoundedTransformation(20, 0))// optional
                    .into(imageView);
        }catch (Exception e){

            e.printStackTrace();
        }

        return convertView;

    }

    public List<String> getFromSdcard(String path)
    {
        ArrayList<String> f = new ArrayList<String>();
        File[] listFile;
        File file= new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+path);

        if (file.isDirectory())
        {
            listFile = file.listFiles();


            for (int i = 0; i < listFile.length; i++)
            {

                f.add(listFile[i].getAbsolutePath());

                Log.d("val", listFile[i].getAbsolutePath());

            }
        }

        return  f;
    }
}
