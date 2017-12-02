package clases;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aaron on 1/12/2017.
 */

public class Criterio {
    String name;

    double percentageC;//porcentaje acumulado del criterio
    double percentageG;//porcentajeC en el valor de la materia
    double percentageValue;// valor de procentaje en la materia

    List<Entregable> entregables = new ArrayList<Entregable>();


    Criterio ( ){
        this.name = " ";
        this.percentageC = 0.0;
        this.percentageG = 0.0;
        this.percentageValue = 0.0;
        //this.entregables = {};
    }

    Criterio (String n, float v){
        this.name = n;
        this.percentageC = 0.0;
        this.percentageG = 0.0;
        this.percentageValue = v;
       // this.entregables = {};
    }

    void set_promC (){
        int i;
        double sum;
        if (!this.entregables.isEmpty())
        {
            sum = 0.0;
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

    void add_entregable (String n, String d, float g){
        Entregable e = new Entregable(n,d,g);
        this.entregables.add(e);
    }

}