package com.example.app_peliculas_series.server;


import androidx.annotation.NonNull;

import com.example.app_peliculas_series.BuildConfig;
import com.google.gson.GsonBuilder;
import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.platform.Platform;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebServices {

    private static WebServicesDefinition servicesDefinition;

    public static WebServicesDefinition services() {
        setupServicesDefinition(); // Show Nulls
        return servicesDefinition;
    }

    private static Headers getJsonHeader() {
        // TODO QUITAR DATOS SENSIBLES
        Headers.Builder builder = new Headers.Builder();
        builder.add("Content-Type", "application/json");
        builder.add("Accept", "application/json");
        builder.add("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwOTlkZDMxM2Y5OGE3YzYxOWQ2NzAyNDViYjdmZjY1YyIsInN1YiI6IjYxNzU3NDYxMGQ1ZDg1MDAyYWVlMzZiZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.RMpKDvlSTmIAoSEdhkGtbKDK49NM4Tjo0w1SSCp8dVM");
        return builder.build();
    }

    private static void setupServicesDefinition() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(120, TimeUnit.SECONDS);
        builder.connectTimeout(120, TimeUnit.SECONDS);
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request.Builder builder1 = chain.request().newBuilder();
                builder1.headers(getJsonHeader());
                return chain.proceed(builder1.build());
            }
        });
        String server_tmdb;
        server_tmdb = "https://api.themoviedb.org/";
        Retrofit retrofit = null;
        servicesDefinition = null;
        retrofit = new Retrofit.Builder()
                .baseUrl(server_tmdb)
                .client(getOkHttpClient(builder))
                //.addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder().serializeNulls().create()))
                .build();

        servicesDefinition = retrofit.create(WebServicesDefinition.class);
    }

    private static OkHttpClient getOkHttpClient(OkHttpClient.Builder builder) {
        try {
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    if (!BuildConfig.DEBUG) {
                        HostnameVerifier hostnameVerifier =
                                HttpsURLConnection.getDefaultHostnameVerifier();
                        return hostnameVerifier.verify(hostname, session);
                    }
                    return true;
                }
            });
            if (BuildConfig.DEBUG) {
                builder.addInterceptor(new LoggingInterceptor.Builder()
                        .loggable(BuildConfig.DEBUG)
                        .setLevel(Level.BASIC)
                        .log(Platform.INFO)
                        .request("Request")
                        .response("Response")
                        .addHeader("version", BuildConfig.VERSION_NAME)
                        .build());
            }
            return builder.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}