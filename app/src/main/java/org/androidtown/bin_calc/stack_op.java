package org.androidtown.bin_calc;

import java.util.Stack;

/**
 * Created by coolt on 2017-09-17.
 */

public class stack_op {
    int index;
    public String[] Postfix(String[] a, int count) {

        Stack<String> operator = new Stack<String>();

        String[] PostFix = new String[10];
        index = 0;
        String top = "";

        for (int i = 0; i <= count; i++) {
            switch(a[i]){
                case "+":case "-": case "*":case "/":
                    while(!operator.isEmpty() &&
                            who_first(a[i]) <= who_first(operator.peek())) {
                        PostFix[index] = operator.pop(); index++;
                    }
                    operator.push(a[i]);
                    break;

                case "(" :
                    operator.push(a[i]);
                    break;
                case ")" :
                    top = operator.pop();
                    while (!top.equals("(")){
                        PostFix[index] = top; index++;

                        try{
                            top = operator.pop();
                        }
                        catch(Exception e){
                        }

                    }
                    break;
                default: PostFix[index] = a[i]; index++; break;
            }
        }

        while(!operator.isEmpty()) {
            PostFix[index] = operator.pop();
            index++;
        }




        return PostFix;
    }


    public int who_first(String a){
        switch(a){
            case "(" : case ")" : return 0;
            case "+" : case "-" : return 1;
            case "*" : case "/" : return 2;
        }
        return 1;
    }

    public int who_length(){
        return index;
    }

}
