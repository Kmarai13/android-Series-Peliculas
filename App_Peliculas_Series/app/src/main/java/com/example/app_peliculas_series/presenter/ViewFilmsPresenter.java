package com.example.app_peliculas_series.presenter;

import android.app.ProgressDialog;
import android.content.Context;

import androidx.annotation.NonNull;

import com.example.app_peliculas_series.R;
import com.example.app_peliculas_series.presenter.callbacks.AccessTokenListener;
import com.example.app_peliculas_series.server.WebServices;
import com.example.app_peliculas_series.server.json.accesstoken.AccessTokenRequest;
import com.example.app_peliculas_series.server.json.accesstoken.AccessTokentResponse;
import com.example.app_peliculas_series.server.json.getlist.GetListRequest;
import com.example.app_peliculas_series.server.json.getlist.GetListResponse;
import com.example.app_peliculas_series.server.json.requesttoken.RequestTokenRequest;
import com.example.app_peliculas_series.server.json.requesttoken.RequestTokenResponse;
import com.example.app_peliculas_series.utils.SingletonPrefs;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewFilmsPresenter {

    private Context ctx;
    private AccessTokenListener accessTokenListener;
    private ProgressDialog mProgressDialog;
    private String wsConsulted;
    private SingletonPrefs singletonPrefs = SingletonPrefs.getInstance();


    public ViewFilmsPresenter(Context ctx,
                              AccessTokenListener accessTokenListener) {
        this.ctx = ctx;
        this.accessTokenListener = accessTokenListener;
    }

    public void sendTokenAcccess() {
        AccessTokenRequest request = new AccessTokenRequest();
        request.request_token = singletonPrefs.requestTokenResponse.request_token;
        sendRequestTokenAccess(request);
    }

    private void sendRequestTokenAccess(AccessTokenRequest request) {
        Call<AccessTokentResponse> mFolioCall = WebServices.services().getCAccessToken(request);
        mFolioCall.enqueue(new Callback<AccessTokentResponse>() {
            @Override
            public void onResponse(@NonNull Call<AccessTokentResponse> call,
                                   @NonNull Response<AccessTokentResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        checkResponseTokenAccess(response.body());
                    } else {
                        sendError(wsConsulted);
                    }
                } else
                    sendError(wsConsulted);
            }

            @Override
            public void onFailure(@NonNull Call<AccessTokentResponse> call, @NonNull Throwable t) {
                sendError(wsConsulted);
            }
        });
    }

    private void checkResponseTokenAccess(AccessTokentResponse body) {
        if (body.success && body.status_code == 1) {
            accessTokenListener.onSuccessAccessToken(body);
//            quitWaitDialog();
        } else {
            sendError(wsConsulted);
        }
    }

    public void sendRequestToken() {
        RequestTokenRequest request = new RequestTokenRequest();
        request.redirect_to = "http://www.themoviedb.org/";
        sendRequestToken(request);
    }

    private void sendRequestToken(RequestTokenRequest request) {
        Call<RequestTokenResponse> mFolioCall = WebServices.services().getRequestToken(request);
        mFolioCall.enqueue(new Callback<RequestTokenResponse>() {
            @Override
            public void onResponse(@NonNull Call<RequestTokenResponse> call,
                                   @NonNull Response<RequestTokenResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().success && response.body().status_code == 1) {
                            accessTokenListener.onSuccessRequestToken(response.body());
//            quitWaitDialog();
                        } else {
                            sendError(wsConsulted);
                        }
                    } else {
                        sendError(wsConsulted);
                    }
                } else
                    sendError(wsConsulted);
            }

            @Override
            public void onFailure(@NonNull Call<RequestTokenResponse> call, @NonNull Throwable t) {
                sendError(wsConsulted);
            }
        });
    }

    public void sendGetListTo() {
        sendGetList();
    }

        private void sendGetList() {
        Call<GetListResponse> mFolioCall = WebServices.services().getList(singletonPrefs.accessTokentResponse.account_id);
        mFolioCall.enqueue(new Callback<GetListResponse>() {
            @Override
            public void onResponse(@NonNull Call<GetListResponse> call,
                                   @NonNull Response<GetListResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                            accessTokenListener.onSuccessGetList(response.body());
//            quitWaitDialog();
                    } else {
                        sendError(wsConsulted);
                    }
                } else
                    sendError(wsConsulted);
            }

            @Override
            public void onFailure(@NonNull Call<GetListResponse> call, @NonNull Throwable t) {
                sendError(wsConsulted);
            }
        });
    }


    public void sendGetListMovie() {
        Call<GetListResponse> mFolioCall = WebServices.services().getListMovie();
        mFolioCall.enqueue(new Callback<GetListResponse>() {
            @Override
            public void onResponse(@NonNull Call<GetListResponse> call,
                                   @NonNull Response<GetListResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        accessTokenListener.onSuccessGetList(response.body());
//            quitWaitDialog();
                    } else {
                        sendError(wsConsulted);
                    }
                } else
                    sendError(wsConsulted);
            }

            @Override
            public void onFailure(@NonNull Call<GetListResponse> call, @NonNull Throwable t) {
                sendError(wsConsulted);
            }
        });
    }



    private void sendError(String wsConsulted) {
//        quitWaitDialog();
        accessTokenListener.onFailedService(ctx.getResources().getString(R.string.ws_error) + " " +
                wsConsulted + " " +
                ctx.getResources().getString(R.string.ws_error_two));
    }

    private void showWaitDialog() {
        if (mProgressDialog == null)
            mProgressDialog = new ProgressDialog(ctx);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage(ctx.getResources().getString(R.string.ws_wait));
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    private void quitWaitDialog() {
        if (mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }
}
