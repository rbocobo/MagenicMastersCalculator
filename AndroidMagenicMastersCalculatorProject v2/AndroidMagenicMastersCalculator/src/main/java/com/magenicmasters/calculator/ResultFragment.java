package com.magenicmasters.calculator;

//import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by RhoderickB on 3/5/14.
 */
public class ResultFragment extends Fragment {
    View mTheView;
    TextView mResultTextView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mTheView = inflater.inflate(R.layout.fragment_result, container, false);
        mResultTextView = (TextView) mTheView.findViewById(R.id.resultTextView);
        initButtons();
        return mTheView;
    }

    public void setResult(String result){
        mResultTextView.setText(result);
    }

    private void initButtons() {

        Button clearButton = (Button) mTheView.findViewById(R.id.clearButton);
        Button calcButton = (Button) mTheView.findViewById(R.id.calcButton);
        Button readButton = (Button) mTheView.findViewById(R.id.buttonReadData);


        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleClearButton((Button) view);
            }
        });

        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleCalcButton((Button) view);
            }
        });

        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleReadButton((Button) view);
            }
        });
    }

    private void handleReadButton(Button view) {
        OnResultButtonsClickedListener listener = (OnResultButtonsClickedListener) getActivity();
        listener.OnReadDataButtonClicked();
    }

    private void handleCalcButton(Button view) {
        OnResultButtonsClickedListener listener = (OnResultButtonsClickedListener) getActivity();
        listener.OnShowCalculatorButtonClicked();
        //Intent intent = new Intent(this, CalculatorActivity.class);
        //startActivityForResult(intent, CALCULATOR_REQUEST);
    }

    private void handleClearButton(Button view) {
        OnResultButtonsClickedListener listener = (OnResultButtonsClickedListener) getActivity();
        listener.OnClearButtonClicked();
        //TextView textview = (TextView) findViewById(R.id.resultTextView);
        //textview.setText(R.string.defaultValTextView);
    }
}
