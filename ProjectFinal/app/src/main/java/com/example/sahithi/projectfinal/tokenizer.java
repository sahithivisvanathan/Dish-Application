package com.example.sahithi.projectfinal;



import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class tokenizer
{
    public tokenizer(){
        symbols = new LinkedList<>();
        tokens = new LinkedList<>();
        createFPLanguage();

    }



    private class Symbol{
        public final Pattern regex;
        public final int token;

        public Symbol(Pattern regex, int token){
            super();
            this.regex = regex;
            this.token = token;
        }
    }


    private LinkedList<Symbol> symbols;
    private LinkedList<Token> tokens;



    public void tokenize(String string) {
        String[] statement = string.split(("\\s+"));
        //String lastGroup = statement[statement.length - 1];

        for(String s : statement){
            boolean found = false;

            for (Symbol sym : symbols){
                Matcher matcher = sym.regex.matcher(s);
                if(matcher.find()){
                    found = true;
                    tokens.add(new Token(sym.token,s));
                    System.out.println("Success " + s +" against " + sym.regex.pattern());
                    break;
                }
            }
            if(!found){
                //TODO make real exception class
                System.out.println("Unexepcted Input");
            }
        }
    }

    public void addSymbol(String regex, int token){
        symbols.add(new Symbol(Pattern.compile("^(" + regex + ")"), token));

    }

    public void createFPLanguage(){
        addSymbol("\\+",Token.PLUS);
        addSymbol("\\-",Token.MINUS);
        addSymbol("=",Token.ASSIGMENT_OPERATOR);
        addSymbol("int",Token.KEYWORD);
        addSymbol("\\*",Token.MULTIPLY);
        addSymbol("/",Token.DIVIDE);
        addSymbol("\\(",Token.OPEN_PAREN);
        addSymbol("\\)",Token.CLOSE_PAREN);
        addSymbol("\\d+", Token.INT_LITERAL);
        addSymbol("[a-z]+",Token.VARIABLE);
        addSymbol("rect", Token.RECT_FUNCTION);
        addSymbol("circle",Token.CIRCLE_FUNCTION);
        addSymbol(";",Token.SEMICOLON);
    }

    public LinkedList<Token> getTokens(){


        return tokens;
    }

    public void clear(){
        tokens.clear();
    }
}