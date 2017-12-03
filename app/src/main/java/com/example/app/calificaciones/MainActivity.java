package com.example.app.calificaciones;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.app.calificaciones.R;
import com.google.gson.Gson;

import java.util.ArrayList;

import clases.Criterio;
import clases.Materia;

public class MainActivity extends AppCompatActivity {

    final int REQUEST_CODE_ADD_MATERIAS = 0;
    final int REQUEST_CODE_CRITERIOS = 2;

    private ArrayList<Materia> materias = new ArrayList<Materia>();
    private ListView lv;
    private ArrayAdapter<Materia> arrayAdapter;
    private int auxIndexClickedMateria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.custom_title_bar);

        if (materias.isEmpty()){
            try{
                loadMaterias();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        setTitle("Grade Manager 3000");

        lv = (ListView) findViewById(R.id.lista_materias);
        arrayAdapter = new ArrayAdapter<Materia>(this,
                android.R.layout.simple_list_item_1,
                materias);
        arrayAdapter.notifyDataSetChanged();
        lv.setAdapter(arrayAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                //intent
                auxIndexClickedMateria = position;
                createCriteriosScreen(materias.get(position));


                //Context context = getApplicationContext();
                //CharSequence text = Integer.toString(position + 1);
                //int duration = Toast.LENGTH_SHORT;

                //Toast toast = Toast.makeText(context, text, duration);
                //toast.show();
            }
        });
    }

    @Override
    public void onPause(){
        super.onPause();
        saveMaterias();
    }

    private boolean saveMaterias(){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor materiasData = sp.edit();
    /* sKey is an array */
        materiasData.putInt("Status_size", materias.size());

        Gson gson = new Gson();

        for(int i=0;i<materias.size();i++)
        {
            materiasData.remove("MyObject_" + i);
            String json = gson.toJson(materias.get(i));
            materiasData.putString("MyObject_" + i, json);
        }

        return materiasData.commit();
    }

    private void loadMaterias(){
        SharedPreferences appSharedPrefs =   PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        Gson gson = new Gson();
        materias.clear();
        int size = appSharedPrefs.getInt("Status_size", 0);

        for(int i=0;i<size;i++)
        {
            String json = appSharedPrefs.getString("MyObject_" + i, "");
            materias.add(gson.fromJson(json, Materia.class));
        }
    }

    //Revisar:
    /*@Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
        arrayAdapter.notifyDataSetChanged();
    }*/

    public void createCriteriosScreen(Materia materia){
        Intent criteriosMateria = new Intent(this, CriteriosScreen.class);
        criteriosMateria.putExtra("objMateria", materia);
        startActivityForResult(criteriosMateria, REQUEST_CODE_CRITERIOS);
    }

    public void add_materia(View view) {
        Intent getNewMat = new Intent(this, add_materiaScreen.class);

        startActivityForResult(getNewMat, REQUEST_CODE_ADD_MATERIAS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_ADD_MATERIAS) {
            if (resultCode == Activity.RESULT_OK) {
                Bundle b = data.getExtras();

                if (b != null) {
                    Materia resultado = (Materia) b.getSerializable("resultMateria");
                    materias.add(resultado);
                }

                lv.invalidate();
                arrayAdapter.notifyDataSetChanged();
            }
        }
        if (requestCode == REQUEST_CODE_CRITERIOS){
            if (resultCode == Activity.RESULT_OK){
                Bundle b = data.getExtras();

                if (b != null) {
                    ArrayList<Criterio> criterios = (ArrayList<Criterio>) b.getSerializable("resultCriterios");
                    String matName = b.getString("matName");
                    materias.get(auxIndexClickedMateria).setName(matName);
                    materias.get(auxIndexClickedMateria).setCriterios(criterios);
                    //materias.add(resultado);
                }

                lv.invalidate();
                arrayAdapter.notifyDataSetChanged();
            }
        }
    }

}


