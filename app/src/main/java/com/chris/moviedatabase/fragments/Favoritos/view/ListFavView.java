package com.chris.moviedatabase.fragments.Favoritos.view;

import com.chris.moviedatabase.vo.MovieFavVO;
import com.chris.moviedatabase.vo.MovieVO;

import java.util.ArrayList;

public interface ListFavView {
    void showProgressDialog();
    void muestraMensaje(String mensaje);
    void dismissProgressDialog();
    void inicializaAdapter(ArrayList<MovieFavVO> movieVOS);
    void validaNohayDatos();

    void actualizaLista();

    void eliminaDato(int movieFavVO);
}
