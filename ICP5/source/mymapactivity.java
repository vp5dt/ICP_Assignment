package com.example.sunny.trackmylocationicp5;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;

public class MapsActivityICP5 extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_icp5);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

        }

            locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            System.out.println("locationManager"+locationManager);
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            //get the latitude and longitude from the location
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            String lat = String.valueOf(latitude);
            Log.d("latitude --> ", lat);
                    /* get the location name from latitude and longitude */
            Geocoder geocoder = new Geocoder(getApplicationContext());
            try {
                List<Address> addresses =
                        geocoder.getFromLocation(latitude, longitude, 1);
                String result = addresses.get(0).getSubLocality() + ":";
                result += addresses.get(0).getLocality() + ":";
                result += addresses.get(0).getCountryCode();
                LatLng latLng = new LatLng(latitude, longitude);

                mMap.addMarker(new MarkerOptions().position(latLng).title(result));

                mMap.setMaxZoomPreference(20);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            } catch (IOException e) {
                e.printStackTrace();
            }

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 10.2f));
    }