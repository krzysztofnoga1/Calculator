package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void exitButton(View view){
        this.finish();
        System.exit(0);
    }

    public void goToSimpleCalculator(View view){
        Intent intent=new Intent(this, SimpleCalcActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void goToAdvancedCalculator(View view){
        Intent intent=new Intent(this, AdvancedCalcActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void goToAbout(View view){
        Intent intent=new Intent(this, AboutActivity.class);
        startActivity(intent);
        this.finish();
    }
}