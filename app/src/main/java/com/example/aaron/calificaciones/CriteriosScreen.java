package com.example.aaron.calificaciones;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import clases.Criterio;
import clases.Materia;

/**
 * Created by gomri on 12/2/2017.
 */

public class CriteriosScreen extends AppCompatActivity {

    final int GET_NEW_CRITERIO = 0;

    private String matName;
    private Materia materia;
    private ListView lv;
    private ArrayList<Criterio> criterios = new ArrayList<Criterio>();
    private ArrayAdapter<Criterio> arrayAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        materia = (Materia) getIntent().getSerializableExtra("objMateria");
        //criterios = null;
        criterios = materia.getCriterios();

        setContentView(R.layout.activity_criterios);

        setTitle(materia.getName());

        lv = (ListView) findViewById(R.id.lista_criterios);
        arrayAdapter = new ArrayAdapter<Criterio>(this,
                android.R.layout.simple_list_item_1,
                criterios);
        arrayAdapter.notifyDataSetChanged();
        lv.setAdapter(arrayAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                //intent


                Context context = getApplicationContext();
                CharSequence text = Integer.toString(position + 1);
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
    }

    public void addCriterio(View view) {
        Intent getNewCrit = new Intent(this, addCriterioScreen.class);

        startActivityForResult(getNewCrit, GET_NEW_CRITERIO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GET_NEW_CRITERIO) {
            if (resultCode == Activity.RESULT_OK) {
                Bundle b = data.getExtras();

                if (b != null) {
                    Criterio resultado = (Criterio) b.getSerializable("result");
                    criterios.add(resultado);
                    //materia.addCriterio(resultado);
                }

                lv.invalidate();
                arrayAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onBackPressed() {

        Log.d("Erro", "Sí regresa");
        /*EditText matNameET = (EditText)
                findViewById(R.id.materia_nom);
        matName = matNameET.getText().toString();

        materia = new Materia(matName);
        */
        Intent returnIntent = new Intent();
        returnIntent.putExtra("resultCriterios", criterios);
        setResult(Activity.RESULT_OK,returnIntent);
        super.onBackPressed();
        finish();
    }
}
