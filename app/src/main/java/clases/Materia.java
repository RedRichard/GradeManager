package clases;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aaron on 1/12/2017.
 */

public class Materia {
    String name;

    double prom;//porcentaje acumulado del criterio

    List<Criterio> criterios = new ArrayList<Criterio>();


    Materia ( ){
        name = " ";
        prom = 0.0;
        //criterios = {};
    }

    Materia (String n){
        name = n;
        prom = 0.0;
        //criterios = {};
    }

    void get_prom() {
        int i;
        double sum =0.0;

        if (!this.criterios.isEmpty()){
            sum = 0.0;
            for(i = 0; i < this.criterios.size();i++)
            {
                sum += this.criterios.get(i).percentageG;
            }
        }

        this.prom = sum/this.criterios.size();
    }

    void add_crit(String n, float v){
        Criterio c = new Criterio(n,v);
        this.criterios.add(c);
    }
}
