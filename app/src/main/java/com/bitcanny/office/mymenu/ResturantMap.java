package com.bitcanny.office.mymenu;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class ResturantMap extends ActionBarActivity {
    String lat="",lng="";
    GoogleMap map;
    String title,desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resturant_map);
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
        }
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        Bundle bundle = getIntent().getExtras();
        try {
            lat = bundle.getString("lat");
            lng = bundle.getString("lng");
            title= bundle.getString("title");
            desc = bundle.getString("desc");

            myMap(Double.valueOf(lat), Double.valueOf(lng));
        }catch (Exception e){

            e.printStackTrace();
        }
      /*  try {
            initilizeMap(lat,lng);
        }catch (Exception e){

            e.printStackTrace();
        }*/


    }


    @Override
    protected void onResume() {
        super.onResume();
        try {
            myMap(Double.valueOf(lat), Double.valueOf(lng));
        }catch(Exception e){

            e.printStackTrace();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_resturant_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

   /* private void initilizeMap() {
        if (map == null) {
            map = ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.map)).getMap();
            // latitude and longitude

            CameraPosition cameraPosition = new CameraPosition.Builder().target(
                    new LatLng(Double.valueOf(lat),Double.valueOf(lng))).zoom(12).build();

            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
// create marker
            MarkerOptions marker = new MarkerOptions().position(new LatLng(Double.valueOf(lat), Double.valueOf(lng))).title("Hello Maps ");
            marker.icon(BitmapDescriptorFactory
                    .fromResource(R.drawable.ic_action_place));
// adding marker
            map.addMarker(marker);

            // check if map is created successfully or not
            if (map == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

*/

    private boolean initilizeMap(String lat,String lng) {

        try {
            if (map == null) {
                map = ((MapFragment) getFragmentManager()
                        .findFragmentById(R.id.map)).getMap();

				/*NiceSupportMapFragment mapFragment = (NiceSupportMapFragment) getResources().findFragmentById(R.id.map);
				GoogleMap mMap = mapFragment.getMap();*/

                // mapFragment.setPreventParentScrolling(false);
                map.stopAnimation();
                //googleMap.set
                // googleMap.setMapType(GoogleMap.MAP_TYPE_NONE);
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(new LatLng(Double.valueOf(lat), Double
                                .valueOf(lng))).zoom(12).build();

                map.setMapType(GoogleMap.MAP_TYPE_HYBRID);

				/*CameraPosition cameraPosition1 = new CameraPosition.Builder()
			    .target(MOUNTAIN_VIEW)      // Sets the center of the map to Mountain View
			    .zoom(17)                   // Sets the zoom
			    .bearing(90)                // Sets the orientation of the camera to east
			    .tilt(30)                   // Sets the tilt of the camera to 30 degrees
			    .build();                   // Creates a CameraPosition from the builder
			map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));*/



                map.animateCamera(CameraUpdateFactory
                        .newCameraPosition(cameraPosition));

                MarkerOptions marker = new MarkerOptions().position(
                        new LatLng(Double.valueOf(lat), Double.valueOf(lng)))
                        .title("hello");
                marker.icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.ic_action_place));
                // adding marker
                map.addMarker(marker);
                // check if map is created successfully or not
                if (map == null) {
                    Toast.makeText(getApplicationContext(),
                            "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return true;
    }

   public void myMap(double lat,double lng){


       LatLng HAMBURG = new LatLng(lat, lng);
       map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
               .getMap();
       map.setMyLocationEnabled(true);
       Marker hamburg = map.addMarker(new MarkerOptions().position(HAMBURG)
               .title(title)
       .snippet(desc));
       hamburg.setVisible(true);

      /* Marker kiel = map.addMarker(new MarkerOptions()
               .position(KIEL)
               .title("Kiel")
               .snippet("Kiel is cool")
               .icon(BitmapDescriptorFactory
                       .fromResource(R.drawable.)));
*/
       // Move the camera instantly to hamburg with a zoom of 15.
       map.moveCamera(CameraUpdateFactory.newLatLngZoom(HAMBURG, 30));

       // Zoom in, animating the camera.
       map.animateCamera(CameraUpdateFactory.zoomTo(17), 2000, null);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

    }
}
