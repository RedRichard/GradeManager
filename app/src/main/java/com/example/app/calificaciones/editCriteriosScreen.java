package com.example.app.calificaciones;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import clases.Criterio;
import clases.Entregable;

/**
 * Created by gomri on 12/3/2017.
 */

public class editCriteriosScreen extends Activity{
    private String criterioName;
    private float porcentaje;
    private Criterio criterio;

    EditText criterioNameET;
    EditText porcentajeET;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        criterio = (Criterio) getIntent().getSerializableExtra("objCriterio");
        setContentView(R.layout.edit_criterio);

        porcentaje = criterio.getPercentageValue();

        criterioNameET = (EditText) findViewById(R.id.nuevo_nombre_criterio);
        porcentajeET = (EditText) findViewById(R.id.nuevo_porcentaje_criterio);

        criterioNameET.setText(criterio.getName());
        porcentajeET.setText(Float.toString(criterio.getPercentageValue()));
    }

    public void onSendCriterio(View view) {
        //EditText entregableNameET = (EditText) findViewById(R.id.nuevo_nombre_entregable);
        //EditText calificacionET = (EditText) findViewById(R.id.nueva_calificacion_entregable);

        //criterioName = criterioNameET.getText().toString();
        //porcentaje = Float.parseFloat(porcentajeET.getText().toString());

        //entregable = new Entregable(entregableName, calificacionEntregable);

        setDatosCriterio(criterioNameET, porcentajeET);

        criterio.setName(criterioName);
        criterio.setPercentageValue(porcentaje);

        Intent returnIntent = new Intent();
        returnIntent.putExtra("resultEditCriterio", criterio);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

    public void setDatosCriterio(EditText nombre, EditText cal){
        criterioName = nombre.getText().toString();
        try {
            porcentaje = Float.parseFloat(cal.getText().toString());
        } catch (Exception e){
            e.printStackTrace();
            InvalidData.printMessage(getApplicationContext());
        }
    }
}
