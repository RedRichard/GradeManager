package clases;

import java.io.Serializable;

/**
 * Created by aaron on 1/12/2017.
 */

public class Entregable implements Serializable{

    String name;
    //String date;
    double grade;


    public Entregable ( ){
        name  =  " ";
        //date = " ";
        grade = 0.0;
    }

    public Entregable (String s, float g ){
        name = s;
        //date = d;
        grade = g;
    }

    public String toString(){
        return (name + "\n" + grade + "\n");
    }
}
