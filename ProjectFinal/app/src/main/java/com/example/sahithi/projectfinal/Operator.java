package com.example.sahithi.projectfinal;

/**
 * Created by sahithi on 5/19/2016.
 */

public interface Operator {

    boolean isRightAssociative();

    int comparePrecedence(Operator o);

    String get_Symbol();
}