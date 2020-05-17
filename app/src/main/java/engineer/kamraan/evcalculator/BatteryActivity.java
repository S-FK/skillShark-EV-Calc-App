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

    Button calculateButton, resetButton, iButton;
    EditText powerEditText, voltageEditText,
            speedEditText, selectEditText;
    Spinner selectSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);

        calculateButton = findViewById(R.id.calculateButton);
        resetButton = findViewById(R.id.resetButton);
        iButton = findViewById(R.id.i);

        iButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent annexureIntent = new Intent(BatteryActivity.this, AnnexureActivity.class);
                startActivity(annexureIntent);
            }

        });

        //spinners
        selectSpinner = findViewById(R.id.selectSpinner);


        //edittexts
        powerEditText = findViewById(R.id.powerEditText);
        voltageEditText = findViewById(R.id.voltageEditText);
        speedEditText = findViewById(R.id.speedEditText);
        selectEditText = findViewById(R.id.selectEditText);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                powerEditText.setText("");
                voltageEditText.setText("");
                speedEditText.setText("");
                selectEditText.setText("");
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
                    valuesEdit.putString("select", selectEditText.getText().toString());
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

        selectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                double selectMultiplier;
                switch(adapterView.getItemAtPosition(position).toString()) {
                    case "Range[Km]":
                        selectMultiplier = 1;
                        break;
                    case "Capacity[Ah]":
                        selectMultiplier = 2;
                        break;
                    default:
                        selectMultiplier = 1;
                        Toast.makeText(BatteryActivity.this, "Error! Please select Weight's unit again", Toast.LENGTH_LONG).show();
                        break;
                }
                //shared prefs
                SharedPreferences values = getSharedPreferences("VALUES", MODE_PRIVATE);
                SharedPreferences.Editor valuesEdit = values.edit();
                //saving double as string
                valuesEdit.putString("selectMultiplier", String.valueOf(selectMultiplier));
                // save to shared prefs
                valuesEdit.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    private boolean checkInput() {
        return !(powerEditText.getText().toString().isEmpty() &&
                voltageEditText.getText().toString().isEmpty() &&
                speedEditText.getText().toString().isEmpty() &&
                selectEditText.getText().toString().isEmpty());
    }

}
