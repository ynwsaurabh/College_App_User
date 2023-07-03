package com.example.kbp2.ui.profile;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.kbp2.MainActivity;
import com.example.kbp2.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UpdateProfileActivity extends AppCompatActivity {

    EditText name, email, mobileno;
    FirebaseFirestore fstore;
    Button updateFacultyBtn;
    String downloadUrl;
    private DatabaseReference reference, dbRef;
    private ImageView updateFacultyImage;
    private Bitmap bitmap = null;
    FirebaseAuth auth;
    private final int REQ = 1;
    DocumentReference documentReference;
    String cname, cemail, cphone, image;
    TextView imageprofile;
    private StorageReference storageReference;
    String userId;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            updateFacultyImage.setImageBitmap(bitmap);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        reference = FirebaseDatabase.getInstance().getReference().child("Profile");
        storageReference = FirebaseStorage.getInstance().getReference().child("Profile");

        image = getIntent().getStringExtra("image");
        auth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        name = findViewById(R.id.updateFacultyName);
        email = findViewById(R.id.updateFacultyEmail);
        mobileno = findViewById(R.id.updateFacultyPost);
        imageprofile = findViewById(R.id.changeimage);
        updateFacultyImage = findViewById(R.id.updateFacultyImage);
        updateFacultyBtn = findViewById(R.id.updateFacultyBtn);
        email.setEnabled(false);
        mobileno.setEnabled(false);

        /*try {
            Picasso.get().load(downloadUrl).into(updateFacultyImage);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        setTitle("Profile");

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateFacultyBtn.setVisibility(s.length() > 0 ? View.VISIBLE : View.INVISIBLE);
            }
        });


        userId = auth.getCurrentUser().getUid();
        documentReference = fstore.collection("user").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                name.setText(value.getString("Name"));
                email.setText(value.getString("email"));
                mobileno.setText(value.getString("phone"));
            }
        });

        imageprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pickImage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickImage, REQ);
            }
        });

        updateFacultyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cname = name.getText().toString();
                if (cname.isEmpty()) {
                    name.setError("Empty");
                    name.requestFocus();
                } else if (bitmap == null) {
                  // uploadData();
                } else {
                   // uploadImage();
                }
                Map<String, Object> hp = new HashMap();
                hp.put("Name", cname);
                hp.put("image", downloadUrl);
                userId = auth.getCurrentUser().getUid();
                documentReference = fstore.collection("user").document(userId);
                documentReference.update(hp).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(UpdateProfileActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        e.printStackTrace();

                    }
                });
            }
        });


    }

/*
    private void uploadImage() {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] finalimg = baos.toByteArray();
        final StorageReference filePath;
        filePath = storageReference.child(finalimg + "jpg");
        final UploadTask uploadTask = filePath.putBytes(finalimg);
        uploadTask.addOnCompleteListener(UpdateProfileActivity.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()) {
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    downloadUrl = String.valueOf(uri);
                                    uploadData();
                                }
                            });
                        }
                    });
                } else {

                    Toast.makeText(UpdateProfileActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

   private void uploadData() {
       reference = reference.child("category");
       final String uniqueKey = reference.push().getKey();

       reference.child(uniqueKey).setValue(downloadUrl).addOnSuccessListener(new OnSuccessListener<Void>() {
           @Override
           public void onSuccess(Void unused) {
               Toast.makeText(UpdateProfileActivity.this, "Image Uploaded Successfully", Toast.LENGTH_SHORT).show();
               startActivity(new Intent(getApplicationContext(), MainActivity.class));
               finish();

           }
       }).addOnFailureListener(new OnFailureListener() {
           @Override
           public void onFailure(@NonNull Exception e) {
               Toast.makeText(UpdateProfileActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

           }
       });
    }*/

   /* private void updateData() {
        dbRef = reference.child("Profile");
        final String uniqueKey = reference.push().getKey();

        reference.child(uniqueKey).setValue(downloadUrl).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

                Toast.makeText(UpdateProfileActivity.this, "Image Uploaded Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(UpdateProfileActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

            }
        });
    }*/

}
