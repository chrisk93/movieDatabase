package com.chris.moviedatabase.fragments.ListMov.presenter;

import com.chris.moviedatabase.vo.MovieVO;

import java.util.ArrayList;

public interface ListMoviePresenter {

    void mostrarDatos(ArrayList<MovieVO> movieVOS);
    void mostrarMensaje(String mensaje);
    void solicitaMovies(String query);

    void showProgress();
    void dismissProgress();
}
