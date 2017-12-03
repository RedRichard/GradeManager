package com.example.app.calificaciones;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import clases.Criterio;
import clases.Materia;

/**
 * Created by gomri on 12/3/2017.
 */

public class editMateriaScreen extends Activity{
    private String matName;
    private Materia materia;

    private EditText matNameET;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        materia = (Materia) getIntent().getSerializableExtra("objMateria");
        setContentView(R.layout.edit_materia);

        matNameET = (EditText) findViewById(R.id.nuevo_nombre_materia);

        matNameET.setText(materia.getName());

    }

    public void onSendMateria(View view) {
        EditText matNameET = (EditText)
                findViewById(R.id.nuevo_nombre_materia);
        matName = matNameET.getText().toString();

        materia.setName(matName);

        Intent returnIntent = new Intent();
        returnIntent.putExtra("resultEditMateria", materia);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

    public void onDeleteMateria(View view){
        materia = null;
        Intent returnIntent = new Intent();
        returnIntent.putExtra("resultEditMateria", materia);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }
}
