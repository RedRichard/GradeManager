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

import com.example.app.calificaciones.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

import clases.Criterio;
import clases.Entregable;
import clases.Materia;

/**
 * Created by gomri on 12/2/2017.
 */

public class CriteriosScreen extends AppCompatActivity {

    final int REQUESTCODE_NEW_CRITERIO = 0;
    final int REQUESTCODE_ENTREGABLES = 1;
    final int REQUESTCODE_EDIT_MATERIA = 2;

    private String matName;
    private Materia materia;
    private ListView lv;
    private ArrayList<Criterio> criterios = new ArrayList<Criterio>();
    private ArrayAdapter<Criterio> arrayAdapter;
    private int auxIndexClickedCriterio;
    private TextView averageText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        materia = (Materia) getIntent().getSerializableExtra("objMateria");
        //criterios = null;
        criterios = materia.getCriterios();

        setContentView(R.layout.activity_criterios);

        averageText = (TextView) findViewById(R.id.promedio_materia);
        averageText.setText("Average: " + materia.getProm());

        setTitle("Subject: " + materia.getName());

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
                auxIndexClickedCriterio = position;
                createEntregablesScreen(criterios.get(position));

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
                createEditMateriaScreen(materia);
                break;
            default:
                break;

        }
        return true;
    }

    public void createEditMateriaScreen(Materia materia){
        Intent editMateria = new Intent(this, editMateriaScreen.class);
        editMateria.putExtra("objMateria", materia);
        //Log.d("Nota", "llega");
        startActivityForResult(editMateria, REQUESTCODE_EDIT_MATERIA);
    }

    public void createEntregablesScreen(Criterio criterio){
        //Log.d("Si", "llega ");
        Intent criteriosMateria = new Intent(this, EntregablesScreen.class);
        criteriosMateria.putExtra("objCriterio", criterio);
        startActivityForResult(criteriosMateria, REQUESTCODE_ENTREGABLES);
    }

    public void addCriterio(View view) {
        Intent getNewCrit = new Intent(this, addCriterioScreen.class);

        startActivityForResult(getNewCrit, REQUESTCODE_NEW_CRITERIO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUESTCODE_NEW_CRITERIO) {
            if (resultCode == Activity.RESULT_OK) {
                Bundle b = data.getExtras();

                if (b != null) {
                    Criterio resultado = (Criterio) b.getSerializable("resultNewCriterio");
                    criterios.add(resultado);
                    //materia.addCriterio(resultado);
                }
                averageText.setText("Average: " + materia.getProm());

                lv.invalidate();
                arrayAdapter.notifyDataSetChanged();
            }
        }
        if (requestCode == REQUESTCODE_ENTREGABLES){
            if (resultCode == Activity.RESULT_OK){
                Bundle b = data.getExtras();

                if (b != null) {

                    Criterio crit = (Criterio) b.getSerializable("criterio");
                    if (crit != null) {
                        ArrayList<Entregable> entregables = (ArrayList<Entregable>) b.getSerializable("resultEntregables");
                        criterios.get(auxIndexClickedCriterio).setEntregables(entregables);
                        criterios.get(auxIndexClickedCriterio).setName(crit.getName());
                        criterios.get(auxIndexClickedCriterio).setPercentageValue(crit.getPercentageValue());
                    }else{
                        criterios.remove(auxIndexClickedCriterio);
                    }
                }
                averageText.setText("Average: " + materia.getProm());

                lv.invalidate();
                arrayAdapter.notifyDataSetChanged();
            }
        }
        if (requestCode == REQUESTCODE_EDIT_MATERIA){
            if (resultCode == Activity.RESULT_OK){
                Bundle b = data.getExtras();

                if (b != null) {
                    Materia resultado = (Materia) b.getSerializable("resultEditMateria");
                    if (resultado != null) {
                        materia.setName(resultado.getName());
                        setTitle("Subject: " + materia.getName());
                    }else{
                        materia = null;
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("matName", materia);
                        setResult(Activity.RESULT_OK,returnIntent);
                        super.onBackPressed();
                        finish();
                    }
                }

                lv.invalidate();
                arrayAdapter.notifyDataSetChanged();

            }
        }
    }

    @Override
    public void onBackPressed() {

        //Log.d("Erro", "Sí regresa");
        /*EditText matNameET = (EditText)
                findViewById(R.id.materia_nom);
        matName = matNameET.getText().toString();

        materia = new Materia(matName);
        */
        Intent returnIntent = new Intent();
        returnIntent.putExtra("resultCriterios", criterios);
        returnIntent.putExtra("matName", materia.getName());
        setResult(Activity.RESULT_OK,returnIntent);
        super.onBackPressed();
        finish();
    }
}
