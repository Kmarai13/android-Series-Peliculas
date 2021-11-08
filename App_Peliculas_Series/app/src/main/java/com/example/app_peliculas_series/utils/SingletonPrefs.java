package com.example.app_peliculas_series.utils;

import com.example.app_peliculas_series.server.json.accesstoken.AccessTokentResponse;
import com.example.app_peliculas_series.server.json.requesttoken.RequestTokenResponse;

public class SingletonPrefs {

    public AccessTokentResponse accessTokentResponse;
    public RequestTokenResponse requestTokenResponse;

    private static SingletonPrefs ourInstance = new SingletonPrefs();


    public static SingletonPrefs getInstance() {
        return ourInstance;
    }

    private SingletonPrefs() {
    }
}
