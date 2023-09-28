package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.mariuszgromada.math.mxparser.Expression;

public class SimpleCalcActivity extends AppCompatActivity {

    public TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_calc);
        result=findViewById(R.id.result);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("currentEquation",result.getText().toString());
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String currentEquation=savedInstanceState.getString("currentEquation");
        result.setText(currentEquation);
    }

    public boolean checkIfSymbolValid(){
        String currentStr=result.getText().toString();
        String lastSymbol=currentStr.substring(currentStr.length()-1);
        return !lastSymbol.equals("+") && !lastSymbol.equals("÷") && !lastSymbol.equals("×") && !lastSymbol.equals("-") && !lastSymbol.equals("^") && !lastSymbol.equals(".");
    }

    public void updateTextInResult(String str){
        String previousStr=result.getText().toString();
        if(previousStr.equals("0.0") && !str.equals("^") && !str.equals("^2")){
            previousStr="";
        }
        String newStr=String.format("%s%s", previousStr, str);
        if(newStr.length()<=getResources().getInteger(R.integer.max_length)){
            result.setText(newStr);
        }
    }

    public void menuButton(View view){
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void zeroButton(View view){
        updateTextInResult("0");
    }

    public void oneButton(View view){
        updateTextInResult("1");
    }

    public void twoButton(View view){
        updateTextInResult("2");
    }

    public void threeButton(View view){
        updateTextInResult("3");
    }

    public void fourButton(View view){
        updateTextInResult("4");
    }

    public void fiveButton(View view){
        updateTextInResult("5");
    }

    public void sixButton(View view){
        updateTextInResult("6");
    }

    public void sevenButton(View view){
        updateTextInResult("7");
    }

    public void eightButton(View view){
        updateTextInResult("8");
    }

    public void nineButton(View view){
        updateTextInResult("9");
    }

    public void clearButton(View view){
        result.setText("0.0");
    }

    public void backspaceButton(View view){
        String newStr=result.getText().toString();
        newStr=newStr.substring(0,newStr.length()-1);
        result.setText(newStr);
    }

    public void divideButton(View view){
        if(checkIfSymbolValid()){
            updateTextInResult("÷");
        }
    }

    public void multiplyButton(View view){
        if(checkIfSymbolValid()){
            updateTextInResult("×");
        }
    }

    public void subtractButton(View view){
        updateTextInResult("-");
    }

    public void addButton(View view){
        if(checkIfSymbolValid() && !result.getText().toString().equals("0.0")){
            updateTextInResult("+");
        }
    }

    public void equalsButton(View view){
        String strToParse=result.getText().toString();
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

    private void addMinusAtTheBeginning(String str){
        String newStr=String.format("%s%s", "-", str);
        result.setText(newStr);
    }

    private void deleteMinusFromBeginning(String str){
        String newStr=str.substring(1);
        result.setText(newStr);
    }

    private void minusReplacement(String str, int minusIdx){
        if(minusIdx==0){
            deleteMinusFromBeginning(str);
        }
        else if(minusIdx!=str.length()-1){
            char[] chars=str.toCharArray();
            chars[minusIdx]='+';
            String newStr=String.valueOf(chars);
            result.setText(newStr);
        }
    }

    private void plusReplacement(String str, int plusIdx){
        if(plusIdx!=-1 && plusIdx!=str.length()-1){
            char[] chars=str.toCharArray();
            chars[plusIdx]='-';
            String newStr=String.valueOf(chars);
            result.setText(newStr);
        }
    }

    private void replaceLastPlusOrMinus(String str){
        int minusIdx=str.lastIndexOf("-");
        int plusIdx=str.lastIndexOf("+");
        if(minusIdx==-1 && plusIdx==-1){
            if(checkIfNumeric(str)){
                addMinusAtTheBeginning(str);
            }
        }
        else{
            if(minusIdx>plusIdx){
                minusReplacement(str, minusIdx);
            }else{
                plusReplacement(str, plusIdx);
            }

        }
    }

    public void plusMinusButton(View view){
        String currentStr=result.getText().toString();
        if(!currentStr.equals("0.0")){
            replaceLastPlusOrMinus(currentStr);
        }

    }

    private boolean checkIfNumeric(String str){
        try{
            Double.parseDouble(str);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }

    private boolean checkIfCanAddDot(){
        String currentExp=result.getText().toString();
        int idx=currentExp.lastIndexOf(".");
        if(idx!=-1){
            if(idx==currentExp.length()-1){
                return false;
            }
            String sub=currentExp.substring(idx);
            return !checkIfNumeric(sub);
        }
        return true;
    }

    public void dotButton(View view){
        if(checkIfSymbolValid() && checkIfCanAddDot()){
            updateTextInResult(".");
        }
    }
}