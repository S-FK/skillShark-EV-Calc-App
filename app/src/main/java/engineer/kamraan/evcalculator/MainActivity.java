package engineer.kamraan.evcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

	public final static int SPLASH_TIMEOUT = 5000; // milliseconds

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_main);

		// change activity after $SPLASH_TIMEOUT milliseconds
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent splashIntent = new Intent(MainActivity.this, HomeActivity.class);
				startActivity(splashIntent);
				// destroy the activity after switching to HomeActivity
				finish();
			}
		}, SPLASH_TIMEOUT);
	}
}
