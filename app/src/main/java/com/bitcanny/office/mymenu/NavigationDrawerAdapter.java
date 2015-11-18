package com.bitcanny.office.mymenu;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by OFFICE on 30-06-2015.
 */
public class NavigationDrawerAdapter extends ArrayAdapter {

    Context context;

    List<FrontEndMenuModel> maps;

    private static String MYPREF = "mypref";
    private static String EMAIL = "email";
    private static  String PASSWORD = "password";
    SharedPreferences sharedPreferences;

    public NavigationDrawerAdapter(Context context, int resource, List<FrontEndMenuModel> maps) {
        super(context, R.layout.navigation_drawer_item, maps);
        this.context = context;
        this.maps = maps;

        sharedPreferences = context.getSharedPreferences(MYPREF, Context.MODE_PRIVATE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.navigation_drawer_item,parent,false);

        RelativeLayout rel1 = (RelativeLayout) convertView.findViewById(R.id.rel1);

        RelativeLayout lay2 = (RelativeLayout) convertView.findViewById(R.id.lay2);

        TextView txt_mnu_name= (TextView) convertView.findViewById(R.id.txt_mnu_name);


            rel1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
        });
        if(position == 0){

            rel1.setVisibility(View.VISIBLE);

        }else{

            rel1.setVisibility(View.GONE);

        }
        lay2.setBackgroundResource(R.drawable.focusable_color);
        rel1.setBackgroundResource(R.drawable.white_focus);
        try {


            if (maps.get(position).getFront_end_menu_name().equals("Mood Switch")) {

                lay2.setVisibility(View.GONE);


            } else {

                txt_mnu_name.setText(maps.get(position).getFront_end_menu_name());
            }


            if (maps.get(position).getFront_end_menu_name().equals("Sign up")){

                if (sharedPreferences.getString("email", "").equals("") || sharedPreferences.getString("password", "").equals("")) {

                    lay2.setVisibility(View.VISIBLE);

                }else{

                    lay2.setVisibility(View.GONE);

                }



            }

            if (maps.get(position).getFront_end_menu_name().equals("Sign in")) {

         if(txt_mnu_name.getText().equals("Sign in")) {
            if (sharedPreferences.getString("email", "").equals("") || sharedPreferences.getString("password", "").equals("")) {


                 txt_mnu_name.setText("Sign in");
                   /* Intent intent = new Intent(context, LogInActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(intent);*/

              } else {

                  /*  sharedPreferences.edit().clear().commit();*/

            // SharedPreferences preferences = getActivity().getSharedPreferences("Mypref", 0);
                 txt_mnu_name.setText("Sign out");
            //putSharedPreference("","");
              }
        }else if(txt_mnu_name.getText().equals("Sign out")){

        if (sharedPreferences.getString("email", "").equals("") || sharedPreferences.getString("password", "").equals("")) {


            txt_mnu_name.setText("Sign in");
                   /* Intent intent = new Intent(context, LogInActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(intent);*/

        } else {

                  /*  sharedPreferences.edit().clear().commit();*/

            // SharedPreferences preferences = getActivity().getSharedPreferences("Mypref", 0);
            txt_mnu_name.setText("Sign out");
            //putSharedPreference("","");
        }



    }
               /* if(sharedPreferences.getString("email", "").equals("") || sharedPreferences.getString("password","").equals("")) {

                    Log.d("email",sharedPreferences.getString("email", ""));
                    txt_mnu_name.setText("Sign out");
                }else{


                    txt_mnu_name.setText("Sign in");
                }
*/

            }
           /* if(maps.get(position).get("logIn").equals("LogIn")) {

                txt_mnu_name.setText(maps.get(position).get("logIn"));

            }*/
        }catch (Exception e){

            e.printStackTrace();
        }
        return convertView;
    }
    public void putSharedPreference(String email,String password){

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email",email);
        editor.putString("password", password);

        editor.commit();

    }
}
