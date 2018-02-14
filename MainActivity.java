package com.example.rahul.mymap;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static android.location.LocationManager.GPS_PROVIDER;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment frag=(SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.fragment);

        frag.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {

              //  googleMap.setMapType(GoogleMap.
                        //MAP_TYPE_SATELLITE);


                final LocationManager lManager = (LocationManager)
                        getSystemService(Context.LOCATION_SERVICE);

                lManager.getLastKnownLocation(
                        GPS_PROVIDER);
                lManager.requestLocationUpdates(
                        GPS_PROVIDER,
                        1000, 1, new LocationListener() {
                            @Override
                            public void onLocationChanged(Location location) {

                                double lati = location.getLatitude();
                                double longi = location.getLongitude();

                                MarkerOptions opt1=new MarkerOptions();
                                opt1.position(new LatLng(lati,longi));
                                opt1.title(lati +","+longi);
                                if(googleMap!=null) {
                                    googleMap.addMarker(opt1);
                                    Toast.makeText(MainActivity.this,"Rahul",Toast.LENGTH_LONG).show();

                                }
                                else
                                {
                                     }

                                googleMap.animateCamera(
                                        CameraUpdateFactory.newLatLngZoom(new LatLng(lati,longi),
                                                15));

                              //  lManager.removeUpdates(this);
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
