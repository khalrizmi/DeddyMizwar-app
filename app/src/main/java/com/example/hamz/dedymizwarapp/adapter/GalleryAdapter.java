package com.example.hamz.dedymizwarapp.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hamz.dedymizwarapp.R;
import com.example.hamz.dedymizwarapp.model.Gallery;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.MyViewHolder> {

    Context context;
    ArrayList<Gallery> galleries;

    public GalleryAdapter(Context context, ArrayList<Gallery> galleries) {
        this.context = context;
        this.galleries = galleries;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.gallery_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

        final Gallery gallery = galleries.get(position);

        Picasso.get()
                .load(gallery.getName())
                .placeholder(R.drawable.progress_animation)
                .error(R.drawable.noimage)
                .into(holder.image, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(context, "Failed when loading image "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });



        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = LayoutInflater.from(context);
                View view = inflater.inflate(R.layout.gallery_modal, null);
                TextView close = view.findViewById(R.id.close);
                ImageView imageModal = view.findViewById(R.id.image_modal);
                TextView description = view.findViewById(R.id.description);

                description.setText(gallery.getDescription());

                Picasso.get()
                        .load(gallery.getName())
                        .placeholder(R.drawable.progress_animation)
                        .error(R.drawable.noimage)
                        .into(imageModal);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setView(view);

                final AlertDialog dialog = builder.create();
                dialog.show();

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return galleries.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
        }
    }
}
