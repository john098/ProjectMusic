package com.example.johnpham.musicproject;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by bradleyfowler on 3/26/15.
 */
public class WordView extends View {

    int textColor;
    String text = "";
    Context context;
    Paint p;
    ArrayList<Letter> ls;

    public WordView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs,R.styleable.WordView,0,0);
        try{
            text = array.getString(R.styleable.WordView_text);
            textColor = array.getColor(R.styleable.WordView_textColor, 0);
        }finally {
            array.recycle();
        }
        this.context = context;
        p = new Paint();
        p.setTextSize(100);
        p.setColor(textColor);
        ls = new ArrayList<Letter>();

        for(int i = 0; i<text.length(); i++){
            ls.add(new Letter(text.charAt(i)+""));
        }
    }

    @Override
    public void onDraw(Canvas canvas){
        for(Letter l : ls){
            canvas.drawText(l.getLetter(),l.getX(),l.getY(),p);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        int x = (int)event.getX();
        int y = (int)event.getY();
        Rect touchPoint = new Rect(10,10,x,y);
        Log.d("Touch Point: ", touchPoint.toString());

       // Log.d("Points", "X: " + x + " Y: " + y);


        for(Letter l : ls){
            if(Rect.intersects(touchPoint,new Rect(l.getX(),l.getY(),10,10))) {
                Log.d("Letter Points  ",l.getLetter()+ "  X: " + l.getX() + " Y: " + l.getY());
            }
        }
        return true;
    }


    public void setText(String text) {
        this.text = text;
    }

    public void setTextColor(int color){
        this.textColor = color;
    }
}
