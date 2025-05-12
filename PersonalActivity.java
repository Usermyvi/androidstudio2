package com.example.myapp;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class PersonalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal); // Make sure you have activity_personal.xml

        EditText nameEditText = findViewById(R.id.name_edit_text); // Set IDs in your activity_personal.xml
        EditText stateEditText = findViewById(R.id.state_edit_text);
        EditText ageEditText = findViewById(R.id.age_edit_text);
        Button nextButton = findViewById(R.id.next_button);

        nextButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString();
            String state = stateEditText.getText().toString();
            String age = ageEditText.getText().toString();

            Intent intent = new Intent(PersonalActivity.this, SubscriptionActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("state", state);
            intent.putExtra("age", age);
            startActivity(intent);
        });
    }
}