package com.example.parth.imahe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

public class DateQuestion extends AppCompatActivity {

    DatePicker datePicker;
    Button ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_question);
        datePicker= (DatePicker) findViewById(R.id.date);
        ok= (Button) findViewById(R.id.ok);


        //getActionBar().setDisplayHomeAsUpEnabled(true);



         Color_question.dateAndTime=String.valueOf(datePicker.getMonth());

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DateQuestion.this,SizeQuestion.class);
                startActivity(i);
            }
        });


    }
}
