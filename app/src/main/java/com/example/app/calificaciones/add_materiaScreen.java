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

import java.io.Serializable;

import clases.Materia;

/**
 * Created by aaron on 1/12/2017.
 */

public class add_materiaScreen extends Activity{

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
        returnIntent.putExtra("resultMateria", materia);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

}
