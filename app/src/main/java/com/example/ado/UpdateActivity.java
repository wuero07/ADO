package com.example.ado;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class UpdateActivity extends AppCompatActivity {

    private EditText editTextId;
    private Spinner spinnerOrigin, spinnerDestination;
    private Button buttonDate, buttonTime;
    private TextView textViewTotal;
    private Button buttonUpdate, buttonCancel, buttonReturnToMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        editTextId = findViewById(R.id.editTextId);
        spinnerOrigin = findViewById(R.id.spinnerOrigin);
        spinnerDestination = findViewById(R.id.spinnerDestination);
        buttonDate = findViewById(R.id.buttonDate);
        buttonTime = findViewById(R.id.buttonTime);
        textViewTotal = findViewById(R.id.textViewTotal);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonCancel = findViewById(R.id.buttonCancel);
        buttonReturnToMenu = findViewById(R.id.buttonReturnToMenu);


        ArrayAdapter<String> originAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, new String[]{
                "México - $1500", "merida - $100", "coloradas - $200", "tizimin - $300", "caucel - $400"});
        originAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOrigin.setAdapter(originAdapter);

        ArrayAdapter<String> destinationAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, new String[]{
                "merida - $100", "coloradas - $200", "tizimin - $300", "caucel - $400", "mexico - $1500"});
        destinationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDestination.setAdapter(destinationAdapter);


        spinnerOrigin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateTotal();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                updateTotal();
            }
        });

        spinnerDestination.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateTotal();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                updateTotal();
            }
        });


        buttonDate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(UpdateActivity.this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        buttonDate.setText(selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear);
                    }, year, month, day);
            datePickerDialog.show();
        });

        buttonTime.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(UpdateActivity.this,
                    (view, selectedHour, selectedMinute) -> {
                        buttonTime.setText(selectedHour + ":" + selectedMinute);
                    }, hour, minute, true);
            timePickerDialog.show();
        });


        buttonUpdate.setOnClickListener(v -> {
            String id = editTextId.getText().toString();
            String origin = spinnerOrigin.getSelectedItem().toString();
            String destination = spinnerDestination.getSelectedItem().toString();
            String date = buttonDate.getText().toString();
            String time = buttonTime.getText().toString();
            String total = textViewTotal.getText().toString();


            updateSale(id, origin, destination, date, time, total);
        });


        buttonCancel.setOnClickListener(v -> {
            finish();
        });

        buttonReturnToMenu.setOnClickListener(v -> {
            finish();
        });
    }

    private void updateTotal() {
        String total = calculateTotal();
        textViewTotal.setText("Total: " + total);
    }

    private String calculateTotal() {
        String origin = spinnerOrigin.getSelectedItem().toString();
        String destination = spinnerDestination.getSelectedItem().toString();


        int originPrice = extractPriceFromString(origin);
        int destinationPrice = extractPriceFromString(destination);


        int totalPrice = originPrice + destinationPrice;


        return "$" + totalPrice;
    }

    private int extractPriceFromString(String text) {

        String[] parts = text.split("\\$");
        if (parts.length > 1) {
            return Integer.parseInt(parts[1].trim());
        } else {

            return 0;
        }
    }

    private void updateSale(String id, String origin, String destination, String date, String time, String total) {
        DatabaseHelper db = new DatabaseHelper(this);
        db.updateSale(id, origin, destination, date, time, total);

    }
}
