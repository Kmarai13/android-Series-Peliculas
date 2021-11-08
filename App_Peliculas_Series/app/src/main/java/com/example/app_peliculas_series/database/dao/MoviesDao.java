package com.example.app_peliculas_series.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.app_peliculas_series.database.entity.MovieEntity;

import java.util.List;

@Dao
public interface MoviesDao {
    @Query("select * from Movie")
    List<MovieEntity> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void  insert (MovieEntity movieEntity);

    @Update
    void update(MovieEntity movieEntity);

     @Delete
    void delete(MovieEntity movieEntity);

}
