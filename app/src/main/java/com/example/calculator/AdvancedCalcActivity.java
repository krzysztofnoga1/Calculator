package com.example.calculator;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.mariuszgromada.math.mxparser.Expression;

public class AdvancedCalcActivity extends SimpleCalcActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_calc);
        result=findViewById(R.id.result_adv);
    }

    public void squareButton(View view){
        updateTextInResult("^2");
    }

    public void powerButton(View view){
        updateTextInResult("^");
    }

    public void sinButton(View view){
        updateTextInResult("sin(");
    }

    public void tanButton(View view){
        updateTextInResult("tan(");
    }

    public void lnButton(View view){
        updateTextInResult("ln(");
    }

    public void logButton(View view){
        updateTextInResult("log(");
    }

    public void cosButton(View view){
        updateTextInResult("cos(");
    }

    public void openSemicolonButton(View view){
        updateTextInResult("(");
    }

    public void endSemicolonButton(View view){
            updateTextInResult(")");
    }

    public void sqrtButton(View view){
            updateTextInResult("sqrt(");
    }

    private boolean checkIfCanAddPercent(){
        String currentStr=result.getText().toString();
        String lastSymbol=currentStr.substring(currentStr.length()-1);
        return !lastSymbol.equals("+") && !lastSymbol.equals("÷") && !lastSymbol.equals("×") && !lastSymbol.equals("-") && !lastSymbol.equals("^") && !lastSymbol.equals(".") && !lastSymbol.equals("%");
    }

    public void percentButton(View view){
        if(checkIfCanAddPercent()){
            updateTextInResult("%");
        }
    }

    public void equalsButtonAdv(View view){
        String strToParse=result.getText().toString();
        int idx=strToParse.indexOf("log");
        if(idx>-1){
            String str1=strToParse.substring(0,idx+1);
            String str2=strToParse.substring(idx+2);
            strToParse=String.format("%s%s",str1,str2);
        }

        Expression res=new Expression(strToParse);
        String res2=String.valueOf(res.calculate());
        if(res2.equals("NaN")){
            Context context=getApplicationContext();
            CharSequence warningMsg="Invalid input. Can't calculate.";
            int duration= Toast.LENGTH_SHORT;

            Toast warning=Toast.makeText(context, warningMsg, duration);
            warning.show();
        }else{
            result.setText(res2);
        }
    }
}