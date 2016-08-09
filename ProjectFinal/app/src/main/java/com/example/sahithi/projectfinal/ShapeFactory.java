package com.example.sahithi.projectfinal;

/**
 * Created by sahithi on 5/19/2016.
 */
import android.content.Context;
//factory design pattern used to create an object
// without having to expose the creation logic
abstract class ShapeFactory{

    public enum ShapeType{
        RECTANGLE,CIRCLE
    }
    public abstract Shape getShape(Context context,int x,int y,int radius);
    public abstract Shape getShape(Context context, int x1,int y1,int x2, int y2);


}

class RedGreenFactory extends ShapeFactory {
    public Shape getShape(Context context, int x, int y, int radius) {
        Circle circle = new Circle(context, x, y, radius);
        circle.setStyle(0xffff0000, 0xff00ff00); //red and green Respectivlet
        return circle;
    }

    public Shape getShape(Context context, int x1, int y1, int x2, int y2) {
        Rectangle rectangle = new Rectangle(context, x1, y1, x2, y2);
        rectangle.setStyle(0xffff0000, 0xff00ff00);
        return rectangle;
    }
}

//0xff00ff00, 0xff0000ff
class BlueGreenFactory extends ShapeFactory {
    public Shape getShape(Context context, int x, int y, int radius) {
        Circle circle = new Circle(context, x, y, radius);
        circle.setStyle(0xff00ff00, 0xff0000ff); //red and green Respectivlet
        return circle;
    }

    public Shape getShape(Context context, int x1, int y1, int x2, int y2) {
        Rectangle rectangle = new Rectangle(context, x1, y1, x2, y2);
        rectangle.setStyle(0xff00ff00, 0xff0000ff);
        return rectangle;
    }
}

//0xff000000, 0xffffffff
class BlackWhiteFactory extends ShapeFactory {
    public Shape getShape(Context context, int x, int y, int radius) {
        Circle circle = new Circle(context, x, y, radius);
        circle.setStyle(0xff000000, 0xffffffff); //red and green Respectivlet
        return circle;
    }

    public Shape getShape(Context context, int x1, int y1, int x2, int y2) {
        Rectangle rectangle = new Rectangle(context, x1, y1, x2, y2);
        rectangle.setStyle(0xff000000, 0xffffffff);
        return rectangle;
    }
}

//0xffff00ff, 0xff00ff00
class PurpleGreenFactory extends ShapeFactory {
    public Shape getShape(Context context, int x, int y, int radius) {
        Circle circle = new Circle(context, x, y, radius);
        circle.setStyle(0xffff00ff, 0xff00ff00); //red and green Respectivlet
        return circle;
    }

    public Shape getShape(Context context, int x1, int y1, int x2, int y2) {
        Rectangle rectangle = new Rectangle(context, x1, y1, x2, y2);
        rectangle.setStyle(0xffff00ff, 0xff00ff00);
        return rectangle;
    }
}

//0xffff0000, 0xff0000ff
class RedBlueFactory extends ShapeFactory {
    public Shape getShape(Context context, int x, int y, int radius) {
        Circle circle = new Circle(context, x, y, radius);
        circle.setStyle(0xffff0000, 0xff0000ff); //red and green Respectivlet
        return circle;
    }

    public Shape getShape(Context context, int x1, int y1, int x2, int y2) {
        Rectangle rectangle = new Rectangle(context, x1, y1, x2, y2);
        rectangle.setStyle(0xffff0000, 0xff0000ff);
        return rectangle;
    }
}
