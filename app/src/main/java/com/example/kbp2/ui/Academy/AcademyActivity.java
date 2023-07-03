package com.example.kbp2.ui.Academy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.kbp2.R;

public class AcademyActivity extends AppCompatActivity {


    TextView bscit;
    TextView ba;
    TextView bcom;
    TextView baf;
    TextView bbi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academy);

        setTitle("Academy");

        bscit = findViewById(R.id.bscit);
        ba = findViewById(R.id.ba);
        bcom = findViewById(R.id.bcom);
        baf = findViewById(R.id.baf);
        bbi = findViewById(R.id.bbi);

        bscit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), BscitActivity.class));

            }
        });


        ba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), BaActivity.class));


            }
        });


        bcom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), BcomActivity.class));

            }
        });


        baf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), BafActivity.class));

            }
        });


        bbi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), BbiActivity.class));

            }
        });


    }
}