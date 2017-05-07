package com.example.parth.imahe;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class UploadActivity extends AppCompatActivity {


    Button add;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
          add= (Button) findViewById(R.id.add);
        image = (ImageView) findViewById(R.id.image);


        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

       // getActionBar().setDisplayHomeAsUpEnabled(true);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                String SD_Card_Path = Environment.getExternalStorageDirectory().toString();

                String Complete_Path =  SD_Card_Path  + "/Android/data/i1.png";

                imageUpload(Complete_Path);*/

              Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos); //bm is the bitmap object
                byte[] b = baos.toByteArray();

                final String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);


                String url = "http://10.42.0.23:8000/Bird/upload/";



                StringRequest sr = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(UploadActivity.this,response,Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UploadActivity.this,error.toString(),Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String,String> getParams(){
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("myfile",encodedImage);
                        return params;
                    }
                };

                Volley.newRequestQueue(getApplicationContext()).add(sr);
            }
        });







    }

    private void imageUpload(final String imagePath) {

        String url = "http://192.168.0.102:8000/Bird/upload/";

       /* SimpleMultiPartRequest smr = new SimpleMultiPartRequest(Request.Method.POST,url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response", response);
                        try {
                            JSONObject jObj = new JSONObject(response);
                            String message = jObj.getString("message");

                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        smr.addMultipartParam("1","1","1");
        smr.addFile("myfile", imagePath);
        Volley.newRequestQueue(getApplicationContext()).add(smr);*/

    }

    public void take(){

        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");

        startActivityForResult(intent, 0);


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {




        }


    }

}
