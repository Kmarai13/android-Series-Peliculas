package com.example.app_peliculas_series.server.json.getlist;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetListResponse {

    @SerializedName("adult")
    public Boolean adult;
    @SerializedName("backdrop_path")
    public String backdropPath;
    @SerializedName("belongs_to_collection")
    public Object belongsToCollection;
    @SerializedName("budget")
    public Integer budget;
    @SerializedName("genres")
    public List<GenresDTO> genres;
    @SerializedName("homepage")
    public String homepage;
    @SerializedName("id")
    public Integer id;
    @SerializedName("imdb_id")
    public String imdbId;
    @SerializedName("original_language")
    public String originalLanguage;
    @SerializedName("original_title")
    public String originalTitle;
    @SerializedName("overview")
    public String overview;
    @SerializedName("popularity")
    public Double popularity;
    @SerializedName("poster_path")
    public String posterPath;
    @SerializedName("production_companies")
    public List<ProductionCompaniesDTO> productionCompanies;
    @SerializedName("production_countries")
    public List<ProductionCountriesDTO> productionCountries;
    @SerializedName("release_date")
    public String releaseDate;
    @SerializedName("revenue")
    public Integer revenue;
    @SerializedName("runtime")
    public Integer runtime;
    @SerializedName("spoken_languages")
    public List<SpokenLanguagesDTO> spokenLanguages;
    @SerializedName("status")
    public String status;
    @SerializedName("tagline")
    public String tagline;
    @SerializedName("title")
    public String title;
    @SerializedName("video")
    public Boolean video;
    @SerializedName("vote_average")
    public Double voteAverage;
    @SerializedName("vote_count")
    public Integer voteCount;

    public static class GenresDTO {
        @SerializedName("id")
        public Integer id;
        @SerializedName("name")
        public String name;
    }

    public static class ProductionCompaniesDTO {
        @SerializedName("id")
        public Integer id;
        @SerializedName("logo_path")
        public String logoPath;
        @SerializedName("name")
        public String name;
        @SerializedName("origin_country")
        public String originCountry;
    }

    public static class ProductionCountriesDTO {
        @SerializedName("iso_3166_1")
        public String iso31661;
        @SerializedName("name")
        public String name;
    }

    public static class SpokenLanguagesDTO {
        @SerializedName("english_name")
        public String englishName;
        @SerializedName("iso_639_1")
        public String iso6391;
        @SerializedName("name")
        public String name;
    }
}
