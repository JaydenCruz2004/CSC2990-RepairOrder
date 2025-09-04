package com.example.repairorder;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class RepairOrderActivity extends AppCompatActivity {

    double numbers =0.0;

    TextView totalTV;
    TextView subtotalTV;
    TextView taxTV;
    Button submitB; //step 1 create button

    EditText orderET;

    EditText techET;

    EditText paintET;
    EditText partsET;

    EditText laborET;

//step 2 create listener
    View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {// step 4 write the code to do things



            String orderTypeValue = orderET.getText().toString();
            String techValue = techET.getText().toString();

            String paintValue = paintET.getText().toString();
            String partsValue = partsET.getText().toString();
            String laborValue = laborET.getText().toString();

            Integer paint = Integer.getInteger(paintValue);
            Integer parts = Integer.getInteger(partsValue);
            Integer labor = Integer.getInteger(laborValue);

            int subtotal = paint + parts + labor;
            String v1 = "$" + subtotal;
            subtotalTV.setText(v1);

            double tax = subtotal * 0.00926;

            String v2 = String.valueOf('$' + tax);
            taxTV.setText(v2);

            double totalofSum = subtotal + tax;
            totalTV.setText((int) totalofSum);

            taxTV.setText((int) totalofSum);



        }
    };

    //onCreate is the starting point of the activity
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
        totalTV = findViewById(R.id.textViewSub2);
        subtotalTV = findViewById(R.id.textViewTotal2);
        submitB = findViewById(R.id.buttonSubmit);
        taxTV = findViewById(R.id.textViewTax2);
        orderET = findViewById(R.id.editTextOrderType);
        techET = findViewById(R.id.editTextTech);


        paintET = findViewById(R.id.editTextPaints);
        partsET = findViewById(R.id.editTextParts);
        laborET = findViewById(R.id.editTextLabor);

        //step 3: register the listener to the button
        submitB.setOnClickListener(buttonListener);



        String value = totalTV.getText().toString();

        Log.i("TEST", value);


    }
}