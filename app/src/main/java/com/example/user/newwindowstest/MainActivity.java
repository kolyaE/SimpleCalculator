package com.example.user.newwindowstest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String input="";
    float resultInt,inputInt1, inputInt2, memorized;
    int operationCode;
    TextView resultTextView;
    Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9,
            buttonAC, buttonMRec, buttonMPlus, buttonDiv, buttonMulti, buttonMin, buttonPlus, buttonResult, buttonDot;
    protected static final int OPERATION_NOT_FOUND = 0;
    protected static final int OPERATION_MIN = 1;
    protected static final int OPERATION_PLUS = 2;
    protected static final int OPERATION_MULTI = 3;
    protected static final int OPERATION_DIV = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = (TextView) findViewById(R.id.resultTextView);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        buttonAC = (Button) findViewById(R.id.buttonAC);
        buttonMRec = (Button) findViewById(R.id.buttonMRec);
        buttonMPlus = (Button) findViewById(R.id.buttonMPlus);
        buttonDiv = (Button) findViewById(R.id.buttonDiv);
        buttonMulti = (Button) findViewById(R.id.buttonMulti);
        buttonMin = (Button) findViewById(R.id.buttonMin);
        buttonPlus = (Button) findViewById(R.id.buttonPlus);
        buttonResult = (Button) findViewById(R.id.buttonResult);
        buttonDot = (Button) findViewById(R.id.buttonDot);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.buttonAC:
                        clearTextView();
                        inputInt1=0;
                        inputInt2=0;
                        break;
                    case R.id.button0:
                        decimalPress("0");
                        break;
                    case R.id.button1:
                        decimalPress("1");
                        break;
                    case R.id.button2:
                        decimalPress("2");
                        break;
                    case R.id.button3:
                        decimalPress("3");
                        break;
                    case R.id.button4:
                        decimalPress("4");
                        break;
                    case R.id.button5:
                        decimalPress("5");
                        break;
                    case R.id.button6:
                        decimalPress("6");
                        break;
                    case R.id.button7:
                        decimalPress("7");
                        break;
                    case R.id.button8:
                        decimalPress("8");
                        break;
                    case R.id.button9:
                        decimalPress("9");
                        break;
                    case R.id.buttonDot:
                        if (!resultTextView.getText().equals("")) {
                            decimalPress(".");
                        }
                        else {
                            decimalPress("0.");
                        }
                        break;
                    case R.id.buttonMin:
                        operationPress(OPERATION_MIN);
                        break;
                    case R.id.buttonPlus:
                        operationPress(OPERATION_PLUS);
                        break;
                    case R.id.buttonMulti:
                        operationPress(OPERATION_MULTI);
                        break;
                    case R.id.buttonDiv:
                        operationPress(OPERATION_DIV);
                        break;
                    case R.id.buttonResult:
                        if (!resultTextView.getText().equals("") && operationCode!=OPERATION_NOT_FOUND) {
                            inputInt2 = Float.parseFloat(resultTextView.getText().toString());
                            input = "";
                            calculate(inputInt1, inputInt2);
                        }
                        break;
                    case R.id.buttonMPlus:
                        if (!resultTextView.getText().equals("")) {
                            memorized = Float.parseFloat(resultTextView.getText().toString());
                            clearTextView();
                            Toast.makeText(MainActivity.this, R.string.saved, Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.buttonMRec:
                        String memorizedString = memorized+"";
                        resultTextView.setText(memorizedString);
                        break;
                }
            }
        };
        button0.setOnClickListener(onClickListener);
        button1.setOnClickListener(onClickListener);
        button2.setOnClickListener(onClickListener);
        button3.setOnClickListener(onClickListener);
        button4.setOnClickListener(onClickListener);
        button5.setOnClickListener(onClickListener);
        button6.setOnClickListener(onClickListener);
        button7.setOnClickListener(onClickListener);
        button8.setOnClickListener(onClickListener);
        button9.setOnClickListener(onClickListener);
        buttonPlus.setOnClickListener(onClickListener);
        buttonMin.setOnClickListener(onClickListener);
        buttonMulti.setOnClickListener(onClickListener);
        buttonDiv.setOnClickListener(onClickListener);
        buttonResult.setOnClickListener(onClickListener);
        buttonDot.setOnClickListener(onClickListener);
        buttonAC.setOnClickListener(onClickListener);
        buttonMPlus.setOnClickListener(onClickListener);
        buttonMRec.setOnClickListener(onClickListener);
    }

    protected void calculate(float inputInt1, float inputInt2) {
        switch (operationCode) {
            case OPERATION_MIN:
                resultInt = inputInt1 - inputInt2;
                setTextView(resultInt);
                break;
            case OPERATION_PLUS:
                resultInt = inputInt1 + inputInt2;
                setTextView(resultInt);
                break;
            case OPERATION_MULTI:
                resultInt = inputInt1 * inputInt2;
                setTextView(resultInt);
                break;
            case OPERATION_DIV:
                if (inputInt2!=0) {
                    resultInt = inputInt1 / inputInt2;
                    setTextView(resultInt);
                }
                else {
                    Toast.makeText(this, R.string.Div0, Toast.LENGTH_LONG).show();
                    clearTextView();
                }
                break;
        }
        operationCode=OPERATION_NOT_FOUND;
    }

    protected void decimalPress(String number) {
        input = input+number;
        resultTextView.setText(input);
    }

    protected void operationPress(int code) {
        if (!resultTextView.getText().equals("")) {
            inputInt1 = Float.parseFloat(resultTextView.getText().toString());
            clearTextView();
            operationCode = code;
        }
        else if (code == OPERATION_MIN) {
            input = "-";
            resultTextView.setText(input);
        }
    }

    protected void clearTextView() {
        input = "";
        resultTextView.setText("");
    }

    protected void setTextView(float resultInt) {
        resultTextView.setText(getResources().getString(R.string.blank, resultInt));
    }
}
