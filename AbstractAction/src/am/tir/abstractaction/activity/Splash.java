package am.tir.abstractaction.activity;

import am.tir.abstractaction.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;

/**
 * @author Artak.Gevorgyan
 * 
 */
public class Splash extends Activity {

	private long ms;
	private CountDownTimer cdt;
	private ImageView logo;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		logo = (ImageView) findViewById(R.id.ivLogo);

		if (savedInstanceState != null) {
			ms = savedInstanceState.getLong("ms");
			if (ms < 1510) {
				logo.setImageResource(R.integer.game_logo);
			}
		} else {
			ms = 3000;
		}

	}

	@Override
	protected void onResume() {
		super.onResume();

		cdt = new CountDownTimer(ms, 100) {

			public void onTick(long millisUntilFinished) {
				ms = millisUntilFinished;
				if (ms < 1510 && ms > 1390) {
					logo.setImageResource(R.integer.game_logo);
				}
			}

			public void onFinish() {
				startActivity(new Intent(Splash.this, Main.class));
				finish();
			}
		};

		cdt.start();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putLong("ms", ms);
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onPause() {
		cdt.cancel();
		super.onPause();
	}
}