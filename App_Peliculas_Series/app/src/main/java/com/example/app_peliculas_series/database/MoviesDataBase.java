package com.example.app_peliculas_series.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.app_peliculas_series.database.dao.MoviesDao;
import com.example.app_peliculas_series.database.entity.MovieEntity;

@Database(entities = {MovieEntity.class}, version = 1)
public abstract class MoviesDataBase extends RoomDatabase {

    public static MoviesDataBase INSTANCE;
 public abstract MoviesDao moviesDao();

    public static MoviesDataBase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, MoviesDataBase.class, "movielist.db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();//Se crea instancia de room

        }
        return INSTANCE;
    }
}
