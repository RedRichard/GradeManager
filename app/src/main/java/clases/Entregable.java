package clases;

import java.io.Serializable;

/**
 * Created by aaron on 1/12/2017.
 */

public class Entregable implements Serializable{

    String name;
    //String date;
    float grade;


    public Entregable ( ){
        name  =  " ";
        //date = " ";
        grade = 0.0f;
    }

    public Entregable (String s, float g ){
        name = s;
        //date = d;
        grade = g;
    }

    public String getName(){
        return this.name;
    }

    public float getGrade(){
        return this.grade;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setCalificacion(float cal){
        this.grade = cal;
    }

    public String toString(){
        return (name + "\n" + grade + "\n");
    }
}
