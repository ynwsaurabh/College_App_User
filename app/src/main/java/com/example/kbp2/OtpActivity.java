package com.example.kbp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OtpActivity extends AppCompatActivity {
    private EditText e1,e2,e3,e4,e5,e6;
    private Button verify;
    String getbackendotp;
    TextView Resend;
    private ProgressBar progbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        e1=findViewById(R.id.otp1);
        e2=findViewById(R.id.otp2);
        e3=findViewById(R.id.otp3);
        e4=findViewById(R.id.otp4);
        e5=findViewById(R.id.otp5);
        e6=findViewById(R.id.otp6);
        Resend=findViewById(R.id.Resend);
        verify= findViewById(R.id.verify);
        progbar=findViewById(R.id.progressbar);

        TextView textView = findViewById(R.id.txtmobileno);
        textView.setText(String.format(
                "+91-%S", getIntent().getStringExtra("phone")
        ));

        getbackendotp=getIntent().getStringExtra("backendotp");


        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!e1.getText().toString().trim().isEmpty()
                &&!e2.getText().toString().trim().isEmpty()
                &&!e3.getText().toString().trim().isEmpty()
                &&!e4.getText().toString().trim().isEmpty()
                &&!e5.getText().toString().trim().isEmpty()
                &&!e6.getText().toString().trim().isEmpty()){

                    String getUserOtp =e1.getText().toString()+
                            e2.getText().toString()+
                            e3.getText().toString()+
                            e4.getText().toString()+
                            e5.getText().toString()+
                            e6.getText().toString();

                    if (getbackendotp != null) {

                        progbar.setVisibility(View.VISIBLE);
                        verify.setVisibility(View.INVISIBLE);

                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(getbackendotp, getUserOtp);
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {

                                        progbar.setVisibility(View.GONE);
                                        verify.setVisibility(View.VISIBLE);

                                        if (task.isSuccessful()) {
                                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                            Toast.makeText(OtpActivity.this, "Registered Successful", Toast.LENGTH_SHORT).show();
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(OtpActivity.this, "Enter correct OTP", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });


                    } else {
                        Toast.makeText(OtpActivity.this, "Please check internet", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(OtpActivity.this, "Please fill all number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + getIntent().getStringExtra("mobile"),
                        60,
                        TimeUnit.SECONDS,
                        OtpActivity.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {


                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {


                                Toast.makeText(OtpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String newbackendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {


                                getbackendotp = newbackendotp;
                                Toast.makeText(OtpActivity.this, "OTP Send Sucessfuly", Toast.LENGTH_SHORT).show();


                            }
                        }

                );

            }
        });
        movenumtonext();
    }

    private void movenumtonext() {
        e1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().trim().isEmpty()) {
                    e2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        e2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().trim().isEmpty()) {
                    e3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        e3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().trim().isEmpty()) {
                    e4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        e4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().trim().isEmpty()) {
                    e5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        e5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().trim().isEmpty()) {
                    e6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}