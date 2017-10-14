package org.androidtown.bin_calc;

import java.util.Stack;

/**
 * Created by coolt on 2017-09-17.
 */

public class stack_number {

    public double result(String[] PostFix, int count){
        Stack<Double> number = new Stack<Double>();

        double number_1, number_2;


        for(int i=0; i<count; i++){
            String token = PostFix[i];

            if(token.equals("+")){
                number_1 = number.pop();
                try{
                    number_2 = number.pop();
                    number.push(number_1 + number_2);
                }
                catch(Exception e){
                }

            }
            else if(token.equals("-")){
                number_1 = number.pop();
                try{
                    number_2 = number.pop();
                    number.push(number_2 - number_1);
                }
                catch(Exception e){
                }

            }
            else if(token.equals("/")){
                number_1 = number.pop();
                try{
                    number_2 = number.pop();
                    number.push(number_2 / number_1 );
                }
                catch(Exception e){
                }

            }
            else if(token.equals("*")){
                number_1 = number.pop();
                try{
                    number_2 = number.pop();
                    number.push(number_1 * number_2);
                }
                catch(Exception e){
                }

            }
            else{
                number.push(Double.parseDouble(token));
            }
        }

        return number.pop();
    }
}
