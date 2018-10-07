package com.chris.moviedatabase.activities.Detalle.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chris.moviedatabase.R;
import com.chris.moviedatabase.activities.Detalle.presenter.DetallePresenter;
import com.chris.moviedatabase.activities.Detalle.presenter.DetallePresenterImpl;
import com.chris.moviedatabase.utils.Constants;

import java.util.HashMap;

public class DetalleActivity extends AppCompatActivity implements DetalleView, View.OnClickListener {

    private TextView textnombre;
    private TextView textId;
    private TextView textOverview;
    ProgressDialog progressDialog;
    DetallePresenter presenter;
    Button btnAgregar;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        textnombre = (TextView) findViewById(R.id.textnombre);
        textId = (TextView) findViewById(R.id.textId);
        textOverview = (TextView) findViewById(R.id.textOverview);
        btnAgregar = (Button) findViewById(R.id.btnAgregar);

        btnAgregar.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);

        presenter = new DetallePresenterImpl(this,this);

       // Intent intent = getIntent();
       // String id = intent.getStringExtra("id_movie");

        intentInicial();


    }

    @Override
    public void muestraDatos(HashMap<String,String> datos){
        textnombre.setText(datos.get("name"));
        textId.setText(datos.get("id"));
        textOverview.setText(datos.get("overview"));
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
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dismissProgressDialog() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }


    public void intentInicial(){
        Bundle intent = getIntent().getExtras();

        if(intent == null){
            id = "195884";
        }else{
            id = intent.getString("id_movie");
        }

        presenter.solitaInfo(id);
    }

    public void agregarFav(){

        HashMap<String,String> datos = new HashMap<>();
        datos.put("url", Constants.URL_IMAGE);
        datos.put("id",id);
        datos.put("name",textnombre.getText().toString());
        datos.put("overview",textOverview.getText().toString());
        presenter.agregarBD(datos);
    }

    @Override
    public void onClick(View v) {
        if (v == btnAgregar){
            agregarFav();
        }
    }
}
