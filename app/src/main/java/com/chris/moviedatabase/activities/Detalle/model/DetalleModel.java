package com.chris.moviedatabase.activities.Detalle.model;

import java.util.HashMap;

public interface DetalleModel {
    void muestraMovie(String id_movie);

    void agregaFavoritos(HashMap<String,String> datos);
}
