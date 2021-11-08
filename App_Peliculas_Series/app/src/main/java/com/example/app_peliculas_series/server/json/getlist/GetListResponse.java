package com.example.app_peliculas_series.server.json.getlist;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetListResponse {


    @SerializedName("page")
    public Integer page;
    @SerializedName("results")
    public List<ListFilms> results;
    @SerializedName("total_pages")
    public Integer totalPages;
    @SerializedName("total_results")
    public Integer totalResults;

}
