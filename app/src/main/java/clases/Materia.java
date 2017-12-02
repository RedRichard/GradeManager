package clases;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aaron on 1/12/2017.
 */

public class Materia {
    private String name;

    private float prom;//porcentaje acumulado del criterio

    private List<Criterio> criterios = new ArrayList<Criterio>();


    public Materia ( ){
        super();
        name = " ";
        prom = 0.0f;
        //criterios = {};
    }

    public Materia (String name){
        super();
        this.name = name;
        prom = 0.0f;
        //criterios = {};
    }

    public void setName(String name){
        this.name = name;
    }

    public void setProm(float prom){
        this.prom = prom;
    }

    public String getName(){
        return this.name;
    }

    public float getProm(){
        return this.prom;
    }

    void calculateProm() {
        int i;
        float sum =0.0f;

        if (!this.criterios.isEmpty()){
            sum = 0.0f;
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
