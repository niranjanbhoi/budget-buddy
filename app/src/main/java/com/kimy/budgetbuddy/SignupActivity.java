package com.kimy.budgetbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    private EditText User_Email;
    private EditText User_Pass;
    private Button btn_reg;
    private TextView User_Sign;

    private ProgressDialog csDialog;


    //Firebase..

    private FirebaseAuth csAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        csAuth = FirebaseAuth.getInstance();

        csDialog = new ProgressDialog(this);

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

                csDialog.setMessage("Processing..");
                csDialog.show();

                csAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){

                            csDialog.dismiss();

                            Toast.makeText(getApplicationContext(),"Signup Successfuly Completed",Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(getApplicationContext(),HomeActivity.class));

                        } else {

                            csDialog.dismiss();

                            Toast.makeText(getApplicationContext(),"Signup Faild",Toast.LENGTH_SHORT).show();

                        }

                    }
                });

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