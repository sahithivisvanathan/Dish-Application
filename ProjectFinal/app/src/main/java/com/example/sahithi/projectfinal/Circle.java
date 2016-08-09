package com.example.sahithi.projectfinal;

/**
 * Created by sahithi on 5/19/2016.
 */


        import android.content.Context;
        import android.graphics.Canvas;
        import android.graphics.Color;
        import android.graphics.Paint;

        import java.util.Random;

/**
 * Created by euphoric on 3/20/16.
 */
public class Circle extends Shape {
    String shapeType = "Circle";
    Random r;
    protected int x;
    protected int y;
    protected int radius;

    public Circle(Context context,int x,int y, int radius){
        super(context);
        this.r = new Random();
        this.border = new Paint();
        this.fill = new Paint();

        this.x = x;
        this.y = y;
        this.radius = radius;

    }

    @Override
    public void onDraw(Canvas canvas) {

        border.setStyle(Paint.Style.STROKE);
        border.setStrokeWidth(10);
        fill.setStyle(Paint.Style.FILL);

        canvas.drawCircle(x, y, radius, border);
        canvas.drawCircle(x, y, radius, fill);

        /*
        int color = Color.rgb(red, green, blue);
        this.paint.setAntiAlias(true);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setColor(color);
        this.paint.setStyle(Paint.Style.FILL);
        int x = r.nextInt(canvas.getWidth()-50) + 50;
        int y = r.nextInt(canvas.getHeight()-50)+ 50;
        int radius = r.nextInt(300) +10;
        canvas.drawCircle(x, y, radius, this.paint);
        */

    }
    @Override
    public String getShapeType () {
        return shapeType;
    }


}