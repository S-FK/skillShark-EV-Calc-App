package engineer.kamraan.evcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

	Button calculationsButton;
	Button aboutUsButton;
	Button annexureButton;
	Button feedbackButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		calculationsButton = findViewById(R.id.calculations_button);
		aboutUsButton = findViewById(R.id.about_us_button);
		annexureButton = findViewById(R.id.annexure_button);
		feedbackButton = findViewById(R.id.feedback_button);

		calculationsButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent calculateIntent = new Intent(HomeActivity.this, CalculateMenuActivity.class);
				startActivity(calculateIntent);
			}
		});

		aboutUsButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent aboutIntent = new Intent(HomeActivity.this, AboutActivity.class);
				startActivity(aboutIntent);
			}

		});

		annexureButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent annexureIntent = new Intent(HomeActivity.this, AnnexureActivity.class);
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
	}
}
