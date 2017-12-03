package com.example.aaron.calificaciones;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import clases.Materia;

/**
 * Created by gomri on 12/2/2017.
 */

public class addCriteroScreen {
    private String matName;
    private Materia materia;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_materia);

    }

    public void onSendMatName(View view) {
        EditText matNameET = (EditText)
                findViewById(R.id.materia_nom);
        matName = matNameET.getText().toString();

        materia = new Materia(matName);

        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", materia);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

}
