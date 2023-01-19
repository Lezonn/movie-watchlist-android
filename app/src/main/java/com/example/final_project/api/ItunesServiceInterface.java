package com.example.final_project.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ItunesServiceInterface {
    @GET("search")
    Call<MovieResponse> getMovies(
        @Query(value = "media") String media,
        @Query(value = "term") String term
    );
}
