package com.example.kbp2.ui.faculty;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kbp2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class FacultyFragment extends Fragment {

    private RecyclerView itDepartment, baDepartment, bbiDepartment, bcomDepartment, bafDepartment;
    private LinearLayout itNoData, baNoData, bbiNoData, bcomNoData, bafNoData;
    private List<FacultyData> list1, list2, list3, list4, list5;
    private FacultyAdapter adapter;
    private ProgressBar itprogressBar,baprogressBar,bafprogressBar,bcomprogressBar,bbiprogressBar;
    private DatabaseReference reference, dbref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_faculty, container, false);

        getActivity().setTitle("Faculty");


        bafDepartment = view.findViewById(R.id.bafDepartment);
        bafprogressBar = view.findViewById(R.id.bafProgressBar);
        bcomDepartment = view.findViewById(R.id.bcomDepartment);
        bcomprogressBar = view.findViewById(R.id.bcomProgressBar);
        bbiDepartment = view.findViewById(R.id.bbiDepartment);
        bbiprogressBar = view.findViewById(R.id.bbiProgressBar);
        baDepartment = view.findViewById(R.id.baDepartment);
        baprogressBar = view.findViewById(R.id.baProgressBar);
        itDepartment = view.findViewById(R.id.itDepartment);
        itprogressBar = view.findViewById(R.id.itProgressBar);


        bafNoData = view.findViewById(R.id.bafNoData);
        bcomNoData = view.findViewById(R.id.bcomNoData);
        bbiNoData = view.findViewById(R.id.bbiNoData);
        baNoData = view.findViewById(R.id.baNoData);
        itNoData = view.findViewById(R.id.itNoData);

        reference = FirebaseDatabase.getInstance().getReference().child("Faculty");

        itDepartment();
        baDepartment();
        bbiDepartment();
        bcomDepartment();
        bafDepartment();

        return view;
    }

    private void itDepartment() {
        dbref = reference.child("IT");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list1 = new ArrayList<>();
                if(!snapshot.exists()){
                    itNoData.setVisibility(View.VISIBLE);
                    itDepartment.setVisibility(View.GONE);
                    itprogressBar.setVisibility(View.GONE);
                }
                else{
                    itNoData.setVisibility(View.GONE);
                    itDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                        FacultyData data = dataSnapshot.getValue(FacultyData.class);
                        list1.add(data);
                    }
                    itDepartment.setHasFixedSize(true);
                    itDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new FacultyAdapter(list1, getContext());
                    itprogressBar.setVisibility(View.GONE);
                    itDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                itprogressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void baDepartment() {
        dbref = reference.child("BA");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list2 = new ArrayList<>();
                if(!snapshot.exists()){
                    baNoData.setVisibility(View.VISIBLE);
                    baDepartment.setVisibility(View.GONE);
                    baprogressBar.setVisibility(View.GONE);
                }
                else{
                    baNoData.setVisibility(View.GONE);
                    baDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                        FacultyData data = dataSnapshot.getValue(FacultyData.class);
                        list2.add(data);
                    }
                    baDepartment.setHasFixedSize(true);
                    baDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new FacultyAdapter(list2, getContext());
                    baprogressBar.setVisibility(View.GONE);
                    baDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                baprogressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void bbiDepartment() {
        dbref = reference.child("BBI");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list3 = new ArrayList<>();
                if(!snapshot.exists()){
                    bbiNoData.setVisibility(View.VISIBLE);
                    bbiDepartment.setVisibility(View.GONE);
                    bbiprogressBar.setVisibility(View.GONE);
                }
                else{
                    bbiNoData.setVisibility(View.GONE);
                    bbiDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                        FacultyData data = dataSnapshot.getValue(FacultyData.class);
                        list3.add(data);
                    }
                    bbiDepartment.setHasFixedSize(true);
                    bbiDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new FacultyAdapter(list3, getContext());
                    bbiprogressBar.setVisibility(View.GONE);
                    bbiDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                bbiprogressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void bcomDepartment() {
        dbref = reference.child("Bcom");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list4 = new ArrayList<>();
                if(!snapshot.exists()){
                    bcomNoData.setVisibility(View.VISIBLE);
                    bcomDepartment.setVisibility(View.GONE);
                    bcomprogressBar.setVisibility(View.GONE);
                }
                else{
                    bcomNoData.setVisibility(View.GONE);
                    bcomDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                        FacultyData data = dataSnapshot.getValue(FacultyData.class);
                        list4.add(data);
                    }
                    bcomDepartment.setHasFixedSize(true);
                    bcomDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new FacultyAdapter(list4, getContext());
                    bcomprogressBar.setVisibility(View.GONE);
                    bcomDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                bcomprogressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void bafDepartment() {
        dbref = reference.child("BAF");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list5 = new ArrayList<>();
                if(!snapshot.exists()){
                    bafNoData.setVisibility(View.VISIBLE);
                    bafDepartment.setVisibility(View.GONE);
                    bafprogressBar.setVisibility(View.GONE);
                }
                else{
                    bafNoData.setVisibility(View.GONE);
                    bafDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                        FacultyData data = dataSnapshot.getValue(FacultyData.class);
                        list5.add(data);
                    }
                    bafDepartment.setHasFixedSize(true);
                    bafDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new FacultyAdapter(list5, getContext());
                    bafprogressBar.setVisibility(View.GONE);
                    bafDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                bafprogressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}