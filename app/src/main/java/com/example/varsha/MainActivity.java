    package com.example.varsha;

    import android.annotation.SuppressLint;
    import android.app.NotificationChannel;
    import android.app.NotificationManager;
    import android.content.Context;
    import android.content.Intent;
    import android.content.pm.PackageManager;
    import android.os.Build;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.Toast;

    import androidx.appcompat.app.AppCompatActivity;
    import androidx.core.app.ActivityCompat;
    import androidx.core.app.NotificationCompat;
    import androidx.core.app.NotificationManagerCompat;

    public class MainActivity extends AppCompatActivity {
        private EditText nameText, ageText;
        private Button button;
        private String name;
        private int age;

        // Notification Channel ID
        private static final String CHANNEL_ID = "my_channel_id";
        private static final int NOTIFICATION_ID = 101;

        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            nameText = findViewById(R.id.mName);
            ageText = findViewById(R.id.mAge);
            button = findViewById(R.id.button2);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendData();
                }
            });

            // Create notification channel
            createNotificationChannel();
        }

        public void sendData() {
            name = nameText.getText().toString().trim();
            age = Integer.parseInt(ageText.getText().toString().trim());

            Intent i = new Intent(MainActivity.this, SecondActivity.class);
            i.putExtra("NAME", name);
            i.putExtra("AGE", age);
            startActivity(i);

            // Displaying toast message
            Toast.makeText(this, "Data sent successfully", Toast.LENGTH_SHORT).show();

            // Display notification
            displayNotification();
        }

        // Create notification channel
        private void createNotificationChannel() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                CharSequence name = getString(R.string.channel_name);
                String description = getString(R.string.channel_description);
                int importance = NotificationManager.IMPORTANCE_DEFAULT;
                NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
                channel.setDescription(description);
                // Register the channel with the system
                NotificationManager notificationManager = getSystemService(NotificationManager.class);
                notificationManager.createNotificationChannel(channel);
            }
        }

        // Display notification
        private void displayNotification() {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle("Data Sent")
                    .setContentText("Your data has been sent successfully.")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

            // notificationId is a unique int for each notification that you must define
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            notificationManager.notify(NOTIFICATION_ID, builder.build());
        }
    }
