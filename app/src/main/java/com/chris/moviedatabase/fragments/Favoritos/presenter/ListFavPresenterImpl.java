package com.chris.moviedatabase.fragments.Favoritos.presenter;

import android.app.Activity;

import com.chris.moviedatabase.fragments.Favoritos.model.ListFavModel;
import com.chris.moviedatabase.fragments.Favoritos.model.ListFavModelImpl;
import com.chris.moviedatabase.fragments.Favoritos.view.ListFavView;
import com.chris.moviedatabase.vo.MovieFavVO;
import com.chris.moviedatabase.vo.MovieVO;

import java.util.ArrayList;

public class ListFavPresenterImpl implements ListFavPresenter {

    private Activity activity;
    private ListFavModel model;
    private ListFavView view;

    public ListFavPresenterImpl(Activity activity, ListFavView view) {
        this.activity = activity;
        this.view = view;
        model = new ListFavModelImpl(activity,this);
    }

    @Override
    public void eliminaRegistro(int movieFavVO) {
        model.delete(movieFavVO);
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        view.muestraMensaje(mensaje);
    }


    @Override
    public void solicitaMovies() {
        model.listaMovies();
    }

    @Override
    public void actualizaTabla() {
        view.actualizaLista();
    }

    @Override
    public void showProgress() {
        view.showProgressDialog();
    }

    @Override
    public void dismissProgress() {
        view.dismissProgressDialog();
    }

    @Override
    public void mostrarDatos(ArrayList<MovieFavVO> movieVOS) {
        view.inicializaAdapter(movieVOS);
    }
}
