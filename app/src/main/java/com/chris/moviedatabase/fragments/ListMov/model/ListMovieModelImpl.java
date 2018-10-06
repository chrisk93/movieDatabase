package com.chris.moviedatabase.fragments.ListMov.model;

import android.app.Activity;
import android.util.Log;

import com.chris.moviedatabase.R;
import com.chris.moviedatabase.fragments.ListMov.presenter.ListMoviePresenter;
import com.chris.moviedatabase.network.APIService;
import com.chris.moviedatabase.network.ApiUtils;
import com.chris.moviedatabase.utils.Constants;
import com.chris.moviedatabase.vo.MovieVO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ListMovieModelImpl implements ListMovieModel{

    private static final String TAG = ListMovieModelImpl.class.getSimpleName();
    private Activity activity;
    private ListMoviePresenter presenter;
    private APIService mAPIService;

    public ListMovieModelImpl(Activity activity, ListMoviePresenter presenter) {
        this.activity = activity;
        this.presenter = presenter;
    }

    @Override
    public void listaMovies(String query) {

        presenter.showProgress();

        mAPIService = ApiUtils.getAPIService();

        mAPIService.searchKeyWord(Constants.API_KEY,query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<String>() {
                    @Override
                    public void onNext(String result) {

                        try {
                            JSONObject jsonObject = new JSONObject(result);

                            JSONArray jsonArray = jsonObject.getJSONArray("results");

                            ArrayList<MovieVO> movieVOS = new ArrayList<>();

                            for(int i = 0; i < jsonArray.length(); i++){


                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                int id = jsonObject1.getInt("id");
                                String name = jsonObject1.getString("name");

                                movieVOS.add(new MovieVO(id,name,Constants.URL_IMAGE));
                            }

                            presenter.mostrarDatos(movieVOS);

                        } catch (JSONException e) {
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
}
