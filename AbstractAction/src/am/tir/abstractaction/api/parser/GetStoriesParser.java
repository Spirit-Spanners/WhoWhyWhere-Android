package am.tir.abstractaction.api.parser;

import android.os.Handler;

public class GetStoriesParser extends ResponseParser {

	public GetStoriesParser(int requestId, Handler handler) {
		super(requestId, handler);
	}

	@Override
	public void processData(byte[] data) {
		
	}
}
