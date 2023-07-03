package com.example.kbp2.ui.Academy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.example.kbp2.R;

public class BcomActivity extends AppCompatActivity {
ViewFlipper flipper_bcom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bcom);

        setTitle("Bcom");

        flipper_bcom =findViewById(R.id.flipper_bcom);
        int i;
        int imgarray[] = {R.drawable.bcom1, R.drawable.bcom2, R.drawable.bcom3};
        for (i = 0; i < imgarray.length; i++)
            showimage(imgarray[i]);

    }
    public void showimage(int img) {

        ImageView imageview = new ImageView(getApplicationContext());
        imageview.setBackgroundResource(img);

        flipper_bcom.addView(imageview);
        flipper_bcom.setFlipInterval(3000);
        flipper_bcom.setAutoStart(true);
        flipper_bcom.setOutAnimation(getApplicationContext(), android.R.anim.slide_out_right);
        flipper_bcom.setInAnimation(getApplicationContext(), android.R.anim.slide_in_left);
    }
}