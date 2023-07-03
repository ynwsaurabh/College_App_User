package com.example.kbp2.ui.Academy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.example.kbp2.R;

public class BbiActivity extends AppCompatActivity {
    ViewFlipper flipper_bbi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bbi);

        setTitle("BBI");

        flipper_bbi =findViewById(R.id.flipper_bbi);
        int i;
        int imgarray[] = {R.drawable.bbi1, R.drawable.bbi2, R.drawable.bbi3};
        for (i = 0; i < imgarray.length; i++)
            showimage(imgarray[i]);


    }

    public void showimage(int img) {

        ImageView imageview = new ImageView(getApplicationContext());
        imageview.setBackgroundResource(img);

        flipper_bbi.addView(imageview);
        flipper_bbi.setFlipInterval(3000);
        flipper_bbi.setAutoStart(true);
        flipper_bbi.setOutAnimation(getApplicationContext(), android.R.anim.slide_out_right);
        flipper_bbi.setInAnimation(getApplicationContext(), android.R.anim.slide_in_left);
    }
    }
