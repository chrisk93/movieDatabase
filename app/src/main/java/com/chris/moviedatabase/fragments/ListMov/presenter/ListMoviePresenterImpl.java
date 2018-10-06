package com.chris.moviedatabase.fragments.ListMov.presenter;

import android.app.Activity;

import com.chris.moviedatabase.fragments.ListMov.model.ListMovieModel;
import com.chris.moviedatabase.fragments.ListMov.model.ListMovieModelImpl;
import com.chris.moviedatabase.fragments.ListMov.view.ListMovieView;
import com.chris.moviedatabase.vo.MovieVO;

import java.util.ArrayList;

public class ListMoviePresenterImpl implements ListMoviePresenter {

    private Activity activity;
    private ListMovieModel model;
    private ListMovieView view;

    public ListMoviePresenterImpl(Activity activity,ListMovieView view) {
        this.activity = activity;
        this.view = view;
        model = new ListMovieModelImpl(activity,this);
    }

    @Override
    public void mostrarDatos(ArrayList<MovieVO> movieVOS) {
        view.inicializaAdapter(movieVOS);

    }

    @Override
    public void mostrarMensaje(String mensaje) {
        view.muestraMensaje(mensaje);

    }

    @Override
    public void solicitaMovies(String query) {
        model.listaMovies(query);
    }

    @Override
    public void showProgress() {
        view.showProgressDialog();
    }

    @Override
    public void dismissProgress() {
        view.dismissProgressDialog();
    }
}
