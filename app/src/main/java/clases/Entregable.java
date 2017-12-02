package clases;

/**
 * Created by aaron on 1/12/2017.
 */

public class Entregable {

    String name;
    String date;
    double grade;


    Entregable ( ){
        name  =  " ";
        date = " ";
        grade = 0.0;
    }

    Entregable (String s, String d, float g ){
        name = s;
        date = d;
        grade = g;
    }
}
