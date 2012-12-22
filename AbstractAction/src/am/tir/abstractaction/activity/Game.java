package am.tir.abstractaction.activity;

import am.tir.abstractaction.R;
import android.os.Bundle;
import android.app.Activity;

public class Game extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);
	}
}
