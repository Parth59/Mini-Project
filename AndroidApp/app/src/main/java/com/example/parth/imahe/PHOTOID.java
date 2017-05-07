package com.example.parth.imahe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
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

public class PHOTOID extends AppCompatActivity {

    ImageView imageview;
    /**
     * Persist URI image to crop URI if specific permissions are required
     */
    private Uri mCropImageUri;
    Button nxt;
    ProgressDialog progressDialog;
    Uri myUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photoid);
        imageview=(ImageView)findViewById(R.id.quick_start_cropped_image);
        nxt=(Button)findViewById(R.id.button2);

         myUri = Uri.parse(getIntent().getExtras().getString("result"));
        imageview.setImageURI(myUri);
        //getActionBar().setDisplayHomeAsUpEnabled(true);

        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                String SD_Card_Path = Environment.getExternalStorageDirectory().toString();
                String Complete_Path =  SD_Card_Path  + "/Android/data/i1.png";
                imageUpload(Complete_Path);*/


                    progressDialog = new ProgressDialog(PHOTOID.this);
                    progressDialog.setMessage("Uploading, please wait...");
                    progressDialog.show();

                    Bitmap bitmap = ((BitmapDrawable)imageview.getDrawable()).getBitmap();

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
                    byte[] b = baos.toByteArray();

                    final String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);

                    String url = "http://smartparth13.pagekite.me/AndroidBirdApp/PHOTOID/imageupload.php";

                    StringRequest sr = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            Toast.makeText(PHOTOID.this,"Image Uploaded Successfully",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(PHOTOID.this,photoDateLocation.class);
                            intent.putExtra("imagename",response);
                            startActivity(intent);
                                                    }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(PHOTOID.this,error.toString(),Toast.LENGTH_SHORT).show();
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



}
