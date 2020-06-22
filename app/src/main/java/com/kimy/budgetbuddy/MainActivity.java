package com.kimy.budgetbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.security.PrivateKey;

public class MainActivity extends AppCompatActivity {

    private EditText User_Email;
    private EditText User_Pass;
    private Button btnlogin;
    private TextView csForgetPassword;
    private TextView csSignupHere;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void logindetails() {

        User_Email = findViewById(R.id.user_email);
        User_Pass = findViewById(R.id.user_password);
        btnlogin = findViewById(R.id.btn_login);
        csForgetPassword = findViewById(R.id.forget_password);
        csSignupHere = findViewById(R.id.signup_reg);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = User_Email.getText().toString().trim();
                String pass = User_Pass.getText().toString().trim();

                if (TextUtils.isEmpty((email))){

                    User_Email.setError("Email Required..");
                    return;

                }

                if (TextUtils.isEmpty((pass))){

                    User_Pass.setError("Password Required..");
                    return;

                }

            }
        });

        //User_registration

        csSignupHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SignupActivity.class));
            }
        });

        //Reset_Password

        csForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ReseatActivity.class));
            }
        });

    }

}