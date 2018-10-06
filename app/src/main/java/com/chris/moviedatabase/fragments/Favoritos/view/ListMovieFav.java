package com.chris.moviedatabase.fragments.Favoritos.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.chris.moviedatabase.R;
import com.chris.moviedatabase.adapters.AdapterMovies;
import com.chris.moviedatabase.adapters.AdapterMoviesFav;
import com.chris.moviedatabase.fragments.Favoritos.presenter.ListFavPresenter;
import com.chris.moviedatabase.fragments.Favoritos.presenter.ListFavPresenterImpl;
import com.chris.moviedatabase.fragments.ListMov.presenter.ListMoviePresenter;
import com.chris.moviedatabase.fragments.ListMov.presenter.ListMoviePresenterImpl;
import com.chris.moviedatabase.vo.MovieFavVO;
import com.chris.moviedatabase.vo.MovieVO;

import java.util.ArrayList;


public class ListMovieFav extends Fragment implements ListFavView{

    RecyclerView recyclerFav;
    ListFavPresenter presenter;
    ProgressDialog progressDialog;
    LinearLayout noData;

    AdapterMoviesFav adapterMovies;

    public ListMovieFav() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_list_movie_fav, container, false);


        progressDialog = new ProgressDialog(getContext());

        noData = (LinearLayout) view.findViewById(R.id.noData);
        recyclerFav = (RecyclerView) view.findViewById(R.id.recyclerFav);

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
    public void inicializaAdapter(ArrayList<MovieFavVO> movieVOS) {
        //ArrayList<MovieVO> movieVOS = new ArrayList<>();
        adapterMovies = new AdapterMoviesFav(movieVOS, getActivity(),this);
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


}
