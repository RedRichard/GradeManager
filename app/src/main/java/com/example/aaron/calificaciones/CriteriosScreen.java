package com.example.aaron.calificaciones;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import clases.Materia;

/**
 * Created by gomri on 12/2/2017.
 */

public class CriteriosScreen extends AppCompatActivity {
    private String matName;
    private Materia materia;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        materia = (Materia) getIntent().getSerializableExtra("objMateria");

        setContentView(R.layout.activity_criterios);

        setTitle(materia.getName());

    }

    public void addCriterio(View view) {
        Intent getNewMat = new Intent(this, add_materiaScreen.class);

        final int result = 0;
        startActivityForResult(getNewMat, result);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK){
            Bundle b = data.getExtras();

            if (b!=null){
                Materia resultado= (Materia) b.getSerializable("result");
                materias.add(resultado);
            }

            lv.invalidate();
            arrayAdapter.notifyDataSetChanged();
        }
    }

    /*public void onSendMatName(View view) {
        EditText matNameET = (EditText)
                findViewById(R.id.materia_nom);
        matName = matNameET.getText().toString();

        materia = new Materia(matName);

        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", materia);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }*/
}
