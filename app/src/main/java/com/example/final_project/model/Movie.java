package com.example.final_project.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Movie implements Serializable {
    @SerializedName("trackId")
    @Expose
    private Integer trackId;

    @SerializedName("artworkUrl100")
    @Expose
    private String artworkUrl100;

    @SerializedName("trackName")
    @Expose
    private String trackName;

    @SerializedName("primaryGenreName")
    @Expose
    private String primaryGenreName;

    @SerializedName("longDescription")
    @Expose
    private String longDescription;

    @SerializedName("releaseDate")
    @Expose
    private String releaseDate;

    public Integer getTrackId() {
        return trackId;
    }

    public String getArtworkUrl100() {
        return artworkUrl100;
    }

    public String getTrackName() {
        return trackName;
    }

    public String getPrimaryGenreName() {
        return primaryGenreName;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}

