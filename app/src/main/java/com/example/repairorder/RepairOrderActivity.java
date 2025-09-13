package com.example.repairorder;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class RepairOrderActivity extends AppCompatActivity {

    TextView totalTV;
    TextView subtotalTV;
    TextView taxTV;
    Button submitB;

    EditText orderET;
    EditText techET;
    EditText paintET;
    EditText partsET;
    EditText laborET;

    Spinner orderSpinner;
    String selectedOrderType = "";


    View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String paintValue = paintET.getText().toString();
            String partsValue = partsET.getText().toString();
            String laborValue = laborET.getText().toString();

            int paint = Integer.parseInt(paintValue);
            int parts = Integer.parseInt(partsValue);
            int labor = Integer.parseInt(laborValue);

            double subtotal = paint + parts + labor;
            String v1 = "$" + String.format("%.2f", subtotal);
            subtotalTV.setText(v1);

            double tax = subtotal * 0.0926;
            String v2 = "$" + String.format("%.2f", tax);
            taxTV.setText(v2);

            double totalOfSum = subtotal + tax;
            String v3 = "$" + String.format("%.2f", totalOfSum);
            totalTV.setText(v3);

            Log.i("DEBUG", "Order type chosen: " + selectedOrderType);
        }
    };

    AdapterView.OnItemSelectedListener spinnerListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            selectedOrderType = parent.getItemAtPosition(position).toString();
            Log.i("DEBUG", "Selected Order Type: " + selectedOrderType);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            selectedOrderType = "";
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_repair_order);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        orderSpinner = findViewById(R.id.spinnerOrderType);

        // Create the ArrayList with  order types
        ArrayList<String> orderList = new ArrayList<>();
        orderList.add("Oil Change");
        orderList.add("Tire Replacement");
        orderList.add("Paint Job");
        orderList.add("Brake Service");

        // Create the adapter and set it to the spinner
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                orderList
        );
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orderSpinner.setAdapter(spinnerAdapter);
        orderSpinner.setOnItemSelectedListener(spinnerListener);


        subtotalTV = findViewById(R.id.textViewSub2);
        totalTV = findViewById(R.id.textViewTotal2);
        taxTV = findViewById(R.id.textViewTax2);
        submitB = findViewById(R.id.buttonSubmit);

        orderET = findViewById(R.id.editTextOrderType);
        techET = findViewById(R.id.editTextTech);
        paintET = findViewById(R.id.editTextPaints);
        partsET = findViewById(R.id.editTextParts);
        laborET = findViewById(R.id.editTextLabor);

        submitB.setOnClickListener(buttonListener);

        Log.i("TEST", "Activity started");
    }
}
