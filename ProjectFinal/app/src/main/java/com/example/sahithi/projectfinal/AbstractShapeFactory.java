package com.example.sahithi.projectfinal;

public class AbstractShapeFactory {

    public enum Style {
        BW,RG,PG,RB,BG
    }



    public ShapeFactory getShapeFactory(Style style) {

        switch (style) {
            case BW:
                return new BlackWhiteFactory();
            case RG:
                return new RedGreenFactory();
            case PG:
                return new PurpleGreenFactory();
            case RB:
                return new RedBlueFactory();
            case BG:
                return new BlueGreenFactory();
        }
        return null;
    }
}

