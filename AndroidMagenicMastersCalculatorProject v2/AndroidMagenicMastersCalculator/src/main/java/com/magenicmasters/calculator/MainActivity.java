package com.magenicmasters.calculator;


import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;


public class MainActivity extends ActionBarActivity
        implements OnResultButtonsClickedListener, 
                   OnCalculationResultChangedListener {

    private static final int CALCULATOR_REQUEST = 100;
    private static final String RESULT_STATE = "result_state_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            restoreState(savedInstanceState);
        }
    }

    @Override
    public void OnReadDataButtonClicked() {
        Intent intent = new Intent(this, ReadDataActivity.class);
        startActivity(intent);
    }

    @Override
    public void OnShowCalculatorButtonClicked() {
        Intent intent = new Intent(this, CalculatorActivity.class);
        startActivityForResult(intent, CALCULATOR_REQUEST);
    }

    @Override
    public void OnClearButtonClicked() {
        FragmentManager fm = getSupportFragmentManager(); //getFragmentManager();

        ResultFragment rm = (ResultFragment) fm.findFragmentById(R.id.resultfragment);
        rm.setResult(getString(R.string.defaultValTextView));
    }

    @Override
    public void OnCalculationResultChanged(String result) {
        FragmentManager fm = getSupportFragmentManager(); //getFragmentManager();
        ResultFragment rm = (ResultFragment) fm.findFragmentById(R.id.resultfragment);
        rm.setResult(getString(R.string.resultTextView).concat(result));
    }

        @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CALCULATOR_REQUEST)
        {
            FragmentManager fm = getSupportFragmentManager();
            ResultFragment rm = (ResultFragment) fm.findFragmentById(R.id.resultfragment);

            if(resultCode == RESULT_OK)
            {

                rm.setResult(getString(R.string.resultTextView).concat(data.getStringExtra(CalculatorActivity.RESULT_EXTRA)));
                //getmResultTextView().setText( getString(R.string.resultTextView).concat(data.getStringExtra(CalculatorActivity.RESULT_EXTRA)));
            }
            else
                rm.setResult( getString(R.string.defaultValTextView) );
                //getmResultTextView().setText(R.string.defaultValTextView);
        }


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        FragmentManager fm =  getSupportFragmentManager();//getFragmentManager();
        ResultFragment rm = (ResultFragment) fm.findFragmentById(R.id.resultfragment);
        outState.putString(RESULT_STATE, rm.mResultTextView.getText().toString());
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.calculator, menu);
//
//        return true;
//    }

    private void restoreState(Bundle savedInstanceState)
    {
        FragmentManager fm = getSupportFragmentManager();//getFragmentManager();
        ResultFragment rm = (ResultFragment) fm.findFragmentById(R.id.resultfragment);
        rm.setResult(savedInstanceState.getString(RESULT_STATE));
    }
}
