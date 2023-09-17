package com.tysonpbr.cpen_321_m1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class LoginServerActivity extends AppCompatActivity {
    final static String TAG = "LoginServerActivity";
    private TextView serverIPText;
    private TextView clientIPText;
    private TextView serverTimeText;
    private TextView clientTimeText;
    private TextView nameText;
    private TextView userNameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_server);

        final OkHttpClient client = new OkHttpClient();

        Request requestName = new Request.Builder()
                .url("http://10.0.2.2:8081/name")
                .build();

        client.newCall(requestName).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                    String name = responseBody.string();

                    nameText = findViewById(R.id.text_name);
                    nameText.setText("My Name: " + name);
                }
            }
        });

        Request requestTime = new Request.Builder()
                .url("http://10.0.2.2:8081/time")
                .build();

        client.newCall(requestTime).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);



                    String time = responseBody.string();

                    serverTimeText = findViewById(R.id.text_time_server);
                    serverTimeText.setText("Server Local Time: \n" + time);
                }
            }
        });

        serverIPText = findViewById(R.id.text_IP_server);
//        serverIPText.setText("Current City:");

        clientIPText = findViewById(R.id.text_IP_client);
//        clientIPText.setText("Current City:");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalTime time = LocalTime.now();
            clientTimeText = findViewById(R.id.text_time_client);
            clientTimeText.setText("Client Local Time: \n" + time.truncatedTo(ChronoUnit.SECONDS));
        }

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        userNameText = findViewById(R.id.text_user_name);
        userNameText.setText("Logged In: " + account.getDisplayName());

    }

    // refresh time
}