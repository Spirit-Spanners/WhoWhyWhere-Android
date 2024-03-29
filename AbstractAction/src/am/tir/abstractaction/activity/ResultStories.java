/**
 * 
 */
package am.tir.abstractaction.activity;

import java.util.ArrayList;
import java.util.List;

import am.tir.abstractaction.R;
import am.tir.abstractaction.data.beans.Story;
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
public class ResultStories extends Activity {

	private ArrayList<Story> stories;
	private int storiesCount;
	private int currentIndex;
	private TextView storyText;

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result_stories);

		stories = (ArrayList<Story>) getIntent()
				.getSerializableExtra("stories");
		storiesCount = stories.size();
		storyText = (TextView) findViewById(R.id.story_text);
		storyText.setText(generateStoryText(stories.get(currentIndex)));
		storyText.setTypeface(Helper.getSeriouslyTypeface(this));
	}

	public void onNextPressed(View view) {
		currentIndex = currentIndex == storiesCount - 1 ? 0 : ++currentIndex;
		storyText.setText(generateStoryText(stories.get(currentIndex)));
		playPaperSound();
	}

	public void onPreviousPressed(View view) {
		currentIndex = currentIndex == 0 ? storiesCount - 1 : --currentIndex;
		storyText.setText(generateStoryText(stories.get(currentIndex)));
		playPaperSound();
	}

	private String generateStoryText(Story story) {
		List<String> answers = story.getAnswers();

		String result = getResources().getString(R.string.story_template,
				answers.get(0), answers.get(1), answers.get(2), answers.get(3),
				answers.get(4), answers.get(5));

		return result;
	}

	private void playPaperSound() {
		MediaPlayer mp = MediaPlayer.create(this, R.raw.paper);
		mp.start();
	}
}
