package com.example.app_peliculas_series.views.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_peliculas_series.adapters.PeliculasSeriesAdapter;
import com.example.app_peliculas_series.adapters.PeliculasSeriesPersonalizadoAdapter;
import com.example.app_peliculas_series.database.MoviesDataBase;
import com.example.app_peliculas_series.database.dao.MoviesDao;
import com.example.app_peliculas_series.database.entity.MovieEntity;
import com.example.app_peliculas_series.database.repository.MovieRepository;
import com.example.app_peliculas_series.database.repository.MovieRepositoryImpl;
import com.example.app_peliculas_series.databinding.FragmentHomeBinding;
import com.example.app_peliculas_series.model.PeliculasBean;
import com.example.app_peliculas_series.presenter.ViewFilmsPresenter;
import com.example.app_peliculas_series.presenter.callbacks.AccessTokenListener;
import com.example.app_peliculas_series.server.json.accesstoken.AccessTokentResponse;
import com.example.app_peliculas_series.server.json.getlist.GetListResponse;
import com.example.app_peliculas_series.server.json.getlist.ListFilms;
import com.example.app_peliculas_series.server.json.requesttoken.RequestTokenResponse;
import com.example.app_peliculas_series.utils.SingletonPrefs;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements AccessTokenListener, PeliculasSeriesAdapter.OnItemClickListener {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private ViewFilmsPresenter viewFilmsPresenter;
    protected TextView textView;
    private SingletonPrefs singletonPrefs;
    ArrayList<PeliculasBean> listMovie;
    protected RecyclerView recyclerViewMovie;
    private PeliculasBean peliculasBean;
    private MoviesDataBase db;
    private MoviesDao dao;
    MovieEntity movieEntity;
    MovieRepository repository;

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
        listMovie = new ArrayList<>();
        peliculasBean = new PeliculasBean();
        db = MoviesDataBase.getInstance(getContext());
        dao = db.moviesDao();
        repository = new MovieRepositoryImpl(dao);
        movieEntity = new MovieEntity();
        recyclerViewMovie = binding.recyclerMovie;
        recyclerViewMovie.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
//        recyclerViewMovie.setLayoutManager(new GridLayoutManager(getContext(), 2));
        viewFilmsPresenter.sendGetListMovie();

        llenarPelculas(peliculasBean);

    }

    protected ViewFilmsPresenter getPresenter() {
        return viewFilmsPresenter = new ViewFilmsPresenter(getContext(), this);
    }

    private void llenarPelculas(PeliculasBean peliBean) {
        List<MovieEntity> movieEntityList = repository.getAllMovies();
        for (MovieEntity movieEntity : movieEntityList) {
            peliculasBean.setTitulo(movieEntity.getTitle());
            peliculasBean.setDescripcion(movieEntity.getDescripcion());
            peliculasBean.setPoster(movieEntity.getImagen());
            listMovie.add(new PeliculasBean(peliBean.getTitulo(), peliBean.getDescripcion(), peliBean.getPoster()));
            PeliculasSeriesPersonalizadoAdapter adapter = new PeliculasSeriesPersonalizadoAdapter(listMovie);
            recyclerViewMovie.setAdapter(adapter);
        }
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
        String url = "https://www.themoviedb.org/auth/access?request_token=" + requestTokenResponse.request_token;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    @Override
    public void onSuccessGetList(GetListResponse requestTokenResponse) {
        singletonPrefs.getListResponse = requestTokenResponse;

        for (ListFilms listFilms : requestTokenResponse.results) {
            movieEntity.setMovieId(listFilms.id);
            movieEntity.setTitle(listFilms.title);
            movieEntity.setDescripcion(listFilms.overview);
            movieEntity.setImagen(listFilms.posterPath);
            repository.insertMovie(movieEntity);
        }

        llenarPelculas(peliculasBean);

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