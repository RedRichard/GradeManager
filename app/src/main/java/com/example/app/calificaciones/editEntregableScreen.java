package com.example.app.calificaciones;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import clases.Entregable;

/**
 * Created by gomri on 12/3/2017.
 */

public class editEntregableScreen extends Activity {
    private String entregableName;
    private float calificacionEntregable;
    private Entregable entregable;

    EditText entregableNameET;
    EditText calificacionET;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        entregable = (Entregable) getIntent().getSerializableExtra("objEntregable");
        setContentView(R.layout.edit_entregable);

        entregableNameET = (EditText) findViewById(R.id.nuevo_nombre_entregable);
        calificacionET = (EditText) findViewById(R.id.nueva_calificacion_entregable);

        entregableNameET.setText(entregable.getName());
        calificacionET.setText(Float.toString(entregable.getGrade()));
    }

    public void onSendEntregable(View view) {
        //EditText entregableNameET = (EditText) findViewById(R.id.nuevo_nombre_entregable);
        //EditText calificacionET = (EditText) findViewById(R.id.nueva_calificacion_entregable);

        entregableName = entregableNameET.getText().toString();
        calificacionEntregable = Float.parseFloat(calificacionET.getText().toString());

        //entregable = new Entregable(entregableName, calificacionEntregable);
        entregable.setName(entregableName);
        entregable.setCalificacion(calificacionEntregable);

        Intent returnIntent = new Intent();
        returnIntent.putExtra("resultEditEntregable", entregable);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }
}
