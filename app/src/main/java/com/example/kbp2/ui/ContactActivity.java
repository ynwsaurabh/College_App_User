package com.example.kbp2.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kbp2.R;

public class ContactActivity extends AppCompatActivity {
    ImageView map;
    TextView phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        setTitle("Contact Us");

        map = findViewById(R.id.map);
        phone = findViewById(R.id.phone);

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String call="tel:8591498099";
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(call));
                startActivity(intent);
            }
        });



        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMap();
            }
        });


    }

    private void openMap() {
        Uri uri = Uri.parse("geo:0, 0?q=https://www.google.com/maps/place/K+B+P+DEGREE+COLLEGE/@19.196457,72.952339,14z/data=!4m14!1m7!3m6!1s0x3be7b90f5aaf5671:0x8db2ed1acc0b611a!2sK+B+P+DEGREE+COLLEGE!8m2!3d19.1964568!4d72.9523386!16s%2Fg%2F1ptwhlljm!3m5!1s0x3be7b90f5aaf5671:0x8db2ed1acc0b611a!8m2!3d19.1964568!4d72.9523386!16s%2Fg%2F1ptwhlljm?hl=en");
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }

}