package com.example.sahithi.projectfinal;


public class BaseOperator implements Operator {

    private final String symbol;
    private final boolean rightAssociative;
    private int precedence;

    public BaseOperator(String symbol, boolean  rightAssociative, int precedence){
        this.symbol = symbol;
        this.rightAssociative =rightAssociative;
        this.precedence = precedence;
    }

    @Override
    public boolean isRightAssociative(){
        return rightAssociative;
    }

    @Override // compares precdence in order to correctly evaluate
    public int comparePrecedence(Operator operator) {
        if (operator instanceof BaseOperator){
            BaseOperator temp = (BaseOperator) operator;
            return precedence > temp.precedence ? 1 :
                    temp.precedence == precedence ? 0 : -1;
        }  else {
            //defer the comparision
            return -operator.comparePrecedence(this);
        }
    }

    public String get_Symbol() {
        return symbol;
    }

    public String toString(){

        return symbol;
    }

}