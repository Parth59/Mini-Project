package com.example.parth.imahe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LocationQuestion extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button b1;
    Spinner city;
    ArrayList<String> locatin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_question);

        city= (Spinner) findViewById(R.id.spinner1);
        locatin=new ArrayList<String>();
        city.setOnItemSelectedListener(this);

        getData();

     /*   String[] country = { "India", "USA", "China", "Japan", "Other",  };
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        city.setAdapter(aa);*/


        b1=(Button)findViewById(R.id.ok);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Color_question.location=city.getSelectedItem().toString();
                Intent i=new Intent(LocationQuestion.this,DateQuestion.class);
                startActivity(i);
            }
        });

    }


    //Performing action onItemSelected and onNothing selected
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
      //  Toast.makeText(getApplicationContext(),"Hello" ,Toast.LENGTH_LONG).show();
        city.setSelection(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    private void getData() {

        String url = "http://smartparth13.pagekite.me/AndroidBirdApp/locationlist.php";
        StringRequest sr = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //   Toast.makeText(photoDateLocation.this,response,Toast.LENGTH_LONG).show();
                             /*   Intent intent=new Intent(photoDateLocation.this,displayOutput.class);
                                intent.putExtra("key",response);
                                startActivity(intent);*/

                //   Log.v("str",response);
                String[] a=response.split("\n");

                //     Toast.makeText(photoDateLocation.this, response, Toast.LENGTH_SHORT).show();

                //
                insertspinner(a);

                //  Intent intent=new Intent(photoDateLocation.this,displayOutput.class);
                //    intent.putExtra("str",l);
                //    startActivity(intent);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LocationQuestion.this,error.toString(),Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                //     params.put("area",e1.getText().toString());
                //      params.put("imagename",imageName);
                return params;
            }
        };

        Volley.newRequestQueue(getApplicationContext()).add(sr);
    }




    private void insertspinner(String []a){
        for(int i=0;i<a.length;i++){
            locatin.add(a[i]);
        }
        city.setAdapter(new ArrayAdapter<String>(LocationQuestion.this, android.R.layout.simple_spinner_dropdown_item, locatin));
    }

}
