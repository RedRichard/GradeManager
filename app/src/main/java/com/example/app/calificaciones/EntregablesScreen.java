package com.example.app.calificaciones;

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
import clases.Entregable;
import clases.Materia;

/**
 * Created by gomri on 12/3/2017.
 */

public class EntregablesScreen extends AppCompatActivity{

    final int REQUESTCODE_NEW_ENTREGABLE = 0;
    final int REQUEST_CODE_EDIT_ENTREGABLES = 1;

    private String matName;
    private Criterio criterio;
    private ListView lv;
    private ArrayList<Entregable> entregables = new ArrayList<Entregable>();
    private ArrayAdapter<Entregable> arrayAdapter;
    private int auxIndexClickedEntregable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        criterio = (Criterio) getIntent().getSerializableExtra("objCriterio");

        //criterios = materia.getCriterios();
        entregables = criterio.getEntregables();

        setContentView(R.layout.activity_entregables);

        setTitle("Criterio: " + criterio.getName());

        lv = (ListView) findViewById(R.id.lista_entregables);
        arrayAdapter = new ArrayAdapter<Entregable>(this,
                android.R.layout.simple_list_item_1,
                entregables);
        arrayAdapter.notifyDataSetChanged();
        lv.setAdapter(arrayAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                //intent
                auxIndexClickedEntregable = position;
                createEditEntregablesScreen(entregables.get(position));


                Context context = getApplicationContext();
                CharSequence text = Integer.toString(position + 1);
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
    }

    public void createEditEntregablesScreen(Entregable entregable){
        Intent criteriosMateria = new Intent(this, editEntregableScreen.class);
        criteriosMateria.putExtra("objEntregable", entregable);
        Log.d("Nota", "llega");
        startActivityForResult(criteriosMateria, REQUEST_CODE_EDIT_ENTREGABLES);
    }

    public void addEntregable(View view) {
        Intent getNewCrit = new Intent(this, addEntregableScreen.class);

        startActivityForResult(getNewCrit, REQUESTCODE_NEW_ENTREGABLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUESTCODE_NEW_ENTREGABLE) {
            if (resultCode == Activity.RESULT_OK) {
                Bundle b = data.getExtras();

                if (b != null) {
                    Entregable resultado = (Entregable) b.getSerializable("resultNewEntregable");
                    entregables.add(resultado);
                }

                lv.invalidate();
                arrayAdapter.notifyDataSetChanged();
            }
        }
        if (requestCode == REQUEST_CODE_EDIT_ENTREGABLES){
            if (resultCode == Activity.RESULT_OK){
                Bundle b = data.getExtras();

                if (b != null) {
                    Entregable resultado = (Entregable) b.getSerializable("resultEditEntregable");
                    entregables.set(auxIndexClickedEntregable, resultado);
                    //entregables.add(resultado);
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
        returnIntent.putExtra("resultEntregables", entregables);
        setResult(Activity.RESULT_OK,returnIntent);
        super.onBackPressed();
        finish();
    }
}
