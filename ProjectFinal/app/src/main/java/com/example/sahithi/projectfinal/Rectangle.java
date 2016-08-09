package com.example.sahithi.projectfinal;

/**
 * Created by sahithi on 5/19/2016.
 */
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Color;

import java.util.Random;

/**
 * Created by euphoric on 3/20/16.
 */
public class Rectangle extends Shape
{
    String shapeType;
    protected int x1;
    protected int y1;
    protected int x2;
    protected int y2;


    public Rectangle(Context context, int x1 , int y1, int x2, int y2)
    {
        super(context);

        this.border = new Paint();
        this.fill = new Paint();
        shapeType="RECTANGLE";

        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = x2;


    }

    @Override
    public void onDraw(Canvas canvas) {

        border.setStyle(Paint.Style.STROKE);
        border.setStrokeWidth(10);
        fill.setStyle(Paint.Style.FILL);





        canvas.drawRect(x1,y1,x2,y2,border);
        canvas.drawRect(x1,y1,x2,y2,fill);



    }

    public String getShapeType()
    {
        return shapeType;
    }

}
