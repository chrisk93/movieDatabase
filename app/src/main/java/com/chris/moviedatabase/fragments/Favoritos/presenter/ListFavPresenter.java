package com.chris.moviedatabase.fragments.Favoritos.presenter;

import com.chris.moviedatabase.vo.MovieFavVO;

import java.util.ArrayList;

public interface ListFavPresenter {

    void mostrarMensaje(String mensaje);
    void solicitaMovies();
    void actualizaTabla();

    void showProgress();
    void dismissProgress();

    void mostrarDatos(ArrayList<MovieFavVO> movieVOS);

    void eliminaRegistro(int movieFavVO);
}

