package com.example.vumobile.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.vumobile.R;
import com.example.vumobile.activities.MatchImageActivity;
import com.example.vumobile.model.ImageDataListModel;

import java.io.Serializable;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.DashboardViewHolder> {

    private Context context;
    private List<ImageDataListModel> dashboardItemList;

    public ImageAdapter(Context context, List<ImageDataListModel> dashboardItemList) {
        this.context = context;
        this.dashboardItemList = dashboardItemList;
    }

    @NonNull
    @Override
    public DashboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_dashboard, parent, false);
        return new DashboardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardViewHolder holder, final int position) {
        ImageDataListModel dashboardItemModel = dashboardItemList.get(position);

        Glide.with(context).load(dashboardItemModel.getAvatar()).placeholder(R.drawable.placeholder_img).into(holder.dashItemImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MatchImageActivity.class);
                intent.putExtra("MyClass", (Serializable) dashboardItemList.get(position));
                context.startActivity(intent);
            }
        });

        holder.userName.setText(dashboardItemModel.getFirstName() + " " + dashboardItemModel.getLastName());
        holder.userEmail.setText(dashboardItemModel.getEmail());


    }

    @Override
    public int getItemCount() {
        return dashboardItemList.size();
    }

    public class DashboardViewHolder extends RecyclerView.ViewHolder {

        private ImageView dashItemImage;
        private TextView userName, userEmail;
        private CardView cardView;

        public DashboardViewHolder(@NonNull View itemView) {
            super(itemView);

            dashItemImage = itemView.findViewById(R.id.itemImage);
            userName = itemView.findViewById(R.id.userName);
            userEmail = itemView.findViewById(R.id.userEmail);
        }
    }
}
