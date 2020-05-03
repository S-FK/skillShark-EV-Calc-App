package engineer.kamraan.evcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ResBatteryActivity extends AppCompatActivity {

    Button resetAndBackButton;
    TextView capacity_value;
    // global values
    double capacity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        SharedPreferences values = getSharedPreferences("VALUES", MODE_PRIVATE);
        double power = Double.parseDouble(values.getString("power", "0"));
        double voltage = Double.parseDouble(values.getString("voltage", "0"));
        double speed = Double.parseDouble(values.getString("speed", "0"));
        double range = Double.parseDouble(values.getString("range", "0"));

        resetAndBackButton = findViewById(R.id.resetAndBackButton);

        //result text views
        capacity_value = findViewById(R.id.capacity_value);

        //calculate results
        calculateRange(capacity_value, power, voltage, speed, range);

        resetAndBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resetAndBackIntent = new Intent(ResBatteryActivity.this, BatteryActivity.class);
                startActivity(resetAndBackIntent);
                finish();
            }
        });
    }

    private void calculateRange(TextView capacity_value,double power,
                                            double voltage, double speed, double range) {
        capacity = ( (power/speed)* range)/voltage;
        capacity_value.setText(String.valueOf(capacity));
    }

}
