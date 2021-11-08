package com.example.app_peliculas_series.server.json.getlist;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetListResponse {

    @SerializedName("page")
    public int page;
    @SerializedName("results")
    public List<ListFilms> results;
    @SerializedName("total_pages")
    public int total_pages;
    @SerializedName("total_results")
    public int total_results;

}
