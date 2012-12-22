package am.tir.abstractaction.api.parser;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.Handler;

public class StartGameParser extends ResponseParser {

	public StartGameParser(int requestId, Handler handler) {
		super(requestId, handler);
	}

	@Override
	public void processData(byte[] data) {
		String res = new String(data);
		try {
			JSONObject resultObject = new JSONObject(res);
			int id = resultObject.getInt("id");
			
			Bundle bundle = new Bundle();
			bundle.putInt(RESULT, id);
			
			sendResult(bundle);
		} catch (JSONException e) {
			sendErrorMessage(MSG_PARSE_ERROR);
		}
	}
}
