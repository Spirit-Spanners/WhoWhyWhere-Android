package am.tir.abstractaction.api.parser;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.Handler;

public class AnswerParser extends ResponseParser {

	public AnswerParser(int requestId, Handler handler) {
		super(requestId, handler);
	}

	@Override
	public void processData(byte[] data) {
		String res = new String(data);
		try {
			JSONObject resultObject = new JSONObject(res);
			String status = resultObject.getString("status");
			
			Bundle bundle = new Bundle();
			bundle.putString(RESULT, status);
			
			sendResult(bundle);
		} catch (JSONException e) {
			sendErrorMessage(MSG_PARSE_ERROR);
		}
	}
}
