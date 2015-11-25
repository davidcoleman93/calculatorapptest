package com.example.android.calculatorapptest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String operand1 = "";
    String operand2 = "";
    String storedMemory = "";
    String operator = "";
    boolean operatorPressed = false;
    boolean percentPressed = false;
    TextView myDisplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDisplay = (TextView) findViewById(R.id.displayView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void updateDisplay(String displayText)
    {
        myDisplay.setText(displayText);
    }

    public void nine(View view)
    {
        numberButton(9);
    }
    public void eight(View view)
    {
        numberButton(8);
    }
    public void seven(View view)
    {
        numberButton(7);
    }
    public void six(View view)
    {
        numberButton(6);
    }
    public void five(View view)
    {
        numberButton(5);
    }
    public void four(View view)
    {
        numberButton(4);
    }
    public void three(View view)
    {
        numberButton(3);
    }
    public void two(View view)
    {
        numberButton(2);
    }
    public void one(View view)
    {
        numberButton(1);
    }
    public void zero(View view)
    {
        if(operand1=="0" || operand1=="") {

        }
        else
        {
            numberButton(0);
        }
    }

    public void numberButton(int number)
    {
        operatorCheck();
        if(operand1 == "") {
            operand1 = String.valueOf(number);
        }
        else
        {
            operand1 = operand1 + String.valueOf(number);
        }

        updateDisplay(operand1);

//        if(operand1=="") {
//            operand1 = "9";
//            updateDisplay();
//        }
//        else{
//            operand1 = operand1 + "9";
//        }
    }

    public void decimalPoint(View view)
    {
        operatorCheck();
        if(operand1=="")
        {
            operand1 = "0.";
            updateDisplay(operand1);
        }
        else if (operand1.contains("."))
        {
            //Don't add another decimal point if it already has one
        }
        else
        {
            operand1 = operand1 + ".";
            updateDisplay(operand1);
        }
    }

    public void operatorCheck()
    {
        if(operatorPressed==true)
        {
            //operand2 = operand1;
            operand1 = "";
            operatorPressed = false;
        }
    }
    public void clearButton(View view)
    {
        operand1 = "";
        updateDisplay("0");
    }

    public void resetButton(View view)
    {
        operand1 = "";
        operand2 = "";
        storedMemory = "";
        operator = "";
        operatorPressed = false;
        updateDisplay("0");
    }

    public void plusButton(View view)
    {
        calculations();
        operator = "+";
        operatorButtons();
    }
    public void minusButton(View view)
    {
        calculations();
        operator = "-";
        operatorButtons();
    }
    public void multiplyButton(View view)
    {
        calculations();
        operator = "*";
        operatorButtons();
    }
    public void divideButton(View view)
    {
        calculations();
        operator = "/";
        operatorButtons();
    }

    public void equalsButton(View view)
    {
        calculations();
        operator = "";
        operatorButtons();
        //Next:
        //1. When you divide two integers and expect a double result. E.g. 11 / 2. Should return 5.5. Currently returns 5.
        //2. Repeatedly pressing an operator repeats the calculation. E.g. 10 + 2 = 12. Press + again and it adds another 2 to make it 14 and so on.
        //          N.B. Equals does not currently do this. Only +, -, * and /.
        //3. Get +/- button working and calculations are still correct when this button is used, including negative values.
    }

    public void percentButton(View view)
    {
        if(operand1 != "" && !(operand1.contains("%"))) {
            operand1 = operand1 + "%";
        }
    }

    public void posNegButton(View view)
    {
        if(operatorPressed == false) {
            if (operand1.contains("-")) {
                if(operand1.length() == 1)
                {
                    operand1 = "";
                    updateDisplay("0");
                }
                else {
                    operand1 = operand1.substring(1, operand1.length());
                    updateDisplay(operand1);
                }
            }
            else
            {
                operand1 = "-" + operand1;
                updateDisplay(operand1);
            }
        }
        else
        {
            if (operand2.contains("-")) {
                operand2 = operand2.substring(1, operand2.length());
            } else {
                operand2 = "-" + operand2;
            }
            updateDisplay(operand2);
        }

    }
    public void calculations()
    {
        if(operand2 == "")
        {
            operand2 = operand1;
        }
        else {
            switch (operator) {

                case "+":
                    addition();
                    break;
                case "-":
                    subtraction();
                    break;
                case "*":
                    multiplication();
                    break;
                case "/":
                    division();
                    break;
//                case "":
//                    break;
            }
        }
    }

    public void addition()
    {
        if(operand1.contains("%")) {
            addMinusPercent();
        }
        if(operand1.contains(".") || operand2.contains("."))
        {
            double calc = Double.parseDouble(operand2)+ Double.parseDouble(operand1);
            operand2 = String.valueOf(calc);
        }
        else {
            int calc = Integer.parseInt(operand2) + Integer.parseInt(operand1);
            operand2 = String.valueOf(calc);
        }
        updateDisplay(operand2);
    }

    public void subtraction()
    {
        if(operand1.contains("%")) {
            addMinusPercent();
        }
        if(operand1.contains(".") || operand2.contains("."))
        {
            double calc = Double.parseDouble(operand2)- Double.parseDouble(operand1);
            operand2 = String.valueOf(calc);
        }
        else {
            int calc = Integer.parseInt(operand2) - Integer.parseInt(operand1);
            operand2 = String.valueOf(calc);
        }
        updateDisplay(operand2);
    }

    public void multiplication()
    {
        if(operand1.contains("%"))
        {
            multiplyDividePercent();
        }
        if(operand1.contains(".") || operand2.contains("."))
        {
            double calc = Double.parseDouble(operand2)* Double.parseDouble(operand1);
            operand2 = String.valueOf(calc);
        }
        else {
            int calc = Integer.parseInt(operand2) * Integer.parseInt(operand1);
            operand2 = String.valueOf(calc);
        }
        updateDisplay(operand2);
    }

    public void division()
    {
        if(operand1.contains("%"))
        {
            multiplyDividePercent();
        }
        if(operand1.contains(".") || operand2.contains("."))
        {
            double calc = Double.parseDouble(operand2)/ Double.parseDouble(operand1);
            operand2 = String.valueOf(calc);
        }
        else {
            if((Integer.parseInt(operand2) % Integer.parseInt(operand1)) == 0)
            {
                int calc = Integer.parseInt(operand2) / Integer.parseInt(operand1);
                operand2 = String.valueOf(calc);
            }
            else
            {
                double calc = Double.parseDouble(operand2)/ Double.parseDouble(operand1);
                operand2 = String.valueOf(calc);
            }
        }
        updateDisplay(operand2);
    }

    public void addMinusPercent()
    {
        operand1 = operand1.substring(0,operand1.length()-1);
        double calc = Double.parseDouble(operand1);
        calc = calc / 100;
        calc = Double.parseDouble(operand2)* calc;
        operand1 = String.valueOf(calc);
    }

    public void multiplyDividePercent()
    {
        operand1 = operand1.substring(0,operand1.length()-1);
        double calc = Double.parseDouble(operand1);
        calc = calc / 100;
        operand1 = String.valueOf(calc);
    }

    public void operatorButtons()
    {
        operatorPressed = true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
