
package com.example.kbp2.ui.gallery;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kbp2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {

    RecyclerView annualdayRecycler, independencedayRecycler,artworkRecycler, yuvatarangRecycler, technoholicRecycler;
    GalleryAdapter adapter;
    DatabaseReference reference;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        getActivity().setTitle("Gallery");

        annualdayRecycler = view.findViewById(R.id.annualdayRecycler);
        independencedayRecycler = view.findViewById(R.id.independencedayRecycler);
        artworkRecycler = view.findViewById(R.id.artworkRecycler);
        yuvatarangRecycler = view.findViewById(R.id.yuvatarangRecycler);
        technoholicRecycler = view.findViewById(R.id.technoholicRecycler);

        reference = FirebaseDatabase.getInstance().getReference().child("gallery");

        getAnnualImage();
        getIndependenceImage();
        getArtImage();
        geYuvatarangImage();
        getTechnoholicImage();


        return view;
    }

    private void geYuvatarangImage() {
        reference.child("Yuva Tarang").addValueEventListener(new ValueEventListener() {

            List<String> imageList = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String data = (String) dataSnapshot.getValue();
                    imageList.add(data);
                }
                adapter = new GalleryAdapter(getContext(), imageList);
                yuvatarangRecycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
                yuvatarangRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void getTechnoholicImage() {
        reference.child("Technoholic").addValueEventListener(new ValueEventListener() {

            List<String> imageList = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String data = (String) dataSnapshot.getValue();
                    imageList.add(data);
                }
                adapter = new GalleryAdapter(getContext(), imageList);
                technoholicRecycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
                technoholicRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getArtImage() {
        reference.child("Art Work").addValueEventListener(new ValueEventListener() {

            List<String> imageList = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String data = (String) dataSnapshot.getValue();
                    imageList.add(data);
                }
                adapter = new GalleryAdapter(getContext(), imageList);
                artworkRecycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
                artworkRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getIndependenceImage() {
        reference.child("Independence Day").addValueEventListener(new ValueEventListener() {

            List<String> imageList = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String data = (String) dataSnapshot.getValue();
                    imageList.add(data);
                }
                adapter = new GalleryAdapter(getContext(), imageList);
                independencedayRecycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
                independencedayRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void getAnnualImage() {
        reference.child("Annual Day").addValueEventListener(new ValueEventListener() {

            List<String> imageList = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String data = (String) dataSnapshot.getValue();
                    imageList.add(data);
                }
                adapter = new GalleryAdapter(getContext(), imageList);
                annualdayRecycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
                annualdayRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}