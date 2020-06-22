package com.kimy.budgetbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignupActivity extends AppCompatActivity {

    private EditText User_Email;
    private EditText User_Pass;
    private Button btn_reg;
    private TextView User_Sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        registration();

    }

    public void registration() {
        User_Email = findViewById(R.id.reg_email);
        User_Pass  = findViewById(R.id.reg_password);
        btn_reg = findViewById(R.id.btn_register);
        User_Sign = findViewById(R.id.signup_reg);

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email=User_Email.getText().toString().trim();
                String pass=User_Pass.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                   User_Email.setError("Email Required..");
                   return;
                }

                if (TextUtils.isEmpty(pass)) {
                    User_Pass.setError("Password Required..");
                }

            }
        });

        User_Sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

    }

}