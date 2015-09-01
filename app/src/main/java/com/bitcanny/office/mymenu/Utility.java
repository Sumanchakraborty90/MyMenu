package com.bitcanny.office.mymenu;

import android.util.Log;

/**
 * Created by OFFICE on 31-08-2015.
 */
public class Utility {

//    String value ="/mnt/sdcard/Pictures/MenuApp/MenuItemGrid/0.jpg";

  public static String  getItemImageName(String publicString){

        String[] split = publicString.split("/");

        String value = split[6];

      Log.d("imageNAmeBitcanny",value);
        String[] split1 = value.split("\\.");

        String value1 = split1[0];

      return value1;

    }



}
