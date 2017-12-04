package clases;

import com.example.app.calificaciones.InvalidData;

import java.io.Serializable;

/**
 * Created by aaron on 1/12/2017.
 */

public class Entregable implements Serializable{

    String name;
    float grade;

    public Entregable (String s, float g ){
        if (g > 10 || g < 0){
            g = 0;
        }
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
        if (cal > 10 || cal < 0){
            cal = 0;
        }
        this.grade = cal;
    }

    public String toString(){
        return (name + "\nAssignment grade: " + grade + "\n");
    }
}
