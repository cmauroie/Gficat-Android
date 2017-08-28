package com.cmauroie.gficatandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmauroie.gficatandroid.DetailsActivity;
import com.cmauroie.gficatandroid.R;
import com.cmauroie.gficatandroid.modeldata.ModelDataAlbum;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Mauricio on 26/8/17.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {

    private final String TAG = DataAdapter.class.getName();
    private List<ModelDataAlbum> items;
    private Context context;

    public static class DataViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textViewTitle;

        public DataViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.id_image);
            textViewTitle = (TextView)itemView.findViewById(R.id.id_name_album);
        }
    }

    public DataAdapter(List<ModelDataAlbum> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.album_card, viewGroup, false);
        /*if (context instanceof SearchMovieActivity) {
            v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_movie_found, viewGroup, false);
        } else {
            v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_movie_card, viewGroup, false);
        }*/
        return new DataViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, final int position) {

        final String imageUrl = items.get(position).getCoverImageUrl();

        Log.i(TAG,"imageUrl:" + imageUrl);
        Picasso.with(context)
                .load(imageUrl)
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.imageView);

        holder.textViewTitle.setText(items.get(position).getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("title", items.get(position).getTitle());
                intent.putExtra("url_video", items.get(position).getMobileUrl());
                intent.putExtra("description", items.get(position).getDescription());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
