package com.chris.moviedatabase.fragments.Favoritos.model;

import android.app.Activity;
import android.database.Cursor;

import com.chris.moviedatabase.Database;
import com.chris.moviedatabase.fragments.Favoritos.presenter.ListFavPresenter;

import com.chris.moviedatabase.network.APIService;

import com.chris.moviedatabase.vo.MovieFavVO;
import com.chris.moviedatabase.vo.MovieVO;

import java.util.ArrayList;


public class ListFavModelImpl implements ListFavModel {

    private static final String TAG = com.chris.moviedatabase.fragments.ListMov.model.ListMovieModelImpl.class.getSimpleName();
    private Activity activity;
    private ListFavPresenter presenter;
    private APIService mAPIService;

    public ListFavModelImpl(Activity activity, ListFavPresenter presenter) {
        this.activity = activity;
        this.presenter = presenter;
    }

    @Override
    public void listaMovies() {

        ArrayList<MovieFavVO> movieFavVO = new ArrayList<>();

        Database database = new Database(activity);

        database.open();

        Cursor cursor = database.read("select * from Favoritos");

        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String url = cursor.getString(cursor.getColumnIndex("url"));
            movieFavVO.add(new MovieFavVO(id,name,url));
        }

        database.close();

        presenter.mostrarDatos(movieFavVO);

    }

    @Override
    public void delete(int item){

        Database database = new Database(activity);

        database.open();
        database.delete("Favoritos","id="+item);
        database.close();

        listaMovies();

        presenter.actualizaTabla();
    }

}

