package com.example.app.calificaciones;

import android.app.Activity;
import android.content.Intent;
import android.drm.DrmStore;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.app.calificaciones.R;

import clases.Criterio;
import clases.Materia;

/**
 * Created by gomri on 12/2/2017.
 */

public class addCriterioScreen extends Activity{
    private String criterioName;
    private float criterioPorcentaje;
    private Criterio criterio;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_criterio);

    }

    public void onSendCriterio(View view) {
        EditText matNameET = (EditText) findViewById(R.id.criterio_nom);
        EditText matPorcentajeET = (EditText) findViewById(R.id.porcentaje_calificacion);

        setDatosCriterio(matNameET, matPorcentajeET);

        criterio = new Criterio(criterioName, criterioPorcentaje);

        Intent returnIntent = new Intent();
        returnIntent.putExtra("resultNewCriterio", criterio);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

    public void setDatosCriterio(EditText nombre, EditText cal){
        criterioName = nombre.getText().toString();
        try {
            criterioPorcentaje = Float.parseFloat(cal.getText().toString());
        } catch (Exception e){
            e.printStackTrace();
            InvalidData.printMessage(getApplicationContext());
        }
    }

}
