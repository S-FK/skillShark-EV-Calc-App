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

public class CalculationsActivity extends AppCompatActivity {

	Button calculateButton, resetButton;
	EditText massEditText, gearRatioEditText,
			transmissionEffEditText, rollingResistanceCoeffEditText,
			dragCoeffEditText, frontalAreaEditText,
			wheelRadiusEditText, velocityEditText,
			gradientEditText;
	Spinner weightSpinner, vehicleFrontalAreaSpinner, wheelRadiusSpinner, velocitySpinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calculations);

		calculateButton = findViewById(R.id.calculateButton);
		resetButton = findViewById(R.id.resetButton);

		//spinners
		weightSpinner = findViewById(R.id.weightSpinner);
		vehicleFrontalAreaSpinner = findViewById(R.id.vehicleFrontalAreaSpinner);
		wheelRadiusSpinner = findViewById(R.id.wheelRadiusSpinner);
		velocitySpinner = findViewById(R.id.velocitySpinner);

		//edittexts
		massEditText = findViewById(R.id.weightEditText);
		gearRatioEditText = findViewById(R.id.gearRatioEditText);
		transmissionEffEditText = findViewById(R.id.transmissionEffEditText);
		rollingResistanceCoeffEditText = findViewById(R.id.coeffRollResEditText);
		dragCoeffEditText = findViewById(R.id.dragCoeffEditText);
		frontalAreaEditText = findViewById(R.id.vehicleFrontalAreaEditText);
		wheelRadiusEditText = findViewById(R.id.wheelRadiusEditText);
		velocityEditText = findViewById(R.id.velocityEditText);
		gradientEditText = findViewById(R.id.gradientEditText);

		resetButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				massEditText.setText("");
				gearRatioEditText.setText("");
				transmissionEffEditText.setText("");
				rollingResistanceCoeffEditText.setText("");
				dragCoeffEditText.setText("");
				frontalAreaEditText.setText("");
				wheelRadiusEditText.setText("");
				velocityEditText.setText("");
				gradientEditText.setText("");
			}
		});

		calculateButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if(checkInput()) {
					Toast.makeText(CalculationsActivity.this, "yay", Toast.LENGTH_SHORT).show();
					// save values in user prefs
					//shared prefs
					SharedPreferences values = getSharedPreferences("VALUES", MODE_PRIVATE);
					SharedPreferences.Editor valuesEdit = values.edit();
					valuesEdit.putString("weight", massEditText.getText().toString());
					valuesEdit.putString("gearRatio", gearRatioEditText.getText().toString());
					valuesEdit.putString("transmissionEff", transmissionEffEditText.getText().toString());
					valuesEdit.putString("rollingResistanceCoeff", rollingResistanceCoeffEditText.getText().toString());
					valuesEdit.putString("dragCoeff", dragCoeffEditText.getText().toString());
					valuesEdit.putString("frontalArea", frontalAreaEditText.getText().toString());
					valuesEdit.putString("wheelRadius", wheelRadiusEditText.getText().toString());
					valuesEdit.putString("velocity", velocityEditText.getText().toString());
					valuesEdit.putString("gradient", gradientEditText.getText().toString());
					// save to shared prefs
					valuesEdit.apply();

					Intent resultIntent = new Intent(CalculationsActivity.this, ResultActivity.class);
					startActivity(resultIntent);
					finish();

				} else {
					Toast.makeText(CalculationsActivity.this, "Please fill all the fields", Toast.LENGTH_LONG).show();
				}
			}
		});

		weightSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
				double weightMultiplier;
				switch(adapterView.getItemAtPosition(position).toString()) {
					case "Kilogram[Kg]":
						weightMultiplier = 1;
						break;
					case "Milligram[mg]":
						weightMultiplier = 0.000001;
						break;
					case "Gram[g]":
						weightMultiplier = 0.001;
						break;
					case "Tonne[t]":
						weightMultiplier = 1000;
						break;
					case "Ounce[oz]":
						weightMultiplier = 0.0283495;
						break;
					case "Stone[s]":
						weightMultiplier = 6.35029;
						break;
					case "UK Hundred Weight[cwt]":
						weightMultiplier = 50.8023;
						break;
					case "UK Long Ton[ton]":
						weightMultiplier = 1016.05;
						break;
					default:
						weightMultiplier = 1;
						Toast.makeText(CalculationsActivity.this, "Error! Please select Weight's unit again", Toast.LENGTH_LONG).show();
						break;
				}
				//shared prefs
				SharedPreferences values = getSharedPreferences("VALUES", MODE_PRIVATE);
				SharedPreferences.Editor valuesEdit = values.edit();
				//saving double as string
				valuesEdit.putString("weightMultiplier", String.valueOf(weightMultiplier));
				// save to shared prefs
				valuesEdit.apply();
			}

			@Override
			public void onNothingSelected(AdapterView<?> adapterView) {

			}
		});

		vehicleFrontalAreaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
				double frontalAreaMultiplier;
				// comparing spinner values from vehicle_frontal_area_array in strings.xml
				switch(adapterView.getItemAtPosition(position).toString()) {
					case "Sq. Millimeter(s) [mm2]":
						frontalAreaMultiplier = 0.000001;
						break;
					case "Sq. Centimeter(s) [cm2]":
						frontalAreaMultiplier = 0.0001;
						break;
					case "Sq. Metre(s) [m2]":
						frontalAreaMultiplier = 1;
						break;
					case "Hectare(s) [ha]":
						frontalAreaMultiplier = 10000;
						break;
					case "Sq. Kilometre(s) [km2]":
						frontalAreaMultiplier = 1000000;
						break;
					case "Sq. Inch(s) [in2]":
						frontalAreaMultiplier = 0.00064516;
						break;
					default:
						frontalAreaMultiplier = 1;
						Toast.makeText(CalculationsActivity.this, "Error! Please select Vehicle Frontal Area's unit again.", Toast.LENGTH_LONG).show();
				}
				//shared prefs
				SharedPreferences values = getSharedPreferences("VALUES", MODE_PRIVATE);
				SharedPreferences.Editor valuesEdit = values.edit();
				//saving double as string
				valuesEdit.putString("frontalAreaMultiplier", String.valueOf(frontalAreaMultiplier));
				// save to shared prefs
				valuesEdit.apply();
			}

			@Override
			public void onNothingSelected(AdapterView<?> adapterView) {

			}
		});

		wheelRadiusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
				double wheelRadiusMultiplier;
				switch(adapterView.getItemAtPosition(position).toString()) {
					case "Millimeter(s) [mm]":
						wheelRadiusMultiplier = 0.001;
						break;
					case "Centimeter(s) [cm]":
						wheelRadiusMultiplier = 0.01;
						break;
					case "Metre(s) [m]":
						wheelRadiusMultiplier = 1;
						break;
					case "Kilometre(s) [km]":
						wheelRadiusMultiplier = 1000;
						break;
					case "Inch(s) [in]":
						wheelRadiusMultiplier = 0.0254;
						break;
					case "Feet(s) [ft]":
						wheelRadiusMultiplier = 0.3048;
						break;
					default:
						wheelRadiusMultiplier = 1;
						Toast.makeText(CalculationsActivity.this, "Error! Please select WheelRadius's unit again.", Toast.LENGTH_LONG).show();
				}
				//shared prefs
				SharedPreferences values = getSharedPreferences("VALUES", MODE_PRIVATE);
				SharedPreferences.Editor valuesEdit = values.edit();
				//saving double as string
				valuesEdit.putString("wheelRadiusMultiplier", String.valueOf(wheelRadiusMultiplier));
				// save to shared prefs
				valuesEdit.apply();
			}

			@Override
			public void onNothingSelected(AdapterView<?> adapterView) {

			}
		});

		velocitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
				double velocityMultiplier;
				switch(adapterView.getItemAtPosition(position).toString()) {
					case "m/s":
						velocityMultiplier = 3.6;
						break;
					case "km/h":
						velocityMultiplier = 1;
						break;
					case "ft/min":
						velocityMultiplier = 0.018288;
						break;
					case "ft/s":
						velocityMultiplier = 1.09728;
						break;
					default:
						velocityMultiplier = 1;
						Toast.makeText(CalculationsActivity.this, "Error! Please select Velocity's unit again.", Toast.LENGTH_LONG).show();
						break;
				}
				//shared prefs
				SharedPreferences values = getSharedPreferences("VALUES", MODE_PRIVATE);
				SharedPreferences.Editor valuesEdit = values.edit();
				//saving double as string
				valuesEdit.putString("velocityMultiplier", String.valueOf(velocityMultiplier));
				// save to shared prefs
				valuesEdit.apply();
			}

			@Override
			public void onNothingSelected(AdapterView<?> adapterView) {

			}
		});

	}

	private boolean checkInput() {
		return !(massEditText.getText().toString().isEmpty() &&
				gearRatioEditText.getText().toString().isEmpty() &&
				transmissionEffEditText.getText().toString().isEmpty() &&
				rollingResistanceCoeffEditText.getText().toString().isEmpty() &&
				dragCoeffEditText.getText().toString().isEmpty() &&
				frontalAreaEditText.getText().toString().isEmpty() &&
				wheelRadiusEditText.getText().toString().isEmpty() &&
				velocityEditText.getText().toString().isEmpty() &&
				gradientEditText.getText().toString().isEmpty());
	}

}
