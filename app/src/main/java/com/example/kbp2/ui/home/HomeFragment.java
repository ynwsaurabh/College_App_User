package com.example.kbp2.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.kbp2.R;


public class HomeFragment extends Fragment {

    ViewFlipper flipper, flipper1;
    TextView atkt, atkt1;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home, container, false);

        getActivity().setTitle("AVM's Kbp Degree College");
        // getActivity().setTitle(Html.fromHtml("<font color=\"#ffffff\">" + getString(R.string.action) + "</font>"));

        flipper = view.findViewById(R.id.flipper);
        flipper1 = view.findViewById(R.id.flipper1);
        atkt = view.findViewById(R.id.atkt);
        atkt1 = view.findViewById(R.id.atkt1);

        atkt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.kbpcollegethane.net/timetable-new.html");
            }
        });

        atkt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.kbpcollegethane.net/timetable-new.html");
            }
        });




        int i;
        int imgarray[]={R.drawable.image1,R.drawable.image2,R.drawable.image3,R.drawable.image4,R.drawable.image5};
        for(i=0; i < imgarray.length; i++)
            showimage(imgarray[i]);

        int j;
        int imgarray1[]={R.drawable.activity1,R.drawable.activity2,R.drawable.activity3,R.drawable.activity4,R.drawable.activity5};
        for(j=0; j < imgarray1.length; j++)
            showimage1(imgarray1[j]);

        return view;
    }


    public void showimage(int img){

        ImageView imageview = new ImageView(getActivity());
        imageview.setBackgroundResource(img);

        flipper.addView(imageview);
        flipper.setFlipInterval(3000);
        flipper.setAutoStart(true);
        flipper.setOutAnimation(getActivity(), android.R.anim.slide_out_right);
        flipper.setInAnimation(getActivity(), android.R.anim.slide_in_left);
    }
    public void showimage1(int img1){

        ImageView imageview1 = new ImageView(getActivity());
        imageview1.setBackgroundResource(img1);

        flipper1.addView(imageview1);
        flipper1.setFlipInterval(3000);
        flipper1.setAutoStart(true);
        flipper1.setOutAnimation(getActivity(), android.R.anim.slide_out_right);
        flipper1.setInAnimation(getActivity(), android.R.anim.slide_in_left);
    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}