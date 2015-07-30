package com.bitcanny.office.mymenu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by OFFICE on 30-06-2015.
 */
public class MyNavigationAdapter extends RecyclerView.Adapter<MyNavigationAdapter.MyViewHolder> {
    LayoutInflater inflater;
    List<Map<String,String>> maps = Collections.emptyList();

    public MyNavigationAdapter(Context context, List<Map<String, String>> maps) {

        this.inflater = LayoutInflater.from(context);
        this.maps = maps;

    }

    @Override
    public MyNavigationAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = (View)inflater.inflate(R.layout.navigation_drawer_item,viewGroup,false);

        MyViewHolder myViewHolder = new MyViewHolder(view);


        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyNavigationAdapter.MyViewHolder viewHolder, int position) {

        if(position == 0){

            viewHolder.layout.setVisibility(View.VISIBLE);


        }else{

            viewHolder.layout.setVisibility(View.INVISIBLE);

        }

        viewHolder.mnu.setText(maps.get(position).get("text"));


    }

    @Override
    public int getItemCount() {
        return maps.size();
    }


    class MyViewHolder extends  RecyclerView.ViewHolder{


        RelativeLayout layout;
        TextView mnu;

        public MyViewHolder(View itemView) {
            super(itemView);

            layout = (RelativeLayout)itemView.findViewById(R.id.rel1);
            mnu = (TextView)itemView.findViewById(R.id.txt_mnu_name);

        }
    }



}
