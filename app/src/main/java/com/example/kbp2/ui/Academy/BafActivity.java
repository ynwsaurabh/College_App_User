package com.example.kbp2.ui.Academy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.example.kbp2.R;

public class BafActivity extends AppCompatActivity {
    ViewFlipper flipper_baf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baf);

        setTitle("BAF");

        flipper_baf = findViewById(R.id.flipper_baf);
        int i;
        int imgarray[] = {R.drawable.baf1, R.drawable.baf2, R.drawable.baf3};
        for (i = 0; i < imgarray.length; i++)
            showimage(imgarray[i]);


    }

    public void showimage(int img) {

        ImageView imageview = new ImageView(getApplicationContext());
        imageview.setBackgroundResource(img);

        flipper_baf.addView(imageview);
        flipper_baf.setFlipInterval(3000);
        flipper_baf.setAutoStart(true);
        flipper_baf.setOutAnimation(getApplicationContext(), android.R.anim.slide_out_right);
        flipper_baf.setInAnimation(getApplicationContext(), android.R.anim.slide_in_left);
    }
    }
