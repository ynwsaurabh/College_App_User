package com.example.kbp2.ui.Result;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.kbp2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    private RecyclerView resultRecycler;
    private DatabaseReference reference;
    private List<ResultData> list;
    private ResultAdapter adapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultRecycler = findViewById(R.id.ResultRecycler);
        progressBar = findViewById(R.id.resultprogressBar);
        reference = FirebaseDatabase.getInstance().getReference().child("pdf");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Result");

        getData();
    }

    private void getData() {
        reference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list = new ArrayList<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ResultData data = dataSnapshot.getValue(ResultData.class);
                    list.add(data);

                }
                adapter = new ResultAdapter(ResultActivity.this, list);
                resultRecycler.setLayoutManager(new LinearLayoutManager(ResultActivity.this));
                progressBar.setVisibility(View.GONE);
                resultRecycler.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(ResultActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}