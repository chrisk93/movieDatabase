package com.chris.moviedatabase.fragments.ListMov.view;

import android.app.ProgressDialog;
import android.os.Bundle;
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

public class ListMovie extends Fragment implements ListMovieView {

    RecyclerView recyclerMovies;
    ListMoviePresenter presenter;
    EditText textData;
    ProgressDialog progressDialog;
    LinearLayout noData;


    AdapterMovies adapterMovies;

    public ListMovie() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_movie, container, false);

        progressDialog = new ProgressDialog(getContext());

        noData = (LinearLayout) view.findViewById(R.id.noData);
        recyclerMovies = (RecyclerView) view.findViewById(R.id.recyclerMovies);
        textData = (EditText) view.findViewById(R.id.textData);

        presenter = new ListMoviePresenterImpl(getActivity(), this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerMovies.setLayoutManager(linearLayoutManager);
        recyclerMovies.setHasFixedSize(true);

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
        adapterMovies = new AdapterMovies(movieVOS1, getActivity());
        recyclerMovies.setAdapter(adapterMovies);

        validaNohayDatos();
    }

    @Override
    public void validaNohayDatos() {
        recyclerMovies.setVisibility(adapterMovies.getItemCount() == 0 ? View.GONE: View.VISIBLE);
        noData.setVisibility(adapterMovies.getItemCount() == 0 ? View.VISIBLE: View.GONE);
    }


}
