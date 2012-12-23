package am.tir.abstractaction.api.parser;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.Handler;

public class GetRandomAnswerParser extends ResponseParser {

	public GetRandomAnswerParser(int requestId, Handler handler) {
		super(requestId, handler);
	}

	@Override
	public void processData(byte[] data) {
		String res = new String(data);
		try {
			JSONObject resultObject = new JSONObject(res);
			String answer = resultObject.getString("answer");
			
			Bundle bundle = new Bundle();
			bundle.putString(RESULT, answer.trim());
			
			sendResult(bundle);
		} catch (JSONException e) {
			sendErrorMessage(MSG_PARSE_ERROR);
		}
	}
}
