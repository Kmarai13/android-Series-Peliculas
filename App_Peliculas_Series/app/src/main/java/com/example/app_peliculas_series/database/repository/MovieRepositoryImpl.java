package com.example.app_peliculas_series.database.repository;

import com.example.app_peliculas_series.database.dao.MoviesDao;
import com.example.app_peliculas_series.database.entity.MovieEntity;

import java.util.List;

public class MovieRepositoryImpl implements MovieRepository {

    MoviesDao dao;

    public MovieRepositoryImpl(MoviesDao dao) {
        this.dao = dao;
    }

    @Override
    public List<MovieEntity> getAllMovies() {
        return dao.getAll();
    }

    @Override
    public void insertMovie(MovieEntity movieEntity) {
        dao.insert(movieEntity);
    }

    @Override
    public void updateMovie(MovieEntity movieEntity) {
        dao.update(movieEntity);
    }

    @Override
    public void deleteMovie(MovieEntity movieEntity) {
        dao.delete(movieEntity);
    }
}
