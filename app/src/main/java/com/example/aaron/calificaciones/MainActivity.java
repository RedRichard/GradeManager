package com.example.aaron.calificaciones;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import clases.Materia;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Materia> materias = new ArrayList<Materia>();
    private ListView lv;
    private ArrayAdapter<Materia> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                Context context = getApplicationContext();
                CharSequence text = Integer.toString(position + 1);
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
    }

    public void add_materia(View view) {
        Intent getNewMat = new Intent(this, add_materiaScreen.class);

        final int result = 1;
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

}


