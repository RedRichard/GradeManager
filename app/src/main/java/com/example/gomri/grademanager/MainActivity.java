package com.example.gomri.grademanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextView subjectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        subjectList = (TextView) findViewById(R.id.subject_list);

        String[] subjects = Example.getSubjects();

        for (String subject: subjects){
            subjectList.append(subject + "\n\n\n");
        }
    }
}
