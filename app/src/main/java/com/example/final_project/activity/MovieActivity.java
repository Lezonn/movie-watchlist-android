package com.example.final_project.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.final_project.R;
import com.example.final_project.db.WatchlistFunctionDB;
import com.example.final_project.model.Movie;
import com.example.final_project.model.User;
import com.example.final_project.singleton.CurrentUser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class MovieActivity extends AppCompatActivity {
    private WatchlistFunctionDB wDB;

    private Movie movie;
    private User currentUser;
    private boolean isAdded;

    private ImageView imageMovie;
    private TextView textTitle;
    private TextView textDescription;
    private TextView textGenre;
    private TextView textReleaseDate;
    private Button btnWatchlistHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        getIntentHandler();
        initComponents();
        setLayoutValue();
    }

    private void setLayoutValue() {
        Glide.with(this).load(movie.getArtworkUrl100()).into(imageMovie);
        textTitle.setText(movie.getTrackName());
        textDescription.setText(movie.getLongDescription());
        textGenre.setText(movie.getPrimaryGenreName());

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(movie.getReleaseDate(), inputFormatter);
        String formattedDate = outputFormatter.format(date);

        textReleaseDate.setText(formattedDate);
        btnWatchlistHandler.setText(isAdded ? "Remove from watchlist" : "Add to watchlist");
    }

    private void getIntentHandler() {
        Intent getIntent = getIntent();
        movie = (Movie) getIntent.getSerializableExtra("movie");
    }

    public void btnWatchlistHandler(View view) {
        if(isAdded) {
            wDB.removeWatchlist(currentUser.getId(), movie.getTrackId());

            String msg = movie.getTrackName() + " removed from watchlist";

            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        wDB.addWatchlist(currentUser.getId(), movie.getTrackId());

        String msg = movie.getTrackName() + " added to watchlist";

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        finish();
    }

    private void initComponents() {
        wDB = new WatchlistFunctionDB(this);
        currentUser = CurrentUser.getInstance().getCurrentUser();
        isAdded = wDB.checkWatchlist(currentUser.getId(), movie.getTrackId());

        imageMovie = findViewById(R.id.movieImage);
        textTitle = findViewById(R.id.movieTitle);
        textDescription = findViewById(R.id.movieDescription);
        textGenre = findViewById(R.id.movieGenre);
        textReleaseDate = findViewById(R.id.movieReleaseDate);
        btnWatchlistHandler = findViewById(R.id.btnWatchlistHandler);
    }
}