package com.example.musketeers.senseanywheremusketeers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class deliver2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliver2);

        Button button = findViewById(R.id.buttonStartDelivery);
        Button buttonback = findViewById(R.id.buttonBack);
        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(deliver2.this,deliver3.class);
                startActivity(intent);
            }
        });

    }
}
