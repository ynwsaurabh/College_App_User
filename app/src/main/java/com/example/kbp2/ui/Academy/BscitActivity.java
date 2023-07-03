
package com.example.kbp2.ui.Academy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.example.kbp2.R;

public class BscitActivity extends AppCompatActivity {
ViewFlipper flipper_bscit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bscit);
        setTitle("BSc.IT");

        flipper_bscit = findViewById(R.id.flipper_bscit);
        int i;
        int imgarray[] = {R.drawable.bscit1, R.drawable.bscit2, R.drawable.bscit3};
        for (i = 0; i < imgarray.length; i++)
            showimage(imgarray[i]);


    }
    public void showimage(int img) {

        ImageView imageview = new ImageView(getApplicationContext());
        imageview.setBackgroundResource(img);

        flipper_bscit.addView(imageview);
        flipper_bscit.setFlipInterval(3000);
        flipper_bscit.setAutoStart(true);
        flipper_bscit.setOutAnimation(getApplicationContext(), android.R.anim.slide_out_right);
        flipper_bscit.setInAnimation(getApplicationContext(), android.R.anim.slide_in_left);
    }
}