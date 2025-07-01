package com.example.varsha;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    public static final String NAME = "NAME";
    public static final String AGE = "AGE";
    private TextView nameText, ageText;
    private String name;
    private int age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        nameText = findViewById(R.id.mName);
        ageText = findViewById(R.id.mAge);

        Intent intent = getIntent();
        name = intent.getStringExtra(NAME);
        age = intent.getIntExtra(AGE, 0);

        nameText.setText("Hi! " + name);
        ageText.setText("Your Age is " + age);

        // Start MainActivity3 after a delay (for demonstration purposes)
        startActivityWithDelay();
    }

    private void startActivityWithDelay() {
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // Start MainActivity3
                        Intent intentMainActivity2= new Intent(SecondActivity.this, MainActivity2.class);
                        startActivity(intentMainActivity2);
                    }
                },
                4000 // Delay for 2 seconds (2000 milliseconds)
        );
    }
}
