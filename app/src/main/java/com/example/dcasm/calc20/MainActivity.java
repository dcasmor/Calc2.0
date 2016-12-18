package com.example.dcasm.calc20;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.*;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    TextView entrada, salida, op;
    char operacion;
    double op1, op2, memoria;
    boolean realizaOperacion, obtieneResultado;
    private Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, bMr, bMc, bC, bCe, bTan, bSin, bCos,
            bSigno, bRes, bComa, bMas, bMenos, bMult, bDiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        entrada = (TextView) findViewById(R.id.entrada);
        salida = (TextView) findViewById(R.id.salida);
        op = (TextView) findViewById(R.id.tvOperador);

        b1 = (Button) findViewById (R.id.btn1);
        b2 = (Button) findViewById (R.id.btn2);
        b3 = (Button) findViewById (R.id.btn3);
        b4 = (Button) findViewById (R.id.btn4);
        b5 = (Button) findViewById (R.id.btn5);
        b6 = (Button) findViewById (R.id.btn6);
        b7 = (Button) findViewById (R.id.btn7);
        b8 = (Button) findViewById (R.id.btn8);
        b9 = (Button) findViewById (R.id.btn9);
        b0 = (Button) findViewById (R.id.btn0);
        bMr = (Button) findViewById(R.id.btnMR);
        bMc = (Button) findViewById(R.id.btnMC);
        bC = (Button) findViewById(R.id.btnC);
        bCe = (Button) findViewById(R.id.btnCE);
        bTan = (Button) findViewById(R.id.btnTan);
        bCos = (Button) findViewById(R.id.btnCos);
        bSin = (Button) findViewById(R.id.btnSin);
        bSigno = (Button) findViewById(R.id.btnSign);
        bRes = (Button) findViewById(R.id.btnRes);
        bComa = (Button) findViewById(R.id.btnComma);
        bMas = (Button) findViewById(R.id.btnPlus);
        bMenos = (Button) findViewById(R.id.btnMin);
        bMult = (Button) findViewById(R.id.btnMult);
        bDiv = (Button) findViewById(R.id.btnDiv);

        op1 = 0;
        op2 = 0;
        memoria = 0;
        realizaOperacion = false;
        obtieneResultado = false;
    }

    //Introducción de números
    protected void entradaNumeros(View view) {
        if (obtieneResultado) {
            entrada.setText("");
            salida.setText("");
            obtieneResultado = false;
        }
        entrada.append(((Button)view).getText());
        salida.append(((Button)view).getText());
    }

    //Se pulsa un operado
    protected void pulsaOperador(View view) {
        if (!realizaOperacion) {
            op1 = Double.parseDouble(entrada.getText().toString());
            realizaOperacion = true;
        } else {
            if (realizaOperacion) {
                op2 = Double.parseDouble(entrada.getText().toString());
                operacion(op1, op2);
            }
        }
    }

    //Se pulsa el cambio de signo
    protected void masMenos(View view) {
        char signo = entrada.getText().charAt(0);
        String in = entrada.getText().toString();
        String ou;
        if (signo == '-') {
            for (int i = 1; i <= entrada.getText().length(); i++) {

            }
        }
        if (signo != '-')
            entrada.setText("-" + in);
    }

    //Se realiza la operacion
    public void operacion(double op1, double op2) {
        switch (operacion) {
            case 's':
                entrada.setText("" + (op1 + op2));
                break;
            case 'r':
                entrada.setText("" + (op1 - op2));
                break;
            case 'm':
                entrada.setText("" + (op1 * op2));
                break;
            case 'd':
                entrada.setText("" + (op1/op2));
                break;
        }
    }

    //Gestión de la memoria
    public void memoria(View view) {
        Log.i("MEMORIA", String.valueOf(memoria));
        if (memoria == 0) {
            memoria = Double.parseDouble(salida.getText().toString());
            Toast.makeText(getApplicationContext(), "Memoria almacenada", Toast.LENGTH_SHORT).show();
        }
        else
            entrada.setText(String.valueOf(memoria));
    }

    public void borraMemoria(View view) {
        memoria = 0;
        Toast.makeText(this, "Memoria borrada", Toast.LENGTH_SHORT).show();
    }

    class Pulsa implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
            }
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
