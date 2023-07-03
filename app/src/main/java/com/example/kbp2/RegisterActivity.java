package com.example.kbp2;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class RegisterActivity extends AppCompatActivity {

    private EditText regName, regPhone, regEmail, regPassword;
    TextView loginBtn;
    private ProgressBar probar;
    private Button regBtn;
    FirebaseFirestore fstore;
    String userID;
    String name, phone, email, password;
    FirebaseAuth auth;

    @Override
    protected void onStart() {
        super.onStart();
        if (auth.getCurrentUser() != null){
            startActivity(new Intent(this, MainActivity.class));
            finish();

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        regName = findViewById(R.id.regName);
        regPhone = findViewById(R.id.regPhone);
        regEmail = findViewById(R.id.regEmail);
        regPassword = findViewById(R.id.regPassword);
        regBtn = findViewById(R.id.regBtn);
        loginBtn = findViewById(R.id.loginBtn);
        probar = findViewById(R.id.progressbar);
        auth=FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });


        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = regEmail.getText().toString().trim();
                String password = regPassword.getText().toString().trim();
                final String fullname = regName.getText().toString();
                final String phone = regPhone.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    regEmail.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(fullname)) {
                    regName.setError("Name is required");
                    return;
                }
                if (TextUtils.isEmpty(phone)) {
                    regPhone.setError("required");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    regPassword.setError("Password is required");
                    return;
                }

                if (password.length() < 6) {
                    regPassword.setError("password must be more than 6 character");
                    return;
                }
                if(!regPhone.getText().toString().trim().isEmpty()){
                    if(regPhone.getText().toString().trim().length()==10){
                        probar.setVisibility(View.VISIBLE);
                        regBtn.setVisibility(View.INVISIBLE);

                        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser fuser = auth.getCurrentUser();
                                    fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(getApplicationContext(), "Register Successful", Toast.LENGTH_SHORT).show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.d(TAG, "OnFailure: Email Not Sent" + e.getMessage());
                                        }
                                    });
                                    userID = auth.getCurrentUser().getUid();
                                    DocumentReference documentReference = fstore.collection("user").document(userID);
                                    Map<String, Object> user = new HashMap<>();
                                    user.put("Name", fullname);
                                    user.put("email", email);
                                    user.put("phone", phone);
                                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Log.d(TAG, "onsucces: user profile is created for" + userID);
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.d(TAG, "onFailure: " + e.toString());
                                        }
                                    });

                                } else {
                                    Toast.makeText(RegisterActivity.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    probar.setVisibility(View.GONE);
                                }
                            }
                        });


                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+91" + regPhone.getText().toString(),
                                60,
                                TimeUnit.SECONDS,
                                RegisterActivity.this,
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                        probar.setVisibility(View.GONE);
                                        regBtn.setVisibility(View.VISIBLE);
                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {

                                        probar.setVisibility(View.GONE);
                                        regBtn.setVisibility(View.VISIBLE);
                                        Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String backendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                                        probar.setVisibility(View.GONE);
                                        regBtn.setVisibility(View.VISIBLE);

                                        Intent intent=new Intent(getApplicationContext(),OtpActivity.class);
                                        intent.putExtra("phone",regPhone.getText().toString());
                                        intent.putExtra("name",regName.getText().toString());
                                        intent.putExtra("backendotp",backendotp);
                                        startActivity(intent);

                                    }
                                }

                        );
                    }else{
                        Toast.makeText(RegisterActivity.this, "Enter correct number", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    regPhone.setError("Required");
                    regPhone.requestFocus();
                }
            }
        });
    }
   /* @Override
    public void onBackPressed() {
        finishAffinity();
        finish();
    }*/
}