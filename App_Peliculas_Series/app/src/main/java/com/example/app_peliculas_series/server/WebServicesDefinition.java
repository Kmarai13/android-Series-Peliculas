package com.example.app_peliculas_series.server;

import com.example.app_peliculas_series.server.json.accesstoken.AccessTokenRequest;
import com.example.app_peliculas_series.server.json.accesstoken.AccessTokentResponse;
import com.example.app_peliculas_series.server.json.getlist.GetListRequest;
import com.example.app_peliculas_series.server.json.getlist.GetListResponse;
import com.example.app_peliculas_series.server.json.requesttoken.RequestTokenRequest;
import com.example.app_peliculas_series.server.json.requesttoken.RequestTokenResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebServicesDefinition {

    @POST("/4/auth/request_token")
    Call<RequestTokenResponse> getRequestToken(@Body RequestTokenRequest rqt);

    @POST("/4/auth/access_token")
    Call<AccessTokentResponse> getCAccessToken(@Body AccessTokenRequest rqt);

    @GET("/4/account/{account_id}/lists")
    Call<GetListResponse> getList(@Path("account_id") String account_id);


}
