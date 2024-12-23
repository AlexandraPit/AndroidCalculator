package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private static final char ADDITION = '+';
    private static final char SUBTRACTION = '-';
    private static final char MULTIPLICATION = '*';
    private static final char DIVISION = '/';
    private static final char PERCENT = '%';

    private char currentSymbol;

    private double firstValue = Double.NaN; // Initialize as NaN
    private double secondValue;
    private TextView inputDisplay, outputDisplay;
    private DecimalFormat decimalFormat;
    private MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9,
            buttonDot, buttonAdd, buttonSub, buttonMultiply, buttonDivide, buttonPercent, buttonClear, buttonOFF, buttonEqual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        decimalFormat = new DecimalFormat("#.##########");

        inputDisplay = findViewById(R.id.input);
        outputDisplay = findViewById(R.id.output);

        button0 = findViewById(R.id.btn0);
        button1 = findViewById(R.id.btn1);
        button2 = findViewById(R.id.btn2);
        button3 = findViewById(R.id.btn3);
        button4 = findViewById(R.id.btn4);
        button5 = findViewById(R.id.btn5);
        button6 = findViewById(R.id.btn6);
        button7 = findViewById(R.id.btn7);
        button8 = findViewById(R.id.btn8);
        button9 = findViewById(R.id.btn9);

        buttonAdd = findViewById(R.id.addition);
        buttonSub = findViewById(R.id.subtraction);
        buttonDivide = findViewById(R.id.division);
        buttonDot = findViewById(R.id.dot);
        buttonMultiply = findViewById(R.id.multiply);
        buttonClear = findViewById(R.id.clear);
        buttonOFF = findViewById(R.id.off);
        buttonEqual = findViewById(R.id.equal);
        buttonPercent = findViewById(R.id.percent);

        // Number buttons
        setNumberButton(button0, "0");
        setNumberButton(button1, "1");
        setNumberButton(button2, "2");
        setNumberButton(button3, "3");
        setNumberButton(button4, "4");
        setNumberButton(button5, "5");
        setNumberButton(button6, "6");
        setNumberButton(button7, "7");
        setNumberButton(button8, "8");
        setNumberButton(button9, "9");

        // Operator buttons
        buttonAdd.setOnClickListener(view -> handleOperator(ADDITION, "+"));
        buttonSub.setOnClickListener(view -> handleOperator(SUBTRACTION, "-"));
        buttonMultiply.setOnClickListener(view -> handleOperator(MULTIPLICATION, "x"));
        buttonDivide.setOnClickListener(view -> handleOperator(DIVISION, "/"));
        buttonPercent.setOnClickListener(view -> handleOperator(PERCENT, "%"));

        // Other buttons
        buttonDot.setOnClickListener(view -> inputDisplay.setText(inputDisplay.getText() + "."));
        buttonClear.setOnClickListener(view -> clear());
        buttonOFF.setOnClickListener(view -> finish());
        buttonEqual.setOnClickListener(view -> performCalculation());
    }

    // Helper method for setting number button click actions
    private void setNumberButton(MaterialButton button, String number) {
        button.setOnClickListener(view -> inputDisplay.setText(inputDisplay.getText() + number));
    }

    // Handle operator button click (addition, subtraction, multiplication, division, percent)
    private void handleOperator(char operator, String symbol) {
        if (!Double.isNaN(firstValue)) {
            secondValue = Double.parseDouble(inputDisplay.getText().toString());
            performCalculation();
            currentSymbol = operator;
            outputDisplay.setText(decimalFormat.format(firstValue) + symbol);
            inputDisplay.setText(null); // Clear input for the next number
        } else {
            firstValue = Double.parseDouble(inputDisplay.getText().toString());
            currentSymbol = operator;
            outputDisplay.setText(decimalFormat.format(firstValue) + symbol);
            inputDisplay.setText(null);
        }
    }

    // Perform calculation based on current symbol and values
    private void performCalculation() {
        if (inputDisplay.getText().length() > 0) {
            secondValue = Double.parseDouble(inputDisplay.getText().toString());

            switch (currentSymbol) {
                case ADDITION:
                    firstValue = firstValue + secondValue;
                    break;
                case SUBTRACTION:
                    firstValue = firstValue - secondValue;
                    break;
                case MULTIPLICATION:
                    firstValue = firstValue * secondValue;
                    break;
                case DIVISION:
                    if (secondValue != 0) {
                        firstValue = firstValue / secondValue;
                    } else {
                        outputDisplay.setText("Error: Div by 0");
                        return;
                    }
                    break;
                case PERCENT:
                    firstValue = firstValue % secondValue;
                    break;
            }

            outputDisplay.setText(decimalFormat.format(firstValue));
            inputDisplay.setText(""); // Clear input
        }
    }

    // Clear the calculator
    private void clear() {
        firstValue = Double.NaN;
        secondValue = 0;
        inputDisplay.setText("");
        outputDisplay.setText("");
    }
}
