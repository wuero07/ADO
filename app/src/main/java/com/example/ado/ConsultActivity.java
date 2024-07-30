package com.example.ado;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ConsultActivity extends AppCompatActivity {

    private EditText editTextId;
    private Button buttonSearch;
    private TextView textViewDetails;
    private Button buttonReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);

        editTextId = findViewById(R.id.editTextId);
        buttonSearch = findViewById(R.id.buttonSearch);
        textViewDetails = findViewById(R.id.textViewDetails);
        buttonReturn = findViewById(R.id.buttonReturn);

        buttonSearch.setOnClickListener(v -> {
            String id = editTextId.getText().toString();
            searchSale(id);
        });

        buttonReturn.setOnClickListener(v -> {
            finish();
        });
    }

    private void searchSale(String id) {
        DatabaseHelper db = new DatabaseHelper(this);
        Cursor cursor = db.getSaleById(id);
        if (cursor.moveToFirst()) {
            String details = "ID: " + cursor.getString(0) + "\n" +
                    "Origen: " + cursor.getString(1) + "\n" +
                    "Destino: " + cursor.getString(2) + "\n" +
                    "Fecha: " + cursor.getString(3) + "\n" +
                    "Hora: " + cursor.getString(4) + "\n" +
                    "Total: " + cursor.getString(5);
            textViewDetails.setText(details);
        } else {
            textViewDetails.setText(getString(R.string.sale_not_found));
        }
        cursor.close();
    }
}
