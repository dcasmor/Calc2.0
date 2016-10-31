package com.example.dcasm.calc20;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.*;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    Button btnSqr;
    EditText input;
    TextView hist;
    char o;
    double op1, op2, mem;
    boolean opera, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        input = (EditText) findViewById(R.id.input);
        hist = (TextView) findViewById(R.id.tv1);
        op1 = 0;
        op2 = 0;
        mem = 0;
        opera = false;
        result = false;
    }

    protected void numero(View view) {
        if (result) {
            input.setText("");
            hist.setText("");
        }
        input.append(((Button)view).getText());
        hist.append(((Button)view).getText());
    }

    protected void operador(View view) {
        if (!opera) {
            op1 = Double.parseDouble(input.getText().toString());
            opera = true;
        } else {
            if (opera) {
                op2 = Double.parseDouble(input.getText().toString());
                operacion(op1, op2);
            }
        }
    }

    public void operacion(double op1, double op2) {
        switch (o) {
            case 's':
                input.setText("" + (op1 + op2));
                break;
            case 'r':
                input.setText("" + (op1 - op2));
                break;
            case 'm':
                input.setText("" + (op1 * op2));
                break;
            case 'd':
                input.setText("" + (op1/op2));
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }


}
