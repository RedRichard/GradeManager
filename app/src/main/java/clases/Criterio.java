package clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aaron on 1/12/2017.
 */

public class Criterio implements Serializable{
    String name;

    float calificacionAcumulada;
    float calificacionPorcentaje;
    float percentageC;//porcentaje acumulado del criterio
    float percentageG;//porcentajeC en el valor de la materia
    float percentageValue;// valor de procentaje en la materia

    private ArrayList<Entregable> entregables = new ArrayList<Entregable>();


    public Criterio ( ){
        this.name = " ";
        this.percentageC = 0.0f;
        this.percentageG = 0.0f;
        this.percentageValue = 0.0f;
        //this.entregables = {};
    }

    public Criterio (String n, float v){
        if (v > 100 || v < 0){
            v = 0;
        }
        this.name = n;
        this.percentageC = 0.0f;
        this.percentageG = 0.0f;
        this.percentageValue = v;
       // this.entregables = {};
    }

    void set_promC (){
        int i;
        float sum;
        if (!this.entregables.isEmpty())
        {
            sum = 0.0f;
            for(i = 0; i < this.entregables.size(); i++)
            {
                sum += this.entregables.get(i).grade;
            }

            this.percentageC  = sum/this.entregables.size();
        }
    }

    void set_promG (){
        this.percentageG = this.percentageC * this.percentageValue;
    }

    /*void add_entregable (String n, String d, float g){
        Entregable e = new Entregable(n,d,g);
        this.entregables.add(e);
    }*/

    public String toString(){
        return name + "\nPercentage: " + Float.toString(calificacionPorcentaje) + "% / " + Float.toString(percentageValue) + "%\n";
    }

    public String getName(){

        return this.name;
    }

    public void setEntregables(ArrayList<Entregable> entregables){
        this.entregables = entregables;
        updatePromedio();
    }

    public ArrayList<Entregable> getEntregables (){
        return this.entregables;
    }


    public void setPorcentaje(float porcentaje) {
        if (porcentaje > 100 || porcentaje <0){
            porcentaje = 0;
        }
        this.calificacionPorcentaje = porcentaje;
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