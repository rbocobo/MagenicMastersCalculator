package com.magenicmasters.calculator;

//import android.app.Fragment;
import android.support.v4.app.Fragment;
//import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
//import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by RhoderickB on 3/5/14.
 */
public class CalculatorFragment extends Fragment {

    View mTheView;

    EditText mFormulaEditText;
    public EditText getmFormulaEditText() {
        if(mFormulaEditText == null)
            mFormulaEditText = (EditText) mTheView.findViewById(R.id.calcEditText);
        return mFormulaEditText;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mTheView == null )
        {
            mTheView = inflater.inflate(R.layout.fragment_calculator, container, false);
            int[] buttonIds = {R.id.button0, R.id.button1, R.id.button2, R.id.button3,
                    R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9,
                    R.id.buttonAdd, R.id.buttonSubt, R.id.buttonMult, R.id.buttonDiv, R.id.clearButton, R.id.buttonEq};


            Init();
        }

        return mTheView;
    }

    private void Init()
    {
        int[] buttonIds = {R.id.button0, R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9,
                R.id.buttonAdd, R.id.buttonSubt, R.id.buttonMult, R.id.buttonDiv, R.id.buttonClear, R.id.buttonEq};

        for(int i=0;i<buttonIds.length;i++)
        {
            Button button = (Button) mTheView.findViewById(buttonIds[i]);
            if(button!=null){
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        calcButtonClicked((Button) view);
                    }
                });
            }
        }

    }


    private void calcButtonClicked(Button view) {

        int buttonId = view.getId();
        switch (buttonId) {
            case R.id.button0:
                getmFormulaEditText().append("0");
                break;
            case R.id.button1:
                getmFormulaEditText().append("1");
                break;
            case R.id.button2:
                getmFormulaEditText().append("2");
                break;
            case R.id.button3:
                getmFormulaEditText().append("3");
                break;
            case R.id.button4:
                getmFormulaEditText().append("4");
                break;
            case R.id.button5:
                getmFormulaEditText().append("5");
                break;
            case R.id.button6:
                getmFormulaEditText().append("6");
                break;
            case R.id.button7:
                getmFormulaEditText().append("7");
                break;
            case R.id.button8:
                getmFormulaEditText().append("8");
                break;
            case R.id.button9:
                getmFormulaEditText().append("9");
                break;
            case R.id.buttonClear:
                getmFormulaEditText().setText("");
                break;
            case R.id.buttonAdd:
                getmFormulaEditText().append("+");
                break;
            case R.id.buttonSubt:
                getmFormulaEditText().append("-");
                break;
            case R.id.buttonMult:
                getmFormulaEditText().append("*");
                break;
            case R.id.buttonDiv:
                getmFormulaEditText().append("/");
                break;
            default:
                String r = calculate();
                OnCalculationResultChangedListener listener = (OnCalculationResultChangedListener) getActivity();
                listener.OnCalculationResultChanged(r);
                //Intent resultIntent = new Intent();
                //resultIntent.putExtra(RESULT_EXTRA,r );
                //setResult(RESULT_OK, resultIntent);
                //finish();

                //Toast.makeText(this, calculate(), Toast.LENGTH_LONG).show();
                //calculate();
        }
    }

    private  boolean isValidFormula()
    {
        return getmFormulaEditText().getText().toString().matches("^[0-9]+[+\\-*/][0-9]*$");
    }

    private String calculate()
    {
        if(isValidFormula())
        {
            Pattern p = Pattern.compile("[+\\-*/]");
            Matcher m = p.matcher(getmFormulaEditText().getText().toString());

            if(m.find())
            {
                String operator = (String)m.group();

                String[] operands = getmFormulaEditText().getText().toString().split("[+\\-*/]");

                Double operand1 = Double.parseDouble(operands[0]);
                Double operand2 = Double.parseDouble(operands[1]);

                Double result;

                if(operator.equals("+"))                {
                    result = operand1 + operand2;                }
                else if(operator.equals("-"))
                    result = operand1 - operand2 ;
                else if(operator.equals("*"))
                    result = operand1 * operand2 ;
                else
                {
                    if(operand2 > 0)
                        result = operand1 / operand2 ;
                    else
                        return "Cannot Divide by Zero";

                }
                return  Double.toString(result);
            }
            else
            {
                //Toast.makeText(this, "Operator not found", Toast.LENGTH_LONG).show();
                return "Invalid Formula";
            }
        }
        else
            return "Invalid Formula";
    }

    public void Clear()
    {
        getmFormulaEditText().setText("");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_clear) {
            onActionClear(item);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onActionClear(MenuItem item) {
        Clear();
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.calculator, menu);
//        super.onCreateOptionsMenu(menu, inflater);
//    }
}
