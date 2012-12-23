/**
 * 
 */
package am.tir.abstractaction.activity;

import am.tir.abstractaction.R;
import am.tir.abstractaction.utils.Helper;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * @author Artak Gevorgyan
 * 
 */
public class RandomStories extends Activity {

	private int storiesCount;
	private int currentIndex;
	private TextView storyText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result_stories);

		storyText = (TextView) findViewById(R.id.story_text);
		storyText
				.setText(getResources().getStringArray(R.array.random_stories)[currentIndex]);
		storyText.setTypeface(Helper.getSeriouslyTypeface(this));
		storiesCount = getResources().getStringArray(R.array.random_stories).length;
	}

	public void onNextPressed(View view) {
		currentIndex = currentIndex == storiesCount - 1 ? 0 : ++currentIndex;
		storyText
				.setText(getResources().getStringArray(R.array.random_stories)[currentIndex]);
		playPaperSound();
	}

	public void onPreviousPressed(View view) {
		currentIndex = currentIndex == 0 ? storiesCount - 1 : --currentIndex;
		storyText
				.setText(getResources().getStringArray(R.array.random_stories)[currentIndex]);
		playPaperSound();
	}

	private void playPaperSound() {
		MediaPlayer mp = MediaPlayer.create(this, R.raw.paper);
		mp.start();
	}
}
