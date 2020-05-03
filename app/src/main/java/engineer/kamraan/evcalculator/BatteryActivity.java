package engineer.kamraan.evcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class BatteryActivity extends AppCompatActivity {

    Button calculateButton, resetButton;
    EditText powerEditText, voltageEditText,
            speedEditText, rangeEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculations);

        calculateButton = findViewById(R.id.calculateButton);
        resetButton = findViewById(R.id.resetButton);


        //edittexts
        powerEditText = findViewById(R.id.powerEditText);
        voltageEditText = findViewById(R.id.voltageEditText);
        speedEditText = findViewById(R.id.speedEditText);
        rangeEditText = findViewById(R.id.rangeEditText);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                powerEditText.setText("");
                voltageEditText.setText("");
                speedEditText.setText("");
                rangeEditText.setText("");
            }
        });

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkInput()) {
                    Toast.makeText(BatteryActivity.this, "yay", Toast.LENGTH_SHORT).show();
                    // save values in user prefs
                    //shared prefs
                    SharedPreferences values = getSharedPreferences("VALUES", MODE_PRIVATE);
                    SharedPreferences.Editor valuesEdit = values.edit();
                    valuesEdit.putString("power", powerEditText.getText().toString());
                    valuesEdit.putString("voltage", voltageEditText.getText().toString());
                    valuesEdit.putString("speed", speedEditText.getText().toString());
                    valuesEdit.putString("range", rangeEditText.getText().toString());
                    // save to shared prefs
                    valuesEdit.apply();

                    Intent resultIntent = new Intent(BatteryActivity.this, ResBatteryActivity.class);
                    startActivity(resultIntent);
                    finish();

                } else {
                    Toast.makeText(BatteryActivity.this, "Please fill all the fields", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private boolean checkInput() {
        return !(powerEditText.getText().toString().isEmpty() &&
                voltageEditText.getText().toString().isEmpty() &&
                speedEditText.getText().toString().isEmpty() &&
                rangeEditText.getText().toString().isEmpty());
    }

}
