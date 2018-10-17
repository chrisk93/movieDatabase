package com.chris.moviedatabase.fragments.Favoritos.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.chris.moviedatabase.R;
import com.chris.moviedatabase.adapters.AdapterMoviesFav;
import com.chris.moviedatabase.fragments.Favoritos.presenter.ListFavPresenter;
import com.chris.moviedatabase.fragments.Favoritos.presenter.ListFavPresenterImpl;
import com.chris.moviedatabase.vo.MovieFavVO;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class ListMovieFav extends Fragment implements ListFavView{

    @BindView(R.id.recyclerFav) RecyclerView recyclerFav;
    @BindView(R.id.noData) LinearLayout noData;
    private Unbinder unbinder;

    ListFavPresenter presenter;
    ProgressDialog progressDialog;
    AdapterMoviesFav adapterMovies;

    public ListMovieFav() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_list_movie_fav, container, false);

        unbinder = ButterKnife.bind(this, view);

        progressDialog = new ProgressDialog(getContext());

        presenter = new ListFavPresenterImpl(getActivity(),this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerFav.setLayoutManager(linearLayoutManager);

        //presenter.solicitaMovies();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.solicitaMovies();
    }

    @Override
    public void showProgressDialog() {
        progressDialog.setMessage(getResources().getString(R.string.msgProgressDialog));
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
    }

    @Override
    public void muestraMensaje(String mensaje) {
        Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dismissProgressDialog() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void inicializaAdapter(ArrayList<MovieFavVO> movieVOS1) {
        adapterMovies = new AdapterMoviesFav(movieVOS1, getActivity(),this);
        recyclerFav.setHasFixedSize(true);
        recyclerFav.setAdapter(adapterMovies);

        validaNohayDatos();
    }

    @Override
    public void validaNohayDatos() {
        recyclerFav.setVisibility(adapterMovies.getItemCount() == 0 ? View.GONE: View.VISIBLE);
        noData.setVisibility(adapterMovies.getItemCount() == 0 ? View.VISIBLE: View.GONE);
    }

    @Override
    public void actualizaLista() {
        adapterMovies.notifyDataSetChanged();
    }

    @Override
    public void eliminaDato(int movieFavVO) {
        presenter.eliminaRegistro(movieFavVO);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
