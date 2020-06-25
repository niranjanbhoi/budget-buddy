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

public class MainActivity extends AppCompatActivity {

    private EditText User_Email;
    private EditText User_Pass;
    private Button btnlogin;
    private TextView csForgetPassword;
    private TextView csSignupHere;
    private ProgressDialog csDialog;
    private FirebaseAuth csAuth;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        csAuth =FirebaseAuth.getInstance();

        if (csAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
        }

        csDialog =new ProgressDialog(this);

        loginDetails();
    }

    private void loginDetails() {

        User_Email = findViewById(R.id.user_email);
        User_Pass = findViewById(R.id.user_password);
        btnlogin = findViewById(R.id.btn_login);
        csForgetPassword = findViewById(R.id.forget_password);
        csSignupHere = findViewById(R.id.sign_up_reg);

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

                csDialog.setMessage("Processing..");
                csDialog.show();

                csAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            csDialog.dismiss();
                            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                            Toast.makeText(getApplicationContext(),"Login Successful..",Toast.LENGTH_SHORT).show();
                        }else {
                            csDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Login Failed..",Toast.LENGTH_SHORT).show();

                        }
                    }
                });

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
                startActivity(new Intent(getApplicationContext(), ResetActivity.class));
            }
        });

    }

}