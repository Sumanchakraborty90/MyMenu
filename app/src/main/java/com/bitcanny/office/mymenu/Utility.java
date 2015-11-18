package com.bitcanny.office.mymenu;

import android.util.Log;

/**
 * Created by OFFICE on 31-08-2015.
 */
public class Utility {

//    String value ="/mnt/sdcard/Pictures/MenuApp/MenuItemGrid/0.jpg";

  public static String  getItemImageName(String publicString){



        String[] split = publicString.split("\\.");
      String value = new StringBuilder(split[0]).reverse().toString();


      Log.d("imageNAmeBitcanny",value);
        String[] split1 = value.split("/");

        String value1 =new StringBuilder(split1[0]).reverse().toString();

      return value1;

    }



}
