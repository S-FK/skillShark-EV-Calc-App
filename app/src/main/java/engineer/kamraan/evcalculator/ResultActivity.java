package engineer.kamraan.evcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

	public final static float GRAVITY = 9.8f;
	public final static double DENSITY = 1.225;

	Button resetAndBackButton;
	TextView rollingResistanceTV, gradientResistanceTV,
			aerodynamicDragTV, totalPowerTV,
			totalPowerEffTV, vehicleSpeedTV,
			shaftSpeedTV, tractionTorqueTV,
			loadTorqueTV, loadPowerTV;
	// global values
	double rollingResistance, gradientResistance, aeroDrag, totalPower, totalPowerEff, vehicleSpeed, shaftSpeed, tractionTorque, loadTorque, loadPower;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);

		SharedPreferences values = getSharedPreferences("VALUES", MODE_PRIVATE);
		double weight = Double.parseDouble(values.getString("weight", "0"));
		double gearRatio = Double.parseDouble(values.getString("gearRatio", "0"));
		double transmissionEff = Double.parseDouble(values.getString("transmissionEff", "0"));
		double rollingResistanceCoeff = Double.parseDouble(values.getString("rollingResistanceCoeff", "0"));
		double dragCoeff = Double.parseDouble(values.getString("dragCoeff", "0"));
		double frontalArea = Double.parseDouble(values.getString("frontalArea", "0"));
		double wheelRadius = Double.parseDouble(values.getString("wheelRadius", "0"));
		double velocity = Double.parseDouble(values.getString("velocity", "0"));
		double gradient = Double.parseDouble(values.getString("gradient", "0"));
		// unit conversion
		double weightMultiplier = Double.parseDouble(values.getString("weightMultiplier", "0"));
		double frontalAreaMultiplier = Double.parseDouble(values.getString("frontalAreaMultiplier", "0"));
		double wheelRadiusMultiplier = Double.parseDouble(values.getString("wheelRadiusMultiplier", "0"));
		double velocityMultiplier = Double.parseDouble(values.getString("velocityMultiplier", "0"));

		Toast.makeText(ResultActivity.this, String.valueOf(wheelRadiusMultiplier), Toast.LENGTH_SHORT).show();

		resetAndBackButton = findViewById(R.id.resetAndBackButton);

		//result text views
		rollingResistanceTV = findViewById(R.id.rolling_resistance_value);
		gradientResistanceTV = findViewById(R.id.gradient_resistance_value);
		aerodynamicDragTV = findViewById(R.id.aerodynamic_drag_value);
		totalPowerTV = findViewById(R.id.total_power_value);
		totalPowerEffTV = findViewById(R.id.total_power_eff_value);
		vehicleSpeedTV = findViewById(R.id.vehicle_speed_value);
		shaftSpeedTV = findViewById(R.id.shaft_speed_value);
		tractionTorqueTV = findViewById(R.id.traction_torque_value);
		loadTorqueTV = findViewById(R.id.load_torque_value);
		loadPowerTV = findViewById(R.id.load_power_value);

		//calculate results
		calculateRollingResistance(rollingResistanceTV, rollingResistanceCoeff, weight, weightMultiplier, velocity, velocityMultiplier);
		calculateGradientResistance(gradientResistanceTV,gradient, weight, weightMultiplier, velocity, velocityMultiplier);
		calculateAeroDrag(aerodynamicDragTV, dragCoeff, frontalArea, frontalAreaMultiplier, velocity, velocityMultiplier);
		calculateTotalPower(totalPowerTV);
		calculateTotalPowerEff(totalPowerEffTV, transmissionEff);
		calculateVehicleSpeed(vehicleSpeedTV, velocity, velocityMultiplier, wheelRadius, wheelRadiusMultiplier);
		calculateShaftSpeed(shaftSpeedTV, gearRatio, velocity, velocityMultiplier, wheelRadius, wheelRadiusMultiplier);
		calculateTractionTorque(tractionTorqueTV, wheelRadius, wheelRadiusMultiplier, velocity, velocityMultiplier);
		calculateLoadTorque(loadTorqueTV, transmissionEff, gearRatio);
		calculateLoadPower(loadPowerTV);

		resetAndBackButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent resetAndBackIntent = new Intent(ResultActivity.this, CalculationsActivity.class);
				startActivity(resetAndBackIntent);
				finish();
			}
		});
	}

	private void calculateRollingResistance(TextView rollingResistanceTV,double rollingResistanceCoeff,
											double weight, double weightMult,
											double velocity, double velocityMult) {
		rollingResistance = (rollingResistanceCoeff * weight * weightMult * velocity * velocityMult * GRAVITY)/3600;
		rollingResistanceTV.setText(String.valueOf(String.format("%.4f",rollingResistance)));
	}

	private void calculateGradientResistance(TextView gradientResistanceTV,double gradient,
											double weight, double weightMult,
											double velocity, double velocityMult) {
		gradientResistance = (Math.sin(gradient * (Math.PI / 180)) * weight * weightMult * velocity * velocityMult * GRAVITY)/3600;
		gradientResistanceTV.setText(String.valueOf(String.format("%.4f",gradientResistance)));
	}

	private void calculateAeroDrag(TextView aerodynamicDragTV, double dragCoeff, double frontalArea, double frontalAreaMult, double velocity, double velocityMult) {
		aeroDrag = (0.5*dragCoeff*frontalArea*frontalAreaMult*DENSITY*velocity*velocityMult*Math.pow((velocity*velocityMult/3.6),2))/3600;
		aerodynamicDragTV.setText(String.valueOf(String.format("%.4f",aeroDrag)));
	}

	private void calculateTotalPower(TextView totalPowerTV) {
		totalPower = rollingResistance + gradientResistance + aeroDrag;
		totalPowerTV.setText(String.valueOf(String.format("%.4f",totalPower)));
	}

	private void calculateTotalPowerEff(TextView totalPowerEffTV, double transmissionEff) {
		totalPowerEff = (rollingResistance + gradientResistance + aeroDrag)/(transmissionEff/100);
		totalPowerEffTV.setText(String.valueOf(String.format("%.4f",totalPowerEff)));
	}

	private void calculateVehicleSpeed(TextView vehicleSpeedTV, double velocity, double velocityMult, double wheelRadius, double wheelRadiusMult) {
		vehicleSpeed = (velocity * velocityMult * 9.5493 / ( 3.6 * wheelRadius * wheelRadiusMult));
		vehicleSpeedTV.setText(String.valueOf(String.format("%.4f",vehicleSpeed)));
	}

	private void calculateShaftSpeed(TextView shaftSpeedTV, double gearRatio, double velocity, double velocityMult, double wheelRadius, double wheelRadiusMult) {
		shaftSpeed = (gearRatio * velocity * velocityMult * 9.5493) / (3.6 * wheelRadius * wheelRadiusMult);
		shaftSpeedTV.setText(String.valueOf(String.format("%.4f", shaftSpeed)));
	}

	private void calculateTractionTorque(TextView tractionTorqueTV, double wheelRadius, double wheelRadiusMult, double velocity, double velocityMult) {
		tractionTorque = ((rollingResistance + gradientResistance + aeroDrag) * 1000 * wheelRadius * wheelRadiusMult * 3.6) / velocity * velocityMult;
		tractionTorqueTV.setText(String.valueOf(String.format("%.4f", tractionTorque)));
	}

	private void calculateLoadTorque(TextView loadTorqueTV, double transmissionEff, double gearRatio) {
		loadTorque = tractionTorque / ((transmissionEff / 100) * gearRatio);
		loadTorqueTV.setText(String.valueOf(String.format("%.4f", loadTorque)));
	}

	private void calculateLoadPower(TextView loadPowerTV) {
		loadPower = (((2 * Math.PI * shaftSpeed * loadTorque)/60)/1000);
		loadPowerTV.setText(String.valueOf(String.format("%.4f", loadPower)));
	}

}
