package com.example.app_peliculas_series.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;
import retrofit2.http.GET;

@Entity(tableName = "Movie")
public class MovieEntity {


    @PrimaryKey(autoGenerate = true)
    @Setter@Getter
    public int MovieId;
    @ColumnInfo(name = "title")
    @Setter@Getter
    public String title;
    @ColumnInfo(name = "descripcion")
    @Setter@Getter
    public String descripcion;
    @ColumnInfo(name = "imagen")
    @Setter@Getter
    public String imagen;

}
