package com.chris.moviedatabase.fragments.ListMov.view;

import com.chris.moviedatabase.vo.MovieVO;

import java.util.ArrayList;

public interface ListMovieView {

    void showProgressDialog();
    void muestraMensaje(String mensaje);
    void dismissProgressDialog();
    void inicializaAdapter(ArrayList<MovieVO> movieVOS);
    void validaNohayDatos();
}
