package com.chris.moviedatabase.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chris.moviedatabase.activities.Detalle.view.DetalleActivity;
import com.chris.moviedatabase.R;
import com.chris.moviedatabase.vo.MovieVO;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterMovies extends RecyclerView.Adapter<AdapterMovies.ViewHolder> {

    private ArrayList<MovieVO> datosMovie;
    private Activity activity;

    public AdapterMovies(ArrayList<MovieVO> datosMovie, Activity activity) {
        this.datosMovie = datosMovie;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pelicula,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final MovieVO movieVO = datosMovie.get(position);

        holder.textDescripcion.setText(movieVO.getId() + "\n "+ movieVO.getName());
        Picasso.get().load(movieVO.getUrl()).into(holder.imgMovie);

        holder.cardItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, DetalleActivity.class);
                intent.putExtra("id_movie",String.valueOf(movieVO.getId()));
                activity.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        int size;

        if(datosMovie != null && !datosMovie.isEmpty()){
            size = datosMovie.size();
        }else{
            size = 0;
        }

        return size;
        //return datosMovie.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.imgMovie) ImageView imgMovie;
        @BindView(R.id.textDescripcion) TextView textDescripcion;
        @BindView(R.id.cardItem) CardView cardItem;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
