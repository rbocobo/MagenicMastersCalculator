package com.magenicmasters.calculator;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.TextView;

public class ResultDisplayActivity extends Activity {

    private static final String RESULT_STATE = "result_state_key";

    TextView mResultTextView;
    public TextView getmResultTextView() {
        if(mResultTextView == null) {
            mResultTextView = (TextView) findViewById(R.id.resultTextView);
        }
        return mResultTextView;
    }



    public static final int CALCULATOR_REQUEST = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultdisplayactivity_main);

        initButtons();

        if (savedInstanceState != null) {
            restoreState(savedInstanceState);
        }

/*
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        */
    }

    private void restoreState(Bundle savedInstanceState)
    {
        getmResultTextView().setText(savedInstanceState.getString(RESULT_STATE));
    }

    private void initButtons() {

        Button clearButton = (Button) findViewById(R.id.clearButton);
        Button calcButton = (Button) findViewById(R.id.calcButton);

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
    }

    private void handleCalcButton(Button view) {
        Intent intent = new Intent(this, CalculatorActivity.class);
        startActivityForResult(intent, CALCULATOR_REQUEST);
    }

    private void handleClearButton(Button view) {
        TextView textview = (TextView) findViewById(R.id.resultTextView);
        textview.setText(R.string.defaultValTextView);
    }



//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.result_display, menu);
//        return true;
//    }
//
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//
//        outState.putString(RESULT_STATE, getmResultTextView().getText().toString());
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if(requestCode == CALCULATOR_REQUEST)
//        {
//            if(resultCode == RESULT_OK)
//            {
//
//                getmResultTextView().setText( getString(R.string.resultTextView).concat(data.getStringExtra(CalculatorActivity.RESULT_EXTRA)));
//            }
//            else
//                getmResultTextView().setText(R.string.defaultValTextView);
//        }
//
//
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        switch (item.getItemId()) {
//            case R.id.action_cancel:
//                return true;
//            case R.id.action_clear:
//                return  true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    /**
     * A placeholder fragment containing a simple view.

    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
    */
}
