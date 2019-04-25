package com.example.weather;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.location.LocationListener;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;


public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView tempTextView;
    private TextView dayTextView;
    private ImageView imageView;
    private ProgressBar progressBar;
    //private FusedLocationProviderClient fusedLocationClient;
    private static final String API_KEY = "1fe076ef00d3e6723ab476b95263a16f";
    private static final String API_URL = "https://api.darksky.net/forecast";
    private RequestQueue queue;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private static String locationProvider = LocationManager.GPS_PROVIDER;

    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);

        tempTextView = findViewById(R.id.tempTextView);
        dayTextView = findViewById(R.id.summaryTextView);
        imageView = findViewById(R.id.imageView);
        progressBar = findViewById(R.id.progressBar);
        // Acquire a reference to the system Location Manager
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        // Define a listener that responds to location updates
        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                if(location != null) {
                    // Called when a new location is found by the network location provider.
                    getLocationInformation(location);
                }
            }
            public void onStatusChanged(String provider, int status, Bundle extras) { }

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
        };
        //checkLocationPermission();
        //startLocationUpdates();
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkLocationPermission();
    }

    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(locationListener);
    }

    public void checkLocationPermission(){
        Log.i("PERM", "inside check loc permission");
        //check for permission
        if(ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            //permission not granted
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
                // returns true if the app has requested this permission previously and the user denied the request
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                //request permission once explanation has been shown
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
                    // returns false if user has chosen Don’t ask again option when it previously asked for permission
                    } else {
                    //no explanation needed, request permissions
                    ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
            }
        }else{
            startLocationUpdates();
            }
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled the result arrays are empty
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    // Permission was granted
                    if (ContextCompat.checkSelfPermission(this,
                            android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                            PackageManager.PERMISSION_GRANTED) {
                        //mMap.setMyLocationEnabled(true);
                        startLocationUpdates();
                        Toast.makeText(this, "permission granted", Toast.LENGTH_LONG).show();

                    }
                } else { // Permission denied
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
            }
            // add other 'case' lines to check for other permissions your app might request
        }
    }

    protected void startLocationUpdates() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);
            getLocationInformation(lastKnownLocation);
            locationManager.requestLocationUpdates(locationProvider, 60000, 150, locationListener);
        }
    }

    public void getLocationInformation(Location location){
        Double lat = location.getLatitude();
        Double lng = location.getLongitude();
        //String latLngQuery = "/"+lat+","+lng;
        //new RetrieveWeatherTask().execute(latLngQuery);
        Log.i("LOCCHNG", "about to call data request");
        requestData(lat, lng);
    }

    private void requestData(Double lat, Double lng){
        progressBar.setVisibility(View.VISIBLE);
        dayTextView.setText("");
        tempTextView.setText("");
        imageView.setImageResource(android.R.color.transparent);

        queue = Volley.newRequestQueue(this);
        //create URL for request
        String url = API_URL + "/" + API_KEY + "/" + lat + "," + lng;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parseJSON(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERROR", error.getMessage(), error);
            }
        });

        // Add the request to the RequestQueue
        queue.add(stringRequest);
    }

    private void parseJSON(String response) {
        //response should be a String with JSON objects
        if (response == null) {
            response = "THERE WAS AN ERROR";
        }
        progressBar.setVisibility(View.GONE);

        //parse JSON object
        try {
            Log.i("TRY", "inside parsing Json");
            JSONObject object = (JSONObject) new JSONTokener(response).nextValue();
            JSONObject currently = object.getJSONObject("currently");
            String descr = currently.getString("summary");
            dayTextView.setText(descr);
            Double temp = currently.getDouble("temperature");
            String tempString = temp.toString()+"ºF";
            tempTextView.setText(tempString);
            String iconName = currently.getString("icon");
            //check for if the icon has one of the problem symbols (includes dashes)
            if(iconName.equals("clear-day")){
                iconName = "clear_day";
            }else if(iconName.equals("clear-night")){
                iconName = "clear_night";
            }else if(iconName.equals("partly-cloudy-day")){
                iconName = "partly_cloudy_day";
            }else if(iconName.equals("partly-cloudy-night")){
                iconName = "partly_cloudy_night";
            }
            int imgId = getResources().getIdentifier("@drawable/"+iconName, "drawable", getPackageName());
            imageView.setImageResource(imgId);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
