package am.tir.abstractaction.activity;

import am.tir.abstractaction.R;
import am.tir.abstractaction.utils.Helper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;

public class Main extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button enterButton = (Button) findViewById(R.id.button_start);
		Button randomStoryButton = (Button) findViewById(R.id.button_random_story);

		enterButton.setTypeface(Helper.getHoboTypeface(this));
		randomStoryButton.setTypeface(Helper.getHoboTypeface(this));
	}

	public void onEnterClick(View view) {
		Intent intent = new Intent(this, Game.class);
		startActivity(intent);
	}

	public void onRandomStoryClick(View view) {
		Intent intent = new Intent(this, RandomStories.class);
		startActivity(intent);
	}
}
