package com.example.kbp2.ui.Result;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kbp2.PdfViewerActivity;
import com.example.kbp2.R;

import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultViewHolder> {

    private Context context;
    private List<ResultData> list;

    public ResultAdapter(Context context, List<ResultData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.result_item_layout, parent, false);

        return new ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {

        holder.resultName.setText(list.get(position).getPdfTitle());
        
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PdfViewerActivity.class);
                intent.putExtra("pdfUrl",list.get(holder.getAdapterPosition()).getPdfUrl() );
                context.startActivity(intent);
            }
        });

        holder.resultDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(list.get(holder.getAdapterPosition()).getPdfUrl()));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ResultViewHolder extends RecyclerView.ViewHolder {

        private TextView resultName;
        private ImageView resultDownload;

        public ResultViewHolder(@NonNull View itemView) {
            super(itemView);

            resultDownload = itemView.findViewById(R.id.resultDownload);
            resultName = itemView.findViewById(R.id.resultName);
        }
    }

}
