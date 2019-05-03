package com.example.hamz.dedymizwarapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hamz.dedymizwarapp.R;
import com.example.hamz.dedymizwarapp.activity.ActivityDetail;
import com.example.hamz.dedymizwarapp.model.Activity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.MyViewHolder> {

    Context context;
    ArrayList<Activity> activities;

    public ActivityAdapter(Context context, ArrayList<Activity> activities) {
        this.context = context;
        this.activities = activities;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Activity activity = activities.get(position);

        final MyViewHolder h = holder;

        holder.title.setText(activity.getTitle());
        holder.date.setText(activity.getDate());

        Picasso.get()
                .load(activity.getImage())
                .placeholder(R.drawable.progress_animation)
                .error(R.drawable.noimage)
                .into(holder.image, new Callback() {
                    @Override
                    public void onSuccess() {
                        h.image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(context, "Failed when loading image "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivityDetail.class);
                intent.putExtra("id", activity.getId());
                intent.putExtra("title", activity.getTitle());
                intent.putExtra("image", activity.getImage());
                intent.putExtra("desc", activity.getDescription());
                intent.putExtra("date", activity.getDate());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return activities.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        LinearLayout container;
        ImageView image;
        TextView title, date;

        public MyViewHolder(View itemView) {
            super(itemView);

            container = itemView.findViewById(R.id.container);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            date = itemView.findViewById(R.id.date);

        }
    }
}
