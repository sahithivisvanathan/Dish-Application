package com.example.sahithi.projectfinal;

public class Token {
    public final static int ASSIGMENT_OPERATOR = 0;
    public final static  int PLUS = 1;
    public final static int MINUS = 2;
    public final static int MULTIPLY = 3;
    public final static int DIVIDE = 4;
    public final static int OPEN_PAREN = 5;
    public final static int CLOSE_PAREN = 6;
    public final static int INT_LITERAL = 7;
    public final static int VARIABLE = 8;
    public final static int KEYWORD = 9;
    public final static int RECT_FUNCTION = 10;
    public final static int CIRCLE_FUNCTION = 11;
    public final static  int SEMICOLON = 12;


    public final int token;
    public final String sequence;


    public Token(int token, String sequence) {
        super();
        this.token = token;
        this.sequence = sequence;
    }
}