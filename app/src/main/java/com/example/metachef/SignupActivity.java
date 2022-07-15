package com.example.metachef;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.metachef.model.User;
import com.google.android.material.textfield.TextInputLayout;
import com.parse.ParseException;
import com.parse.SignUpCallback;

//This class represents the sign up page
public class SignupActivity extends AppCompatActivity {

    private EditText etFirstname;
    private EditText etLastname;
    private EditText etUsername;
    private EditText email;
    private TextInputLayout layout_ConfirmPasswordSignUp;
    private EditText etPassword;
    private EditText etConfirmPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupactivity);

        etFirstname = findViewById(R.id.etFirstName);
        email = findViewById(R.id.etEmail);
        etLastname = findViewById(R.id.etLastName);
        layout_ConfirmPasswordSignUp = findViewById(R.id.layout_ConfirmPasswordSignUp);
        TextView tvLogInLink = findViewById(R.id.tvLogInLink);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);

        etUsername = findViewById(R.id.etSignupUsername);
        etPassword = findViewById(R.id.etSignupPassword);
        Button btnSendSignUp = findViewById(R.id.btnSendSignup);

        etConfirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!etPassword.getText().toString().equals(etConfirmPassword.getText().toString()))
                {
                    layout_ConfirmPasswordSignUp.setError("password do not match");
                }
                else{
                    layout_ConfirmPasswordSignUp.setErrorEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        btnSendSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etPassword.getText().toString().equals(etConfirmPassword.getText().toString())) {
                    layout_ConfirmPasswordSignUp.setError("password do not match");
                } else {
                    User user = new User();
                    user.setFirstname(String.valueOf(etFirstname.getText()));
                    user.setLastname(String.valueOf(etLastname.getText()));
                    user.setEmail(String.valueOf(email.getText()));
                    user.setUsername(String.valueOf(etUsername.getText()));
                    user.setPassword(String.valueOf(etPassword.getText()));
                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e != null) {
                                Toast.makeText(SignupActivity.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(SignupActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                                goToMainActivity();
                            }
                        }
                    });
                }
            }
        });

        tvLogInLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
    private void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
