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
    Button bResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        entrada = (TextView) findViewById(R.id.entrada);
        salida = (TextView) findViewById(R.id.salida);
        op = (TextView) findViewById(R.id.tvOperador);

        bResultado = (Button) findViewById(R.id.btnRes);
        bResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operacion(op1, op2);
            }
        });

        op1 = 0;
        op2 = 0;
        memoria = 0;
        operacion = 'n';
        realizaOperacion = false;
        obtieneResultado = false;
    }

    //Introducción de números
    public void entradaNumeros(View view) {
        if (obtieneResultado) {
            entrada.setText("");
            salida.setText("");
            obtieneResultado = false;
        }
        entrada.append(((Button)view).getText());
        salida.append(((Button)view).getText());
    }

    //Se pulsa un operado
    public void pulsaOperador(View view) {
        if (!realizaOperacion) {
            op1 = Double.parseDouble(entrada.getText().toString());
            salida.setText(String.valueOf(op1));
            entrada.setText("");
            realizaOperacion = true;
        } else {
            op2 = Double.parseDouble(entrada.getText().toString());
            operacion(op1, op2);
        }
    }

    //Se pulsa el cambio de signo
    public void masMenos(View view) {
        char signo = entrada.getText().charAt(0);
        String in = entrada.getText().toString();
        if (signo != '-')
            entrada.setText('-' + in);
        else if (signo == '-') {
            in = in.substring(1, in.length()-1);
            entrada.setText(in);
        }
    }

    //Se realiza la operacion
    public void operacion(double op1, double op2) {
        switch (operacion) {
            case 's':
                entrada.setText(String.valueOf(op1 + op2));
                break;
            case 'r':
                entrada.setText(String.valueOf(op1 - op2));
                break;
            case 'm':
                entrada.setText(String.valueOf(op1 * op2));
                break;
            case 'd':
                entrada.setText(String.valueOf(op1/op2));
                break;
        }
    }

    public void trigonom(View view) {

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

    public void limpiarTodo(View view) {
        entrada.setText("");
        salida.setText("");
        op1 = 0;
        op2 = 0;
        operacion = 'n';
        realizaOperacion = false;
        obtieneResultado = false;
    }

    public void limpiaEntrada(View view) {
        entrada.setText("");
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
