package com.example.johnpham.musicproject;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by bradleyfowler on 3/27/15.
 */
public class Letter{
    String letter;
    Random r = new Random();
    int x,y;

    public Letter(String l){
        this.letter = l;
        x = r.nextInt(500)+100;
        y = r.nextInt(500)+100;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public String getLetter(){
        return letter;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }
}
