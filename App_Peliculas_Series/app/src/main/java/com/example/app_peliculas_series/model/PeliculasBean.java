package com.example.app_peliculas_series.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PeliculasBean implements Serializable {

    @SerializedName("name")
    public int name;
    @SerializedName("detail")
    public int detail;

}
