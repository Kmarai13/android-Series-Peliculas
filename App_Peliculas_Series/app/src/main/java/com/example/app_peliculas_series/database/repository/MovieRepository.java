package com.example.app_peliculas_series.database.repository;

import com.example.app_peliculas_series.database.entity.MovieEntity;

import java.util.List;

public interface MovieRepository {

    List<MovieEntity> getAllMovies();
    void insertMovie(MovieEntity movieEntity);
    void updateMovie(MovieEntity movieEntity);
    void deleteMovie(MovieEntity movieEntity);
}
