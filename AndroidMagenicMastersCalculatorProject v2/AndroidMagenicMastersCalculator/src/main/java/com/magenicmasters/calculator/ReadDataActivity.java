package com.magenicmasters.calculator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.apache.http.util.EncodingUtils.getBytes;

public class ReadDataActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_data);

        initButton();

    }

    private void initButton() {
        Button readButton = (Button) findViewById(R.id.readDataButton);
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReadFile();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.read_data, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_return) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void ReadFile() {

        final ProgressBar pb = (ProgressBar) findViewById(R.id.progressBar);

        pb.setVisibility(View.VISIBLE);
        new AsyncTask<String, Integer, String>() {
            @Override
            protected String doInBackground(String... params) {
                AssetManager am = getAssets();
                StringBuffer sb = new StringBuffer("");
                //StringBuilder stringBuilder = new StringBuilder("");

                try {

//                    long iii = afd.getLength();
//                    FileInputStream fis = afd.createInputStream();

                    //long ffff =  getResources().openRawResourceFd(R.raw.sample).getLength();

                    InputStream fi = am.open("samplefile.dat");  //
                    InputStreamReader isr = new InputStreamReader(fi);
                    BufferedReader br = new BufferedReader(isr);

                    String readString = br.readLine();


                    while (readString != null) {
                        int ilen = readString.getBytes().length;

                        sb.append(readString);
                        sb.append(System.getProperty("line.separator"));
                        readString = br.readLine();
                    }

                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }

                return sb.toString();
            }


            @Override
            protected void onProgressUpdate(Integer... values) {
                //
                pb.setProgress(values[0]);
            }

            @Override
            protected void onPostExecute(String data) {
                pb.setVisibility(View.INVISIBLE);
                showData(data);
                showCompletedDialog();

            }


        }.execute("");


        //return sb.toString();
    }

    private void showData(String data) {
        final TextView mReadDataTextView = (TextView) findViewById(R.id.readDataTextView);
        mReadDataTextView.setText(data);

    }

    public  void showCompletedDialog()
    {
        final AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        dlgAlert.setMessage("Completed reading file");
        dlgAlert.setTitle("Read File");
        dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dlgAlert.create().show();
    }

}
