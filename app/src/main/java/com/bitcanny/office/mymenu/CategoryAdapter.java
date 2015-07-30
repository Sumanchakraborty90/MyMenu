package com.bitcanny.office.mymenu;

import java.util.List;
import java.util.Map;


import com.squareup.picasso.Picasso;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CategoryAdapter extends ArrayAdapter {

	Context context;
	List<Map<String, String>> list ;
	Map<String, String> map;
	



	/*public CategoryAdapter(Context context,
			 List<Map<String, String>> list) {
		super(context, R.layout.category_item, list);
		this.context = context;
		this.list = list;
	}*/

	public CategoryAdapter(Context context, int resource, List<Map<String, String>> list) {
		super(context, R.layout.category_item, list);
		this.list = list;
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = (LayoutInflater)context.getSystemService
				(Context.LAYOUT_INFLATER_SERVICE);

		convertView = inflater.inflate(R.layout.category_item, parent, false);
		
		ImageView imgView = (ImageView) convertView.findViewById(R.id.img_cat);
		
		TextView button = (TextView) convertView.findViewById(R.id.btn_cat_name);

		RelativeLayout relativeLayout = (RelativeLayout)convertView.findViewById(R.id.rel2);
		
		map = list.get(position);

		//relativeLayout.setBackgroundResource(R.drawable.focusable_color);

		Picasso.with(context).load(JsonFunctions.BASE_URL+map.get("CategoryImageURL"))
	    .placeholder(R.mipmap.ic_launcher)
				.fit()
	    .error(R.mipmap.ic_launcher)
	    .into(imgView);
		
		button.setText(map.get("CategoryName"));
		
		return convertView;
	}





	
	

}
