package com.chris.moviedatabase.activities.Detalle.view;

import java.util.HashMap;

public interface DetalleView {

    void showProgressDialog();
    void muestraMensaje(String mensaje);
    void dismissProgressDialog();
    void muestraDatos(HashMap<String,String> datos);
}
