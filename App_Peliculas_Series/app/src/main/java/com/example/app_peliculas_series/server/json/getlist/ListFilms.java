package com.example.app_peliculas_series.server.json.getlist;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ListFilms {

    @SerializedName("release_date")
    public String releaseDate;
    @SerializedName("adult")
    public Boolean adult;
    @SerializedName("backdrop_path")
    public String backdropPath;
    @SerializedName("genre_ids")
    public List<Integer> genreIds;
    @SerializedName("vote_count")
    public Integer voteCount;
    @SerializedName("original_language")
    public String originalLanguage;
    @SerializedName("original_title")
    public String originalTitle;
    @SerializedName("poster_path")
    public String posterPath;
    @SerializedName("video")
    public Boolean video;
    @SerializedName("id")
    public Integer id;
    @SerializedName("vote_average")
    public Double voteAverage;
    @SerializedName("title")
    public String title;
    @SerializedName("overview")
    public String overview;
    @SerializedName("popularity")
    public Double popularity;
    @SerializedName("media_type")
    public String mediaType;

}
