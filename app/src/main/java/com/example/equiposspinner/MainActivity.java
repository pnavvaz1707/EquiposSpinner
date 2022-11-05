package com.example.equiposspinner;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinnerEquipoA;
    private Spinner spinnerNacional;
    private Spinner spinnerEquipoB;
    ArrayList<String> jugadoresA;
    ArrayList<String> jugadoresNacional;
    ArrayList<String> jugadoresB;
    ArrayList<String> jugadoresFijosA;
    ArrayList<String> jugadoresFijosB;
    ArrayAdapter<String> adapterEquipoA;
    ArrayAdapter<String> adapterNacional;
    ArrayAdapter<String> adapterEquipoB;
    int num;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_window);

        num = 0;

        spinnerEquipoA = findViewById(R.id.spinnerEquipoA);
        spinnerNacional = findViewById(R.id.spinnerNacional);
        spinnerEquipoB = findViewById(R.id.spinnerEquipoB);

        jugadoresFijosA = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.jugadoresA)));
        jugadoresFijosB = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.jugadoresA)));

        jugadoresA = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.jugadoresA)));

        adapterEquipoA = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, jugadoresA);
        spinnerEquipoA.setAdapter(adapterEquipoA);

        spinnerEquipoA.setOnItemSelectedListener(this);


        jugadoresB = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.jugadoresB)));

        adapterEquipoB = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, jugadoresB);
        spinnerEquipoB.setAdapter(adapterEquipoB);

        spinnerEquipoB.setOnItemSelectedListener(this);


        jugadoresNacional = new ArrayList<>();

        adapterNacional = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, jugadoresNacional);
        spinnerNacional.setAdapter(adapterNacional);

        spinnerNacional.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (num >= 3) {
            int id = ((Spinner) view.getParent()).getId();
            switch (id) {

                case R.id.spinnerEquipoA:
                    System.out.println("JugadorA a borrar --> " + jugadoresA.get(i));

                    jugadoresNacional.add(adapterView.getSelectedItem().toString());
                    jugadoresA.remove(i);
                    adapterEquipoA.notifyDataSetChanged();
                    break;

                case R.id.spinnerEquipoB:
                    System.out.println("JugadorB a borrar --> " + jugadoresB.get(i));

                    jugadoresNacional.add(adapterView.getSelectedItem().toString());
                    jugadoresB.remove(i);
                    adapterEquipoB.notifyDataSetChanged();
                    break;

                case R.id.spinnerNacional:
                    System.out.println("EL SELECCIONADO --> " + adapterView.getSelectedItem().toString());
                    if (jugadoresFijosA.contains(adapterView.getSelectedItem().toString())) {
                        System.out.println("Ha entrao");
                        jugadoresA.add(adapterView.getSelectedItem().toString());
                        adapterEquipoA.notifyDataSetChanged();

                    } else if (jugadoresFijosB.contains(adapterView.getSelectedItem().toString())) {
                        jugadoresB.add(adapterView.getSelectedItem().toString());
                        adapterEquipoB.notifyDataSetChanged();
                    }
                    jugadoresNacional.remove(i);
                    adapterNacional.notifyDataSetChanged();
                    break;
            }
        }
        num++;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
