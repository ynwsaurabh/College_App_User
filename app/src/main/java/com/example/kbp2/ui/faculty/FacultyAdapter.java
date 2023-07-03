package com.example.kbp2.ui.faculty;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kbp2.FullImageView;
import com.example.kbp2.R;

import java.util.List;

public class FacultyAdapter extends RecyclerView.Adapter<FacultyAdapter.FacultyViewAdapter> {

    private List<FacultyData> list;
    private Context context;


    public FacultyAdapter(List<FacultyData> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public FacultyViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.faculty_item_layout, parent, false);
        return new FacultyViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FacultyViewAdapter holder, int position) {

        FacultyData item = list.get(position);
        holder.name.setText(item.getName());
        holder.email.setText(item.getEmail());
        holder.post.setText(item.getPost());

        try {
            Glide.with(context).load(item.getImage()).placeholder(R.drawable.avatar).into(holder.imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FullImageView.class);
                intent.putExtra("image", item.getImage());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class FacultyViewAdapter extends RecyclerView.ViewHolder {

        private TextView name, email, post;
        private ImageView imageView;

        public FacultyViewAdapter(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.facultyName);
            email = itemView.findViewById(R.id.facultyEmail);
            post = itemView.findViewById(R.id.facultyPost);
            imageView = itemView.findViewById(R.id.facultyImage);
        }
    }
}
