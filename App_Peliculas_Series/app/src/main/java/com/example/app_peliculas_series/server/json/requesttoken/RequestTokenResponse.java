package com.example.app_peliculas_series.server.json.requesttoken;

import com.google.gson.annotations.SerializedName;

public class RequestTokenResponse {

    @SerializedName("request_token")
    public String request_token;
    @SerializedName("status_code")
    public int status_code;
    @SerializedName("status_message")
    public String status_message;
    @SerializedName("success")
    public Boolean success;

}
