package com.example.maheshthippala.googlemapstest;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment frag = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.frag1);
        frag.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {

              /*  googleMap.setMapType(GoogleMap.
                        MAP_TYPE_SATELLITE); */


     final LocationManager lManager = (LocationManager)
                     getSystemService(Context.LOCATION_SERVICE);

     lManager.getLastKnownLocation(
             LocationManager.NETWORK_PROVIDER);
     lManager.requestLocationUpdates(
             LocationManager.NETWORK_PROVIDER,
             1000, 1, new LocationListener() {
                 @Override
                 public void onLocationChanged(Location location) {

                     double lati = location.getLatitude();
                     double longi = location.getLongitude();

                     MarkerOptions opt1=new MarkerOptions();
                     opt1.position(new LatLng(lati,longi));
                     opt1.title(lati +","+longi);
                  //  opt1.icon(BitmapDescriptorFactory.fromResource(R.drawable.bike));
                      googleMap.addMarker(opt1);

                      googleMap.animateCamera(
                CameraUpdateFactory.newLatLngZoom(new LatLng(lati,longi),
                        15));

                      lManager.removeUpdates(this);
                 }

                 @Override
                 public void onStatusChanged(String provider, int status, Bundle extras) {

                 }

                 @Override
                 public void onProviderEnabled(String provider) {

                 }

                 @Override
                 public void onProviderDisabled(String provider) {

                 }
             }
     );


            }
        });

    }
}
