package com.example.sahithi.projectfinal;

/**
 * Created by sahithi on 5/19/2016.
 */
public class ASTNode {

    private final String value;
    private final ASTNode leftASTNode;
    private final ASTNode rightASTNode;

    public ASTNode(String value, ASTNode leftASTNode, ASTNode rightASTNode) {
        this.value = value;
        this.leftASTNode = leftASTNode;
        this.rightASTNode = rightASTNode;
    }

    public String getValue() {
        return value;
    }

    public ASTNode getLeftASTNode() {
        return leftASTNode;
    }

    public ASTNode getRightASTNode() {
        return rightASTNode;
    }


}