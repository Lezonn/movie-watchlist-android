package com.example.final_project.fragment.watchlist;

import static android.content.ContentValues.TAG;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project.adapter.MovieAdapter;
import com.example.final_project.databinding.FragmentWatchlistBinding;
import com.example.final_project.db.WatchlistFunctionDB;
import com.example.final_project.model.Movie;
import com.example.final_project.singleton.CurrentUser;
import com.example.final_project.singleton.MovieList;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class WatchlistFragment extends Fragment {

    private FragmentWatchlistBinding binding;
    private WatchlistFunctionDB wDB;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentWatchlistBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        setRecyclerView(root);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        View root = binding.getRoot();
        setRecyclerView(root);
    }

    private void setRecyclerView(View root) {
        getWatchlist();
        RecyclerView rvBook = binding.rvBook;

        rvBook.setLayoutManager( new GridLayoutManager(root.getContext(),2));

        MovieAdapter movieAdapter = new MovieAdapter(root.getContext());
        rvBook.setAdapter(movieAdapter);
        movieAdapter.setListMovie(CurrentUser.getInstance().getUserWatchlist());
    }


    private void getWatchlist() {
        CurrentUser currentUser = CurrentUser.getInstance();
        MovieList movieList = MovieList.getInstance();
        ArrayList<Movie> movies = new ArrayList<>();

        wDB = new WatchlistFunctionDB(getContext());

        ArrayList<Integer> trackIdList = wDB.getUserWatchlist(currentUser
                .getCurrentUser()
                .getId());

        if(trackIdList == null) {
            return;
        }

        for(Movie m : movieList.getMovieList()) {
            if(trackIdList.contains(m.getTrackId())) {
                movies.add(m);
            }
        }

        currentUser.setUserWatchlist(movies);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}