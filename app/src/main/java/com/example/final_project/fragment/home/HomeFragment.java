package com.example.final_project.fragment.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project.adapter.MovieAdapter;
import com.example.final_project.api.APIClient;
import com.example.final_project.api.ItunesServiceInterface;
import com.example.final_project.api.MovieResponse;
import com.example.final_project.databinding.FragmentHomeBinding;
import com.example.final_project.model.Movie;
import com.example.final_project.singleton.MovieList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView rvBook = binding.rvBook;

        rvBook.setLayoutManager( new GridLayoutManager(root.getContext(),2));

        MovieAdapter movieAdapter = new MovieAdapter(root.getContext());
        rvBook.setAdapter(movieAdapter);

        // Fetch API
        if(MovieList.getInstance().getMovieList() == null) {
            Retrofit retrofit = APIClient.getRetrofit();
            ItunesServiceInterface service = retrofit.create(ItunesServiceInterface.class);
            Call<MovieResponse> call = service.getMovies("movie", "marvel");
            // URL: https://itunes.apple.com/search?media=movie&term=marvel
            call.enqueue(new Callback<MovieResponse>() {
                @Override
                public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                    List<Movie> movieList = response.body().getResults();
                    if(movieList != null) {
                        MovieList.getInstance().setMovieList(movieList);
                        movieAdapter.setListMovie(movieList);
                    }
                }

                @Override
                public void onFailure(Call<MovieResponse> call, Throwable t) {
                    call.cancel();
                }
            });
        }
        else {
            movieAdapter.setListMovie(MovieList.getInstance().getMovieList());
        }


        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}