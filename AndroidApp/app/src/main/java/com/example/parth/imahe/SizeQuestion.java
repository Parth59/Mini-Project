package com.example.parth.imahe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SizeQuestion extends AppCompatActivity {

    RadioGroup group;
    Button ok;

    int size=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_size_question);
        group= (RadioGroup) findViewById(R.id.group);
        ok= (Button) findViewById(R.id.ok);

        group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton b=(RadioButton)v;

                switch (b.getId()){

                    case R.id.first:
                        size=1;
                        break;
                    case R.id.second:
                        size=2;
                        break;
                    case R.id.third:
                        size=3;
                        break;
                    case R.id.fourth:
                        size=4;
                        break;
                    case R.id.five:
                        size=5;
                        break;
                    case R.id.six:
                        size=6;
                        break;
                    case R.id.seven:
                        size=7;
                        break;

                }

            }
        });

       // getActionBar().setDisplayHomeAsUpEnabled(true);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Color_question.size=String.valueOf(size);
                Intent i=new Intent(SizeQuestion.this,Color_question.class);
                startActivity(i);

            }
        });
    }
}
