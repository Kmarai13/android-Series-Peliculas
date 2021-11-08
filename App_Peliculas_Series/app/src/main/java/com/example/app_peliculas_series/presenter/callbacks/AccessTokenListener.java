package com.example.app_peliculas_series.presenter.callbacks;

import com.example.app_peliculas_series.server.json.accesstoken.AccessTokentResponse;
import com.example.app_peliculas_series.server.json.getlist.GetListResponse;
import com.example.app_peliculas_series.server.json.requesttoken.RequestTokenRequest;
import com.example.app_peliculas_series.server.json.requesttoken.RequestTokenResponse;

public interface AccessTokenListener {
    void onSuccessAccessToken(AccessTokentResponse accessTokentResponse);
    void onSuccessRequestToken(RequestTokenResponse requestTokenResponse);
    void onSuccessGetList(GetListResponse requestTokenResponse);

    void onFailedService(String message);

    void onShowAlert(String msg);

    void onShowMessage(String msg);
}
