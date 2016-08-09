package com.example.sahithi.projectfinal;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity
{
    HashMap<String,Integer> symbolTable = new HashMap<>();
    int circleCount = 0;
    int rectCount = 0;
    String stream;
    String[] commands;
    Vector<Shape> vizShapes = new Vector<>();
    TextView tv;
    EditText terminal;
    int currentStyle = 0;
    Pattern rectRegex;
    Pattern circleRegex;
    Matcher matcher;
    LinkedList<Token> tokens;
    Iterator<Token> sequence;
    tokenizer lex;




    public String statementCon(LinkedList<Token> tokens){
        String expression = "";
        boolean rightside = false;
        for (Token tok : tokens){
            if(rightside == true){
                if(tok.token == Token.VARIABLE){
                    int temp = lookup(tok.sequence);
                    expression = expression + temp;
                } else {
                    expression = expression + tok.sequence;
                }
            }
            if(tok.token == Token.ASSIGMENT_OPERATOR){
                rightside = true;
            }
        }
        return expression;
    }

    boolean checkLHS(String str){
        Pattern temp = Pattern.compile("(int)(\\s+)([a-z]+)(\\s+)(=)");
        Matcher mTemp =  temp.matcher(str);

        if (mTemp.find()){
            return true;
        } else {
            return false;
        }
    }

    public int lookup (String key){
        int value;
        try {
            value = Integer.parseInt(key);
        } catch (Exception e){
            if (symbolTable.containsKey(key)){
                value = symbolTable.get(key);
            } else {
                // default value for incorrect variables;
                value = 0;
            }
        }
        return value;
    }

    void adjustShapeAlpha()
    {
        int i = 0;
        int target = -1;

        for(Shape element:vizShapes){
            element.setShapeAlpha((element.getShapeAlpha()-(float).20));
            if(element.getShapeAlpha() <= (float) .40) {
                element.removeShape();
                target = i;
            }
            i++;
        }

        if(target != -1){
            if(vizShapes.get(target).getShapeType().equalsIgnoreCase("CIRCLE")){
                circleCount--;
            } else {
                rectCount--;
            }
            vizShapes.removeElementAt(target);
        }
    }

    private static double evaluateAST(ASTNode tree) {
        switch(tree.getValue()) {
            case "^":
                return Math.pow(evaluateAST(tree.getLeftASTNode()),
                        evaluateAST(tree.getRightASTNode()));
            case "*":
                return evaluateAST(tree.getLeftASTNode()) * evaluateAST(tree.
                        getRightASTNode());
            case "/":
                return evaluateAST(tree.getLeftASTNode()) / evaluateAST(tree.
                        getRightASTNode());
            case "+":
                return evaluateAST(tree.getLeftASTNode()) + evaluateAST(tree.
                        getRightASTNode());
            case "-":
                return evaluateAST(tree.getLeftASTNode()) - evaluateAST(tree.
                        getRightASTNode());
            default:
                return Double.valueOf(
                        tree.getValue());
        }
    }


    void updateShapeCount()
    {
        String text = circleCount + " Circles, " + rectCount + " Rectangles " + " Style " +
                AbstractShapeFactory.Style.values()[currentStyle].name();
        tv.setText(text);
    }

    void changeStyle(){
        if(this.currentStyle == 4){
            currentStyle = 0;
        } else {
            currentStyle++;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        setContentView(R.layout.activity_main);
        terminal = (EditText) findViewById(R.id.editText);
        tv = (TextView) findViewById(R.id.Display);

        final AbstractShapeFactory factoryProducer = new AbstractShapeFactory();
        final RelativeLayout shapeDisplay = (RelativeLayout) findViewById(R.id.outputView);
        final Button compile = (Button) findViewById(R.id.compile);
        final Button btnClear = (Button) findViewById(R.id.btnClear);


        final Collection<Operator> Op = new ArrayList<>();
        lex = new tokenizer();
        tokens = new LinkedList<>();
        rectRegex = Pattern.compile("(rect)(\\s+)([a-z]+|[0-9]+)(\\s+)([a-z]+|[0-9]+)(\\s+)([a-z]+|[0-9]+)(\\s+)([a-z]+|[0-9]+)(\\s+)([a-z]+|[0-9]+)");
        circleRegex = Pattern.compile("(circle)(\\s+)([a-z]+|[0-9]+)(\\s+)([a-z]+|[0-9]+)(\\s+)([a-z]+|[0-9]+)(\\s+)([a-z]+|[0-9]+)");


        //legal operators for script expressions
        Op.add(new BaseOperator("*", false, 3));
        Op.add(new BaseOperator("/", false, 3));
        Op.add(new BaseOperator("+", false, 2));
        Op.add(new BaseOperator("-", false, 2));

        compile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int[] args = new int[10];
                stream = terminal.getText().toString();
                commands = stream.split(";");
                //trim white space
                for (String s : commands) {
                    s.trim();
                }

                for (String str : commands) {
                    String memoryLOLOLOL = "null";
                    int position = 0;
                    str.trim();
                    String strCopy = str;
                    lex.tokenize(strCopy);
                    tokens = lex.getTokens();
                    System.out.println("first token: " + tokens.getFirst().sequence);
                    switch (tokens.getFirst().sequence) {
                        case "int":
                            System.out.println(str);
                            str.trim();
                            System.out.println(tokens.size());
                            if (tokens.size() == 4) {
                                Pattern temp = Pattern.compile("(int)(\\s+)([a-z]+)(\\s+)(=)(\\s+)([0-9]+)(\\s*)");
                                matcher = temp.matcher(str);
                                System.out.println(str);
                                if (matcher.find()) {
                                    System.out.println("name of variable: " + matcher.group(2));
                                    System.out.println("Value to be added to Symbol Table: " + matcher.group(7));
                                    symbolTable.put(matcher.group(3), Integer.parseInt(matcher.group(7)));
                                }
                            } else if (checkLHS(str)) {
                                for (Token x : tokens) {
                                    if (x.token == Token.VARIABLE && position == 1) {
                                        memoryLOLOLOL = x.sequence;
                                    } else if (x.token == Token.VARIABLE && position != 1) {
                                        int t1 = symbolTable.get(x.sequence);
                                        tokens.set(position, new Token(Token.INT_LITERAL, Integer.toString(t1)));
                                    }
                                    position++;
                                }
                                String[] expressionString = str.split("=");
                                System.out.println(expressionString[1]);
                                final ShuntingYardParser expParser = new ShuntingYardParser(Op);
                                final ASTNode parseTree = expParser.convertInfixNotationToAST(expressionString[1]);
                                final double result = evaluateAST(parseTree);
                                System.out.println("result: " + result);
                                int rounded = (int) Math.round(result);
                                System.out.println("result rounded: " + rounded);

                                symbolTable.put(memoryLOLOLOL, rounded);

                            } else {
                                System.out.println("invalid Syntax");
                            }
                            tokens.clear();
                            break;
                        case "rect":
                            matcher = rectRegex.matcher(str);
                            if (matcher.find()) {
                                args[0] = lookup(matcher.group(3));
                                args[1] = lookup(matcher.group(5));
                                args[2] = lookup(matcher.group(7));
                                args[3] = lookup(matcher.group(9));
                                args[4] = lookup(matcher.group(11));

                                System.out.println(args.toString());
                                try {
                                    ShapeFactory factory = factoryProducer.getShapeFactory(AbstractShapeFactory.Style.values()[args[4]]);
                                    Shape rect = factory.getShape(view.getContext(), args[0], args[1], args[2], args[3]);
                                    vizShapes.add(rect);
                                    shapeDisplay.addView(rect);
                                    rectCount++;
                                    updateShapeCount();

                                } catch (Exception e) {
                                    tv.setText("Invalid Style");
                                }
                            }
                            tokens.clear();
                            adjustShapeAlpha();
                            break;
                        case "circle":
                            matcher = circleRegex.matcher(str);
                            if (matcher.find()) {
                                args[0] = lookup(matcher.group(3));
                                args[1] = lookup(matcher.group(5));
                                args[2] = lookup(matcher.group(7));
                                args[3] = lookup(matcher.group(9));

                                for (int x : args) {
                                    System.out.println("args: " + x);
                                }
                                try {
                                    ShapeFactory factory = factoryProducer.getShapeFactory(AbstractShapeFactory.Style.values()[args[3]]);
                                    Shape circ = factory.getShape(view.getContext(), args[0], args[1], args[2]);
                                    vizShapes.add(circ);
                                    shapeDisplay.addView(circ);
                                    circleCount++;
                                    updateShapeCount();


                                } catch (Exception e) {
                                    tv.setText("Invalid Style");
                                }
                            }
                            adjustShapeAlpha();

                            tokens.clear();

                            break;
                        default:
                            System.out.println("switch exited");
                            break;


                    }


                }


            }

        });

        btnClear.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                for (Shape x : vizShapes){
                    x.setShapeAlpha(0);
                }
                vizShapes.clear();
                rectCount = 0;
                circleCount = 0;
                terminal.setText("");
                tv.setText("");


            }
        });









/*

        btnClear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                for (Shape element : vizShapes) {
                    element.removeShape();
                }
                rectCount = 0;
                circleCount = 0;
                vizShapes.clear();
                updateShapeCount();
            }
        });
    }
*/
    }


}

