package com.example.app.calificaciones;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by gomri on 12/3/2017.
 */

public class InvalidData extends Activity{

    public static void printMessage(Context context) {
        Toast toast = Toast.makeText(context, "Datos inválidos", Toast.LENGTH_SHORT);
        toast.show();
    }
}
