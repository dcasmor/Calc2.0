package com.example.dcasm.calc20;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    TextView entrada, salida;
    String operador;
    double resultado, memoria;
    boolean memo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        entrada = (TextView) findViewById(R.id.entrada);
        salida = (TextView) findViewById(R.id.salida);

        resultado = 0;
        memoria = 0;
        operador = "";
        memo = false;
    }

    protected void pulsarNumero(String numero) {
        if (entrada.getText().toString().equals("0"))
            entrada.setText(numero);
        else
            entrada.setText(entrada.getText() + numero);
    }

    protected void pulsaComa() {
        if (!entrada.getText().toString().contains("."))
            entrada.append(".");
    }

    protected void pulsarMemoria(String mem) {
        switch (mem) {
            case "MC":
                memoria = 0;
                memo = false;
                Toast.makeText(getApplicationContext(), "Memoria borrada", Toast.LENGTH_SHORT).show();
                break;
            case "MR":
                if (!memo) {
                    try {
                        memoria = Double.parseDouble(entrada.getText().toString());
                        memo = true;
                    } catch (NumberFormatException nfe) {
                        Toast.makeText(this, "Valor de memoria no válido", Toast.LENGTH_SHORT);
                        memo = false;
                    }
                }
                else if (memo)
                    entrada.setText(String.valueOf(memoria));
                break;
        }
    }

    protected void pulsarOperacion(String operacion) {
        switch (operacion) {
            case "=":
                if (resultado!=0 && !operador.equals("") && !entrada.getText().toString().equals("0"))
                    calcularResultado();
                break;
            case "C":
                resultado = 0;
                operador = "";
                entrada.setText("0");
                salida.setText("");
                break;
            case "CE":
                entrada.setText("0");
                break;
            default:
                if (!entrada.getText().toString().equals("0")) {
                    resultado = Double.parseDouble(entrada.getText().toString());
                    operador = operacion;
                    entrada.setText("0");
                }
        }
    }

    protected void calcularResultado() {
        switch (operador) {
            case "+":
                salida.setText(resultado + "+" + entrada.getText().toString());
                resultado += Double.parseDouble(entrada.getText().toString());
                break;
            case "-":
                salida.setText(resultado + "-" + entrada.getText().toString());
                resultado -= Double.parseDouble(entrada.getText().toString());
                break;
            case "*":
                salida.setText(resultado + "*" + entrada.getText().toString());
                resultado *= Double.parseDouble(entrada.getText().toString());
                break;
            case "/":
                salida.setText(resultado + "/" + entrada.getText().toString());
                resultado /= Double.parseDouble(entrada.getText().toString());
                break;
        }
        entrada.setText(String.valueOf(resultado));
    }

    protected void cambiaSigno() {
        if (!entrada.getText().toString().contains("-")) {
            entrada.setText("-" + entrada.getText().toString());
        } else
            entrada.setText(entrada.getText().toString().substring(1, entrada.getText().toString().length()));
    }

    public void pulsar(View view) {
        switch (view.getId()) {

            //Memoria y entrada
            case R.id.btnMC:
                pulsarMemoria("MC");
                break;
            case R.id.btnMR:
                pulsarMemoria("MR");
                break;
            case R.id.btnC:
                pulsarOperacion("C");
                break;
            case R.id.btnCE:
                pulsarOperacion("CE");
                break;

            //Números
            case R.id.btn1:
                pulsarNumero("1");
                break;
            case R.id.btn2:
                pulsarNumero("2");
                break;
            case R.id.btn3:
                pulsarNumero("3");
                break;
            case R.id.btn4:
                pulsarNumero("4");
                break;
            case R.id.btn5:
                pulsarNumero("5");
                break;
            case R.id.btn6:
                pulsarNumero("6");
                break;
            case R.id.btn7:
                pulsarNumero("7");
                break;
            case R.id.btn8:
                pulsarNumero("8");
                break;
            case R.id.btn9:
                pulsarNumero("9");
                break;
            case R.id.btn0:
                pulsarNumero("0");
                break;

            //Operadores
            case R.id.btnPlus:
                pulsarOperacion("+");
                break;
            case R.id.btnMin:
                pulsarOperacion("-");
                break;
            case R.id.btnMult:
                pulsarOperacion("*");
                break;
            case R.id.btnDiv:
                pulsarOperacion("/");
                break;

            //Coma y cambio de signo
            case R.id.btnComma:
                pulsaComa();
                break;
            case R.id.btnSign:
                cambiaSigno();
                break;

            //Resultado
            case R.id.btnRes:
                pulsarOperacion("=");
                break;

            //Trigonometricas
            case R.id.btnSin:
                try {
                    salida.setText("sin(" + entrada.getText().toString() + ")");
                    entrada.setText("" + Math.sin(Double.parseDouble(entrada.getText().toString())));
                } catch (NumberFormatException nfe) {}
                break;
            case R.id.btnCos:
                try {
                    salida.setText("cos(" + entrada.getText().toString() +")");
                    entrada.setText("" + Math.cos(Double.parseDouble(entrada.getText().toString())));
                } catch (NumberFormatException nfe) {}
                break;
            case R.id.btnTan:
                try {
                    salida.setText("tan(" + entrada.getText().toString() + ")");
                    entrada.setText(String.valueOf((Math.sin(Double.parseDouble(entrada.getText()
                            .toString()))) / Math.cos(Double.parseDouble(entrada.getText().toString()))));
                } catch (NumberFormatException nfe) {}
                break;
        }
    }

    protected void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putDouble("resultado", Double.parseDouble(entrada.getText().toString()));
        savedInstanceState.putString("operador", operador);
        savedInstanceState.putString("salida", salida.getText().toString());
        savedInstanceState.putDouble("memoria", memoria);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        double res = savedInstanceState.getDouble("resultado");
        resultado = res;
        operador = savedInstanceState.getString("operador");
        memoria = savedInstanceState.getDouble("memoria");
        salida.setText(savedInstanceState.getString("salida"));
        if (Integer.parseInt(String.valueOf(res).substring(String.valueOf(res).indexOf(".")+1))==Integer.parseInt("0"))
            entrada.setText(String.valueOf(res).substring(0, String.valueOf(res).indexOf(".")));
        else
            entrada.setText("" + res);
    }

    /*@Override
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
    }*/
}
