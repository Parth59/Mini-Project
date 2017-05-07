package com.example.parth.imahe;

/**
 * Created by parth on 19/2/17.
 */
public class Bd {

    String name;
    String url;
    String dis;
    String path="http://smartparth13.pagekite.me/AndroidBirdApp/Oriental_images/";

    public Bd(String n,String d){
        name=n;
        name=name.replace(" ","_");
        url=path+name.toUpperCase()+"/1.jpg";
        dis=d;

    }




}
