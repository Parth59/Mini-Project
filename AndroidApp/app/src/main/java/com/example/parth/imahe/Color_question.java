package com.example.parth.imahe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Color_question extends AppCompatActivity {

    ImageView im1,im2,im3,im4,im5,im6,im7,im8,im9;

    static String location;
    static String color="";
    static String dateAndTime;
    static String size;

    ArrayList<String> l=new ArrayList<>();
   HashSet<String> selectedColor=new HashSet<>();

    CheckBox b1,b2,b3,b4,b5,b6,b7,b8,b9;




    Button n;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_question);

        im1= (ImageView) findViewById(R.id.firstimage);
        im2= (ImageView) findViewById(R.id.secondimage);
        im3= (ImageView) findViewById(R.id.thirdimage);
        im4= (ImageView) findViewById(R.id.fourthimage);
        im5= (ImageView) findViewById(R.id.fifthimage);
        im6= (ImageView) findViewById(R.id.sixthimage);
        im7= (ImageView) findViewById(R.id.seventhimage);
        im8= (ImageView) findViewById(R.id.eightimage);
        im9= (ImageView) findViewById(R.id.nineimage);

        b1= (CheckBox) findViewById(R.id.firstckb);
        b2= (CheckBox) findViewById(R.id.secondckb);
        b3= (CheckBox) findViewById(R.id.thirdckb);
        b4= (CheckBox) findViewById(R.id.fourthckb);
        b5= (CheckBox) findViewById(R.id.fifthckb);
        b6= (CheckBox) findViewById(R.id.sixthckb);
        b7= (CheckBox) findViewById(R.id.secondckb);
        b8= (CheckBox) findViewById(R.id.eightckb);
        b9= (CheckBox) findViewById(R.id.nineckb);


        n= (Button) findViewById(R.id.ok);





        n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String url = "http://smartparth13.pagekite.me/AndroidBirdApp/BIRDID/findbirds.php";

                StringRequest sr = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(Color_question.this,response.toString(),Toast.LENGTH_SHORT).show();
                        Log.v("str",color+location+size+dateAndTime);
                        Log.v("str",response);

                        String[] a=response.split("\n");


                        for(int i=0;i<a.length;i++){

                            l.add(a[i]);
                        }
                        displayOutput.flag=true;
                        Intent intent=new Intent(Color_question.this,displayOutput.class);
                        intent.putExtra("str",l);
                        startActivity(intent);



                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Color_question.this,error.toString(),Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String,String> getParams(){
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("location",location);

                        for(String t:selectedColor){
                            color+=t+",";
                            Log.v("selected",t);
                        }
                        Log.v("selected",selectedColor.toString());
                        params.put("size",size);
                        params.put("dateAndTime",dateAndTime);
                        params.put("color",color);
                        return params;
                    }
                };

                Volley.newRequestQueue(getApplicationContext()).add(sr);

            }
        });



    }

    public void Okay(View v){

        CheckBox c=(CheckBox)v;
        switch (c.getId()) {
            case R.id.firstckb:
                if(c.isChecked()){
                    selectedColor.add("BLACK");
                }else{
                    selectedColor.remove("BLACK");
                }
                break;
            case R.id.secondckb:
                if(c.isChecked()){
                    selectedColor.add("GREY");
                }else{
                    selectedColor.remove("GREY");
                }
                break;
            case R.id.thirdckb:
                if(c.isChecked()){
                    selectedColor.add("WHITE");
                }else{
                    selectedColor.remove("WHITE");
                }
                break;
            case R.id.fourthckb:
                if(c.isChecked()){
                    selectedColor.add("BROWN");
                }else{
                    selectedColor.remove("BROWN");
                }
                break;
            case R.id.fifthckb:
                if(c.isChecked()){
                    selectedColor.add("RED");
                }else{
                    selectedColor.remove("RED");
                }
                break;
            case R.id.sixthckb:
                if(c.isChecked()){
                    selectedColor.add("YELLOW");
                }else{
                    selectedColor.remove("YELLOW");
                }
                break;
            case R.id.seventhckb:
                if(c.isChecked()){
                    selectedColor.add("GREEN");
                }else{
                    selectedColor.remove("GREEN");
                }
                break;
            case R.id.eightckb:
                if(c.isChecked()){
                    selectedColor.add("BLUE");
                }else{
                    selectedColor.remove("BLUE");
                }
                break;
            case R.id.nineckb:
                if(c.isChecked()){
                    selectedColor.add("ORANGE");
                }else{
                    selectedColor.remove("ORANGE");
                }
                break;
        }
    }
}
