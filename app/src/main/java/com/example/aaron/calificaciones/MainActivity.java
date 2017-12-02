package com.example.aaron.calificaciones;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import clases.Materia;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> materias = new ArrayList<String>();
    private ListView lv;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.lista_materias);
        arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                materias);
        arrayAdapter.notifyDataSetChanged();
        lv.setAdapter(arrayAdapter);
    }

    public void add_materia(View view) {
        Intent getNewMat = new Intent(this, add_materiaScreen.class);

        final int result = 1;
        startActivityForResult(getNewMat, result);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK){
            Materia m = new Materia("hola");
            materias.add(m.getName());
            String result=data.getStringExtra("result");
            lv.invalidate();
            arrayAdapter.notifyDataSetChanged();
        }
    }



    /*public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_materia);
    }*/
}


