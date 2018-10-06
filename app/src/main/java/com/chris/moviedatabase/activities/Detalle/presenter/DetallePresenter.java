package com.chris.moviedatabase.activities.Detalle.presenter;

import java.util.HashMap;

public interface DetallePresenter {

    void cargarDatos(HashMap<String,String> datos);
    void mostrarMensaje(String mensaje);
    void dismissProgress();
    void showProgress();

    void solitaInfo(String id);

    void agregarBD(HashMap<String,String> datos);
}
