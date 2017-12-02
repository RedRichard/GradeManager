package com.example.aaron.calificaciones;

import android.app.Activity;
import android.content.Intent;
import android.drm.DrmStore;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

/**
 * Created by aaron on 1/12/2017.
 */

public class add_materiaScreen extends Activity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_materia);

    }

    public void onSendMatName(View view) {

        EditText matNameET = (EditText)
                findViewById(R.id.materia_nom);
        String matName = String.valueOf(matNameET.getText());

        Intent goingBack = new Intent();

        setResult(RESULT_OK);

        finish();
    }
}
