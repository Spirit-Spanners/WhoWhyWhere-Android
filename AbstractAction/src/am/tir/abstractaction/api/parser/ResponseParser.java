package am.tir.abstractaction.api.parser;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public abstract class ResponseParser {

    public static final int MSG_OK = 0;
    public static final int MSG_NO_CONNECTION = 1;
    public static final int MSG_TIMEOUT = 2;
    public static final int MSG_IO_ERROR = 3;
    public static final int MSG_PARSE_ERROR = 4;

    public static final String RESULT = "result";

    protected Handler handler;
    private int requestId;

    public ResponseParser(int requestId, Handler handler) {
        this.handler = handler;
        this.requestId = requestId;
    }

    public void sendErrorMessage(int conde) {
        Message msg = Message.obtain(handler, conde);
        handler.sendMessage(msg);
    }
    
    public void sendResult(Bundle data) {
    	Message message = Message.obtain(handler, MSG_OK);
    	message.setData(data);
    	message.arg1 = requestId;
    	handler.sendMessage(message);
    }

    public abstract void processData(byte[] data);
}
