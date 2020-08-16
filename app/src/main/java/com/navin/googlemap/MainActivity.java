package com.navin.googlemap;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {


    GPSTracker gpsTracker;

    double latitude;
    double longitude;

    SupportMapFragment map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gpsTracker = new GPSTracker(getApplicationContext());

        map = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);


        map.getMapAsync(this);




        if (gpsTracker.canGetLocation) {
            latitude = gpsTracker.getLatitude();
            longitude = gpsTracker.getLongitude();

            new GetLocation().execute();

        } else {


        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        double latitude =35.72749369051;
        double longitude = 51.32034518091;

        MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(latitude , longitude))
                .title("Android class")
                .snippet("Class room")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        googleMap.addMarker(markerOptions);


        double latitude1 =35.62749379051;
        double longitude1 = 51.42037518091;


        LatLng myPosition = new LatLng(latitude , longitude);
        LatLng myFamily = new LatLng(latitude1 , longitude1);


        MarkerOptions markerOptions1 = new MarkerOptions().position(new LatLng(latitude1 , longitude1))
                .title("Android class")
                .snippet("Class room")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.location));
        googleMap.addMarker(markerOptions1);


        googleMap.getUiSettings().setZoomControlsEnabled(true);


        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(latitude , longitude)).zoom(15).build();

        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.getUiSettings().setMapToolbarEnabled(true);
        googleMap.setMyLocationEnabled(true);


        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        googleMap.addPolyline(new PolylineOptions().add(myPosition,myFamily)
        .color(Color.parseColor("#ff0000"))
        .width(3));


        googleMap.addCircle(new CircleOptions().center(myPosition).radius(1000)
        .fillColor(Color.parseColor("#66000000"))
        .strokeColor(Color.parseColor("#ffff00")));




        googleMap.addPolygon(new PolygonOptions().add(new LatLng(0,0),new LatLng(0,5),
                new LatLng(3,5),new LatLng(0,0)).strokeColor(Color.RED)
                .fillColor(Color.parseColor("#66000000"))
        );


        googleMap.addPolygon(new PolygonOptions().add(new LatLng(35.72564,51.37231),new LatLng(34.72564,51.37231),
                new LatLng(35.72564,21.37231),new LatLng(35.72564,51.37231)).strokeColor(Color.RED)
                .fillColor(Color.parseColor("#66000000"))
        );






    }

    class GetLocation extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Locale locale = new Locale("fa");
            Geocoder geocoder = new Geocoder(getApplicationContext(), locale);


            try {
                List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }
    }


}