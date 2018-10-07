package com.chris.moviedatabase.activities.Detalle.model;

import android.app.Activity;
import android.content.ContentValues;
import android.util.Log;

import com.chris.moviedatabase.Database;
import com.chris.moviedatabase.R;
import com.chris.moviedatabase.activities.Detalle.presenter.DetallePresenter;
import com.chris.moviedatabase.network.APIService;
import com.chris.moviedatabase.network.ApiUtils;
import com.chris.moviedatabase.utils.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.ContentHandler;
import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class DetalleModelImpl implements DetalleModel{

    private static final String TAG = DetalleModelImpl.class.getSimpleName();
    private Activity activity;
    private DetallePresenter presenter;
    private APIService mAPIService;

    public DetalleModelImpl(Activity activity, DetallePresenter presenter) {
        this.activity = activity;
        this.presenter = presenter;
    }

    @Override
    public void muestraMovie(String id_movie) {

        presenter.showProgress();

        mAPIService = ApiUtils.getAPIService();

        //mAPIService.getDetailsMovie(id_movie,Constants.API_KEY)
        //mAPIService.getDetailsMovie(Constants.API_KEY)
        mAPIService.getDetailsMovie(id_movie)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<String>() {
                    @Override
                    public void onNext(String result) {

                        try {
                            JSONObject jsonObject = new JSONObject(result);

                                int id = jsonObject.getInt("id");
                                String name = jsonObject.getString("title");
                                String overview = jsonObject.getString("overview");

                            HashMap<String,String> datos = new HashMap<>();
                            datos.put("id",String.valueOf(id));
                            datos.put("name",name);
                            datos.put("overview",overview);

                            presenter.cargarDatos(datos);

                            }

                        catch (JSONException e) {
                            Log.e(TAG, "JSOnExc: " + e.toString());
                            presenter.mostrarMensaje(activity.getString(R.string.errorGeneral));
                        }

                        }

                    @Override
                    public void onError(Throwable e) {

                        Log.e(TAG, "onError: " + e.toString());
                        presenter.mostrarMensaje(activity.getString(R.string.errorGeneral));

                        presenter.dismissProgress();

                    }

                    @Override
                    public void onComplete() {

                        presenter.dismissProgress();

                    }
                });

    }

    @Override
    public void agregaFavoritos(HashMap<String,String> datos){
        Database database = new Database(activity);
        database.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",datos.get("id"));
        contentValues.put("url",datos.get("url"));
        contentValues.put("name",datos.get("name"));

        database.insert("Favoritos",contentValues);

        presenter.mostrarMensaje(activity.getString(R.string.agregado));

        database.close();
    }

}
