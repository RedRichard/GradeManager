package com.example.app.calificaciones;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

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
    final int REQUEST_CODE_EDIT_CRITERIO = 2;

    private String matName;
    private Criterio criterio;
    private ListView lv;
    private ArrayList<Entregable> entregables = new ArrayList<Entregable>();
    private ArrayAdapter<Entregable> arrayAdapter;
    private int auxIndexClickedEntregable;
    private TextView promedioCriterio;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        criterio = (Criterio) getIntent().getSerializableExtra("objCriterio");

        //criterios = materia.getCriterios();
        entregables = criterio.getEntregables();

        setContentView(R.layout.activity_entregables);

        promedioCriterio = (TextView) findViewById(R.id.promedio_criterio);
        setAverageText(criterio.getPromedio());

        setTitle("Criterion: " + criterio.getName());

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


                /*Context context = getApplicationContext();
                CharSequence text = Integer.toString(position + 1);
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();*/
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.edit:
                createEditCriterioScreen();
                break;
            default:
                break;

        }
        return true;
    }

    public void createEditCriterioScreen(){
        Intent editCriterio = new Intent(this, editCriteriosScreen.class);
        editCriterio.putExtra("objCriterio", this.criterio);
        //Log.d("Nota", "llega");
        startActivityForResult(editCriterio, REQUEST_CODE_EDIT_CRITERIO);
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
                setAverageText(criterio.getPromedio());
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
                setAverageText(criterio.getPromedio());
                lv.invalidate();
                arrayAdapter.notifyDataSetChanged();
            }
        }
        if (requestCode == REQUEST_CODE_EDIT_CRITERIO){
            if (resultCode == Activity.RESULT_OK){
                Bundle b = data.getExtras();

                if (b != null) {
                    Criterio resultado = (Criterio) b.getSerializable("resultEditCriterio");
                    criterio.setName(resultado.getName());
                    criterio.setPorcentaje(resultado.getPromedio());
                    //criterios.set(auxIndexClickedCriterio, resultado);
                    //entregables.add(resultado);
                }

                lv.invalidate();
                arrayAdapter.notifyDataSetChanged();
                setTitle("Criterion: " + criterio.getName());
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
        returnIntent.putExtra("criterio", criterio);
        returnIntent.putExtra("resultEntregables", entregables);
        setResult(Activity.RESULT_OK,returnIntent);
        super.onBackPressed();
        finish();
    }

    public void setAverageText(Float cal){
        promedioCriterio.setText("Average: " + Float.toString(cal));
    }
}
