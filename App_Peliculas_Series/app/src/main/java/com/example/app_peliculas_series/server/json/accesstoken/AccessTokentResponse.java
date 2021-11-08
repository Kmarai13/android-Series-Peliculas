package com.example.app_peliculas_series.server.json.accesstoken;

import com.google.gson.annotations.SerializedName;

public class AccessTokentResponse {

    @SerializedName("access_token")
    public String access_token;
    @SerializedName("account_id")
    public String account_id;
    @SerializedName("status_code")
    public int status_code;
    @SerializedName("status_message")
    public String status_message;
    @SerializedName("success")
    public Boolean success;
}
