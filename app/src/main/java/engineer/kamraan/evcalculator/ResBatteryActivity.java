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
    TextView capacity_value, range_value;
    // global values
    double capacity, range;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_battery);

        SharedPreferences values = getSharedPreferences("VALUES", MODE_PRIVATE);
        double power = Double.parseDouble(values.getString("power", "0"));
        double voltage = Double.parseDouble(values.getString("voltage", "0"));
        double speed = Double.parseDouble(values.getString("speed", "0"));
        double select = Double.parseDouble(values.getString("select", "0"));
        double selectMultiplier = Double.parseDouble(values.getString("selectMultiplier", "0"));

        resetAndBackButton = findViewById(R.id.resetAndBackButton);

        //result text views
        capacity_value = findViewById(R.id.capacity_value);
        range_value = findViewById(R.id.range_value);

        //calculate results
        calculateRange(capacity_value, range_value, selectMultiplier, power, voltage, speed, select);

        resetAndBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resetAndBackIntent = new Intent(ResBatteryActivity.this, BatteryActivity.class);
                startActivity(resetAndBackIntent);
                finish();
            }
        });
    }

    private void calculateRange(TextView capacity_value,TextView range_value, double selectMultiplier, double power,
                                double voltage, double speed, double select) {

        if(selectMultiplier==1) {
            capacity = ((power / speed) * select) / voltage;
            capacity_value.setText(String.valueOf(capacity));
            range_value.setText(String.valueOf(select));
        }
        else if(selectMultiplier==2){
            range = ((power / speed) * select * voltage) ;
            capacity_value.setText(String.valueOf(select));
            range_value.setText(String.valueOf(range));
        }
    }

}
