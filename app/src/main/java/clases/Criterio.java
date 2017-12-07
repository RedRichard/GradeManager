package clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aaron on 1/12/2017.
 */

public class Criterio implements Serializable{

    private String name;

    private float calificacionAcumulada;
    private float calificacionPorcentaje = 0;
    private float percentageValue;// valor de procentaje en la materia

    private ArrayList<Entregable> entregables = new ArrayList<Entregable>();

    public Criterio (String n, float v){
        if (v > 100 || v < 0){
            v = 0;
        }
        this.name = n;
        this.percentageValue = v;
        this.calificacionPorcentaje = 0f;
        this.calificacionAcumulada = 0f;
    }

    public String toString(){
        return name + "\nPercentage: " + Float.toString(calificacionPorcentaje) +
                "% / " + Float.toString(percentageValue) + "%\n";
    }

    public String getName(){

        return this.name;
    }

    public float getCalificacionPorcentaje(){
        return this.calificacionPorcentaje;
    }

    public void setEntregables(ArrayList<Entregable> entregables){
        this.entregables = entregables;
        updatePromedio();
    }

    public void setCalificacionPorcentaje(float cal){
        this.calificacionPorcentaje = cal;
    }

    public ArrayList<Entregable> getEntregables (){
        return this.entregables;
    }

    public void setPercentageValue(float porcentaje) {
        if (porcentaje > 100 || porcentaje <0){
            porcentaje = 0;
        }
        this.percentageValue = porcentaje;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPercentageValue() {
        updatePromedio();
        return this.percentageValue;
    }

    public float getPromedio(){
        return this.calificacionPorcentaje;
    }

    public void updatePromedio(){
        calificacionAcumulada = 0;
        calificacionPorcentaje = 0;
        for (Entregable entregable : entregables){
            calificacionAcumulada += entregable.getGrade();
        }
        calificacionAcumulada = calificacionAcumulada/(entregables.size());
        calificacionPorcentaje = calificacionAcumulada*(percentageValue/10);
    }
}