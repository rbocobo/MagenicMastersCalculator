package com.magenicmasters.calculator;

//import android.app.Activity;
//import android.app.FragmentManager;
//import android.support.v4.app.FragmentActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class CalculatorActivity extends ActionBarActivity
implements OnCalculationResultChangedListener{

    public  static final String RESULT_EXTRA = "101";
    private static final String FORMULA_STATE = "formula_state";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }




    private void onClickMenuClear(MenuItem menuItem) {
        FragmentManager fm =  getSupportFragmentManager();  //getFragmentManager();
        CalculatorFragment cf = (CalculatorFragment) fm.findFragmentById(R.id.calcfragment);
        cf.Clear();
    }

    private void onClickMenuCancel(MenuItem menuItem) {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putString(FORMULA_STATE, getmFormulaEditText().getText().toString());
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.calculator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_cancel:
                onClickMenuCancel(item);
                return true;
            case R.id.action_clear:
                onClickMenuClear(item);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void OnCalculationResultChanged(String result) {
        FragmentManager fm = getSupportFragmentManager(); //getFragmentManager();
        ResultFragment resultFragment = (ResultFragment) fm.findFragmentById(R.id.resultfragment);
        if(resultFragment != null)
            resultFragment.setResult(result);
        else{
            Intent resultIntent = new Intent();
            resultIntent.putExtra(RESULT_EXTRA,result );
            setResult(RESULT_OK, resultIntent);
            finish();
        }
    }
}
