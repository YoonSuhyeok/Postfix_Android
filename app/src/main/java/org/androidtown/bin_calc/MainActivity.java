package org.androidtown.bin_calc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editview ;
    TextView textview ;

    int tokenlength;
    stack_op op;
    stack_number nu;
    String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editview = (EditText)findViewById(R.id.edit_view);
        textview = (TextView)findViewById(R.id.Text_view);
        Button button_plus = (Button)findViewById(R.id.Button_Plus);
        Button button_sub  = (Button)findViewById(R.id.Button_Sub);
        Button button_muti = (Button)findViewById(R.id.Button_Mutl);
        Button button_div  = (Button)findViewById(R.id.Button_Div);
        Button button_reset = (Button)findViewById(R.id.Button_Reset);
        Button button_result = (Button)findViewById(R.id.Button_result);
        Button button_left = (Button)findViewById(R.id.Button_width_Left);
        Button button_rigth = (Button)findViewById(R.id.Button_width_Right);
        Button button_del  = (Button)findViewById(R.id.Button_Del);
        Button button_dot  = (Button)findViewById(R.id.Button_Dot);
        Button button_0    = (Button)findViewById(R.id.Button_0);
        Button button_1    = (Button)findViewById(R.id.Button_1);
        Button button_2    = (Button)findViewById(R.id.Button_2);
        Button button_3    = (Button)findViewById(R.id.Button_3);
        Button button_4    = (Button)findViewById(R.id.Button_4);
        Button button_5    = (Button)findViewById(R.id.Button_5);
        Button button_6    = (Button)findViewById(R.id.Button_6);
        Button button_7    = (Button)findViewById(R.id.Button_7);
        Button button_8    = (Button)findViewById(R.id.Button_8);
        Button button_9    = (Button)findViewById(R.id.Button_9);

        button_0.setOnClickListener(operClickListener);
        button_1.setOnClickListener(operClickListener);
        button_2.setOnClickListener(operClickListener);
        button_3.setOnClickListener(operClickListener);
        button_4.setOnClickListener(operClickListener);
        button_5.setOnClickListener(operClickListener);
        button_6.setOnClickListener(operClickListener);
        button_7.setOnClickListener(operClickListener);
        button_8.setOnClickListener(operClickListener);
        button_9.setOnClickListener(operClickListener);

        button_plus.setOnClickListener(operClickListener);
        button_sub.setOnClickListener(operClickListener);
        button_muti.setOnClickListener(operClickListener);
        button_div.setOnClickListener(operClickListener);
        button_reset.setOnClickListener(operClickListener);
        button_result.setOnClickListener(operClickListener);
        button_left.setOnClickListener(operClickListener);
        button_rigth.setOnClickListener(operClickListener);
        button_del.setOnClickListener(operClickListener);
        button_dot.setOnClickListener(operClickListener);
        op = new stack_op();
        nu = new stack_number();
        number = "";
        tokenlength=0;
    }

    public Button.OnClickListener operClickListener = new Button.OnClickListener(){
      @Override
      public void onClick(View v){

        switch(v.getId()) {
            case R.id.Button_0:
                editview.setText(editview.getText().toString() + 0);
                number += "0";
                working_result();
                break;
            case R.id.Button_1:
                editview.setText(editview.getText().toString() + 1);
                number += "1";
                working_result();
                break;
            case R.id.Button_2:
                editview.setText(editview.getText().toString() + 2);
                number += 2;
                working_result();
                break;
            case R.id.Button_3:
                editview.setText(editview.getText().toString() + 3);
                number += 3;
                working_result();
                break;
            case R.id.Button_4:
                editview.setText(editview.getText().toString() + 4);
                number += 4;
                working_result();
                break;
            case R.id.Button_5:
                editview.setText(editview.getText().toString() + 5);
                number += 5;
                working_result();
                break;
            case R.id.Button_6:
                editview.setText(editview.getText().toString() + 6);
                number += 6;
                working_result();
                break;
            case R.id.Button_7:
                editview.setText(editview.getText().toString() + 7);
                number += 7;
                working_result();
                break;
            case R.id.Button_8:
                editview.setText(editview.getText().toString() + 8);
                number += 8;
                working_result();
                break;
            case R.id.Button_9:
                editview.setText(editview.getText().toString() + 9);
                number += 9;
                working_result();
                break;


            case R.id.Button_result:
                working_result();
                editview.setText("");
                break;

            case R.id.Button_Plus:
                if(number.length()>0) {
                    operator_overlap();
                    editview.setText(editview.getText().toString() + "+");
                    number += "+";
                }
                break;
            case R.id.Button_Sub:
                operator_overlap();
                editview.setText(editview.getText().toString() + "-");
                number += "-";
                break;
            case R.id.Button_Mutl:
                if(number.length()>0){
                    operator_overlap();
                    editview.setText(editview.getText().toString() + "*");
                    number += "*";
                }
                break;
            case R.id.Button_Div:
                if(number.length()>0){
                    operator_overlap();
                    editview.setText(editview.getText().toString() + "/");
                    number += "/";
                }
                break;
            case R.id.Button_Reset:
                number = "";
                editview.setText(number);
                textview.setText("");
                break;
            case R.id.Button_width_Left:
                number += "(";
                editview.setText(editview.getText().toString() + "(");
                break;
            case R.id.Button_width_Right:
                number += ")";
                editview.setText(editview.getText().toString() + ")");
                break;
            case R.id.Button_Del :
                if(number.length()>0){
                    number = number.substring(0, number.length()-1);
                    editview.setText(number);
                }
                break;
            case R.id.Button_Dot :
                number += ".";
                editview.setText(editview.getText().toString() + ".");
                break;
        }
      }

    public String[] tokenizer(String numbers){
        String[] arrys = new String[10];
        int cipher;
        int begin=0;
        int count=0;
        int left=0;
        int right=0;
        for(int i=0; i<number.length(); i++){
            switch(number.charAt(i)){

                case '+' :  cipher = number.indexOf("+", begin);
                    if(cipher>0 && (number.charAt(cipher-1)!='(')){ // 가로가 앞에 있다면 양수다 token 나누지 마라
                        //						System.out.println("hi");
                        if(cipher>0 && number.charAt(cipher-1)!=')'){
                            arrys[count] = number.substring(begin, cipher);
                            count++;
                        }
                        arrys[count] = number.substring(cipher, cipher+1);
                        count++;
                        begin = cipher+1;
                        tokenlength += 2;
                    }

                    break;

                case '-' :
                    cipher = number.indexOf("-", begin);
                    if(cipher>0 && (number.charAt(cipher-1)!='(')){ // 가로가 앞에 있다면 음수다 token 나누지 마라
                        if(cipher>0 && number.charAt(cipher-1)!=')'){//앞에 ) 아니라면 숫자 담기
                            arrys[count] = number.substring(begin, cipher);
                            count++;
                        }
                        arrys[count] = number.substring(cipher, cipher + 1);
                        count++;
                        begin = cipher + 1;
                        tokenlength += 2;
                    }
                    break;

                case '*' : cipher = number.indexOf("*", begin);
                    if(cipher>0 && number.charAt(cipher-1)!=')'){
                        arrys[count] = number.substring(begin, cipher);
                        count++;
                    }
                    arrys[count] = number.substring(cipher, cipher+1);
                    count++;
                    begin = cipher+1;
                    tokenlength += 2;
                    break;

                case '/' :cipher = number.indexOf("/", begin);
                    if(cipher>0 && number.charAt(cipher-1)!=')'){
                        arrys[count] = number.substring(begin, cipher);
                        count++;
                    }
                    arrys[count] = number.substring(cipher, cipher+1);
                    count++;
                    begin = cipher+1;
                    tokenlength += 2;
                    break;

                case '(' :
                    cipher = number.indexOf("(", begin);
                    if(cipher>0&&!(number.charAt(cipher-1)=='+'||number.charAt(cipher-1)=='-'||number.charAt(cipher-1)=='*'||number.charAt(cipher-1)=='/')){
                        arrys[count] = number.substring(begin, cipher); count++;
                        arrys[count] = "*";	count++;
                        tokenlength += 2;
                    }
                    arrys[count] = number.substring(cipher, cipher+1);
                    count++;
                    begin = cipher+1;left++;
                    tokenlength += 1;
                    break;

                case ')' :
                    cipher = number.indexOf(")", begin);
                    arrys[count] = number.substring(begin, cipher); count++;
                    arrys[count] = number.substring(cipher, cipher+1);
                    count++;
                    if(number.length()>cipher&&!(number.charAt(cipher+1)=='+'||number.charAt(cipher+1)=='-'||number.charAt(cipher+1)=='*'||number.charAt(cipher+1)=='/')){
                        arrys[count] = "*";	count++;
                        tokenlength += 2;
                    }
                    begin = cipher+1;
                    right++;
                    tokenlength += 1;
                    break;

            }


        }

        arrys[count] = number.substring(begin, number.length()); count++;
        for(int i=right; i<left; i++){
            arrys[count] = ")";
            count++;
            tokenlength += 1;
        }

        return arrys;

    }

    public void operator_overlap(){
       if(number.length()>0 && (number.charAt(number.length()-1) == '+' || number.charAt(number.length()-1) == '-' || number.charAt(number.length()-1) == '*' || number.charAt(number.length()-1) == '/')) {
                number = number.substring(0, number.length()-1);
                editview.setText(number);
        }
    }

    public void working_result(){

            String[] hi = op.Postfix(tokenizer(number), tokenlength);


//            int count = op.who_length();
//            double result = nu.result(hi, count);
//
//            textview.setText(Double.toString(result));

        tokenlength=0;


        String text ="";
        for(String i: hi)
            text+=i;

        textview.setText(text);

        String please = "";
        for(String i: tokenizer(number))
            please += " " + i;

        Toast.makeText(getApplicationContext(),
                please , Toast.LENGTH_LONG).show();

    }

    };


}



