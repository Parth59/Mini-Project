package com.example.parth.imahe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class displayOutput extends AppCompatActivity {

    ArrayList<String> l;
    RecyclerView recyclerView;
    static boolean flag=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_output);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        l= (ArrayList<String>) getIntent().getExtras().get("str");

        ArrayList<Bd> b=new ArrayList<>();

        for(int i=0;i<l.size();i++){
            if(flag){
                String[] s=l.get(i).split(";");
                try{
                    Log.v("str",l.get(i));
                    Bd a=new Bd(s[0],s[1]);
                    b.add(a);
                }catch (Exception e){

                }
            }else{
                String[] s=l.get(i).split(":");
                try{
                    Log.v("str",l.get(i));
                    Bd a=new Bd(s[0],s[1]);
                    b.add(a);
                }catch (Exception e){

                }
            }


        }
        flag=false;

        BirdAdapter mAdapter = new BirdAdapter(b);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();



    }


    public class BirdAdapter extends RecyclerView.Adapter<BirdAdapter.MyViewHolder> {

        private List<Bd> List;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView name, d;
            public ImageView imageView;

            public MyViewHolder(View view) {
                super(view);
                name = (TextView) view.findViewById(R.id.name);
                d = (TextView) view.findViewById(R.id.d);
                imageView = (ImageView) view.findViewById(R.id.image);
            }
        }


        public BirdAdapter(List<Bd> moviesList) {
            this.List = moviesList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Bd bird = List.get(position);



            holder.name.setText(bird.name);
            holder.d.setText(bird.dis);

            Picasso.with(displayOutput.this)
                    .load(bird.url)
                    .into(holder.imageView);

        }

        @Override
        public int getItemCount() {
            return List.size();
        }
    }



}
