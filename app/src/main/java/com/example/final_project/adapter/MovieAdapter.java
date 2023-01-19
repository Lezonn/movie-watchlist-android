package com.example.final_project.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.final_project.R;
import com.example.final_project.activity.MovieActivity;
import com.example.final_project.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    Context context;
    List<Movie> listMovie;

    public MovieAdapter(Context context){
        this.context = context;
        this.listMovie = new ArrayList<>();
    }

    public void setListMovie(List<Movie> listMovie) {
        this.listMovie = listMovie;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {
        Movie movie = listMovie.get(position);
        Glide.with(context).load(movie.getArtworkUrl100()).into(holder.itemImage);

        String title = movie.getTrackName().length() >= 30 ?
                movie.getTrackName().substring(0, 26) + " ..." :
                movie.getTrackName();

        holder.itemTitle.setText(title);
    }

    @Override
    public int getItemCount() {
        if(listMovie == null) {
            return 0;
        }
        return listMovie.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView itemImage;
        TextView itemTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.itemImage);
            itemTitle = itemView.findViewById(R.id.itemTitle);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Movie movie = listMovie.get(getAdapterPosition());
            Intent movieActivity = new Intent(context, MovieActivity.class);
            movieActivity.putExtra("movie", movie);
            context.startActivity(movieActivity);
        }
    }
}
