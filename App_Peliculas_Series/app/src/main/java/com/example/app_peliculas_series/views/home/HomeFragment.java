package com.example.app_peliculas_series.views.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.app_peliculas_series.R;
import com.example.app_peliculas_series.adapters.PeliculasSeriesAdapter;
import com.example.app_peliculas_series.databinding.FragmentHomeBinding;
import com.example.app_peliculas_series.presenter.ViewFilmsPresenter;
import com.example.app_peliculas_series.presenter.callbacks.AccessTokenListener;
import com.example.app_peliculas_series.server.json.accesstoken.AccessTokentResponse;
import com.example.app_peliculas_series.server.json.getlist.GetListResponse;
import com.example.app_peliculas_series.server.json.requesttoken.RequestTokenResponse;
import com.example.app_peliculas_series.utils.SingletonPrefs;

public class HomeFragment extends Fragment implements AccessTokenListener, PeliculasSeriesAdapter.OnItemClickListener {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private ViewFilmsPresenter viewFilmsPresenter;
    protected Button btnAcept, btnContinuar;
    protected TextView textView;
    private SingletonPrefs singletonPrefs;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        getPresenter();
        singletonPrefs = SingletonPrefs.getInstance();
        initView();


        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    private void initView() {
        textView = binding.tvList;
        btnAcept = binding.btnAcept;
        btnContinuar = binding.btnContinuar;

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
        return viewFilmsPresenter = new ViewFilmsPresenter(getContext(), this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
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
}