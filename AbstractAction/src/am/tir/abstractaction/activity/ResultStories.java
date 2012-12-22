/**
 * 
 */
package am.tir.abstractaction.activity;

import java.util.List;

import am.tir.abstractaction.R;
import am.tir.abstractaction.data.beans.Story;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;

/**
 * @author Artak Gevorgyan
 * 
 */
public class ResultStories extends Activity {

	private List<Story> stories;
	private int currentIndex;
	TextView storyText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result_stories);

		storyText = (TextView) findViewById(R.id.story_text);
	}

	public void onNextPressed() {
		currentIndex = currentIndex == 5 ? 0 : ++currentIndex;
		storyText.setText(generateStoryText(stories.get(currentIndex)));
		playPaperSound();
	}

	public void onPreviousPressed() {
		currentIndex = currentIndex == 0 ? 5 : --currentIndex;
		storyText.setText(generateStoryText(stories.get(currentIndex)));
	}

	private String generateStoryText(Story story) {
		List<String> answers = story.getAnswers();

		String result = String.format(
				getResources().getString(R.string.story_template),
				answers.get(0), answers.get(1), answers.get(2), answers.get(3),
				answers.get(4), answers.get(5));

		return result;
	}

	private void playPaperSound() {
		MediaPlayer mp = MediaPlayer.create(this, R.raw.paper);
		mp.start();
	}
}
