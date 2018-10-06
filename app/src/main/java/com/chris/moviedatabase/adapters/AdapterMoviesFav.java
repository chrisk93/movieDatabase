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
import com.chris.moviedatabase.fragments.Favoritos.view.ListFavView;
import com.chris.moviedatabase.vo.MovieFavVO;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterMoviesFav extends RecyclerView.Adapter<AdapterMoviesFav.ViewHolder> {

    private ArrayList<MovieFavVO> datosMovie;
    private Activity activity;
    private ListFavView listFavView;

    public AdapterMoviesFav(ArrayList<MovieFavVO> datosMovie, Activity activity, ListFavView listFavView) {
        this.datosMovie = datosMovie;
        this.activity = activity;
        this.listFavView = listFavView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pelicula_fav,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final MovieFavVO movieVO = datosMovie.get(position);

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

        holder.imgdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listFavView.eliminaDato(movieVO.getId());
            }
        });


    }

    @Override
    public int getItemCount() {
        return datosMovie.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgMovie;
        ImageView imgdelete;
        TextView textDescripcion;
        CardView cardItem;

        public ViewHolder(View itemView) {
            super(itemView);
            imgMovie = (ImageView) itemView.findViewById(R.id.imgMovie);
            imgdelete = (ImageView) itemView.findViewById(R.id.imgdelete);
            textDescripcion = (TextView) itemView.findViewById(R.id.textDescripcion);
            cardItem = (CardView)  itemView.findViewById(R.id.cardItem);
        }
    }
}
