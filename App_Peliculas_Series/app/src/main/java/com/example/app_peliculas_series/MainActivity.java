package com.example.app_peliculas_series;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
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
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import com.example.app_peliculas_series.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity implements AccessTokenListener, PeliculasSeriesAdapter.OnItemClickListener {

    private ViewFilmsPresenter viewFilmsPresenter;
    //protected Button btnAcept, btnContinuar;
//    protected TextView textView;
    private SingletonPrefs singletonPrefs;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        getPresenter();
        singletonPrefs = SingletonPrefs.getInstance();
        initView();
    }
    private void initView() {
//        btnAcept = findViewById(R.id.btnAcept);
//        btnContinuar = findViewById(R.id.btnContinuar);
//        textView = findViewById(R.id.tv_list);
//        btnAcept.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                viewFilmsPresenter.sendRequestToken();
//
//            }
//        });
//        btnContinuar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                viewFilmsPresenter.sendTokenAcccess();
//            }
//        });

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
//       textView.setText(String.valueOf(requestTokenResponse.total_results));
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}