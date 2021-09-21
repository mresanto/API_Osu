package com.example.osu.ActionClasse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.osu.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Localizacao extends AppCompatActivity {


    Button btnGetLocation;
    TextView lat, lon, country, loc, add;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localizacao);


        btnGetLocation = findViewById(R.id.btnGetLocation);
        lat = findViewById(R.id.txtlatitude);
        lon = findViewById(R.id.txtlongtude);
        country = findViewById(R.id.txtcountry);
        loc = findViewById(R.id.txtlocality);
        add = findViewById(R.id.txtadress);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        btnGetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ActivityCompat.checkSelfPermission(Localizacao.this,
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    getLocation();

                } else {
                    ActivityCompat.requestPermissions(Localizacao.this
                            , new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }
            }
        });
    }

    private void getLocation() {

        if (ActivityCompat.checkSelfPermission(Localizacao.this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            Task<Location> locationTask = fusedLocationProviderClient.getLastLocation();

            locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {

                    if(location != null){
                        try {
                            Geocoder geocoder = new Geocoder(Localizacao.this,
                                    Locale.getDefault());

                            List<Address> addresses = geocoder.getFromLocation(
                                    location.getLatitude(), location.getLongitude(), 1
                            );

                            lat.setText(Html.fromHtml(
                                    "<font color = '#6200EE'><b> Latitude: </b> <br> </font>"
                                            + addresses.get(0).getLatitude()
                            ));

                            lon.setText(Html.fromHtml(
                                    "<font color = '#6200EE'><b> Longitude: </b> <br> </font>"
                                            + addresses.get(0).getLongitude()
                            ));

                            country.setText(Html.fromHtml(
                                    "<font color = '#6200EE'><b> Country: </b> <br> </font>"
                                            + addresses.get(0).getCountryName()
                            ));

                            loc.setText(Html.fromHtml(
                                    "<font color = '#6200EE'><b> Locality: </b> <br> </font>"
                                            + addresses.get(0).getLocality()
                            ));

                            add.setText(Html.fromHtml(
                                    "<font color = '#6200EE'><b> Address: </b> <br> </font>"
                                            + addresses.get(0).getAddressLine(0)
                            ));

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });



        }



    }
}