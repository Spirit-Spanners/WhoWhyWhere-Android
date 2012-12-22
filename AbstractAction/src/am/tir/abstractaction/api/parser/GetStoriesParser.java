package am.tir.abstractaction.api.parser;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import am.tir.abstractaction.data.beans.Story;
import android.os.Bundle;
import android.os.Handler;

public class GetStoriesParser extends ResponseParser {

	public GetStoriesParser(int requestId, Handler handler) {
		super(requestId, handler);
	}

	@Override
	public void processData(byte[] data) {
		String res = new String(data);
		try {
			JSONArray resultArray = new JSONArray(res);
			
			int length = resultArray.length();
			ArrayList<Story> stories = new ArrayList<Story>();
			
			for (int i = 0; i < length; i++) {
				JSONObject storyObject = resultArray.getJSONObject(i);
				ArrayList<String> answers = new ArrayList<String>();
				
				answers.add(storyObject.getString("1"));
				answers.add(storyObject.getString("2"));
				answers.add(storyObject.getString("3"));
				answers.add(storyObject.getString("4"));
				answers.add(storyObject.getString("5"));
				answers.add(storyObject.getString("6"));
				
				Story story = new Story();
				story.setAnswers(answers);
				stories.add(story);
			}
			
			Bundle bundle = new Bundle();
			bundle.putSerializable(RESULT, stories);
			sendResult(bundle);
		} catch (JSONException e) {
			sendErrorMessage(MSG_PARSE_ERROR);
		}
	}
}
