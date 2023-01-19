package com.example.final_project.api;

import com.example.final_project.model.Movie;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {
    @SerializedName("resultCount")
    @Expose
    private Integer resultCount;

    @SerializedName("results")
    @Expose
    private List<Movie> results = null;

    public Integer getResultCount() {
        return resultCount;
    }

    public List<Movie> getResults() {
        return results;
    }
}
