package com.example.kbp2.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.kbp2.R;
import com.example.kbp2.ui.Result.ResultActivity;

public class ExamActivity extends AppCompatActivity {
    CardView hallticket, timetable, result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        setTitle("Exam");

        hallticket = findViewById(R.id.hallticket);
        timetable =findViewById(R.id.timetable);
        result = findViewById(R.id.result);

        hallticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.kbpcollegethane.net/hall-ticket.html");
            }
        });


        timetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.kbpcollegethane.net/timetable-new.html");
            }
        });


        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ResultActivity.class));
            }
        });





    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));

    }
}