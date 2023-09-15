package com.tysonpbr.cpen_321_m1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class PhoneDetailsActivity extends AppCompatActivity implements LocationListener {
    private TextView currentCityText;
    private TextView phoneManufacturerText;
    private TextView phoneModelText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_details);

        currentCityText = findViewById(R.id.text_current_city);
        currentCityText.setText("Current City:");

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);

        } else {
            currentCityText.setText("Current City: \n ERROR: No Location Permissions");
        }

        phoneManufacturerText = findViewById(R.id.text_phone_manufacturer);
        phoneManufacturerText.setText("Phone Manufacturer: \n" + Build.MANUFACTURER);

        phoneModelText = findViewById(R.id.text_phone_model);
        phoneModelText.setText("Phone Model: \n" + Build.MODEL);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        Geocoder geoCoder = new Geocoder(this, Locale.getDefault());

        try {
            List<Address> address = geoCoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

            currentCityText = findViewById(R.id.text_current_city);
            currentCityText.setText("Current City: " + address.get(0).getLocality());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}