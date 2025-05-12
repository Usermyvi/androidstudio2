package com.example.myapp;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;

public class SubscriptionActivity extends AppCompatActivity {

    private String name;
    private String state;
    private String age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription); // Make sure you have activity_subscription.xml

        // Retrieve data from previous activity
        name = getIntent().getStringExtra("name");
        state = getIntent().getStringExtra("state");
        age = getIntent().getStringExtra("age");

        RadioGroup packageRadioGroup = findViewById(R.id.package_radio_group); // Set IDs in your activity_subscription.xml
        EditText durationEditText = findViewById(R.id.duration_edit_text);
        EditText discountCodeEditText = findViewById(R.id.discount_code_edit_text);
        Button payNowButton = findViewById(R.id.pay_now_button);

        payNowButton.setOnClickListener(v -> {
            int selectedPackageId = packageRadioGroup.getCheckedRadioButtonId();
            RadioButton selectedPackageRadioButton = findViewById(selectedPackageId);
            String selectedPackage = selectedPackageRadioButton != null ? selectedPackageRadioButton.getText().toString() : "";

            String durationString = durationEditText.getText().toString();
            int duration = 0;
            if (!durationString.isEmpty()) {
                try {
                    duration = Integer.parseInt(durationString);
                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Please enter a valid duration", Toast.LENGTH_SHORT).show();
                    return; // Stop processing if duration is invalid
                }
            } else {
                Toast.makeText(this, "Please enter subscription duration", Toast.LENGTH_SHORT).show();
                return; // Stop processing if duration is empty
            }


            String discountCode = discountCodeEditText.getText().toString();

            double basePrice = 0.0;
            String plan = "";

            if (selectedPackage.contains("Basic Plan")) {
                basePrice = 17.0;
                plan = "Basic Plan";
            } else if (selectedPackage.contains("Standard Plan")) {
                basePrice = 28.0;
                plan = "Standard Plan";
            } else if (selectedPackage.contains("Premium Plan")) {
                basePrice = 45.0;
                plan = "Premium Plan";
            } else {
                Toast.makeText(this, "Please select a package", Toast.LENGTH_SHORT).show();
                return; // Stop processing if no package is selected
            }

            double totalBeforeDiscount = basePrice * duration;
            double discountAmount = 0.0;

            if (discountCode.equalsIgnoreCase("JOMBELAJAR")) {
                discountAmount = totalBeforeDiscount * 0.10; // 10% discount
            } else if (!discountCode.isEmpty()) {
                Toast.makeText(this, "Invalid discount code", Toast.LENGTH_SHORT).show();
            }

            double totalAfterDiscount = totalBeforeDiscount - discountAmount;

            double serviceTaxRate = 0.06; // 6% service tax
            double serviceTax = totalAfterDiscount * serviceTaxRate;
            double finalTotal = totalAfterDiscount + serviceTax;

            Intent intent = new Intent(SubscriptionActivity.this, InvoiceActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("state", state);
            intent.putExtra("age", age);
            intent.putExtra("plan", plan);
            intent.putExtra("duration", duration);
            intent.putExtra("pricePerMonth", basePrice); // Pass the base price per month
            intent.putExtra("discountAmount", discountAmount);
            intent.putExtra("serviceTax", serviceTax);
            intent.putExtra("finalTotal", finalTotal);

            Toast.makeText(this, "Please make a payment within 15 minutes", Toast.LENGTH_LONG).show();
            startActivity(intent);
        });
    }
}