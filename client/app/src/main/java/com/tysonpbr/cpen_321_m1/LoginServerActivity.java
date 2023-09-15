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

        serverIPText = findViewById(R.id.text_IP_server);
//        serverIPText.setText("Current City:");

        clientIPText = findViewById(R.id.text_IP_client);
//        clientIPText.setText("Current City:");

        serverTimeText = findViewById(R.id.text_time_server);
//        serverTimeText.setText("Current City:");

        clientTimeText = findViewById(R.id.text_time_client);
//        clientTimeText.setText("Current City:");

        nameText = findViewById(R.id.text_name);
//        nameText.setText("Current City:");

        userNameText = findViewById(R.id.text_user_name);
//        userNameText.setText("Current City:");

    }
}