package com.chris.moviedatabase.activities.Detalle.presenter;

import android.app.Activity;

import com.chris.moviedatabase.activities.Detalle.model.DetalleModel;
import com.chris.moviedatabase.activities.Detalle.model.DetalleModelImpl;
import com.chris.moviedatabase.activities.Detalle.view.DetalleView;

import java.util.HashMap;

public class DetallePresenterImpl implements DetallePresenter {

    private Activity activity;
    private DetalleView view;
    private DetalleModel model;

    public DetallePresenterImpl(Activity activity, DetalleView view) {
        this.activity = activity;
        this.view = view;
        model = new DetalleModelImpl(activity,this);
    }

    @Override
    public void cargarDatos(HashMap<String, String> datos) {
        view.muestraDatos(datos);
    }

    @Override
    public void mostrarMensaje(String mensaje) {

        view.muestraMensaje(mensaje);

    }

    @Override
    public void dismissProgress() {

        view.dismissProgressDialog();

    }

    @Override
    public void showProgress() {
        view.showProgressDialog();

    }

    @Override
    public void solitaInfo(String id) {
        model.muestraMovie(id);
    }

    @Override
    public void agregarBD(HashMap<String,String> datos) {
        model.agregaFavoritos(datos);
    }


}
