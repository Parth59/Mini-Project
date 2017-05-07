package com.example.parth.imahe;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class photoDateLocation extends FragmentActivity implements AdapterView.OnItemSelectedListener {

    private Uri myUri;
    ImageView imageview;
    Button identify,pick1,pick2;
    EditText e1,e2;

    ArrayList<String> l=new ArrayList<>();
    Spinner mSpinner;
    ArrayList<String> locatin;
    String imageName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_date_location);

        // imageview=(ImageView)findViewById(R.id.imageView);
        identify=(Button)findViewById(R.id.button3);

        imageName = getIntent().getExtras().getString("imagename");
        // imageview.setImageURI(myUri);




      //  e1=(EditText)findViewById(R.id.editText1);
        e2=(EditText)findViewById(R.id.editText2);
        imageview=(ImageView)findViewById(R.id.imageView);
        mSpinner=(Spinner)findViewById(R.id.mspinner) ;

        mSpinner.setOnItemSelectedListener(this);
        locatin=new ArrayList<String>();

        String ntemp="http://192.168.43.161:8090/AndroidBirdApp/PHOTOID/files/"+imageName;
        Picasso.with(photoDateLocation.this).load(ntemp).into(imageview);

        getData();

    //    pick1=(Button)findViewById(R.id.button5);
        pick2=(Button)findViewById(R.id.button4);

        pick2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();

            }
        });
        //getActionBar().setDisplayHomeAsUpEnabled(true);

        identify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String url = "http://smartparth13.pagekite.me/AndroidBirdApp/PHOTOID/tensorflow.php";
                StringRequest sr = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(photoDateLocation.this,response,Toast.LENGTH_LONG).show();
                             /*   Intent intent=new Intent(photoDateLocation.this,displayOutput.class);
                                intent.putExtra("key",response);
                                startActivity(intent);*/

                        Log.v("str",response);
                        String[] a=response.split("\n");

                        Toast.makeText(photoDateLocation.this, response, Toast.LENGTH_SHORT).show();
                        for(int i=0;i<a.length;i++){

                            l.add(a[i]);
                        }

                        Intent intent=new Intent(photoDateLocation.this,displayOutput.class);
                        intent.putExtra("str",l);
                        startActivity(intent);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(photoDateLocation.this,error.toString(),Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String,String> getParams(){
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("area",mSpinner.getSelectedItem().toString());
                        params.put("imagename",imageName);
                        return params;
                    }
                };

                Volley.newRequestQueue(getApplicationContext()).add(sr);


                }



        });

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
               insertdata(a);

              //  Intent intent=new Intent(photoDateLocation.this,displayOutput.class);
            //    intent.putExtra("str",l);
            //    startActivity(intent);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(photoDateLocation.this,error.toString(),Toast.LENGTH_SHORT).show();
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



    private  void insertdata(String []a){
        for(int i=0;i<a.length;i++){

            locatin.add(a[i]);
        }
        mSpinner.setAdapter(new ArrayAdapter<String>(photoDateLocation.this, android.R.layout.simple_spinner_dropdown_item, locatin));


    }

    private void showDatePicker() {
        DatePickerFragment date = new DatePickerFragment();
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);

        date.setCallBack(ondate);
        date.show(getSupportFragmentManager(), "Date Picker");


    }

    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            Toast.makeText(
                    photoDateLocation.this,
                    String.valueOf(year) + "-" + String.valueOf(monthOfYear)
                            + "-" + String.valueOf(dayOfMonth),
                    Toast.LENGTH_LONG).show();

            e2.setText(String.valueOf(year) + "-" + String.valueOf(monthOfYear)
                    + "-" + String.valueOf(dayOfMonth));

        }
    };


    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        //  Toast.makeText(getApplicationContext(),"Hello" ,Toast.LENGTH_LONG).show();
        mSpinner.setSelection(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
