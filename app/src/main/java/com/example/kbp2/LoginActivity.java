package com.example.kbp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private EditText logemail, logpass;
    private Button logBtn;
    private TextView regBtn, forgotBtn;
    private FirebaseAuth auth;


    String email, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();


        logemail = findViewById(R.id.logEmail);
        logpass = findViewById(R.id.logPassword);
        logBtn = findViewById(R.id.logBtn);
        regBtn = findViewById(R.id.registerBtn);
        forgotBtn=findViewById(R.id.forgotBtn);

        forgotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ForgotPassActivity.class));
            }
        });

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
                finish();
            }
        });

        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateData();
            }
        });
    }

    private void validateData() {
        email = logemail.getText().toString();
        pass = logpass.getText().toString();

        if (email.isEmpty()){
            logemail.setError("Required");
            logemail.requestFocus();
        }else if(pass.isEmpty()){
            logpass.setError("Required");
            logpass.requestFocus();
        }else{
            loginUser();
        }
    }

    private void loginUser() {
        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this, "Error :"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LoginActivity.this, "Error :"+e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    @Override
    public void onBackPressed() {
        finishAffinity();
        finish();
    }



}