package com.example.varsha;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    private ProgressDialog progressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();
    private Button startDownloadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        startDownloadButton = findViewById(R.id.startDownloadButton);
        startDownloadButton.setOnClickListener(v -> {
            progressStatus = 0; // Reset progress
            showProgressDialog();
            startFileDownload();
        });
    }

    private void showProgressDialog() {
        progressBar = new ProgressDialog(this);
        progressBar.setCancelable(false); // Avoid cancellation during download
        progressBar.setMessage("Downloading file...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressBar.setProgress(0);
        progressBar.setMax(100);
        progressBar.show();
    }

    private void startFileDownload() {
        new Thread(() -> {
            while (progressStatus < 100) {
                progressStatus++;

                try {
                    Thread.sleep(50); // Simulate work
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                handler.post(() -> progressBar.setProgress(progressStatus));
            }

            // After completion
            handler.post(() -> {
                progressBar.dismiss();
                Toast.makeText(MainActivity2.this, "Download Complete!", Toast.LENGTH_SHORT).show();

                // Move to next activity (MainActivity3)
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(intent);
                finish(); // Optional: finish this activity
            });
        }).start();
    }
}
