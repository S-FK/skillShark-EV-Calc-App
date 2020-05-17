package engineer.kamraan.evcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CalculateMenuActivity extends AppCompatActivity {

    Button calculationsButton;
    Button aboutUsButton;
    Button annexureButton;
    Button feedbackButton;
    Button motorButton;
    Button batteryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_menu);

        calculationsButton = findViewById(R.id.calculations_button);
        aboutUsButton = findViewById(R.id.about_us_button);
        annexureButton = findViewById(R.id.annexure_button);
        feedbackButton = findViewById(R.id.feedback_button);
        motorButton = findViewById(R.id.motor_button);
        batteryButton = findViewById(R.id.battery_button);

        calculationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent calculateIntent = new Intent(CalculateMenuActivity.this, CalculateMenuActivity.class);
                startActivity(calculateIntent);
            }
        });

        aboutUsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent aboutIntent = new Intent(CalculateMenuActivity.this, AboutActivity.class);
                startActivity(aboutIntent);
            }

        });

        annexureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent annexureIntent = new Intent(CalculateMenuActivity.this, AnnexureActivity.class);
                startActivity(annexureIntent);
            }

        });

        feedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent feedbackIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://forms.gle/h7q2n8ty9qnEEfin6"));
                startActivity(feedbackIntent);
            }

        });

        motorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent motorIntent = new Intent(CalculateMenuActivity.this, CalculationsActivity.class);
                startActivity(motorIntent);
            }

        });

        batteryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent batteryIntent = new Intent(CalculateMenuActivity.this, BatteryActivity.class);
                startActivity(batteryIntent);
            }

        });
    }
}
