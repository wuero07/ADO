package com.example.ado;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DeleteActivity extends AppCompatActivity {

    private EditText editTextId;
    private Button buttonSearch, buttonDelete, buttonReturn;
    private TextView textViewDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        editTextId = findViewById(R.id.editTextId);
        buttonSearch = findViewById(R.id.buttonSearch);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonReturn = findViewById(R.id.buttonReturn);
        textViewDetails = findViewById(R.id.textViewDetails);

        buttonSearch.setOnClickListener(v -> {
            String id = editTextId.getText().toString();
            searchSale(id);
        });

        buttonDelete.setOnClickListener(v -> {
            String id = editTextId.getText().toString();
            deleteSale(id);
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

    private void deleteSale(String id) {
        DatabaseHelper db = new DatabaseHelper(this);
        db.deleteSale(id);
    }
}
