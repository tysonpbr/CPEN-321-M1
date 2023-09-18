package com.tysonpbr.cpen_321_m1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class SearchBuildingActivity extends AppCompatActivity {
    private Button buttonSearch;
    private Button buttonSeeBuildingCodes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_building);

        buttonSearch = findViewById(R.id.button_search);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText input = (EditText) findViewById(R.id.textInputEditText);

                final OkHttpClient client = new OkHttpClient();
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("code", input.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                RequestBody body = RequestBody.create(JSON, jsonObject.toString());
                Request requestName = new Request.Builder()
                        .url("http://20.172.9.70:8081/allBuildings")
                        .post(body)
                        .build();

                client.newCall(requestName).enqueue(new Callback() {
                    @Override public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override public void onResponse(Call call, Response response) throws IOException {
                        try (ResponseBody responseBody = response.body()) {
                            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                            input.setText("");

                            String address = (String) responseBody.string().toString();

                            if (address.equals("none")) {
                                Log.w("SearchBuildingActivity", "HERE");
                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        Toast.makeText( SearchBuildingActivity.this, "This is not a valid building code", Toast.LENGTH_LONG).show();
                                    }
                                });
                            } else {
                                List<Address> addressList = null;

                                Geocoder geocoder = new Geocoder(SearchBuildingActivity.this);
                                try {
                                    addressList = geocoder.getFromLocationName(address + " Vancouver", 1);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }

                                Address _address = addressList.get(0);

                                Intent mapsIntent = new Intent(SearchBuildingActivity.this, ShowRouteActivity.class);
                                Bundle b = new Bundle();
                                b.putDouble("lat", _address.getLatitude());
                                b.putDouble("lng", _address.getLongitude());
                                mapsIntent.putExtras(b);
                                startActivity(mapsIntent);
                            }
                        }
                    }
                });
            }
        });

        buttonSeeBuildingCodes = findViewById(R.id.button_see_building_codes);
        buttonSeeBuildingCodes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://wiki.ubc.ca/List_of_UBC_Buildings_with_Classrooms"));
                startActivity(browserIntent);
            }
        });
    }
}