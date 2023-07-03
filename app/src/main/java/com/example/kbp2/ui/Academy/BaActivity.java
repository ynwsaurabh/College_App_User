package com.example.kbp2.ui.Academy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.example.kbp2.R;

public class BaActivity extends AppCompatActivity {

    ViewFlipper flipper_ba;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ba);

        setTitle("BA");

        flipper_ba = findViewById(R.id.flipper_ba);
        int i;
        int imgarray[] = {R.drawable.ba1, R.drawable.ba2, R.drawable.ba3};
        for (i = 0; i < imgarray.length; i++)
            showimage(imgarray[i]);


    }
    public void showimage(int img) {

        ImageView imageview = new ImageView(getApplicationContext());
        imageview.setBackgroundResource(img);

        flipper_ba.addView(imageview);
        flipper_ba.setFlipInterval(3000);
        flipper_ba.setAutoStart(true);
        flipper_ba.setOutAnimation(getApplicationContext(), android.R.anim.slide_out_right);
        flipper_ba.setInAnimation(getApplicationContext(), android.R.anim.slide_in_left);
    }

}