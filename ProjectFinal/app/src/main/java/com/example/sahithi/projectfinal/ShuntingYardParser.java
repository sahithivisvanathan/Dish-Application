package com.example.sahithi.projectfinal;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


public class ShuntingYardParser {

    private final Map<String, Operator> operators;

    private static void addNode(Stack<ASTNode> stack, String operator) {
        final ASTNode rightASTNode = stack.pop();
        final ASTNode leftASTNode = stack.pop();
        stack.push(new ASTNode(operator, leftASTNode, rightASTNode));
    }public class BaseOperator {
    }


    public ShuntingYardParser(Collection<Operator> operators) {
        this.operators = new HashMap<>();
        for(Operator o : operators) {
            this.operators.put(o.get_Symbol(), o);
        }
    }


    public ASTNode convertInfixNotationToAST(final String input) {
        final Stack<String> operatorStack = new Stack<>();
        final Stack<ASTNode> operandStack = new Stack<>();
        final String[] chars = input.split(" ");
        main:
        for(String c : chars) {
            String popped;
            System.out.println(c);
            switch(c) {
                case " ":
                    break;
                case "(":
                    operatorStack.push("(");
                    break;
                case ")":
                    while(!operatorStack.isEmpty()) {
                        popped = operatorStack.pop();
                        if("(" == popped) {
                            continue main;
                        } else {
                            addNode(operandStack, popped);
                        }
                    }
                    throw new IllegalStateException("Unbalanced right " +
                            "parentheses");
                default:
                    if(operators.containsKey(c)) {
                        final Operator o1 = operators.get(c);
                        Operator o2;
                        while(!operatorStack.isEmpty() && null != (o2 =
                                operators.get(operatorStack.peek()))) {
                            if((!o1.isRightAssociative() &&
                                    0 == o1.comparePrecedence(o2)) ||
                                    o1.comparePrecedence(o2) < 0) {
                                operatorStack.pop();
                                addNode(operandStack, o2.get_Symbol());
                            } else {
                                break;
                            }
                        }
                        operatorStack.push(c);
                    } else {
                        operandStack.push(new ASTNode(c, null, null));
                    }
                    break;
            }
        }
        while(!operatorStack.isEmpty()) {
            addNode(operandStack, operatorStack.pop());
        }
        return operandStack.pop();
    }


}