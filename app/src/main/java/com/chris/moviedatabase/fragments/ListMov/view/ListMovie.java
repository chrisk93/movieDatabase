package com.chris.moviedatabase.fragments.ListMov.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.chris.moviedatabase.R;
import com.chris.moviedatabase.adapters.AdapterMovies;
import com.chris.moviedatabase.fragments.ListMov.presenter.ListMoviePresenter;
import com.chris.moviedatabase.fragments.ListMov.presenter.ListMoviePresenterImpl;
import com.chris.moviedatabase.vo.MovieVO;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ListMovie extends Fragment implements ListMovieView {

    @BindView(R.id.recyclerMovies) RecyclerView recyclerMovies;
    @BindView(R.id.textData) EditText textData;
    @BindView(R.id.noData) LinearLayout noData;

    ListMoviePresenter presenter;
    ProgressDialog progressDialog;



    AdapterMovies adapterMovies;
    ArrayList<MovieVO> movieVOS;

    Unbinder unbinder;


    public ListMovie() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_movie, container, false);

        unbinder = ButterKnife.bind(this, view);

        progressDialog = new ProgressDialog(getContext());

        presenter = new ListMoviePresenterImpl(getActivity(), this);

        movieVOS = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerMovies.setLayoutManager(linearLayoutManager);
        recyclerMovies.setHasFixedSize(true);

        adapterMovies = new AdapterMovies(movieVOS, getActivity());
        recyclerMovies.setAdapter(adapterMovies);

        textData.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() != 0) {
                    presenter.solicitaMovies(s.toString());


                }
            }
        });

        return view;
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
    public void inicializaAdapter(ArrayList<MovieVO> movieVOS1) {
        movieVOS.clear();
        movieVOS.addAll(movieVOS1);
        //adapterMovies = new AdapterMovies(movieVOS1, getActivity());
        //recyclerMovies.setAdapter(adapterMovies);
        adapterMovies.notifyDataSetChanged();

        validaNohayDatos();
    }

    @Override
    public void validaNohayDatos() {
        recyclerMovies.setVisibility(adapterMovies.getItemCount() == 0 ? View.GONE: View.VISIBLE);
        noData.setVisibility(adapterMovies.getItemCount() == 0 ? View.VISIBLE: View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
