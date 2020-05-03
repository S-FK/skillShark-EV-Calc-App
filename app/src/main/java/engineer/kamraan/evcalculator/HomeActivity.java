package engineer.kamraan.evcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

	Button calculationsButton;
	Button aboutUsButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		calculationsButton = findViewById(R.id.calculations_button);
		aboutUsButton = findViewById(R.id.about_us_button);

		calculationsButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent calculateIntent = new Intent(HomeActivity.this, CalculationsActivity.class);
				startActivity(calculateIntent);
			}
		});

		aboutUsButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent batteryIntent = new Intent(HomeActivity.this, BatteryActivity.class);
				startActivity(batteryIntent);
			}
		});
	}
}
