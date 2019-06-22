package com.example.myapplication5;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication5.weatherapi.WeatherRespons;
import com.example.myapplication5.weatherapi.WeatherSer;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private TextView view;
    public static String apikey = "43bc9998f6e1bb15994ccd5d89d5b1ad";
    public static String apilink = "http://api.openweathermap.org/data/2.5/weather?";
    public StringBuilder stringBuilder;
    private TextView view1;
    private TextView view2;
    ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        view = (TextView) findViewById(R.id.textView);
        view1 = findViewById(R.id.textView2);
        view2 = findViewById(R.id.textView4);
        progressBar=findViewById(R.id.progessbar);


    }

    private void fetchWeather(LatLng latLng) {
        //Genrate the services
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create()).build();
        WeatherSer service = retrofit.create(WeatherSer.class);
        double lat = latLng.latitude;
        double lng = latLng.longitude;
        String s = Double.toString(lat);
        String s2 = Double.toString(lng);


        //Run the requst
        service.get("43bc9998f6e1bb15994ccd5d89d5b1ad", s, s2).enqueue(new Callback<WeatherRespons>() {
            @Override
            public void onResponse(Call<WeatherRespons> call, Response<WeatherRespons> response) {
                //Upadte Ui
                //Respons Weather
                if (response.isSuccessful()) {
                    WeatherRespons weatherRespons = response.body();

                    String ss = weatherRespons.getmWeathers().get(0).getmMain();
                    String temp = response.body().getMain().getTemp();
                    view1.setText(ss);
                    view2.setText(temp);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Do something after 5s = 5000ms
                            progressBar.setVisibility(View.GONE);
                            view.setVisibility(View.VISIBLE);
                            view1.setVisibility(View.VISIBLE);
                            view2.setVisibility(View.VISIBLE);

                        }
                    }, 5000);


                    return;


                }


            }

            @Override
            public void onFailure(Call<WeatherRespons> call, Throwable t) {
                //display err !! ok
                Toast.makeText(MapsActivity.this, t.getMessage(), Toast.LENGTH_SHORT);

            }
        });


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
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;

        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                googleMap.clear();
                double lat = latLng.latitude;
                double lng = latLng.longitude;
                LatLng sydney = new LatLng(lat, lng);
                mMap.addMarker(new MarkerOptions().position(sydney).title("This is palestine"));
                mMap.animateCamera(CameraUpdateFactory.newLatLng(sydney));
                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                try {
                    List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
                    String cityName = addresses.get(0).getAddressLine(0);
                    view.setText(cityName);
                } catch (IOException e) {
                    e.printStackTrace();
                }


                fetchWeather(latLng);
                progressBar.setVisibility(View.VISIBLE);
                view.setVisibility(View.INVISIBLE);
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.INVISIBLE);



            }
        });
    }
}


