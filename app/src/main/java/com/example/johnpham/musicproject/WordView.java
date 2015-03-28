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
    //String text = "";
    Context context;
    Paint p;
    ArrayList<Letter> ls;

    public WordView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs,R.styleable.WordView,0,0);
        try{
            textColor = array.getColor(R.styleable.WordView_textColor, 0);
        }finally {
            array.recycle();
        }
        this.context = context;
        p = new Paint();
        p.setTextSize(100);
        p.setStyle(Paint.Style.STROKE);
        p.setColor(textColor);
        ls = new ArrayList<>();
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

        for(Letter l : ls){
            Rect r = new Rect(l.getX()-10,l.getY()-100,l.getX()+80,l.getY()+30);

           if(r.contains(x,y)) {
            Toast.makeText(context,l.getLetter(),Toast.LENGTH_SHORT).show();
            ls.remove(l);
            break;
           }
        }
        invalidate();
        return true;
    }


    public void setText(String text) {
        for(int i = 0; i<text.length(); i++){
            ls.add(new Letter(text.charAt(i)+""));
        }
    }

    public void setTextColor(int color){
        this.textColor = color;
    }
}
