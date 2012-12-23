package am.tir.abstractaction.api.parser;

import org.json.JSONArray;
import org.json.JSONException;

import android.os.Bundle;
import android.os.Handler;

public class GetAnswersParser extends ResponseParser {

	public GetAnswersParser(int requestId, Handler handler) {
		super(requestId, handler);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void processData(byte[] data) {
		String res = new String(data);
		try {
			JSONArray resultArray = new JSONArray(res);
			int length = resultArray.length();			
			String[] result = new String[length];
			
			for (int i = 0; i < length; i++) {
				result[i] = (resultArray.getString(i).trim());
			}
			
			Bundle bundle = new Bundle();
			bundle.putStringArray(RESULT, result);
			sendResult(bundle);
		} catch (JSONException e) {
			sendErrorMessage(MSG_PARSE_ERROR);
		}
	}
}
