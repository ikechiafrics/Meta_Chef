package com.example.metachef;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseUser;

public class StartingActivity extends AppCompatActivity {

    private TextView tvMain;
    Button btnGetStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(ParseUser.getCurrentUser() != null){
            goMainActivity();
        }

        setContentView(R.layout.activity_starting);

        tvMain = findViewById(R.id.tvMain);
        btnGetStarted = findViewById(R.id.btnGetStarted);

        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                ProgressDialog mDialog = new ProgressDialog(StartingActivity.this);
//                mDialog.setMessage("Please waiting...");
//                mDialog.show();

                goToLoginActivity();
            }
        });
    }

    private void goToLoginActivity() {
        Intent intent = new Intent(StartingActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void goMainActivity() {
        Intent i = new Intent(StartingActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}

