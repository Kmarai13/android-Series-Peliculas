package com.example.app_peliculas_series;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.example.app_peliculas_series.adapters.PeliculasSeriesAdapter;
import com.example.app_peliculas_series.presenter.ViewFilmsPresenter;
import com.example.app_peliculas_series.presenter.callbacks.AccessTokenListener;
import com.example.app_peliculas_series.server.json.accesstoken.AccessTokentResponse;
import com.example.app_peliculas_series.server.json.getlist.GetListResponse;
import com.example.app_peliculas_series.server.json.requesttoken.RequestTokenResponse;
import com.example.app_peliculas_series.utils.SingletonPrefs;

public class MainActivity extends AppCompatActivity implements AccessTokenListener, PeliculasSeriesAdapter.OnItemClickListener {

    private ViewFilmsPresenter viewFilmsPresenter;
    protected Button btnAcept, btnContinuar;
    protected TextView textView;
    private SingletonPrefs singletonPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPresenter();
        singletonPrefs = SingletonPrefs.getInstance();
        initView();
    }
    private void initView() {
        btnAcept = findViewById(R.id.btnAcept);
        btnContinuar = findViewById(R.id.btnContinuar);
        textView = findViewById(R.id.tv_list);
        btnAcept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFilmsPresenter.sendRequestToken();

            }
        });
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFilmsPresenter.sendTokenAcccess();
            }
        });

    }

    protected ViewFilmsPresenter getPresenter() {
        return viewFilmsPresenter = new ViewFilmsPresenter(this, this);
    }

    @Override
    public void onSuccessAccessToken(AccessTokentResponse accessTokentResponse) {
        singletonPrefs.accessTokentResponse = accessTokentResponse;
        viewFilmsPresenter.sendGetListTo();
    }

    @Override
    public void onSuccessRequestToken(RequestTokenResponse requestTokenResponse) {
        singletonPrefs.requestTokenResponse = requestTokenResponse;
        String url = "https://www.themoviedb.org/auth/access?request_token="+ requestTokenResponse.request_token;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    @Override
    public void onSuccessGetList(GetListResponse requestTokenResponse) {
       textView.setText(String.valueOf(requestTokenResponse.total_results));
    }

    @Override
    public void onFailedService(String message) {

    }

    @Override
    public void onShowAlert(String msg) {

    }

    @Override
    public void onShowMessage(String msg) {

    }

    @Override
    protected void onResume() {
        super.onResume();
//        viewFilmsPresenter.sendRequestToken();

    }
}