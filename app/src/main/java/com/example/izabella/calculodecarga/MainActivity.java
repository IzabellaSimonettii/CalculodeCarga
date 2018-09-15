package com.example.izabella.calculodecarga;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.View;

import java.util.ArrayList;

import java.util.List;

public class MainActivity extends Activity {

    Spinner estados;
    Spinner cargas;

    EditText pesoCarga;

    TextView valorImposto;
    TextView valorLiqCarga;
    TextView valorPesoCarga;

    Button btnCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        estados = findViewById(R.id.spEstado);
        cargas = findViewById(R.id.spCarga);
        pesoCarga = findViewById(R.id.etPesoCarga);
        valorImposto = findViewById(R.id.tvValorImposto);
        valorLiqCarga = findViewById(R.id.tvValorLiqCarga);
        valorPesoCarga = findViewById(R.id.tvPesoCargaKg);
        btnCalcular = findViewById(R.id.btCalcular);
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calcular();
            }
            });

        //Spinner para selecionar o codigo do estado
        ArrayAdapter<CharSequence> estadosAdapter = ArrayAdapter.createFromResource(this,
                R.array.estados, android.R.layout.simple_spinner_item);
        //Spinner para selecionar o codigo da carga
        ArrayAdapter<CharSequence> cargasAdapter = ArrayAdapter.createFromResource(this,
                R.array.cargas, android.R.layout.simple_spinner_item);

        //Dropdown do spinner
        estadosAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cargasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        estados.setAdapter(estadosAdapter);
        cargas.setAdapter(cargasAdapter);
    }



    private void Calcular() {

        //Bloco para definir o valor da Aliquota
        int estadoSelection = Integer.parseInt(estados.getSelectedItem().toString());

        double vlAliquota = 0;
        switch (estadoSelection) {
            case 1:
                vlAliquota = 0.35;
                break;
            case 2:
                vlAliquota = 0.25;
                break;
            case 3:
                vlAliquota = 0.15;
                break;
            case 4:
                vlAliquota = 0.05;
                break;
            case 5:
                vlAliquota = 0.00;
        }

        //Valor da carga
        int codCarga = cargas.getSelectedItemPosition();
        double vlCarga = 0.00;
        switch (codCarga){
            case 0:
                vlCarga = 100;
                break;
            case 1:
                vlCarga = 250;
                break;
            case 2:
                vlCarga = 340;
                break;
        }

        //Recuperar vari[avel peso.
        String pToneladas = pesoCarga.getText().toString();
        double pesoToneladas = Double.parseDouble(pToneladas);
        pesoToneladas = pesoToneladas*1000;

        double vlImposto = pesoToneladas * vlAliquota;
        double vlBruto = pesoToneladas * vlCarga;
        double vlLiquido = vlBruto - vlImposto;
        double pKilo = pesoToneladas;

        valorImposto.setText(String.valueOf(vlImposto));
        valorLiqCarga.setText(String.valueOf(vlLiquido));
        valorPesoCarga.setText(String.valueOf(pKilo));
    }
}
