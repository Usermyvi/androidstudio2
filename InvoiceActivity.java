package com.example.myapp;


import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;

public class InvoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice); // Make sure you have activity_invoice.xml

        // Get references to TextViews in your layout (set appropriate IDs)
        TextView nameTextView = findViewById(R.id.invoice_name_text_view);
        TextView stateTextView = findViewById(R.id.invoice_state_text_view);
        TextView ageTextView = findViewById(R.id.invoice_age_text_view);
        TextView planTextView = findViewById(R.id.invoice_plan_text_view);
        TextView durationTextView = findViewById(R.id.invoice_duration_text_view);
        TextView pricePerMonthTextView = findViewById(R.id.invoice_price_per_month_text_view); // Corrected ID
        TextView discountTextView = findViewById(R.id.invoice_discount_text_view);
        TextView serviceTaxTextView = findViewById(R.id.invoice_service_tax_text_view);
        TextView totalTextView = findViewById(R.id.invoice_total_text_view);

        // Retrieve data from the Intent
        String name = getIntent().getStringExtra("name");
        String state = getIntent().getStringExtra("state");
        String age = getIntent().getStringExtra("age");
        String plan = getIntent().getStringExtra("plan");
        int duration = getIntent().getIntExtra("duration", 0);
        double pricePerMonth = getIntent().getDoubleExtra("pricePerMonth", 0.0); // Corrected key
        double discountAmount = getIntent().getDoubleExtra("discountAmount", 0.0);
        double serviceTax = getIntent().getDoubleExtra("serviceTax", 0.0);
        double finalTotal = getIntent().getDoubleExtra("finalTotal", 0.0);

        // Format currency values
        DecimalFormat currencyFormat = new DecimalFormat("RM #,##0.00");

        // Display the data in the TextViews
        nameTextView.setText("Name: " + name);
        stateTextView.setText("State: " + state);
        ageTextView.setText("Age: " + age);
        planTextView.setText("Plan: " + plan);
        durationTextView.setText("Duration: " + duration + " Months");
        pricePerMonthTextView.setText("Price per Month: " + currencyFormat.format(pricePerMonth)); // Display price per month
        discountTextView.setText("Discount: " + currencyFormat.format(discountAmount));
        serviceTaxTextView.setText("Service Tax (6%): " + currencyFormat.format(serviceTax));
        totalTextView.setText("Total: " + currencyFormat.format(finalTotal));
    }
}