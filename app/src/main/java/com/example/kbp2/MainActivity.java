package com.example.kbp2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kbp2.ui.Academy.AcademyActivity;
import com.example.kbp2.ui.ContactActivity;
import com.example.kbp2.ui.ExamActivity;
import com.example.kbp2.ui.faculty.FacultyFragment;
import com.example.kbp2.ui.gallery.GalleryFragment;
import com.example.kbp2.ui.home.HomeFragment;
import com.example.kbp2.ui.notice.NoticeFragment;
import com.example.kbp2.ui.profile.UpdateProfileActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
   public DrawerLayout drawerLayout;
    NavigationView navigationView;
    RelativeLayout frame_layout;
   public ActionBarDrawerToggle actionBarDrawerToggle;
   FirebaseAuth auth;
   FirebaseFirestore fstore;
   DocumentReference documentReference;
   String userId;

    private Boolean exit = false;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_menu, menu);
        return true;
    }

   @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser == null){
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new HomeFragment()).commit();

        drawerLayout = findViewById(R.id.drawer_Layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.start, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        auth =FirebaseAuth.getInstance();
        fstore =FirebaseFirestore.getInstance();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle("AVM's Kbp Degree College");

        bottomNavigationView= findViewById(R.id.bottomNavigationView);

        navigationView =findViewById(R.id.navigation_view);
        frame_layout=findViewById(R.id.frame_layout);
        FirebaseMessaging.getInstance().subscribeToTopic("notification");

        perm();


        View navView = navigationView.getHeaderView(0);
        ImageView updateFacultyImage = navView.findViewById(R.id.updateFacultyImage);
        ImageView edit = navView.findViewById(R.id.edit);
        TextView name = navView.findViewById(R.id.dhname);
        TextView email = navView.findViewById(R.id.dhemail);
        TextView mobileno = navView.findViewById(R.id.dhmobileno);

        userId = auth.getCurrentUser().getUid();
        documentReference =fstore.collection("user").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                name.setText(value.getString("Name"));
                email.setText(value.getString("email"));
                mobileno.setText(value.getString("phone"));
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), UpdateProfileActivity.class));

                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });





        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){

                    case R.id.navigation_academy:
                        startActivity(new Intent(getApplicationContext(), AcademyActivity.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new HomeFragment()).commit();
                        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_gallery:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new GalleryFragment()).commit();
                        bottomNavigationView.setSelectedItemId(R.id.navigation_gallery);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_about:
                        startActivity(new Intent(getApplicationContext(), AboutActivity.class));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.navigation_exam:
                        startActivity(new Intent(getApplicationContext(), ExamActivity.class));

                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.navigation_contact:
                        startActivity(new Intent(getApplicationContext(), ContactActivity.class));

                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.navigation_logout:
                        auth.signOut();
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        finish();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.navigation_website:
                        gotoUrl("https://www.kbpcollegethane.net/index.html");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }


                return true;
            }
        });


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new HomeFragment()).commit();
                        break;

                    case R.id.navigation_gallery:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new GalleryFragment()).commit();
                        break;
                    case R.id.navigation_faculty:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new FacultyFragment()).commit();
                        break;
                    case R.id.navigation_notice:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new NoticeFragment()).commit();
                        break;
                }

                return true;
            }
        });
    }

    private void perm() {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            int permissionNotify = ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS);

            if(permissionNotify != PackageManager.PERMISSION_GRANTED){
                String[] NOTIF_PERM = {Manifest.permission.POST_NOTIFICATIONS};
                ActivityCompat.requestPermissions(this, NOTIF_PERM, 100);
            }
        }
    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));

    }


    @Override
    public void onBackPressed() {
        if (bottomNavigationView.getSelectedItemId() != R.id.navigation_home) {
            bottomNavigationView.setSelectedItemId(R.id.navigation_home);

        } else if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);

        } else if (exit) {
            finish(); // finish activity
        }else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }
    }

    /* @Override
    public void onBackPressed() {
        if(isBackPressedOnce){
            super.onBackPressed();
            return;
        }
        Toast.makeText(this, "Press again to exit!!", Toast.LENGTH_SHORT).show();
        isBackPressedOnce =true;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isBackPressedOnce = false;
            }
        }, 3000);

    }*/
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
