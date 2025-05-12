package com.example.myapp;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Make sure you have activity_main.xml

        Button enterButton = findViewById(R.id.enter_button); // Make sure you set an ID for your button in activity_main.xml

        enterButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PersonalActivity.class);
            startActivity(intent);
        });
    }
}