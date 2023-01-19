package com.example.final_project.singleton;

import com.example.final_project.model.Movie;
import java.util.List;

public class MovieList {
    private static MovieList instance;

    private List<Movie> movieList = null;

    private MovieList(){}

    public static MovieList getInstance() {
        if(instance == null) {
            synchronized (MovieList.class) {
                if(instance == null) {
                    instance = new MovieList();
                }
            }
        }
        return instance;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movies) {
        movieList = movies;
    }
}
