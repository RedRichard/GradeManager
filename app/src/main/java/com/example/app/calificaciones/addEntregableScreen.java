package com.example.app.calificaciones;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.example.app.calificaciones.R;

import clases.Criterio;
import clases.Entregable;

/**
 * Created by gomri on 12/2/2017.
 */

public class addEntregableScreen extends Activity{
    private String entregableName = "";
    private float calificacionEntregable = 0;
    private Entregable entregable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_entregable);

    }

    public void onSendEntregable(View view) {
        EditText entregableNameET = (EditText) findViewById(R.id.nombre_entregable);
        EditText calificacionET = (EditText) findViewById(R.id.calificacion_entregable);

        setDatosEntregable(entregableNameET, calificacionET);

        entregable = new Entregable(entregableName, calificacionEntregable);

        Intent returnIntent = new Intent();
        returnIntent.putExtra("resultNewEntregable", entregable);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

    public void setDatosEntregable(EditText nombre, EditText cal){
        entregableName = nombre.getText().toString();
        try {
            calificacionEntregable = Float.parseFloat(cal.getText().toString());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
