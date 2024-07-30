package com.example.ado;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.buttonSell).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SellActivity.class));
        });

        findViewById(R.id.buttonConsult).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ConsultActivity.class));
        });

        findViewById(R.id.buttonUpdate).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, UpdateActivity.class));
        });

        findViewById(R.id.buttonDelete).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, DeleteActivity.class));
        });
    }
}
