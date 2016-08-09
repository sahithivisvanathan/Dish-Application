package com.example.sahithi.projectfinal;

/**
 * Created by sahithi on 5/19/2016.
 */
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by euphoric on 3/20/16.
 */
public abstract class Shape extends View
{
    protected Paint border;
    protected Paint fill;
    protected float alpha;


    public Shape (Context context)
    {
        super(context);
    }

    void setShapeAlpha(float alpha)
    {
        this.setAlpha(alpha);
    }

    float getShapeAlpha()
    {
        return this.getAlpha();
    }

    void setStyle(int borderColor, int fillColor){
        border.setColor(borderColor);
        fill.setColor(fillColor);

    }

    void removeShape()
    {
        this.setVisibility(GONE);
    }

    abstract String getShapeType();

    @Override
    public abstract void onDraw(Canvas canvas);
}
